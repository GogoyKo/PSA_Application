<?php
//$con = mysqli_connect('localhost', 'root','','firstdb');
include("config.php");
$name = mysqli_real_escape_string($con,$_POST["name"]);
$username = mysqli_real_escape_string($con,$_POST["username"]);
$age = mysqli_real_escape_string($con,$_POST["age"]);
$password = mysqli_real_escape_string($con,$_POST["password"]);

$statement = mysqli_prepare($con, "INSERT INTO users (name, username, age, password) VALUES (?,?,?,?)");
	mysqli_stmt_bind_param($statement, "ssis", $name, $username, $age, $password);
mysqli_stmt_execute($statement);

$response = array();
$response["success"] = true;
echo json_encode($response);


?>