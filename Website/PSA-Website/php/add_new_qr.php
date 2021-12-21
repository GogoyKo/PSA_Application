<?php session_start(); ?>

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

    <title>PSA Dashboard - Add new QR number</title>

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

                        

                        <ul class="nav navbar-nav">

                             <li><a href="add_new_qr.php"><i class="glyphicon glyphicon-qrcode"></i> Add new QR number</a></li>

                        </ul>

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



<div class="well well-lg">

    <h1>Add new QR number</h1>



<?php



include("config.php");

if($_SERVER["REQUEST_METHOD"] == "POST"){

                        $newQR = mysqli_real_escape_string($con, $_POST['newQR']);

                        $query = mysqli_query($con,"SELECT * FROM qr_codes WHERE qr_code_number ='$newQR'");

$result = mysqli_num_rows($query);

//$row = mysqli_fetch_array($query);      

if($result >0 ){

echo "qr already inserted";

}else{

    $sql = mysqli_query($con, "INSERT INTO qr_codes (qr_code_number, status) VALUES ('$newQR', '0')");

    echo "QR inserted";

}
}

?>
<form method="POST">

  <div class="form-row align-items-center">

    <div class="col-sm-3 my-1">

      <label class="sr-only" for="inlineFormInputName">Name</label>

      <input type="text" name="newQR" class="form-control" id="inlineFormInputName" placeholder="Insert new QR number" required="">

    </div>

    <div class="col-auto my-1">

      <button type="submit" class="btn btn-primary">Add QR number</button>

    </div>

  </div>

</form>



</div>



    </div>

    <!-- /#wrapper -->



    <script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>

    <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>

    <script type="text/javascript" src="js/theme.js"></script>

    

</body>

</html>

