<?php
include('config.php');

if($_SERVER['REQUEST_METHOD']=='POST'){

	$geo_iden_id = mysqli_real_escape_string($conn, $_POST['geo_iden_id']);
	$b1 = mysqli_real_escape_string($conn, $_POST['b1']);
	$b2 = mysqli_real_escape_string($conn, $_POST['b2']);
	$b3 = mysqli_real_escape_string($conn, $_POST['b3']);
	$h1 = mysqli_real_escape_string($conn, $_POST['h1']);
	$h2= mysqli_real_escape_string($conn, $_POST['h2']);
	$h3 = mysqli_real_escape_string($conn, $_POST['h3']);
	$h4 = mysqli_real_escape_string($conn, $_POST['h4']);

	$sql = "INSERT INTO housing_census_question (geo_iden_id,b1,b2,b3,h1,h2,h3,h4) VALUES ('$geo_iden_id','$b1','$b2','$b3','$h1','$h2','$h3','$h4')";
	$query = mysqli_query($conn,$sql);
	if($query){
echo "inserted";

	}else{
		echo "Failed";
	}
}
?>
<form action="" method="POST">
	<table>
		<tr>
			<td><label>Geo Identification</label></td>
			<td><select type="text" name="geo_iden_id">
					<?php
						include('config.php');
						$sql = "SELECT geo_iden_id FROM geo_identification";
						$query = mysqli_query($conn, $sql); 
						while($result = mysqli_fetch_array($query)){
						echo "<option>".$result['geo_iden_id']."</option>";
					}
					?>
			</select></td>
		</tr>		
		<tr>
			<td><label>B1</label></td>
			<td><input type="text" name="b1"></td>
		</tr>
		<tr>
			<td><label>B2</label></td>
			<td><input type="text" name="b2"></td>
		</tr>
		<tr>
			<td><label>B3</label></td>
			<td><input type="text" name="b3"></td>
		</tr>
		<tr>
			<td><label>H1</label></td>
			<td><input type="text" name="h1"></td>
		</tr>
		<tr>
			<td><label>H2</label></td>
			<td><input type="text" name="h2"></td>
		</tr>
		<tr>
			<td><label>H3</label></td>
			<td><input type="text" name="h3"></td>
		</tr>
		<tr>
			<td><label>H4</label></td>
			<td><input type="text" name="h4"></td>
		</tr>
		<tr>
			<td><input type="submit" value="Submit"></td>
		</tr>


	</table>


</form>