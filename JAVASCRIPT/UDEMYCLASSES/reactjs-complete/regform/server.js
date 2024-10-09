var express = require('express');
var path = require('path');
var bodyParser = require('body-parser');

var app = express();

app.set('port', (process.env.PORT || 3000));

app.use(express.static(path.join(__dirname, 'client')));

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended: true}));

app.listen(app.get('port'), function() {
  console.log("Server listening on port " + app.get('port'));
});
