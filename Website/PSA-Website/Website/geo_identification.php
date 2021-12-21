<?php
include('config.php');

if($_SERVER['REQUEST_METHOD']== 'POST'){
$certification_id = mysqli_real_escape_string($conn,$_POST['certification_id']);
$gogoy = $_POST['gogoy'];
$booklet1 = mysqli_real_escape_string($conn,$_POST['booklet1']);
$booklet2 = mysqli_real_escape_string($conn,$_POST['booklet2']);
$province = mysqli_real_escape_string($conn,$_POST['province']);
$city = mysqli_real_escape_string($conn,$_POST['city']);
$brgy = mysqli_real_escape_string($conn,$_POST['brgy']);
$enum_erea_num = mysqli_real_escape_string($conn,$_POST['enum_erea_num']);
$building_serial_num = mysqli_real_escape_string($conn,$_POST['building_serial_num']);
$house_unit_serial_num = mysqli_real_escape_string($conn,$_POST['house_unit_serial_num']);
$household_serial_num = mysqli_real_escape_string($conn,$_POST['household_serial_num']);
$line_num_respondent = mysqli_real_escape_string($conn,$_POST['line_num_respondent']);
$household_head_lname = mysqli_real_escape_string($conn,$_POST['household_head_lname']);
$household_head_fname = mysqli_real_escape_string($conn,$_POST['household_head_fname']);
$address = mysqli_real_escape_string($conn,$_POST['address']);
	
$sql = "INSERT INTO geo_identification (certification_id,booklet_1,booklet_2,province,city,brgy,EAN,BSN,HUSN,HSN,LNTR,Lname,Fname,Address) VALUES ('$certification_id','$booklet1','$booklet2','$province','$city','$brgy','$enum_erea_num','$building_serial_num','$house_unit_serial_num','$household_serial_num','$line_num_respondent','$household_head_lname','$household_head_fname','$address')";

$query = mysqli_query($conn, $sql);
if($query){
 

}else{

	echo "Inserted Failed";
}


}
?>
<form action="" method="POST">
<table>
	<tr>
	<td><label>Certification</label></td>
	<td><input type="text" name="certification_id" value=<?php $gogoy;?>></td>
	</tr>
	<tr>
	<td><label>Booklet1</label></td>
	<td><input type="text" name="booklet1"></td>
	</tr>
	<tr>
	<td><label>Booklet2</label></td>
	<td><input type="text" name="booklet2"></td>
	</tr>
	<tr>
	<td><label>Provice</label></td>
	<td><input type="text" name="province"></td>
	<tr>
	<td><label>City</label></td>
	<td><input type="text" name="city"></td>
	</tr>
	<tr>
	<td><label>Barangay</label></td>
	<td><input type="text" name="brgy"></td>
	</tr>
	<tr>
	<td><label>Enumerator Area Number</label></td>
	<td><input type="text" name="enum_erea_num"></td>
	</tr>
	<tr>
	<td><label>Building Serial Number</label></td>
	<td><input type="text" name="building_serial_num"></td>
	</tr>
	<tr>
	<td><label>House Unit Serial Number</label></td>
	<td><input type="text" name="house_unit_serial_num"></td>
	</tr>
	<tr>
	<td><label>Household Serial Number</label></td>
	<td><input type="text" name="household_serial_num"></td>
	</tr>
	<tr>
	<td><label>Line Number Respondent</label></td>
	<td><input type="text" name="line_num_respondent"></td>
	</tr>
	<tr>
	<td><label>Household Head Lastname</label></td>
	<td><input type="text" name="household_head_lname"></td>
	</tr>
	<tr>
	<td><label>Household Head Firstname</label></td>
	<td><input type="text" name="household_head_fname"></td>
	</tr>
	<tr>
	<td><label>Address</label></td>
	<td><input type="text" name="address"></td>
	</tr>
	<tr>
	<td><input type="submit" value="Submit" ></td>
	</tr>
	


</table>
</form>