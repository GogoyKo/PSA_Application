<?php

include('config.php');

if(isset($_FILES['image'])){

if($_SERVER["REQUEST_METHOD"] == "POST"){

$firstname = mysqli_real_escape_string($con,$_POST['firstname']);

$lastname = mysqli_real_escape_string($con,$_POST['lastname']);

$gender = mysqli_real_escape_string($con,$_POST['gender']);

$username = mysqli_real_escape_string($con,$_POST['username']);

$age = mysqli_real_escape_string($con,$_POST['age']);

$mobile = mysqli_real_escape_string($con,$_POST['mobile']);

$email = mysqli_real_escape_string($con,$_POST['email']);

$password = mysqli_real_escape_string($con,$_POST['pass']);



$pass = md5($password);



      $errors= array();

      $file_name = $_FILES['image']['name'];

      $link = "http://psasurveyapp.000webhostapp.com/php/images/$file_name";

    

      $file_tmp =$_FILES['image']['tmp_name'];

   
$variable = explode('.',$_FILES['image']['name']);
      $file_ext=strtolower(end($variable));

      

      $expensions= array("jpeg","jpg","png");

      

      if(in_array($file_ext,$expensions)=== false){

         $errors[]="extension not allowed, please choose a JPEG or PNG file.";

      }

    

      if(empty($errors)==true){

         $sql = "INSERT INTO enum_user (firstname, lastname, username, password, email, age,mobile,gender,image,status) VALUES ('$firstname','$lastname','$username','$pass','$email','$age','$mobile','$gender','$link',1)";

mysqli_query($con,$sql);

         move_uploaded_file($file_tmp,"images/".$file_name);

        header("location:add_user.php");

      }else{

         print_r($errors);

      }

}

}

?>

