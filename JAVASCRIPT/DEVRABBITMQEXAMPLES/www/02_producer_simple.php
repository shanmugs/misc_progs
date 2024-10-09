<!DOCTYPE html>
<html>
    <body>
        <h1>Producer Simple</h1>
<?php
require_once __DIR__ . '/vendor/autoload.php';
use PhpAmqpLib\Connection\AMQPStreamConnection;
use PhpAmqpLib\Message\AMQPMessage;

# 1
$connection = new AMQPStreamConnection('localhost', 5672, 'guest', 'guest');
$channel = $connection->channel();
echo "<li>Connected to server'</li>";

# 2
$queue = 'produce_simple';
$channel->queue_declare($queue, false, false, false, false);
echo "<li>Create queue'</li>";

# 3
$msg = new AMQPMessage('Hello World!');
$channel->basic_publish($msg, '', $queue);
echo "<li>Publish msg'</li>";

# 1
$channel->close();
$connection->close();
?>