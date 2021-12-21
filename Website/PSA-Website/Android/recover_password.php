<?php



include("config.php");

$email=mysqli_real_escape_string($con,$_GET['email']);

$query="SELECT * FROM enum_user where email='{$email}'";



$result = mysqli_query($con, $query);

while(($row = mysqli_fetch_assoc($result)) == true){

	$data[]=$row;

}

echo json_encode($data);



?>

