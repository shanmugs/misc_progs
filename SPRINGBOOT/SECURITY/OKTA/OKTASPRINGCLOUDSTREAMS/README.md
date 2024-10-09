# Spring Cloud Streams with Okta Auth
 
This example app shows how to create a Spring Cloud Streams app using Spring WebFlux and JWT OAuth. The app 
demonstrates how to publish and subscribe to topics, as well as how to stream events from a WebFlux REST endpoint. It generates
a random stream of integers, which it publishes to the `ints` channel. A processor listens to this channel, calculates a running
total, and publishes the total and the current value to the `total` channel. A WebFlux endpoint publishes the `total` channel as
an events stream.

Please read [FINAL TITLE HERE](https://<need.a.link>) to see how this app was created.

#### ^-- TODO: needs a link --^

**Prerequisites:** 

**Java 11**: This project uses Java 11. OpenJDK 11 will work just as well. Instructions are found on the [OpenJDK website](https://openjdk.java.net/install/). OpenJDK can also be installed using [Homebrew](https://brew.sh/). Alternatively, [SDKMAN](https://sdkman.io/) is another great option for installing and managing Java versions.

**Maven**: You need Maven installed to bootstrap the project. Install it according to the instructions on [their website](https://maven.apache.org/install.html). Or use SDKMAN or Homebrew.

**Okta Developer Account**: You’ll be using Okta as an OAuth/OIDC provider to add JWT authentication and authorization to the application. You can go to [our developer site](https://developer.okta.com/signup/) and sign up for a free developer account.

**HTTPie**: This is a powerful command-line HTTP request utility that you'll use to test the WebFlux server. Install it according to [the docs on their site](https://httpie.org/doc#installation).

**Docker** and **Docker Compose**: You'll use Docker and Docker Compose to run the RabbitMQ service. First you need to install Docker. On Mac and Windows you can install the desktop client. On Linux you'll need to install Docker Machine directly. Take a look at [the Docker docs for installation instructions.](https://docs.docker.com/) for your operating system. Once you have Docker installed, follow [the instructions to install Docker Compose](https://docs.docker.com/compose/install/).

> [Okta](https://developer.okta.com/) has Authentication and User Management APIs that reduce development time with instant-on, scalable user infrastructure. Okta's intuitive API and expert support make it easy for developers to authenticate, manage, and secure users and roles in any application.

* [Getting Started](#getting-started)
* [Create an Okta OIDC Application](#create-an-okta-oidc-application)
* [Start the Apps](#start-the-apps)
* [Links](#links)
* [Help](#help)
* [License](#license)

## Getting Started

To install this example application, run the following commands:

```bash
git clone https://<need.a.link> spring-cloud-streams
cd spring-cloud-streams
```

This will get a copy of the project installed locally. Before the projects apps will run, however, you need to create an OIDC application in Okta and configure the application to use it.

## Create an Okta OIDC Application

The fastest way to do this is to use the Okta Maven Plugin, which will configure an OIDC application for you.

```bash
mvn com.okta:okta-maven-plugin:setup
```

This places the necessary values in the `src/main/resources/application.yml` file. If you create an OIDC application manually on the Okta website,
just make sure you put the necessary values in the `application.yml` file.

```yaml
okta:
  oauth2:
    client-secret: {yourClientSecret}
    client-id: {yourClientId}
    issuer: https://{yourOktaDomain}/oauth2/default
```

If you already have an Okta developer account and Okta Org, you can create a configuration file at `~/.okta/okta.yaml` for Okta Maven Plugin that will use that account.

`~/.okta/okta.yaml` 
```yml
okta:
  client:
    orgUrl: https://{yourOktaDomain}
    token: {yourApiToken}
```

You'll need to create an API token. From the Okta developer's console, go to **API** and **Tokens**. Click **Create Token**. Give the token a **name**. Copy the token value and place it in the `yaml` file along with your Okta domain.

If you do not have an Okta developer's account, don't worry about the `okta.yaml` file. The Okta Maven Plugin will configure it for you.

## Start the Apps

To run the RabbitMQ messaging server, run the following command. You may need to run this as root using `sudo`. Leave this process running.
 
```bash
docker-compose up
```

To run the Spring Boot application, open a new shell and run the following.
 
```bash
./mvnw spring-boot:run
```
## Testing the Application

You will need to generate a JWT to test the application. To do this, you can use the [OIDC Debugger](https://oidcdebugger.com/). For full instructions, see the blog post associated with this project.

Once you have a token, store it in a shell variable and run a request using HTTPie.

```bash
TOKEN={your token value}
http --stream :8080/sse "Authorization: Bearer ${TOKEN}"
```
You should see some streaming data.

```bash
...
data:{"currentValue":57,"total":6055}
data:{"currentValue":1,"total":6056}
data:{"currentValue":2,"total":6058}
data:{"currentValue":10,"total":6068}
data:{"currentValue":62,"total":6130}
data:{"currentValue":29,"total":6159}
data:{"currentValue":85,"total":6244}
data:{"currentValue":84,"total":6328}
...
```

## Links

This example uses the following open source libraries:

* [Spring Cloud Streams](https://spring.io/projects/spring-cloud-stream)
* [Spring Boot](https://spring.io/projects/spring-boot)
* [Spring Security](https://spring.io/projects/spring-security)
* [RabbitMQ](https://www.rabbitmq.com/)

## Help

Please post any questions as comments on the [blog post](https://need.a.link), or visit our [Okta Developer Forums](https://devforum.okta.com/). You can also email developers@okta.com if you'd like to create a support ticket.

#### ^-- TODO: needs a link --^

## License

Apache 2.0, see [LICENSE](LICENSE).
