<?php

include("config.php");
$certification_id = mysqli_real_escape_string($con,$_POST["certification_id"]);
$geo_iden_id = mysqli_real_escape_string($con,$_POST["geo_iden_id"]);
$house_cen_ques_id = mysqli_real_escape_string($con,$_POST["house_cen_ques_id"]);
$interview_rec_id = mysqli_real_escape_string($con,$_POST["interview_rec_id"]);
$popu_cen_ques_id = mysqli_real_escape_string($con,$_POST["popu_cen_ques_id"]);
$rdlyhm_id = mysqli_real_escape_string($con,$_POST["rdlyhm_id"]);


$new_qr = mysqli_real_escape_string($con,$_POST["new_qr"]);
$qr_number = mysqli_real_escape_string($con,$_POST["qr_number"]);




$statement = mysqli_prepare($con, "UPDATE certification SET qr_number='$new_qr' WHERE qr_number='$qr_number'");
$statement2 = mysqli_prepare($con, "UPDATE geo_identification SET qr_number='$new_qr' WHERE qr_number='$qr_number'");
$statement3 = mysqli_prepare($con, "UPDATE house_cen_ques SET qr_number='$new_qr' WHERE qr_number='$qr_number'");
$statement4 = mysqli_prepare($con, "UPDATE interview_rec SET qr_number='$new_qr' WHERE qr_number='$qr_number'");
$statement5 = mysqli_prepare($con, "UPDATE popu_cen_ques SET qr_number='$new_qr' WHERE qr_number='$qr_number'");
$statement6 = mysqli_prepare($con, "UPDATE rdlyhm SET qr_number='$new_qr' WHERE qr_number='$qr_number'");



$statement7 = mysqli_prepare($con, "UPDATE certification_records SET qr_number='$new_qr' WHERE qr_number='$qr_number'");
$statement8 = mysqli_prepare($con, "UPDATE geo_identification_records SET qr_number='$new_qr' WHERE qr_number='$qr_number'");
$statement9 = mysqli_prepare($con, "UPDATE house_cen_ques_records SET qr_number='$new_qr' WHERE qr_number='$qr_number'");
$statement10 = mysqli_prepare($con, "UPDATE interview_rec_records SET qr_number='$new_qr' WHERE qr_number='$qr_number'");
$statement11 = mysqli_prepare($con, "UPDATE popu_cen_ques_records SET qr_number='$new_qr' WHERE qr_number='$qr_number'");
$statement12 = mysqli_prepare($con, "UPDATE rdlyhm_records SET qr_number='$new_qr' WHERE qr_number='$qr_number'");


mysqli_stmt_execute($statement);
mysqli_stmt_execute($statement2);
mysqli_stmt_execute($statement3);
mysqli_stmt_execute($statement4);
mysqli_stmt_execute($statement5);
mysqli_stmt_execute($statement6);

mysqli_stmt_execute($statement7);
mysqli_stmt_execute($statement8);
mysqli_stmt_execute($statement9);
mysqli_stmt_execute($statement10);
mysqli_stmt_execute($statement11);
mysqli_stmt_execute($statement12);



$response = array();
$response["success"] = "QR number successfully change";
echo json_encode($response);


?>