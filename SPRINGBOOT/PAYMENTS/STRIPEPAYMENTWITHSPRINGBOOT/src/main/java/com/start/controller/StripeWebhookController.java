package com.start.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.model.EventDataObjectDeserializer;
import com.stripe.model.StripeObject;
import com.stripe.net.Webhook;
import com.stripe.model.*;
import ch.qos.logback.classic.Logger;

@RestController
public class StripeWebhookController {

	private static final String PaymentIntent = null;

	private Logger logger = (Logger) LoggerFactory.getLogger( StripeWebhookController.class);
	
	@Value("${stripe.webhook.secret}")
	private String endpointSecret;

	private Event event;
	
	@PostMapping("/stripe/events")
	public String handleStripeEvent(@RequestBody String payLoad,@RequestHeader("Stripe-Signature") String sigHeader,HttpServletResponse res) throws IOException,ServletException
	{
		
        if(sigHeader == null)
        {
        	return "";
        }
		
        Event event;   
        //System.out.println(payLoad);
        
                // Only verify the event if you have an endpoint secret defined.
                // Otherwise use the basic event deserialized with GSON.
                try {
                    event = Webhook.constructEvent(
                        payLoad, sigHeader, endpointSecret
                    );
                } catch (SignatureVerificationException e) {
                    // Invalid signature
                    logger.info("⚠️  Webhook error while validating signature.");
                    return "";
                }
            
            // Deserialize the nested object inside the event
            EventDataObjectDeserializer dataObjectDeserializer = event.getDataObjectDeserializer();
            StripeObject stripeObject = null ;
            if (dataObjectDeserializer.getObject().isPresent()) {
                stripeObject = dataObjectDeserializer.getObject().get();
            } else {
                // Deserialization failed, probably due to an API version mismatch.
                // Refer to the Javadoc documentation on `EventDataObjectDeserializer` for
                // instructions on how to handle this case, or return an error here.
            }
            // Handle the event
            switch (event.getType()) {
                case "payment_intent.succeeded":
                	System.out.println("Payment done");
                	PaymentIntent paymentIntent =(PaymentIntent) stripeObject;                	
                    logger.info("Payment for id={}, {} succeeded.",paymentIntent.getId(),paymentIntent.getAmount());
                    // Then define and call a method to handle the successful payment intent.
                    // handlePaymentIntentSucceeded(paymentIntent);
                    break;
                 
                case "payment_intent.payment_failed":
                	logger.info("Payment failed Soory");
                	
                default:
                    logger.warn("Unhandled event type: {} " + event.getType());
                    break;
            }
            return "";
	}

	private void sendPOST() {
		// TODO Auto-generated method stub
		
	}
	

}
