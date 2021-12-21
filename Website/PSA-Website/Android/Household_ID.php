<?php

include("config.php");
$popu_cen_ques_id =mysqli_real_escape_string($con,$_GET['popu_cen_ques_id']);
$query="SELECT * FROM popu_cen_ques where popu_cen_ques_id ='{$popu_cen_ques_id}'";

$result = mysqli_query($con, $query);
while(($row = mysqli_fetch_assoc($result)) == true){
	$data[]=$row;
}
echo json_encode($data);

?>
