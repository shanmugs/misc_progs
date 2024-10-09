<!DOCTYPE html>
<html>
    <body>
        <h1>Connect Test</h1>
<?php
require_once __DIR__ . '/vendor/autoload.php';
use PhpAmqpLib\Connection\AMQPStreamConnection;
use PhpAmqpLib\Message\AMQPMessage;

$connection = new AMQPStreamConnection('localhost', 5672, 'guest', 'guest');
$channel = $connection->channel();

echo "<li>Connected to server'</li>";

$channel->close();
$connection->close();

echo "<li>Closed connection'</li>";
?>
    </body>
</html>

