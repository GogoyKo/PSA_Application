<?php



include("config.php");



if($con){

	$qr_code = mysqli_real_escape_string($con,$_POST['qr_number']);

	

	$enumerator_id = mysqli_real_escape_string($con,$_POST['enumerator_id']);
	$geo_iden_id = mysqli_real_escape_string($con,$_POST['geo_iden_id']);

	$enumname = mysqli_real_escape_string($con,$_POST['EnumName']);
	$team_supervisor = mysqli_real_escape_string($con,$_POST['team_supervisor']);
	$cen_area_super = mysqli_real_escape_string($con,$_POST['cen_area_super']);
	$co_rsso_po = mysqli_real_escape_string($con,$_POST['co_rsso_po']);
	$year = mysqli_real_escape_string($con, date("Y"));
	$date1 = mysqli_real_escape_string($con,$_POST['date_1']);
	$date2 = mysqli_real_escape_string($con,$_POST['date_2']);
	$date3 = mysqli_real_escape_string($con,$_POST['date_3']);
	$date4 = mysqli_real_escape_string($con,$_POST['date_4']);
	$image1 = $_POST['signature1'];
	$image2 = $_POST['signature2'];
	$image3 = $_POST['signature3'];
	$image4 = $_POST['signature4'];

	$link1 = "http://psasurveyapp.000webhostapp.com/Android/uploads/$enumname.png";

	$link2 = "http://psasurveyapp.000webhostapp.com/Android/uploads/$team_supervisor.png";

	$link3 = "http://psasurveyapp.000webhostapp.com/Android/uploads/$cen_area_super.png";

	$link4 = "http://psasurveyapp.000webhostapp.com/Android/uploads/$co_rsso_po.png";



	$sql = "insert into certification (enumerator_id,geo_iden_id,enumerator,signature1,date_1, team_supervisor,signature2,date_2,census_area_supervisor,signature3,date_3,co_rsso_po,signature4,date_4,qr_number, year) values ('$enumerator_id','$geo_iden_id','$enumname','$link1','$date1', '$team_supervisor','$link2','$date2','$cen_area_super','$link3','$date3','$co_rsso_po','$link4','$date4','$qr_code','$year')";





$sql2 = mysqli_query($con, "insert into certification_records (enumerator_id,geo_iden_id,enumerator,signature1,date_1, team_supervisor,signature2,date_2,census_area_supervisor,signature3,date_3,co_rsso_po,signature4,date_4, year,qr_number) values ('$enumerator_id','$geo_iden_id','$enumname','$link1','$date1', '$team_supervisor','$link2','$date2','$cen_area_super','$link3','$date3','$co_rsso_po','$link4','$date4', '$year', '$qr_code')");



	$upload_path1 = "uploads/$enumname.png";

	$upload_path2 = "uploads/$team_supervisor.png";

	$upload_path3 = "uploads/$cen_area_super.png";

	$upload_path4 = "uploads/$co_rsso_po.png";











	if(mysqli_query($con, $sql)){

		file_put_contents($upload_path1,base64_decode($image1));

		file_put_contents($upload_path2,base64_decode($image2));

		file_put_contents($upload_path3,base64_decode($image3));

		file_put_contents($upload_path4,base64_decode($image4));



		

		echo json_encode(array('response'=> 'Successfully Save'));



	}



	else{

		echo json_encode(array('response'=> 'Image Failed'));

	}



	

}else{

		echo json_encode(array('response'=> 'Image Failed'));

	}





?>