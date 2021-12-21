<?php



include("config.php");

$qr_number=mysqli_real_escape_string($con,$_GET['qr_number']);

$query="SELECT * FROM rdlyhm_records where qr_number='{$qr_number}'";



$result = mysqli_query($con, $query);

while(($row = mysqli_fetch_assoc($result)) == true){

	$data[]=$row;

}

echo json_encode($data);



?>

