<?php

//$con = mysqli_connect('localhost', 'root','','firstdb');

include("config.php");



$qr_code = mysqli_real_escape_string($con,$_POST['qr_number']);

$enumerator_id = mysqli_real_escape_string($con,$_POST['enumerator_id']);
	$geo_iden_id = mysqli_real_escape_string($con,$_POST['geo_iden_id']);

$lastname_p1 = mysqli_real_escape_string($con,$_POST["lastname_p1"]);

$firstname_p1 = mysqli_real_escape_string($con,$_POST["firstname_p1"]);

$p2 = mysqli_real_escape_string($con,$_POST["p2"]);

$p3	 = mysqli_real_escape_string($con,$_POST["p3"]);

$month_p4 = mysqli_real_escape_string($con,$_POST["month_p4"]);

$p5 = mysqli_real_escape_string($con,$_POST["p5"]);



$p6 = mysqli_real_escape_string($con,$_POST["p6"]);

$p7 = mysqli_real_escape_string($con,$_POST["p7"]);

$p8 = mysqli_real_escape_string($con,$_POST["p8"]);

$p9 = mysqli_real_escape_string($con,$_POST["p9"]);

$p10 = mysqli_real_escape_string($con,$_POST["p10"]);



$p11 = mysqli_real_escape_string($con,$_POST["p11"]);

$p12 = mysqli_real_escape_string($con,$_POST["p12"]);

$p13 = mysqli_real_escape_string($con,$_POST["p13"]);

$p14 = mysqli_real_escape_string($con,$_POST["p14"]);

$p15 = mysqli_real_escape_string($con,$_POST["p15"]);

$p16 = mysqli_real_escape_string($con,$_POST["p16"]);



$year = mysqli_real_escape_string($con, date("Y"));


$person_num = rand();




$statement = mysqli_prepare($con, "INSERT INTO popu_cen_ques (enumerator_id,geo_iden_id,person_num,lastname_p1,firstname_p1,p2,p3,month_p4,p5,p6,p7,p8,p9,p10,p11,p12,p13,p14,p15,p16,qr_number,year) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

	mysqli_stmt_bind_param($statement, "ssssssssssssssssssssss",$enumerator_id,$geo_iden_id,$person_num,$lastname_p1,$firstname_p1,$p2,$p3,$month_p4,$p5,$p6,$p7,$p8,$p9,$p10,$p11,$p12,$p13,$p14,$p15,$p16,$qr_code,$year);

mysqli_stmt_execute($statement);





$sql2 = mysqli_query($con, "INSERT INTO popu_cen_ques_records (enumerator_id,geo_iden_id,person_num,lastname_p1,firstname_p1,p2,p3,month_p4,p5,p6,p7,p8,p9,p10,p11,p12,p13,p14,p15,p16,year, qr_number) VALUES ('$enumerator_id','$geo_iden_id','$person_num','$lastname_p1','$firstname_p1','$p2','$p3','$month_p4','$p5','$p6','$p7','$p8','$p9','$p10','$p11','$p12','$p13','$p14','$p15','$p16','$year', '$qr_code')");





$response = array();

$response["success"] = "Successfully Save";

echo json_encode($response);





?>