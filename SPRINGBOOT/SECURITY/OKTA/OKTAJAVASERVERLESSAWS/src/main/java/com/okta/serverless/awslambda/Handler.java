package com.okta.serverless.awslambda;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.okta.jwt.AccessTokenVerifier;
import com.okta.jwt.Jwt;
import com.okta.jwt.JwtVerificationException;
import com.okta.jwt.JwtVerifiers;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;

public class Handler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    // Parse and create JSON
    Gson gson = new GsonBuilder().create();
    // OAuth configuration properties
    Config config;
    // Okta token verifier
    AccessTokenVerifier jwtVerifier;

    public Handler() throws IOException {
        // Get our config properties
        this.config = new Config();
        // Initialize the Okta JWT verifier
        this.jwtVerifier = JwtVerifiers.accessTokenVerifierBuilder()
                .setIssuer(this.config.issuer)
                .setAudience(this.config.audience)            // defaults to 'api://default'
                .setConnectionTimeout(Duration.ofSeconds(this.config.connectionTimeoutSeconds)) // defaults to 1s
                .setReadTimeout(Duration.ofSeconds(this.config.readTimeoutSeconds))       // defaults to 1s
                .build();
    }

    // Request method called by AWS Lambda service
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context)
    {

        // Create the response object (AWS is picky about the response format, so using their event
        // objects is helfpul)
        APIGatewayProxyResponseEvent apiGatewayProxyResponseEvent = new APIGatewayProxyResponseEvent();

        try {

            // Get the logger from the context
            LambdaLogger logger = context.getLogger();

            // Get the response body
            String body = request.getBody();

            // Log the body--'cause, hey, we went through the trouble of getting the logger
            logger.log("BODY: " + body);

            // Use Gson to pare the response body to our LambdaInput class
            LambdaInput inputObj = gson.fromJson(body, LambdaInput.class);

            // Get the headers so we can check the auth header
            Map<String, String> headers = request.getHeaders();

            if (headers == null) {
                throw new JwtVerificationException("Authorization header empty");
            }

            // Get the auth header
            String authHeader = headers.get("Authorization");

            if (authHeader == null) {
                throw new JwtVerificationException("Authorization header empty");
            }

            String token = authHeader.replaceAll("\\s*Bearer\\s*", "");

            logger.log("TOKEN: " + token);

            // This verifies the token in the auth header and throws an
            // exception if it does't validate
            Jwt jwt = jwtVerifier.decode(token);

            // Strip the spaces!
            String stripped = inputObj.input.replaceAll("\\s", "");

            // Set the status code and response
            apiGatewayProxyResponseEvent.setStatusCode(200);
            apiGatewayProxyResponseEvent.setBody(stripped);

            // Log the full response object, just for fun
            logger.log(gson.toJson(apiGatewayProxyResponseEvent));

            // Return the result
            return apiGatewayProxyResponseEvent;
        }
        catch (JsonParseException ex) {
            apiGatewayProxyResponseEvent.setStatusCode(400);
            apiGatewayProxyResponseEvent.setBody("Failed to parse JSON: " + ex.getMessage());
            return apiGatewayProxyResponseEvent;
        } catch (JwtVerificationException ex) {
            apiGatewayProxyResponseEvent.setStatusCode(403);
            // In production, you probably just want to return a 403 and not return the error
            apiGatewayProxyResponseEvent.setBody("Invalid Auth: " + ex.getMessage());
            return apiGatewayProxyResponseEvent;
        }
    }
}
