package okta.springcloudstreams.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@SpringBootApplication
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class DemoApplication {

	private static Logger logger = LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
		http
				.authorizeExchange()
				.anyExchange().authenticated()
				.and()
				.oauth2ResourceServer()
				.jwt();
		return http.build();
	}

	static class Accumulator {

		private AtomicInteger count = new AtomicInteger(0);

		@Bean
		public Function<Integer, AccumulatorMessage> accumulate() {
			return payload -> new AccumulatorMessage(payload ,this.count.addAndGet(payload));
		}

	}

	static class Source {

		private Random random = new Random();

		@Bean
		public Supplier<Integer> send() {
			return () -> {
				return random.nextInt(100);
			};

		}
	}

	static class Sink {

		@Bean
		public Consumer<AccumulatorMessage> receive() {
			return payload -> {
				logger.info(payload.toString());
			};
		}

	}

}