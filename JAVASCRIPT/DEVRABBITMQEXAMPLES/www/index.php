<!DOCTYPE html>
<html>
    <body>
        <h1>Home of examples</h1>
<?php

function isExample($var)
{
    $needle = '.php';
    return empty(trim($var)) == false && substr($var,-strlen($needle))===$needle;
}

$files  = scandir("./");
$filteredFiles  = array_filter($files, "isExample");

foreach ($filteredFiles as $row)
{
    print "<li><a href='./$row'>$row</a></li>\n";
}
?>
    </body>
</html>
