<?php



include("config.php");

if(isset($_POST['qr_number'])){


$qr_code = mysqli_real_escape_string($con,$_POST['qr_number']);


$enumerator_id = mysqli_real_escape_string($con,$_POST['enumerator_id']);
	

$geo_iden_id = mysqli_real_escape_string($con,$_POST["geo_iden_id"]);





$province = mysqli_real_escape_string($con,$_POST["province"]);

$province_num = mysqli_real_escape_string($con,$_POST["province_num"]);

$city = mysqli_real_escape_string($con,$_POST["city"]);

$city_num = mysqli_real_escape_string($con,$_POST["city_num"]);

$brgy = mysqli_real_escape_string($con,$_POST["brgy"]);

$brgy_num = mysqli_real_escape_string($con,$_POST["brgy_num"]);	

$EAN = mysqli_real_escape_string($con,$_POST["EAN"]);

$BSN = mysqli_real_escape_string($con,$_POST["BSN"]);



$HUSN = mysqli_real_escape_string($con,$_POST["HUSN"]);

$HSN = mysqli_real_escape_string($con,$_POST["HSN"]);

$LNTR = mysqli_real_escape_string($con,$_POST["LNTR"]);

$Lname = mysqli_real_escape_string($con,$_POST["Lname"]);

$Fname = mysqli_real_escape_string($con,$_POST["Fname"]);

$Address = mysqli_real_escape_string($con,$_POST["Address"]);



$year = mysqli_real_escape_string($con, date("Y"));







$statement = mysqli_prepare($con, "UPDATE geo_identification SET enumerator_id = '$enumerator_id',province='$province' ,province_num = '$province_num' ,city='$city' ,city_num='$city_num' ,brgy='$brgy' ,brgy_num='$brgy_num' ,EAN='$EAN',BSN='$BSN' ,HUSN='$HUSN' ,HSN='$HSN' ,LNTR='$LNTR' ,Lname='$Lname' ,Fname='$Fname' ,Address='$Address', year = '$year' WHERE geo_iden_id='$geo_iden_id'");

mysqli_stmt_execute($statement);




$sql3 = "SELECT * FROM geo_identification_records WHERE qr_number = '$qr_code' AND year = '$year'";
             $query3 = mysqli_query($con, $sql3);
             $count3 = mysqli_num_rows($query3);

$response = array();

$response["success"] = "Update successfully";

echo json_encode($response);



if($count3 == 0){
	$sql2 = mysqli_query($con, "INSERT INTO geo_identification_records (enumerator_id,province,province_num,city,city_num,brgy,brgy_num,EAN,BSN,HUSN,HSN,LNTR,Lname,Fname,Address,year, qr_number) VALUES ('$enumerator_id','$province','$province_num','$city','$city_num','$brgy','$brgy_num','$EAN','$BSN','$HUSN','$HSN','$LNTR','$Lname','$Fname','$Address','$year','$qr_code')");

}else if($count3 == 1){
	$sql2 = mysqli_query($con, "UPDATE geo_identification_records SET enumerator_id = '$enumerator_id',province='$province' ,province_num = '$province_num' ,city='$city' ,city_num='$city_num' ,brgy='$brgy' ,brgy_num='$brgy_num' ,EAN='$EAN',BSN='$BSN' ,HUSN='$HUSN' ,HSN='$HSN' ,LNTR='$LNTR' ,Lname='$Lname' ,Fname='$Fname' ,Address='$Address' WHERE qr_number ='$qr_code' AND year = '$year'");

}else{
	$response["success"] = "Error";

echo json_encode($response);
}








}

?>