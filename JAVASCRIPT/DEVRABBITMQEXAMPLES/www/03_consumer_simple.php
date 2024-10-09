<!DOCTYPE html>
<html>
    <body>
        <h1>Consumer Simple</h1>
<?php
require_once __DIR__ . '/vendor/autoload.php';
use PhpAmqpLib\Connection\AMQPStreamConnection;
use PhpAmqpLib\Message\AMQPMessage;

# 1
$connection = new AMQPStreamConnection('localhost', 5672, 'guest', 'guest');
$channel = $connection->channel();
echo "<li>Connected to server'</li>";

# 3
$callback = function ($msg) {
    echo "<li> ---> Message: $msg->body</li>";
};

$queue = 'produce_simple';
$channel->basic_consume($queue, '', false, true, false, false, $callback);

while ($channel->is_open()) {
    $channel->wait();
}

# 1
$channel->close();
$connection->close();
?>