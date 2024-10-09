'use strict';

require('dotenv').config()
const _ = require('lodash');
const Sunshine = require('./sunshine');

/*
  Helper script that will delete any existing switchboards. You may find
  you need to run this if you stop Ngrok and have to restart it, thus
  changing public domains, which will cause you to need to delete the old
  webhook and add a new one (but you can't delete a webhook integration
  that has switcboards associated with it, hence you'll need to delete the
  switchboard before you delete the webhook).
*/
async function main() {

  if (!process.env.APP_ID || !process.env.SMOOCH_KEY_SECRET || !process.env.SMOOCH_KEY_ID) {
    throw new Error(".env file not properly configured")
  }

  try {

    // Delete any existing switchboards for app
    // - help method that will delete any existing switchboards for the app
    console.log("Deleted all existing switchboards for app ID\n");
    const response = await Sunshine.deleteAnyExistingSwitchboardsForAppId(process.env.APP_ID);


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
