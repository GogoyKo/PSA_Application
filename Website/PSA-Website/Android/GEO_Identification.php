<?php

//$con = mysqli_connect('localhost', 'root','','firstdb');

include("config.php");



$qr_code = mysqli_real_escape_string($con,$_POST['qr_number']);

$enumerator_id = mysqli_real_escape_string($con,$_POST['enumerator_id']);



$province = mysqli_real_escape_string($con,$_POST["province"]);

$province_num = mysqli_real_escape_string($con,$_POST["province_num"]);

$city = mysqli_real_escape_string($con,$_POST["city"]);

$city_num = mysqli_real_escape_string($con,$_POST["city_num"]);

$brgy = mysqli_real_escape_string($con,$_POST["barangay"]);

$brgy_num = mysqli_real_escape_string($con,$_POST["barangay_num"]);

$ean = mysqli_real_escape_string($con,$_POST["enumerator"]);

$bsn = mysqli_real_escape_string($con,$_POST["building"]);

$husn = mysqli_real_escape_string($con,$_POST["house"]);

$hsn = mysqli_real_escape_string($con,$_POST["household"]);

$lntr = mysqli_real_escape_string($con,$_POST["line"]);

$Lname = mysqli_real_escape_string($con,$_POST["lastname"]);

$Fname = mysqli_real_escape_string($con,$_POST["firstname"]);

$Address = mysqli_real_escape_string($con,$_POST["address"]);



$year = mysqli_real_escape_string($con, date("Y"));













$statement = mysqli_prepare($con, "INSERT INTO geo_identification (enumerator_id,province,province_num,city,city_num,brgy,brgy_num,EAN,BSN,HUSN,HSN,LNTR,Lname,Fname,Address,qr_number,year) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

	mysqli_stmt_bind_param($statement, "sssssssssssssssss",$enumerator_id,$province,$province_num,$city,$city_num,$brgy,$brgy_num,$ean,$bsn,$husn,$hsn,$lntr,$Lname,$Fname,$Address,$qr_code,$year);

mysqli_stmt_execute($statement);





$sql2 = mysqli_query($con, "INSERT INTO geo_identification_records (enumerator_id,province,province_num,city,city_num,brgy,brgy_num,EAN,BSN,HUSN,HSN,LNTR,Lname,Fname,Address,year, qr_number) VALUES ('$enumerator_id','$province','$province_num','$city','$city_num','$brgy','$brgy_num','$ean','$bsn','$husn','$hsn','$lntr','$Lname','$Fname','$Address','$year','$qr_code')");



$response = array();

$response["response"] = "Successfully Save";

echo json_encode($response);









?>