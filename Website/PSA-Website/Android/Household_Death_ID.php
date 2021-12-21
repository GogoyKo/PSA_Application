<?php

include("config.php");
$rdlyhm_id =mysqli_real_escape_string($con,$_GET['rdlyhm_id']);
$query="SELECT * FROM rdlyhm where rdlyhm_id ='{$rdlyhm_id}'";

$result = mysqli_query($con, $query);
while(($row = mysqli_fetch_assoc($result)) == true){
	$data[]=$row;
}
echo json_encode($data);

?>
