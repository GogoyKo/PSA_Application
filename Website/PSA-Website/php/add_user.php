<?php session_start();?>



<?php 

   

include("config.php");

                        $id =$_SESSION['id'];

                        $result = mysqli_query($con,"SELECT * FROM admin WHERE id ='$id'");

                        $test = mysqli_fetch_array($result);

                       

                        

?>

<?php

    if($_SESSION['logged'] != 'true'){

     header('location:../index.html');}



 ?>

 





<!DOCTYPE html>

<html lang="en">

<head>

    <meta charset="utf-8">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>PSA Dashboard - Add User</title>

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

                        <a><i class="fa fa-user"></i> Tables <span class="caret"></span></a>

                        <ul class="nav navbar-nav">

                            <li><a href="population_table.php"><i class="fa fa-table"></i> POPULATION TABLE </a></li>

                            <li><a href="housing.php"><i class="fa fa-edit"></i> HOUSING</a></li>

                            <li><a href="rdlty.php"><i class="fa fa-edit"></i> DEATHS IN THE LAST TWO YEARS</a></li>

                             <li><a href="unfinish_interview.php"><i class="fa fa-edit"></i> Unfinish Interviews</a></li>

                           

                        </ul>

                    </li>

                   

                    <li class="nav nav-list nav-list-expandable">

                        <a><i class="fa fa-key"></i> Enumerator Settings <span class="caret"></span></a>

                        <ul class="nav navbar-nav">

                            <li><a href="manage_enumerators.php">Manage Enumerators</a></li>

                            <li><a href="add_user.php" style="background-color: #375595;">Add New Enumerator</a></li>

                        </ul>

                    <li>

                       <li class="nav nav-list nav-list-expandable">

                      <li><a href="add_new_qr.php"><i class="fa fa-edit"></i> Add new QR number</a></li>
                        </li>



 
                </ul>


                
                

                

                <ul class="nav navbar-nav navbar-right navbar-user">

                    

            <li class="dropdown user-dropdown">

    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i><?php echo $test['fname'];?><b class="caret"></b></a>

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

<div class="well well-lg">

                     <h1>Add New Enumerator Form</h1>

                    </div>

<div class="row">

                <div class="col-lg-6">

                    <div class="well">



                        <form class="form-horizontal" method="POST" action="add_user_code.php" enctype="multipart/form-data">

                            <fieldset>

                                <center><img id="output" src="" width="280" height="280" required></center>

                                <script>


                                        var loadFile = function(event) {

                                         var output = document.getElementById('output');

                                          output.src = URL.createObjectURL(event.target.files[0]);

                                                 };

                                </script>

                            <fieldset>

                                <center><legend>Enumerator Image</legend>

               <input type="file" accept="image/*" onchange="loadFile(event)" class="btn btn-primary" name="image" id="image"> 

         



                                </center>

                            </fieldset>

                               

                                 <div class="form-group">

                                    <label for="text" class="col-lg-2 control-label">Firstname</label>

                                    <div class="col-lg-10">

                                        <input type="text" class="form-control" id="text" name="firstname" placeholder="Firstname" required>

                                    </div>

                                </div>

                                 <div class="form-group">

                                    <label for="text" class="col-lg-2 control-label">Lastname</label>

                                    <div class="col-lg-10">

                                        <input type="text" class="form-control" name="lastname" id="text" placeholder="Lastname" required>

                                    </div>

                                </div>

                                  <div class="form-group">

                                    <label for="text" class="col-lg-2 control-label">Gender</label>

                                    <div class="col-lg-10">

                                       <select class="form-control" name="gender" id="select" >

                                            <option></option>

                                            <option>Male</option>

                                            <option>Female</option>

                                        </select>

                                    </div>

                                </div>

                                  <div class="form-group">

                                    <label for="text" class="col-lg-2 control-label">Username</label>

                                    <div class="col-lg-10">

                                        <input type="text" class="form-control" name = "username" id="text" placeholder="Username" required>

                                    </div>

                                </div>

                                <div class="form-group">

                                    <label for="text" class="col-lg-2 control-label">Age</label>

                                    <div class="col-lg-10">

                                       <select class="form-control" name="age" id="select">

                                           <option></option>

                                          <?php

                                         $no = 18;

                                         while ($no <= 90)

                                         {

            

                                             echo '<option>'.$no.'</option>';

                                    

                                               $no++;

                                         }

                                             

                                         ?>



                                         

                                        </select>

                                    </div>

                                </div>

                                <div class="form-group">

                                    <label for="text" class="col-lg-2 control-label">Mobile No.</label>

                                    <div class="col-lg-10">

                                        <input type="text" class="form-control" name = "mobile" id="text" placeholder="Mobile Number" required>

                                    </div>

                                </div>

                                <div class="form-group">

                                    <label for="inputEmail" class="col-lg-2 control-label">Email</label>

                                    <div class="col-lg-10">

                                        <input type="text" class="form-control" name="email" id="inputEmail" placeholder="Email" required>

                                    </div>

                                </div>

                                <div class="form-group">

                                    <label for="inputPassword" class="col-lg-2 control-label">Password</label>

                                    <div class="col-lg-10">

                                        <input type="password" class="form-control" name="pass" id="inputPassword" placeholder="Password" required>

                                    </div>

                                </div>

                               

                                <div class="form-group">

                                    <div class="col-lg-10 col-lg-offset-2">

                                       

                                        <input type="submit" value="submit" name="register" class="btn btn-primary">

                                       

                                        <a href ="add_user.php" class="cancel">Cancel</a>

                                    </div>

                                </div>

                            </fieldset>

                        </form>

                    </div>

                </div>

              

            </div>





    </div>

    <!-- /#wrapper -->

    <script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>

    <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>

    <script type="text/javascript" src="js/theme.js"></script>

    

</body>

</html>

