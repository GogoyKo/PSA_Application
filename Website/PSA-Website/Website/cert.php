<?php
include('config.php');

if($_SERVER['REQUEST_METHOD']=='POST'){

	$enumerator = mysqli_real_escape_string($conn, $_POST['enumerator']);
	$date_accom = mysqli_real_escape_string($conn, $_POST['date_accom']);
	$team_supervisor = mysqli_real_escape_string($conn, $_POST['team_supervisor']);
	$date_rev_1 = mysqli_real_escape_string($conn, $_POST['date_rev_1']);
	$census_area_supervisor = mysqli_real_escape_string($conn, $_POST['census_area_supervisor']);
	$date_rev_2 = mysqli_real_escape_string($conn, $_POST['date_rev_2']);
	$co_rsso_po = mysqli_real_escape_string($conn, $_POST['co_rsso_po']);
	$date_rev_3 = mysqli_real_escape_string($conn, $_POST['date_rev_3']);

	$sql = "INSERT INTO certification(enumerator,date_accom,team_supervisor,date_rev_1,census_area_supervisor,date_rev_2,co_rsso_po,date_rev_3) VALUES ('$enumerator','$date_accom','$team_supervisor','$date_rev_1','$census_area_supervisor','$date_rev_2','$co_rsso_po','$date_rev_3')";
	$query = mysqli_query($conn,$sql);

	if($query){
		echo "data inserted";
		$id = mysqli_insert_id($conn);

		$my_id = $id;

		
		echo $my_id;
	}else{
		echo "failed";
	}
}
?>

<form action="geo_identification.php" method="POST">

		<input type="submit" value = "<?php $id;?>" name="gogoy">

		</form>
