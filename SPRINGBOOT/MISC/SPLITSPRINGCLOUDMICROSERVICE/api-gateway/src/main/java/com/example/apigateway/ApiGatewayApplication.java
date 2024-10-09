package com.example.apigateway;

import io.split.client.SplitClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

// V1 Feign Client - used to access version 1 API on the weather resource server
@FeignClient(name="weather-service", path = "api/v1", contextId = "v1")
interface WeatherServerClientV1 {
	@RequestMapping(method = RequestMethod.GET, value = "weather", consumes = "application/json")
	OpenWeatherApiResponse getWeatherByZip(@RequestParam("zip") String zip);
}

// V2 Feign Client - used to access version 2 API on the weather resource server
@FeignClient(name="weather-service", path = "api/v2",  contextId = "v2")
interface WeatherServerClientV2 {
	@RequestMapping(method = RequestMethod.GET, value = "weather", consumes = "application/json")
	OpenWeatherApiResponse getWeatherByZip(@RequestParam("zip") String zip);

	@RequestMapping(method = RequestMethod.GET, value = "weather", consumes = "application/json")
	OpenWeatherApiResponse getWeatherByCityStateCountry(@RequestParam("cityStateCountry") String cityStateCountry);
}

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@RestController("gateway-api")
	@RequestMapping()
	public class WeatherGatewayService {

		Logger logger = LoggerFactory.getLogger(WeatherGatewayService.class);

		private final WeatherServerClientV1 weatherClientV1;
		private final WeatherServerClientV2 weatherClientV2;
		//  the Split Client bean configured in SplitConfiguration.java
		private final SplitClient splitClient;

		public WeatherGatewayService(WeatherServerClientV1 weatherClientV1, WeatherServerClientV2 weatherClientV2, SplitClient splitClient) {
			this.weatherClientV1 = weatherClientV1;
			this.weatherClientV2 = weatherClientV2;
			this.splitClient = splitClient;
		}

		// Retrieves the username from the authenticated principal and
		// passes this username to the splitClient.getTreatment() method,
		// which is where the treatment is retrieved from Split (it's
		// actually cached so that this call is really fast but still
		// updated in near real-time)
		String getTreatmentForPrincipal(Principal principal) {
			String username = principal.getName();
			logger.info("username = " + username);
			String treatment = splitClient.getTreatment(username, "v2-deployment");
			logger.info("treatment = " + treatment);
			return treatment;
		}

		@GetMapping("/temperature/zip/{zip}")
		String getTempByZip(@PathVariable("zip") String zip, Principal principal) {
			String treatment = getTreatmentForPrincipal(principal);
			logger.info("Getting weather for zip = " + zip);

			OpenWeatherApiResponse weatherApiResponse = null;

			// using the treatment to select the correct API version
			if (treatment.equals("v1")) {
				weatherApiResponse = weatherClientV1.getWeatherByZip(zip);
			}
			else if (treatment.equals("v2")){
				weatherApiResponse = weatherClientV2.getWeatherByZip(zip);
			}
			else {
				throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
			}

			logger.info(weatherApiResponse.toString());
			return weatherApiResponse.getMain().getTemp().toString()+"°F, feels like " + weatherApiResponse.getMain().getFeels_like().toString()+"°F";
		}

		@GetMapping("/temperature/citystatecountry")
		String getTempByCityStateCountry(
				@RequestParam() String city,
				@RequestParam(required = false) String state,
				@RequestParam(required = false) String country,
				Principal principal) {
			String treatment = getTreatmentForPrincipal(principal);
			// only the users with treatment "v2" should be able to access this method
			if (treatment.equals("v2")) {
				logger.info("Getting weather for city = " + city + ", state = " + state + ", country = " + country);
				OpenWeatherApiResponse weatherApiResponse = weatherClientV2.getWeatherByCityStateCountry(city+","+state+","+country);
				logger.info(weatherApiResponse.toString());
				return weatherApiResponse.getMain().getTemp().toString()+"°F, feels like " + weatherApiResponse.getMain().getFeels_like().toString()+"°F";
			}
			else if (treatment.equals("v1")) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Method not found");
			}
			else {
				throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		@GetMapping("/weather/zip/{zip}")
		OpenWeatherApiResponse getWeatherByZip(@PathVariable("zip") String zip, Principal principal) {
			String treatment = getTreatmentForPrincipal(principal);
			// only the users with treatment "v2" should be able to access this method
			if (treatment.equals("v2")) {
				splitClient.track(principal.getName(), "user", "json-access");
				OpenWeatherApiResponse weatherApiResponse = weatherClientV2.getWeatherByZip(zip);
				return weatherApiResponse;
			}
			else if (treatment.equals("v1")) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Method not found");
			}
			else {
				throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

	}

}
