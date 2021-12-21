<?php 
    session_start();
	session_destroy();
	$_SESSION['login']='false';
	header("Location:../index.php");
 ?>