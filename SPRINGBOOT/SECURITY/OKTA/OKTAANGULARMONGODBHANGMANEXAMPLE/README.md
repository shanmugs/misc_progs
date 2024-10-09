# Angular 8 + MongoDB Example

This example app shows how to build a hangman game with Angular, MongoDB, Node, and Express.

**Prerequisites:** [Node.js](https://nodejs.org/), MongoDB, and an [Okta developer account](https://developer.okta.com).

> [Okta](https://developer.okta.com/) has Authentication and User Management APIs that reduce development time with instant-on, scalable user infrastructure. Okta's REST API and makes it easy for developers to authenticate, manage, and secure users + roles in any application.

* [Getting Started](#getting-started)
* [Links](#links)
* [Help](#help)
* [License](#license)

## Getting Started

To install this example application, run the following commands:

```bash
git clone https://github.com/oktadeveloper/okta-angular-mongodb-hangman-example.git
cd okta-angular-mongodb-hangman-example
```

This will get a copy of the project installed locally. To install all of its dependencies and start each app, follow the instructions below.

To run the server, cd into the `hangman-server` folder and run:

```bash
npm install && node src/index.js
```

To run the client, cd into the `hangman-client` folder and run:

```bash
npm install && npm start
```

### Create a New OIDC App in Okta

If you don't have an Okta developer account, please [create one](https://developer.okta.com/signup/). Then, create a new OIDC app on Okta:

1. Log in to your developer account, navigate to **Applications** > **Add Application**.
3. Select **Single-Page App** > **Next**.
4. Give the application a name, change all instances of `https://localhost:8080` to `https://localhost:4200`, and click **Done**.

#### Server Configuration

Set your domain and copy the `clientId` into `hangman-server/src/auth.ts`.

**NOTE:** The value of `{yourOktaDomain}` should be something like `dev-123456.okta.com`. Make sure you don't include `-admin` in the value!

```typescript
const oktaJwtVerifier = new OktaJwtVerifier({
  clientId: '{yourClientId}',
  issuer: 'https://{yourOktaDomain}/oauth2/default'
});
```

#### Client Configuration

For the client, set the `issuer` and copy the `clientId` into `hangman-client/src/app/app.module.ts`.

```typescript
OktaAuthModule.initAuth({
  issuer: 'https://{yourOktaDomain}/oauth2/default',
  redirectUri: 'http://localhost:4200/implicit/callback',
  clientId: '{yourClientId}'
})
```

## Links

This example uses the following open source libraries from Okta:

* [Okta JWT Verifier](https://github.com/okta/okta-oidc-js/tree/master/packages/jwt-verifier#readme)
* [Okta Angular SDK](https://github.com/okta/okta-oidc-js/tree/master/packages/okta-angular#readme)

## Help

Please post any questions as comments on the [blog post]() or post them to Stack Overflow with the `okta` tag.

## License

Apache 2.0, see [LICENSE](LICENSE).
