<?php session_start();?>
<?php
include('config.php');


if($_SERVER["REQUEST_METHOD"] == "POST"){

$username = mysqli_real_escape_string($con, $_POST['username']);
$password = mysqli_real_escape_string($con, $_POST['pass']);
$psw = md5($password);

$sql = "SELECT * FROM admin WHERE email = '$username' AND password = '$psw'";
$query = mysqli_query($con, $sql);


$result = mysqli_num_rows($query);
$row = mysqli_fetch_array($query);		
if($result >0 ){

		//session_register('is');
					$_SESSION['login']    = TRUE;
					$_SESSION['username'] = $_POST['username'];									
					$_SESSION['id']=$row['id'];
					$_SESSION['logged']="true";
					$session = "1";	

header("Location:index.php");

}else{
	echo "error";
//	header("location: ../");
}

}


?>