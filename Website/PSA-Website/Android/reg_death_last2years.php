<?php

//$con = mysqli_connect('localhost', 'root','','firstdb');

include("config.php");



$qr_code = mysqli_real_escape_string($con,$_POST['qr_number']);

$enumerator_id = mysqli_real_escape_string($con,$_POST['enumerator_id']);
	$geo_iden_id = mysqli_real_escape_string($con,$_POST['geo_iden_id']);



$d1 = mysqli_real_escape_string($con,$_POST["d1"]);

$d2 = mysqli_real_escape_string($con,$_POST["d2"]);

$lastname = mysqli_real_escape_string($con,$_POST["lastname"]);

$firstname = mysqli_real_escape_string($con,$_POST["firstname"]);

$gender = mysqli_real_escape_string($con,$_POST["gender"]);

$age_at_death = mysqli_real_escape_string($con,$_POST["age_at_death"]);

$death_reg_d6 = mysqli_real_escape_string($con,$_POST["death_reg_d6"]);

$death_reg_d7 = mysqli_real_escape_string($con,$_POST["death_reg_d7"]);



$year = mysqli_real_escape_string($con, date("Y"));

$death_num = rand();



$statement = mysqli_prepare($con, "INSERT INTO rdlyhm (enumerator_id,geo_iden_id,death_num,d1,d2,lastname,firstname,gender,age_at_death,death_reg_d6,death_reg_d7,qr_number,year) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");

	mysqli_stmt_bind_param($statement, "sssssssssssss",$enumerator_id,$geo_iden_id,$death_num,$d1,$d2,$lastname,$firstname,$gender,$age_at_death,$death_reg_d6,$death_reg_d7,$qr_code,$year);

mysqli_stmt_execute($statement);



$sql2 = mysqli_query($con, "INSERT INTO rdlyhm_records (enumerator_id,geo_iden_id,death_num,d1,d2,lastname,firstname,gender,age_at_death,death_reg_d6,death_reg_d7,year, qr_number) VALUES ('$enumerator_id','$geo_iden_id','$death_num','$d1','$d2','$lastname','$firstname','$gender','$age_at_death','$death_reg_d6','$death_reg_d7','$year','$qr_code')");





$response = array();

$response["success"] = "Successfully Save";

echo json_encode($response);





?>	