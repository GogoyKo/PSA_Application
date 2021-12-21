<?php

//$con = mysqli_connect('localhost', 'root','','firstdb');

include("config.php");



$qr_code = mysqli_real_escape_string($con,$_POST['qr_number']);

$enumerator_id = mysqli_real_escape_string($con,$_POST['enumerator_id']);
	$geo_iden_id = mysqli_real_escape_string($con,$_POST['geo_iden_id']);

$date_visit = mysqli_real_escape_string($con,$_POST["date_visit"]);

$time_began =mysqli_real_escape_string($con,$_POST["time_began"]);

$time_end = mysqli_real_escape_string($con,$_POST["time_end"]);

$result_visit = mysqli_real_escape_string($con,$_POST["result_visit"]);



$date_visit2 = mysqli_real_escape_string($con,$_POST["date_visit2"]);

$time_visit = mysqli_real_escape_string($con,$_POST["time_visit"]);





$num_of_visit = mysqli_real_escape_string($con,$_POST["num_of_visit"]);

$result_of_visit = mysqli_real_escape_string($con,$_POST["result_of_visit"]);

$num_of_household_mem = mysqli_real_escape_string($con,$_POST["num_of_household_mem"]);

$num_of_males = mysqli_real_escape_string($con,$_POST["num_of_males"]);

$num_of_females = mysqli_real_escape_string($con,$_POST["num_of_females"]);

$mode_of_date_collection = mysqli_real_escape_string($con,$_POST["mode_of_date_collection"]);



$year = mysqli_real_escape_string($con, date("Y"));





$statement = mysqli_prepare($con, "INSERT INTO interview_rec (enumerator_id,geo_iden_id,date_visit, time_began,time_end,result_visit,date_visit2,time_visit,num_of_visit,result_of_visit,num_of_household_mem,num_of_males,num_of_females,mode_of_date_collection,qr_number,year) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

	mysqli_stmt_bind_param($statement, "ssssssssssssssss",$enumerator_id,$geo_iden_id,$date_visit, $time_began,$time_end,$result_visit,$date_visit2,$time_visit,$num_of_visit,$result_of_visit,$num_of_household_mem,$num_of_males,$num_of_females,$mode_of_date_collection,$qr_code,$year);

mysqli_stmt_execute($statement);





$sql2 = mysqli_query($con, "INSERT INTO interview_rec_records (enumerator_id,geo_iden_id,date_visit, time_began,time_end,result_visit,date_visit2,time_visit,num_of_visit,result_of_visit,num_of_household_mem,num_of_males,num_of_females,mode_of_date_collection,year, qr_number) VALUES ('$enumerator_id','$geo_iden_id','$date_visit', '$time_began','$time_end','$result_visit','$date_visit2','$time_visit','$num_of_visit','$result_of_visit','$num_of_household_mem','$num_of_males','$num_of_females','$mode_of_date_collection','$year','$qr_code')");



$response = array();

$response["success"] = "Save Successfuly";

echo json_encode($response);





?>