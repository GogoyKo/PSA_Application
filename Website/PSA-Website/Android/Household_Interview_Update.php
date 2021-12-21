<?php



include("config.php");


if(isset($_POST['qr_number'])){

	

$qr_code = mysqli_real_escape_string($con,$_POST['qr_number']);
$interview_rec_id = mysqli_real_escape_string($con,$_POST["interview_rec_id"]);
$enumerator_id = mysqli_real_escape_string($con,$_POST['enumerator_id']);
$geo_iden_id = mysqli_real_escape_string($con,$_POST["geo_iden_id"]);
$date_visit = mysqli_real_escape_string($con,$_POST["date_visit"]);
$time_began = mysqli_real_escape_string($con,$_POST["time_began"]);
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

 $sql3 = "SELECT * FROM interview_rec_records WHERE qr_number = '$qr_code' and year = '$year'";
             $query3 = mysqli_query($con, $sql3);
             $count3 = mysqli_num_rows($query3);




$statement = mysqli_prepare($con, "UPDATE interview_rec SET enumerator_id = '$enumerator_id',date_visit='$date_visit' ,time_began = '$time_began',time_end='$time_end' ,result_visit='$result_visit' ,date_visit2='$date_visit2' ,time_visit='$time_visit' ,num_of_visit='$num_of_visit' ,result_of_visit='$result_of_visit',num_of_household_mem = '$num_of_household_mem',num_of_males = '$num_of_males',num_of_females = '$num_of_females',mode_of_date_collection = '$mode_of_date_collection',year = '$year' WHERE interview_rec_id='$interview_rec_id'");

mysqli_stmt_execute($statement);

$response = array();

$response["success"] = "Update successfully";

echo json_encode($response);


if($count3 == 0){
$sql2 = mysqli_query($con, "INSERT INTO interview_rec_records (enumerator_id,geo_iden_id,date_visit, time_began,time_end,result_visit,date_visit2,time_visit,num_of_visit,result_of_visit,num_of_household_mem,num_of_males,num_of_females,mode_of_date_collection,year, qr_number) VALUES ('$enumerator_id','$geo_iden_id','$date_visit', '$time_began','$time_end','$result_visit','$date_visit2','$time_visit','$num_of_visit','$result_of_visit','$num_of_household_mem','$num_of_males','$num_of_females','$mode_of_date_collection','$year','$qr_code')");


}else if($count3 == 1){
$sql2 = mysqli_query($con, "UPDATE interview_rec_records SET enumerator_id = '$enumerator_id',date_visit='$date_visit' ,time_began = '$time_began',time_end='$time_end' ,result_visit='$result_visit' ,date_visit2='$date_visit2' ,time_visit='$time_visit' ,num_of_visit='$num_of_visit' ,result_of_visit='$result_of_visit',num_of_household_mem = '$num_of_household_mem',num_of_males = '$num_of_males',num_of_females = '$num_of_females',mode_of_date_collection = '$mode_of_date_collection' WHERE qr_number ='$qr_code' AND year = '$year'");

}else{
	$response["success"] = "Error";

echo json_encode($response);

}



}


?>