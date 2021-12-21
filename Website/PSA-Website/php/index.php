<?php session_start();?>
<?php 
 if(!isset($_SESSION['login'])){
     header('location:../index.php');}
?>
<?php
   include("config.php");
                        $id =$_SESSION['id'];
                        $result = mysqli_query($con,"SELECT * FROM admin WHERE id ='$id'");
                        $test = mysqli_fetch_array($result);

 ?>
 


<?php
 if(isset($_POST['change_pass'])){
                 $current_password = mysqli_real_escape_string($con, $_POST['current_password']);
                 $current = md5($current_password);
                 $newpassword = mysqli_real_escape_string($con, $_POST['new_password']);
                 $repeat_password = mysqli_real_escape_string($con, $_POST['repeat_password']);
                
                 $npass = md5($newpassword);

                $query = mysqli_query($con,"SELECT * FROM admin WHERE password ='$current' AND id = '$id'");
                $result = mysqli_num_rows($query); 



if($result > 0){

 while($row = mysqli_fetch_assoc($query)) {
                    $u_pass = $row['password'];

                    if($current == $u_pass){

                        $query2 = "UPDATE admin SET password='$npass' WHERE id='$id'";
                         if ($con->query($query2) === TRUE) {
                          echo "<script>window.alert('Password Successfully Updated');</script>";
                          }else{
                             echo "Error updating record: " . $con->error;
                          }


                    }else{
                           echo "<script>window.alert('Invalid password');</script>";
                    }
                }

            }
            else{
     echo "<script>window.alert('Invalid Password');</script>";
}




}

  if(isset($_POST['update_me'])){
                            $firstname = $_POST['firstname'];
                            $lastname = $_POST['lastname'];
                            $email = $_POST['email'];
                         
                          
                            $sql = "UPDATE admin SET 
                                fname='$firstname',
                                lname='$lastname',
                                email='$email'
                                WHERE id='$id' ";
                            if ($con->query($sql) === TRUE) {
                                echo '<script>window.location.href="index.php"</script>';
                            } else {
                                echo "Error updating record: " . $con->error;
                            }
                        }




?>


<!DOCTYPE html>

<html lang="en">

<head>

    <meta charset="utf-8">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>PSA Dashboard</title>

<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css" />

   



    <link rel="stylesheet" type="text/css" href="css/theme.css" />

    <link rel="stylesheet" type="text/css" href="css/dashboard.css" />

	  <link rel="stylesheet" type="text/css" href="bootstrap.min.css" />



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

                <a class="navbar-brand" href="index.php" title="PB Dashboard">PSA Dashboard</a>

            </div>

            <div class="collapse navbar-collapse navbar-ex1-collapse">

                <ul class="nav navbar-nav side-nav">

                    <li class="active"><a href="index.php"><i class="glyphicon glyphicon-th"></i> Dashboard</a></li>

                    <li class="nav nav-list nav-list-expandable nav-list-expanded">

                      <a><i class="fa fa-user"></i> <i class="glyphicon glyphicon-list-alt"></i>Tables <span class="caret"></span></a>

                        <ul class="nav navbar-nav">

                            <li><a href="population_table.php"><i class="glyphicon glyphicon-stats"></i> Population Records</a></li>

                            <li><a href="housing.php"><i class="glyphicon glyphicon-home"></i> Housing Records</a></li>

                            <li><a href="rdlty.php"><i class=" glyphicon glyphicon-king"></i></i> Death Records</a></li>

                             <li><a href="unfinish_interview.php"><i class="glyphicon glyphicon-list-alt"></i> Unfinish Interviews</a></li>

                          

                            </li>

                        </ul>

                        

                    </li>

                   

                    <li class="nav nav-list nav-list-expandable">

                        <a><i class="fa fa-key"></i><i class="glyphicon glyphicon-th-list"></i> Enumerator Settings <span class="caret"></span></a>

                        <ul class="nav navbar-nav">

                            <li><a href="manage_enumerators.php" style="background-color: #375595;"><i class="glyphicon glyphicon-edit"></i>Manage Enumerators</a></li>

                            <li><a href="add_user.php"><i class="glyphicon glyphicon-plus"></i>Add New Enumerator</a></li>

                        </ul>

                    <li>

                        <li class="nav nav-list nav-list-expandable">

                      <li><a href="add_new_qr.php"><i class="glyphicon glyphicon-qrcode"></i> Add new QR number</a></li>
                        <li>

                </ul>



                

                

                <ul class="nav navbar-nav navbar-right navbar-user">

                   



            <li class="dropdown user-dropdown">

    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class='glyphicon glyphicon-user' aria-hidden='true'></span><i class="fa fa-user"></i><?php echo $test['fname'];?><b class="caret"></b></a>

                       <ul class="dropdown-menu">
                            <li><a href="#edit<?php echo $id;?>" data-toggle="modal"> <span class='glyphicon glyphicon-user' aria-hidden='true'></span> My Profile</a></li>
                           <li><a href="#changepass" data-toggle="modal"><span class='glyphicon glyphicon-edit' aria-hidden='true'></span> Change Password</a></li>

                         

                           <li class="divider"></li>

                           <li><a href="logout.php"><i class="fa fa-power-off"></i> Log Out</a></li>

                       </ul>

                   </li>

                </ul>

            </div>

        </nav>



        <div id="page-wrapper">

            <div class="row">

                <div class="col-md-8">

                    <div class="panel panel-primary">

                        <div class="panel-heading">

                            <h3 class="panel-title"><i class="fa fa-line-chart"></i> Home<a class="pull-right glyphicon glyphicon-option-horizontal" href="#" style="text-decoration:none;"></a></h3>

                        </div>


<div class="card text-center" style="max-width: 100%;">

<!-- Heading -->
<div class="card-body">
<h2 class="card-title">Census of Population and Housing Report</h2>
<h4 class="card-subtitle text-muted">PSA Application</h4>
</div>

<!-- Image -->
<img src="images/AboutApp.png" alt="PSA App Image">

<!-- Text Content -->
<div class="card-body" style="margin-top: 50px;">
<p class="card-text">Click the button below to download the application</p>
<form action="Application/PSA-Application.zip" method="post">
           <input class="btn btn-primary btn-lg btn-block" type="submit" name="submit" value="Download Now" />
       </form>

</div>

</div>


                        <div class="panel-body">

                            

                           
                        </div>

                    </div>



                  

                </div>



            </div>
<div id="changepass" class="modal fade" role="dialog">
                        <div class="modal-dialog">
                            <!-- Modal content-->
                            <div class="modal-content">
                                <form action="" method="post">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <h4 class="modal-title">Change Password</h4>
                                    </div>
                                    <div class="modal-body">
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
                                            <button type="submit" class="btn btn-primary" name="change_pass">Update</button>
                                        </div>
                                    </div>





                                </form>
                            </div>
                        </div>
                    </div>

<!-- Profile -->

<?php 
                    $sql = "SELECT * FROM admin";
                    
                    $result = $con->query($sql);
                    if ($result->num_rows > 0) {
                        // output data of each row
                     
                        while($row = $result->fetch_assoc()) {

                            $id = $row['id'];
                            $firstname = $row['fname'];
                            $lastname = $row['lname'];
                            $email = $row['email'];
                   }
               }

                           

                    ?>


                    <div id="edit<?php echo $id; ?>" class="modal fade" role="dialog">
        <div class="modal-dialog modal-lg">
            <!-- Modal content-->
            <div class="modal-content">
                <form method="post" class="form-horizontal" role="form">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">My profile</h4>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="item_name">Firstname:</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="item_name" name="firstname" value="<?php echo $firstname; ?>" placeholder="firstname" autocomplete="off" autofocus required> </div>
                                <label class="control-label col-sm-2" for="item_name">Lastname:</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="item_name" name="lastname" value="<?php echo $lastname; ?>" placeholder="lastname" autocomplete="off" autofocus required> </div>
                            
                        </div>

                         <div class="form-group">
                                <label class="control-label col-sm-2" for="item_code">Email:</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="item_code" name="email" value="<?php echo $email; ?>" placeholder="email" autocomplete="off" required> </div>
                                
                         </div>
                     
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-primary" name="update_me"><span class="glyphicon glyphicon-edit"></span> Update</button>
                        <button type="button" class="btn btn-warning" data-dismiss="modal"><span class="glyphicon glyphicon-remove-circle"></span> Cancel</button>
                    </div>
                </form>
            </div>
        </div>
    </div>




            <!-- /.row -->

        </div>

        <!-- /#page-wrapper -->

    </div>

    <!-- /#wrapper -->



    <script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>

    <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>

    <script type="text/javascript" src="js/theme.js"></script>

    

</body>

</html>

