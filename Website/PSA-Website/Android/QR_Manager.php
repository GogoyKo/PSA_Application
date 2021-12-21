<?php

include("config.php");

$qr_code_number = mysqli_real_escape_string($con,$_POST["qr_code_number"]);

$statement = mysqli_prepare($con, "UPDATE qr_codes SET status='1' WHERE qr_code_number='$qr_code_number'");
mysqli_stmt_execute($statement);

$response = array();
$response["success"] = "Successfully Save";
echo json_encode($response);


?>