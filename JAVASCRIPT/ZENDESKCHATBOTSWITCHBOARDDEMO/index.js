'use strict';

require('dotenv').config()
const express = require('express');
const bodyParser = require('body-parser');
const _ = require('lodash');

// Finish configuring express
const port = process.env.PORT || 8081;
const app = express();
app.use(bodyParser.json());

// Load the Sunshine API helper module
const Sunshine = require('./sunshine');

// Helper func that returns a random message for the bot
const getRandomMessage = function () {
  const botReplies = [
    'That\'s very interesting. Please tell me more.',
    'Wow!',
    'Have you tried turning it off and back on?',
    'I think it\'s supposed to do that.',
    'I\'ll ask my manager...',
    'Just a moment...'
  ];
  return botReplies[Math.floor(Math.random()*botReplies.length)];
}

app.post('/', function(req, res) {

  //console.log('webhook PAYLOAD:\n', JSON.stringify(req.body, null, 4));

  // Need to let app.smooch.io know we received the webhook
  res.sendStatus(200);

  // Each message may contain multiple events
  const events = _.get(req.body, "events", []);

  // Iterate over the events in the webhook call
  events.forEach(event => {

    // Is a switchboard active?
    const activeSwitchboardIntegrationName = _.get(event, 'payload.conversation.activeSwitchboardIntegration.name', null);
    // Ignore all messages when the Zendesk integration is active
    const shouldIgnoreMessage = activeSwitchboardIntegrationName === 'Zendesk';

    // We only want to response to conversation messages
    if (event.type === 'conversation:message' && !shouldIgnoreMessage) {

      // Gather some details for logging purposes
      const authorType = _.get(event, 'payload.message.author.type', null);
      const displayName = _.get(event, 'payload.message.author.displayName', null);
      const conversationId = _.get(event, 'payload.conversation.id', null);
      const content = _.get(event, 'payload.message.content.text', null);

      // Log the message info
      console.log(`${event.type} author ( type=${authorType}, displayName=${displayName} ) content='${content}'`);
      //console.log(JSON.stringify(event, null, 2));

      // Is this a user message (hence one that we should respond to)?
      if (authorType === 'user') {
        // The user is asking for a human, so send the switchboard pass code
        if (content.includes('human') || content.includes('Human')) {
          Sunshine.sendMessage("What do you have against robots? Fine. I'll find you a human.", conversationId);
            Sunshine.sendMessage("%((switchboard:passControl))%", conversationId);
        }
        // Respond with a random message
        else {
          Sunshine.sendMessage(getRandomMessage(), conversationId);
        }
      }

    }
    // Not a conversation message, simply log the type for
    // for demonstration purposes
    else {
      console.log(event.type)
    }

  });

});

app.get('/', function(req, res){
  res.sendFile(__dirname + '/index.html');
});

app.listen(port, function(){
  console.log('listening on *:' + port);
});

// Helper func that returns the integrations for the app ID
app.get('/integrations', function (req, res) {
  Sunshine.getIntegrations().then(
      function(data) {
        res.send(data);
      },
      function(error) {
        console.error(error);
        res.sendStatus(500);
      }
  );
});

// Helper func that returns the switchboards for the app ID
app.get('/switchboards', function (req, res) {
  Sunshine.listSwitchboards(process.env.APP_ID).then(
      function(data) {
        res.send(data);
      },
      function(error) {
        console.error(error);
        res.sendStatus(500);
      }
  );
});

// Helper func that returns the switchboard integrations for
// the switchboard ID
app.get('/switchboards/:id/integrations', function (req, res) {
  Sunshine.listSwitchboardIntegrations(process.env.APP_ID, req.params.id).then(
      function(data) {
        res.send(data);
      },
      function(error) {
        console.error(error);
        res.sendStatus(500);
      }
  );
});
