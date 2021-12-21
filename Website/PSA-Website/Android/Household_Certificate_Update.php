<?php



include("config.php");



if($con){

if(isset($_POST['qr_number'])){

	$qr_code = mysqli_real_escape_string($con,$_POST['qr_number']);
	$enumerator_id = mysqli_real_escape_string($con,$_POST['enumerator_id']);
	$geo_iden_id = mysqli_real_escape_string($con,$_POST['geo_iden_id']);
$certification_id = mysqli_real_escape_string($con,$_POST["certification_id"]);
$enumerator = mysqli_real_escape_string($con,$_POST["EnumName"]);
$date_1 = mysqli_real_escape_string($con,$_POST["date_1"]);
$team_supervisor = mysqli_real_escape_string($con,$_POST["team_supervisor"]);
$date_2 = mysqli_real_escape_string($con,$_POST["date_2"]);	
$cen_area_super = mysqli_real_escape_string($con,$_POST["cen_area_super"]);
$date_3 = mysqli_real_escape_string($con,$_POST["date_3"]);
$co_rsso_po = mysqli_real_escape_string($con,$_POST["co_rsso_po"]);
$date_4 = mysqli_real_escape_string($con,$_POST["date_4"]);
$year = mysqli_real_escape_string($con, date("Y"));



	$image1 = $_POST['signature1'];

	$image2 = $_POST['signature2'];

	$image3 = $_POST['signature3'];

	$image4 = $_POST['signature4'];

	

	

	$link1 = "http://psasurveyapp.000webhostapp.com/Android/uploads/$enumerator.png";

	$link2 = "http://psasurveyapp.000webhostapp.com/Android/uploads/$team_supervisor.png";

	$link3 = "http://psasurveyapp.000webhostapp.com/Android/uploads/$cen_area_super.png";

	$link4 = "http://psasurveyapp.000webhostapp.com/Android/uploads/$co_rsso_po.png";



$sql3 = "SELECT * FROM certification_records WHERE qr_number = '$qr_code' and year = '$year'";
             $query3 = mysqli_query($con, $sql3);
             $count3 = mysqli_num_rows($query3);

$sql = "UPDATE certification SET enumerator_id = '$enumerator_id', enumerator='$enumerator' ,signature1 = '$link1',date_1='$date_1' ,team_supervisor='$team_supervisor' ,signature2='$link2' ,date_2='$date_2' ,census_area_supervisor='$cen_area_super' ,signature3='$link3',date_3='$date_3',co_rsso_po='$co_rsso_po',signature4='$link4',date_4='$date_4',year = '$year' WHERE certification_id='$certification_id'";



if($count3 == 0){
					$sql2 = mysqli_query($con, "insert into certification_records (enumerator_id,geo_iden_id,enumerator,signature1,date_1, team_supervisor,signature2,date_2,census_area_supervisor,signature3,date_3,co_rsso_po,signature4,date_4, year, qr_number) values ('$enumerator_id','$geo_iden_id','$enumerator','$link1','$date_1', '$team_supervisor','$link2','$date_2','$cen_area_super','$link3','$date_3','$co_rsso_po','$link4','$date_4','$year', '$qr_code')");


						$upload_path1 = "uploads/$enumerator.png";
						$upload_path2 = "uploads/$team_supervisor.png";
						$upload_path3 = "uploads/$cen_area_super.png";
						$upload_path4 = "uploads/$co_rsso_po.png";
						echo json_encode(array('success'=> 'Successfully Save'));
            				 }
             					else if($count3 == 1){

									$sql2 = mysqli_query($con, "UPDATE certification_records SET enumerator_id = '$enumerator_id', enumerator='$enumerator' ,signature1 = '$link1',date_1='$date_1' ,team_supervisor='$team_supervisor' ,signature2='$link2' ,date_2='$date_2' ,census_area_supervisor='$cen_area_super' ,signature3='$link3',date_3='$date_3',co_rsso_po='$co_rsso_po',signature4='$link4',date_4='$date_4' WHERE qr_number ='$qr_code' AND year = '$year'");
	$upload_path1 = "uploads/$enumerator.png";
						$upload_path2 = "uploads/$team_supervisor.png";
						$upload_path3 = "uploads/$cen_area_super.png";
						$upload_path4 = "uploads/$co_rsso_po.png";
										echo json_encode(array('success'=> 'Successfully Save'));

       									      }else{
													$response["success"] = "Error";

															echo json_encode($response);

													}

if(mysqli_query($con, $sql)){

		file_put_contents($upload_path1,base64_decode($image1));

		file_put_contents($upload_path2,base64_decode($image2));

		file_put_contents($upload_path3,base64_decode($image3));

		file_put_contents($upload_path4,base64_decode($image4));



$response = array();

$response["success"] = "Update successfully";

echo json_encode($response);
	


	}else{

		echo json_encode(array('success'=> 'Image Failed'));

		}

	}
}


else{

		echo json_encode(array('response'=> 'Image Failed'));

	}





?>





























