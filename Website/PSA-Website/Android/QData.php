<?php

include("config.php");
$qr_number=$_GET['qr_number'];
$query="SELECT * FROM qr_codes where qr_code_number='{$qr_number}'";

$result = mysqli_query($con, $query);
while(($row = mysqli_fetch_assoc($result)) == true){
	$data[]=$row;
}
echo json_encode($data);

?>
	