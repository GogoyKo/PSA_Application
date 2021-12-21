<?php

//$con = mysqli_connect('localhost', 'root','','firstdb');

include("config.php");



$qr_code = mysqli_real_escape_string($con,$_POST['qr_number']);


	$enumerator_id = mysqli_real_escape_string($con,$_POST['enumerator_id']);
	$geo_iden_id = mysqli_real_escape_string($con,$_POST['geo_iden_id']);


$b1 = mysqli_real_escape_string($con,$_POST["b1"]);

$b2 = mysqli_real_escape_string($con,$_POST["b2"]);

$b3 = mysqli_real_escape_string($con,$_POST["b3"]);

$h1 = mysqli_real_escape_string($con,$_POST["h1"]);

$h2 = mysqli_real_escape_string($con,$_POST["h2"]);

$h3 = mysqli_real_escape_string($con,$_POST["h3"]);

$h4 = mysqli_real_escape_string($con,$_POST["h4"]);



$year = mysqli_real_escape_string($con, date("Y"));



$statement = mysqli_prepare($con, "INSERT INTO house_cen_ques (enumerator_id,geo_iden_id,b1,b2,b3,h1,h2,h3,h4,qr_number,year) VALUES (?,?,?,?,?,?,?,?,?,?,?)");

	mysqli_stmt_bind_param($statement, "sssssssssss",$enumerator_id,$geo_iden_id,$b1,$b2, $b3,$h1,$h2,$h3,$h4,$qr_code,$year);

mysqli_stmt_execute($statement);





$sql2 = mysqli_query($con, "INSERT INTO house_cen_ques_records (enumerator_id,geo_iden_id,b1,b2,b3,h1,h2,h3,h4,year,qr_number) VALUES ('$enumerator_id','$geo_iden_id','$b1','$b2', '$b3','$h1','$h2','$h3','$h4','$year', '$qr_code')");



$response = array();

$response["success"] = "Successfully Save";

echo json_encode($response);





?>