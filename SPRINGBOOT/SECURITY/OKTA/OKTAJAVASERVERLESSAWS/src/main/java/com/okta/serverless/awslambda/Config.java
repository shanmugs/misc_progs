package com.okta.serverless.awslambda;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    public final String issuer;
    public final String audience;
    public final Long connectionTimeoutSeconds;
    public final Long readTimeoutSeconds;


    Config() throws IOException {

        // Create input stream to read config.properties from classpath (src/main/resources)
        InputStream input = Config.class.getClassLoader().getResourceAsStream("config.properties");

        // Make sure we found it on the classpath
        if (input == null) {
            throw new IOException("Unable to load config properties.");
        }

        // Load the file into our properties object
        Properties prop = new Properties();
        prop.load(input);

        // Save our props into our class properties
        this.issuer = prop.getProperty("okta.oauth.issuer");
        this.audience = prop.getProperty("okta.oauth.audience");
        this.connectionTimeoutSeconds = Long.parseLong(prop.getProperty("okta.oauth.connectionTimeoutSeconds"));
        this.readTimeoutSeconds = Long.parseLong(prop.getProperty("okta.oauth.readTimeoutSeconds"));

    }

}
