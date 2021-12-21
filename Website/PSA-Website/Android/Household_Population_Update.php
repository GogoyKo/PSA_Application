<?php



include("config.php");

if(isset($_POST['qr_number'])){

$qr_code = mysqli_real_escape_string($con,$_POST['qr_number']);

$popu_cen_ques_id = mysqli_real_escape_string($con,$_POST["popu_cen_ques_id"]);



	$enumerator_id = mysqli_real_escape_string($con,$_POST['enumerator_id']);
	$geo_iden_id = mysqli_real_escape_string($con,$_POST['geo_iden_id']);

$person_num = mysqli_real_escape_string($con,$_POST['person_num']);




$lastname_p1 = mysqli_real_escape_string($con,$_POST["lastname_p1"]);

$firstname_p1 = mysqli_real_escape_string($con,$_POST["firstname_p1"]);

$p2 = mysqli_real_escape_string($con,$_POST["p2"]);

$p3 = mysqli_real_escape_string($con,$_POST["p3"]);

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


$sql3 = "SELECT * FROM popu_cen_ques_records WHERE person_num = '$person_num' and year = '$year'";
             $query3 = mysqli_query($con, $sql3);
             $count3 = mysqli_num_rows($query3);


$statement = mysqli_prepare($con, "UPDATE popu_cen_ques SET enumerator_id = '$enumerator_id', lastname_p1='$lastname_p1' ,firstname_p1 = '$firstname_p1',p2='$p2' ,p3='$p3' ,month_p4='$month_p4' ,p5='$p5' ,p6='$p6' ,p7='$p7' ,p8='$p8' ,p9='$p9' ,p10='$p10' ,p11='$p11' ,p12='$p12' ,p13='$p13' ,p14='$p14' ,p15='$p15' ,p16='$p16', year = '$year' WHERE popu_cen_ques_id='$popu_cen_ques_id'");

mysqli_stmt_execute($statement);

$response = array();

$response["success"] = "Update successfully";

echo json_encode($response);

if($count3 == 0){
$sql2 = mysqli_query($con, "INSERT INTO popu_cen_ques_records (enumerator_id,geo_iden_id,person_num,lastname_p1,firstname_p1,p2,p3,month_p4,p5,p6,p7,p8,p9,p10,p11,p12,p13,p14,p15,p16,year, qr_number) VALUES ('$enumerator_id','$geo_iden_id','$person_num','$lastname_p1','$firstname_p1','$p2','$p3','$month_p4','$p5','$p6','$p7','$p8','$p9','$p10','$p11','$p12','$p13','$p14','$p15','$p16','$year','$qr_code')");

}elseif ($count3 == 1) {
	$sql2 = mysqli_query($con, "UPDATE popu_cen_ques_records SET enumerator_id = '$enumerator_id', lastname_p1='$lastname_p1' ,firstname_p1 = '$firstname_p1',p2='$p2' ,p3='$p3' ,month_p4='$month_p4' ,p5='$p5' ,p6='$p6' ,p7='$p7' ,p8='$p8' ,p9='$p9' ,p10='$p10' ,p11='$p11' ,p12='$p12' ,p13='$p13' ,p14='$p14' ,p15='$p15' ,p16='$p16', year = '$year' WHERE person_num='$person_num'");
}else{

	$response["success"] = "Error";

echo json_encode($response);
}











}



?>