<?php



include("config.php");


if(isset($_POST['qr_number'])){
$qr_code = mysqli_real_escape_string($con,$_POST['qr_number']);

$enumerator_id = mysqli_real_escape_string($con,$_POST['enumerator_id']);
	$geo_iden_id = mysqli_real_escape_string($con,$_POST['geo_iden_id']);

$death_num = mysqli_real_escape_string($con,$_POST['death_num']);


$rdlyhm_id = mysqli_real_escape_string($con,$_POST["rdlyhm_id"]);

$persons_id = mysqli_real_escape_string($con,$_POST["persons_id"]);



$d1 = mysqli_real_escape_string($con,$_POST["d1"]);

$d2 = mysqli_real_escape_string($con,$_POST["d2"]);

$lastname = mysqli_real_escape_string($con,$_POST["lastname"]);

$firstname = mysqli_real_escape_string($con,$_POST["firstname"]);

$gender = mysqli_real_escape_string($con,$_POST["gender"]);

$age_at_death = mysqli_real_escape_string($con,$_POST["age_at_death"]);	

$death_reg_d6 = mysqli_real_escape_string($con,$_POST["death_reg_d6"]);

$death_reg_d7 = mysqli_real_escape_string($con,$_POST["death_reg_d7"]);



$year = mysqli_real_escape_string($con, date("Y"));


$sql3 = "SELECT * FROM rdlyhm_records WHERE death_num = '$death_num' AND year = '$year'";
             $query3 = mysqli_query($con, $sql3);
             $count3 = mysqli_num_rows($query3);

if($count3 == 0){
$sql2 = mysqli_query($con, "INSERT INTO rdlyhm_records (enumerator_id,geo_iden_id,death_num,d1,d2,lastname,firstname,gender,age_at_death,death_reg_d6,death_reg_d7,year, qr_number) VALUES ('$enumerator_id','$geo_iden_id','$death_num','$d1','$d2','$lastname','$firstname','$gender','$age_at_death','$death_reg_d6','$death_reg_d7','$year','$qr_code')");

}else if($count3 == 1){

$sql2 = mysqli_query($con, "UPDATE rdlyhm_records SET enumerator_id = '$enumerator_id',d1='$d1' ,d2 = '$d2',lastname='$lastname' ,firstname='$firstname' ,gender='$gender' ,age_at_death='$age_at_death' ,death_reg_d6='$death_reg_d6' ,death_reg_d7='$death_reg_d7', year = '$year' WHERE death_num='$death_num'");
}else{
	$response["success"] = "Error";

echo json_encode($response);
}




$statement = mysqli_prepare($con, "UPDATE rdlyhm SET enumerator_id = '$enumerator_id',d1='$d1' ,d2 = '$d2',lastname='$lastname' ,firstname='$firstname' ,gender='$gender' ,age_at_death='$age_at_death' ,death_reg_d6='$death_reg_d6' ,death_reg_d7='$death_reg_d7', year = '$year' WHERE rdlyhm_id='$rdlyhm_id'");

mysqli_stmt_execute($statement);



$response = array();

$response["success"] = "Update successfully";

echo json_encode($response);


}


?>