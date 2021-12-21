<?php

include('config.php');

if($_SERVER['REQUEST_METHOD']=='POST'){

$username = mysqli_real_escape_string($conn,$_POST['username']);
$password = mysqli_real_escape_string($conn,$_POST['password']);


$sql = "SELECT * FROM enum_user WHERE username = '$username' AND password = '$password'";
$query = mysqli_query($conn,$sql);
$result = mysqli_fetch_array($query);
if($result > 0){
	echo "Success";
}else{

echo "Failed";
}
}
?>

<form action="" method="POST">
	
	<label>USERNAME</label>
	<input type="text" name="username">
	<label>PASSWORD</label>
	<input type="text" name="password">
	<input type="submit" name="submit" value="Login">

</form>