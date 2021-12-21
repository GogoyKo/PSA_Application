<?php



include("config.php");


if(isset($_POST['qr_number'])){


$qr_code = mysqli_real_escape_string($con,$_POST['qr_number']);

$house_cen_ques_id = mysqli_real_escape_string($con,$_POST["house_cen_ques_id"]);


$enumerator_id = mysqli_real_escape_string($con,$_POST['enumerator_id']);
	

$geo_iden_id = mysqli_real_escape_string($con,$_POST["geo_iden_id"]);


$b1 = mysqli_real_escape_string($con,$_POST["b1"]);

$b2 = mysqli_real_escape_string($con,$_POST["b2"]);

$b3 = mysqli_real_escape_string($con,$_POST["b3"]);

$h1 = mysqli_real_escape_string($con,$_POST["h1"]);

$h2 = mysqli_real_escape_string($con,$_POST["h2"]);

$h3 = mysqli_real_escape_string($con,$_POST["h3"]);	

$h4 = mysqli_real_escape_string($con,$_POST["h4"]);



$year = mysqli_real_escape_string($con, date("Y"));




 $sql3 = "SELECT * FROM house_cen_ques_records WHERE qr_number = '$qr_code' and year = '$year'";
             $query3 = mysqli_query($con, $sql3);
             $count3 = mysqli_num_rows($query3);


$statement = mysqli_prepare($con, "UPDATE house_cen_ques SET enumerator_id = '$enumerator_id',b1='$b1' ,b2 = '$b2',b3='$b3' ,h1='$h1' ,h2='$h2' ,h3='$h3' ,h4='$h4',year ='$year'WHERE qr_number ='$qr_code' AND year = '$year'");

mysqli_stmt_execute($statement);






$response = array();

$response["success"] = "Update successfully";

echo json_encode($response);




if($count3 == 0){
$sql2 = mysqli_query($con, "INSERT INTO house_cen_ques_records (enumerator_id,geo_iden_id,b1,b2,b3,h1,h2,h3,h4,year,qr_number) VALUES ('$enumerator_id','$geo_iden_id','$b1','$b2', '$b3','$h1','$h2','$h3','$h4','$year','$qr_code')");
}else if($count3 == 1){
	$sql2 = mysqli_query($con, "UPDATE house_cen_ques_records SET enumerator_id = '$enumerator_id',b1='$b1' ,b2 = '$b2',b3='$b3' ,h1='$h1' ,h2='$h2' ,h3='$h3' ,h4='$h4' WHERE qr_number ='$qr_code' AND year='$year'");
}else{
	$response["success"] = "Error";

echo json_encode($response);
}



}

?>