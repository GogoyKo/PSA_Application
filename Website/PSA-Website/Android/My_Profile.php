<?php

include("config.php");
$enumerator_id=mysqli_real_escape_string($con,$_GET['enumerator_id']);
$query="SELECT * FROM enum_user where enumerator_id='{$enumerator_id}'";

$result = mysqli_query($con, $query);
while(($row = mysqli_fetch_assoc($result)) == true){
	$data[]=$row;
}
echo json_encode($data);

?>
