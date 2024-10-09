package com.example.demo;

import io.split.client.SplitClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api")
public class DonutController {

    Logger logger = LoggerFactory.getLogger(DonutController.class);

    DonutRepository donutRepository;
    SplitClient splitClient;

    DonutController(DonutRepository donutRepository, SplitClient splitClient) {
        this.donutRepository = donutRepository;
        this.splitClient = splitClient;
    }

    @RequestMapping("/buy-donut")
    @ResponseBody
    String buyDonut(@RequestParam String donutName, @RequestParam String username, @RequestParam String userLocation) {

        // Create the attributes map
        Map<String, Object> attributes = new HashMap<String, Object>();
        attributes.put("location", userLocation);
        // Get the treatment from the Split Client
        String treatment = this.splitClient.getTreatment(username,"DonutFactory", attributes);

        // Log the treatment, just for fun
        logger.info("Treatment="+treatment.toString());

        // Make sure only people with the treatment "on" get the chocolate donuts
        if (treatment.equals("off") && donutName.toLowerCase().equals("chocolate")) {
            return "Wah. Wah. No chocolate donuts for you.";
        }

        List<Donut> found = this.donutRepository.findByNameIgnoreCase(donutName);

        if (found.size() <= 0) {
            return "Wah. Wah. No donuts for you.";
        }
        else {
            Donut donut = found.get(0);
            if (donut.numberAvailable <= 0) {
                return "Sorry. All out of those.";
            }
            donut.numberAvailable = donut.numberAvailable - 1;
            this.donutRepository.save(donut);
            return "Enjoy your " + donutName + " donut. It costs $" + donut.costDollars + ". There are " + donut.numberAvailable + " of these remaining.";
        }
    }

}
