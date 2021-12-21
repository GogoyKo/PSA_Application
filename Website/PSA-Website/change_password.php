<?php
include('php/config.php');
 if(isset($_POST['change_pass'])){
                $username = mysqli_real_escape_string($con, $_POST['username']);
                $email = mysqli_real_escape_string($con, $_POST['email']);
                 $current_password = mysqli_real_escape_string($con, $_POST['current_password']);
                 $current = md5($current_password);
                 $newpassword = mysqli_real_escape_string($con, $_POST['new_password']);
                 $repeat_password = mysqli_real_escape_string($con, $_POST['repeat_password']);
            
                 $npass = md5($newpassword);
                $query = mysqli_query($con,"SELECT * FROM enum_user WHERE email ='$email' AND username = '$username'");
                $result = mysqli_num_rows($query); 
                if($newpassword == $repeat_password){
 if($result >0 ){
                while($row = mysqli_fetch_assoc($query)) {
                    $u_username = $row['username'];
                    $u_pass = $row['password'];
                    $u_email = $row['email'];
                         if($current != $u_pass){
                            echo "<script>window.alert('Invalid Password');</script>";
                              }else{
                                         $query2 = "UPDATE enum_user SET password='$npass' WHERE email='$email' AND username ='$username'";
 if ($con->query($query2) === TRUE) {
    echo "<script>window.alert('Password Successfully Updated');</script>";
     }
                 }
                }
          }else{echo "<script>window.alert('Invalid Credentials');</script>";}
      }else{ echo "<script>window.alert('Password not Matched!');</script>";}
}else{
     echo "<script>window.alert(Something went wrong);</script>";
}




?>
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








                </ul>

            </div>

        </nav>



<div class="well well-lg">

    <h1>Change Password</h1>

                                <form action="" method="post">
                                    
                                    <div class="modal-body">
                                        <div class="form-group">
                                            <label class="control-label col-sm-2" for="name">Username:</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" name="username" required placeholder="Username" autofocus autocomplete="off"> </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-2" for="name">Email:</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" name="email" required placeholder="Email" autofocus autocomplete="off"> </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-2" for="name">Current:</label>
                                            <div class="col-sm-10">
                                                <input type="password" class="form-control" name="current_password" required placeholder="Current Password" autofocus autocomplete="off"> </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-2" for="name">New:</label>
                                            <div class="col-sm-10">
                                                <input type="password" class="form-control" name="new_password" required placeholder="New Password" autocomplete="off"> </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-2" for="name">Repeat:</label>
                                            <div class="col-sm-10">
                                                <input type="password" class="form-control" name="repeat_password" required placeholder="Repeat Password" autocomplete="off"> </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="submit" class="btn btn-primary" name="change_pass">Change</button>
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

