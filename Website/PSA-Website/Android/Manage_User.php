<?php

include("config.php");

$id = mysqli_real_escape_string($con,$_POST["enumerator_id"]);
$email = mysqli_real_escape_string($con,$_POST["email"]);
$mobile = mysqli_real_escape_string($con,$_POST["mobile"]);
$username = mysqli_real_escape_string($con,$_POST["username"]);



$statement = mysqli_prepare($con, "UPDATE enum_user SET username='$username', email = '$email', mobile='$mobile' WHERE enumerator_id='$id'");
mysqli_stmt_execute($statement);

$response = array();
$response["success"] = true;
echo json_encode($response);


?>