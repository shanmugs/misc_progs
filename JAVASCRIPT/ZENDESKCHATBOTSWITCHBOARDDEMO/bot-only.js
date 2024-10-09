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

    // We only want to response to conversation messages
    if (event.type === 'conversation:message') {

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
        Sunshine.sendMessage(getRandomMessage(), conversationId);
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
