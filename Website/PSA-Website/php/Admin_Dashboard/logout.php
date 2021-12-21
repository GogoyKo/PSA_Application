<?php session_start();
	session_destroy();
	session_start();
	$_SESSION['logged']='false';
	header("Location:../index.html");
 ?>
