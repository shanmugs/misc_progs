'use strict';

require('dotenv').config()
const _ = require('lodash');
const Sunshine = require('./sunshine');

/*
  Creates and configures a switchboard to hand over traffic from
  a custom integration / webhook to the Zendesk integration. Assumes
  that the application only has 1 custom integration, which is the
  webhook integration for the bot in the index.js of this project.
  Also assumes that .env has the app ID, smooch key ID, and smooch
  key secret.
*/
async function main() {

  if (!process.env.APP_ID || !process.env.SMOOCH_KEY_SECRET || !process.env.SMOOCH_KEY_ID) {
    throw new Error(".env file not properly configured")
  }

  try {
    // Get the webhook / custom integration ID
    // - this is the integration ID that identifies the webhook for the bot
    let response = await Sunshine.getIntegrations();
    console.log("All integrations:\n", response);
    const customIntegration = response.integrations.find(i => i['type'] === 'custom');
    const customIntegrationId = customIntegration.id;

    // Delete any existing switchboards for app
    // - help method that will delete any existing switchboards for the app
    console.log("Deleted all existing switchboards for app ID\n");
    response = await Sunshine.deleteAnyExistingSwitchboardsForAppId(process.env.APP_ID);

    // create the switchboard
    response = await Sunshine.createSwitchboard(process.env.APP_ID);

    console.log("Switchboard created:\n", response);

    // get the switchboard ID
    const switchboardId = response.switchboard.id;

    // Create switchboard integration for webhook
    response = await Sunshine.createSwitchboardIntegrationWithId(
        process.env.APP_ID,
        switchboardId,
        'bot',
        customIntegrationId
    );

    console.log("Webhook switchboard integration created:\n", response);

    // Get the switchboard integration ID for the webhook switchboard integration
    const webhookSwitchboardIntegrationId = response.switchboardIntegration.id;

    // Create the switchboard integration for Zendesk
    response = await Sunshine.createSwitchboardIntegrationWithType(
        process.env.APP_ID,
        switchboardId,
        'Zendesk',
        'zd:agentWorkspace',
        webhookSwitchboardIntegrationId
    );

    console.log("Zendesk switchboard integration created:\n", response);

    // Get the switchboard integration ID for the Zendesk switchboard integration
    const zdSwitchboardIntegrationId = response.switchboardIntegration.id;

    // Update the webhook switchboard integration to tie it back to the Zendesk
    // switchboard integration with the nextSwitchboardIntegrationId attribute
    response = await Sunshine.updateSwitchboardIntegration(
        process.env.APP_ID,
        switchboardId,
        webhookSwitchboardIntegrationId,
        { nextSwitchboardIntegrationId: zdSwitchboardIntegrationId }
    )

    console.log("Webhook switchboard integration updated:\n", response);

    // Enable the switchboard and set the webhook (bot) integration as the default
    response = await Sunshine.updateSwitchboard(
        process.env.APP_ID,
        switchboardId,
        {
          defaultSwitchboardIntegrationId: webhookSwitchboardIntegrationId,
          enabled: true
        }
    )

    console.log("Switchboard updated:\n", response);

    console.log("\nAll done!\n")
  }
  catch (err) {
    console.log(err)
  }

}

/*
  This is JS weirdness that is required so that we can run
  a top-level method with async/await.
 */
(async () => {
  try {
    await main();
  }
  catch (err) {
    console.log(err)
  }
})();
