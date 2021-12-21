<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PSA Dashboard - Reset Password</title>
<link rel="stylesheet" type="text/css" href="php/bootstrap/css/bootstrap.min.css" />
   

    <link rel="stylesheet" type="text/css" href="php/css/theme.css" />
    <link rel="stylesheet" type="text/css" href="php/css/dashboard.css" />
      <link rel="stylesheet" type="text/css" href="php/bootstrap.min.css" />

</head>
<body>
    <div id="wrapper">
        <nav class="navbar navbar-default navbar-fixed-top" role="navigation">            
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="php/index.php" title="PB Dashboard">PSA Dashboard</a>
            </div>
            <div class="collapse navbar-collapse navbar-ex1-collapse">
                <ul class="nav navbar-nav side-nav">
                   
                   
                 
                </ul>

                
                
                <ul class="nav navbar-nav navbar-right navbar-user">
                    </ul>



            <li class="dropdown user-dropdown">
    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i><b class="caret"></b></a>
                       <ul class="dropdown-menu">
                           <li><a href="#"><i class="fa fa-user"></i> Profile</a></li>
                           <li><a href="#"><i class="fa fa-gear"></i> Settings</a></li>
                           <li class="divider"></li>
                           <li><a href="logout.php"><i class="fa fa-power-off"></i> Log Out</a></li>
                       </ul>
                   </li>
                </ul>
            </div>
        </nav>

<div class="well well-lg">
    <h1>Reset Password</h1>

<?php

include("php/config.php");

if($_SERVER["REQUEST_METHOD"] == "POST"){
                        $code = mysqli_real_escape_string($con, $_POST['code']);
                         $newpassword = mysqli_real_escape_string($con, $_POST['newpassword']);
                         $user_email = mysqli_real_escape_string($con, $_POST['user_email']);

                         $npass = md5($newpassword);
                        $query = mysqli_query($con,"SELECT * FROM enum_user WHERE password ='$code' AND email = '$user_email'");
       
$result = mysqli_num_rows($query);   
if($result >0 ){


$query2 = mysqli_query($con,"UPDATE enum_user SET password='$npass' WHERE email='$user_email'");

}else{
    echo "Invalid";
}
}
?>

<form method="POST">
  <div class="form-row align-items-center">
    <div class="col-sm-5 my-1">
      <label class="sr-only" for="inlineFormInputName">Name</label>
      <input type="text" name="code" class="form-control" id="inlineFormInputName" placeholder="Code">
    </div>
    <div class="col-sm-5 my-1">
      <label class="sr-only" for="inlineFormInputName">Username</label>
      <div class="input-group">
        <input type="text" name="newpassword" class="form-control" id="inlineFormInputName" placeholder="New Password">
      </div>
    </div>
    <div class="col-sm-5 my-1">
     <label class="sr-only" for="inlineFormInputName">UserEmail</label>
      <div class="input-group">
        <input type="text" name="user_email" class="form-control" id="inlineFormInputName" placeholder="Your Email">
      </div>
    </div>
    <div class="col-sm-2 my-1">
      <button type="submit" class="btn btn-primary">Reset Now</button>
    </div>
  </div>
</form>

</div>

    </div>
    <!-- /#wrapper -->

    <script type="text/javascript" src="php/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="php/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="php/js/theme.js"></script>
    
</body>
</html>
