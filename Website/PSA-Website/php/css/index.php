<?php session_start();?>
<?php 
 if(!isset($_SESSION['login'])){
     header('location:../index.php');}
?>