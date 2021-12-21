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

    <title>PSA Dashboard - Manage User</title>

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



<div class="well well-lg">

    <h1>Manage Enumerators Form</h1>
    <div class="col-lg-12">
                    <div class="page-header">
                        <h1>Users</h1>
                    </div>
                    <table class="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Firstname</th>
                                <th>Lastname</th>
                                <th>Email</th>
                                 <th>Mobile</th>
                                <th>Gender</th>
                                <th>Status</th>
                                <th>Action</th>
                            </tr>
                        </thead>
         <tbody>
                <?php 
                    $sql = "SELECT * FROM enum_user";
                    
                    $result = $con->query($sql);
                    if ($result->num_rows > 0) {
                        // output data of each row
                      $no = 0;
                        while($row = $result->fetch_assoc()) {

                            $id = $row['enumerator_id'];
                            $firstname = $row['firstname'];
                            $lastname = $row['lastname'];
                            $email = $row['email'];
                            $mobile = $row['mobile'];
                            $gender = $row['gender'];
                            $status = $row['status'];
                      $no++;
                            if($status == 0){
                                $alert = "<div class='alert alert-danger'>
                                <strong>$status</strong> No Stock
                                </div>";
                            }
                            if($status == 1){
                              $stat = "Active";
                            }else if($status == 0){
                              $stat = "Deactivate";
                            }

                           

                    ?>
                <tr>
                    <td>
                        <?php echo $no; ?>
                    </td>
                    <td>
                        <?php echo $firstname; ?>
                    </td>
                    <td>
                        <?php echo $lastname; ?>
                    </td>
                    <td>
                        <?php echo $email; ?>
                    </td>
                    <td>
                        <?php echo $mobile; ?>
                    </td>
                    <td>
                        <?php echo $gender; ?>
                    </td>
                     <td>
                        <?php echo $stat; ?>
                    </td>
                    <td>
                       
                       
                        <a href="#edit<?php echo $id;?>" data-toggle="modal">
                            <button type='button' class='btn btn-warning btn-sm'><span class='glyphicon glyphicon-edit' aria-hidden='true'></span></button>
                        </a>
                       
                    </td>
                   
                

                    <!--Edit Item Modal -->
                    <div id="edit<?php echo $id; ?>" class="modal fade" role="dialog">
                        <form method="post" class="form-horizontal" role="form">
                            <div class="modal-dialog modal-lg">
                                <!-- Modal content-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <h4 class="modal-title">Update User</h4>
                                    </div>
                                    <div class="modal-body">
                                        <input type="hidden" name="enumerator_id" value="<?php echo $id; ?>">
                                        <div class="form-group">
                                            <label class="control-label col-sm-2" for="item_name">Firstname:</label>
                                            <div class="col-sm-4">
                    <input type="text" class="form-control" id="item_name" name="firstname" value="<?php echo $firstname; ?>" placeholder="Firstname" required autofocus> </div>
                                            <label class="control-label col-sm-2" for="item_code">Lastname:</label>
                                            <div class="col-sm-4">
                                                <input type="text" class="form-control" id="item_code" name="lastname" value="<?php echo $lastname; ?>" placeholder="Lastname" required> </div>
                                        </div>
                                        <div class="form-group">
                                              <label class="control-label col-sm-2" for="item_name">Email:</label>
                                            <div class="col-sm-4">
                    <input type="text" class="form-control" id="item_name" name="email" value="<?php echo $email; ?>" placeholder="Email" required autofocus> </div>

                                            <label class="control-label col-sm-2" for="item_category">Mobile:</label>
                                            <div class="col-sm-4">
                                                <input type="text" class="form-control" id="item_category" name="mobile" value="<?php echo $mobile; ?>" placeholder="Mobile" required> </div>
                                        </div>



                                         <div class="form-group">
                                             <label class="control-label col-sm-2" for="item_name">Gender:</label>
                                            <div class="col-sm-4">
                                             <select class="form-control" name="gender" id="item_name" >
                                                    <option><?php echo $gender; ?></option>
                                                    <option>Male</option>
                                                    <option>Female</option>
                                            </select>
                                            </div>




                                           
                                        </div>
                                         <div class="form-group">
                                             <label class="control-label col-sm-2" for="item_name">Status:</label>
                                            <div class="col-sm-4">
                                             <select class="form-control" name="status" id="item_name" >
                                                    <option><?php echo $stat; ?></option>
                                                    <option>Activate</option>
                                                    <option>Deactivate</option>
                                            </select>
                                            </div>


                                        </div>



                                    </div>
                                    <div class="modal-footer">
                                        <button type="submit" class="btn btn-primary" name="update_item"><span class="glyphicon glyphicon-edit"></span> Update</button>
                                        <button type="button" class="btn btn-warning" data-dismiss="modal"><span class="glyphicon glyphicon-remove-circle"></span> Cancel</button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                    <!--Delete Modal -->
                    
                </tr>
                <?php
                        }
                        if(isset($_POST['change_pass'])){
                            $sql = "SELECT password FROM tbl_user WHERE username='$session_username'";
                            $result = $conn->query($sql);

                            if ($result->num_rows > 0) {
                                // output data of each row
                                while($row = $result->fetch_assoc()) {
                                    if($row['password'] != $current_password){
                                        echo "<script>window.alert('Invalid Password');</script>";
                                        $passwordErr = '<div class="alert alert-warning"><strong>Password!</strong> Invalid.</div>';
                                    } elseif($new_password != $repeat_password) {
                                        echo "<script>window.alert('Password Not Match!');</script>";
                                        $passwordErr = '<div class="alert alert-warning"><strong>Password!</strong> Not Match.</div>';
                                    } else{
                                        $sql = "UPDATE tbl_user SET password='$new_password' WHERE username='$session_username'";

                                        if ($conn->query($sql) === TRUE) {
                                            echo "<script>window.alert('Password Successfully Updated');</script>";
                                        } else {
                                            echo "Error updating record: " . $conn->error;
                                        }
                                    }    
                                }
                            } else {
                                $usernameErr = '<div class="alert alert-danger"><strong>Username</strong> Not Found.</div>';
                                $username = "";
                            }
                        }



                        //Update Items
                        if(isset($_POST['update_item'])){
                          $id = $_POST['enumerator_id'];
                            $firstname = $_POST['firstname'];
                            $lastname = $_POST['lastname'];
                            $mobile = $_POST['mobile'];
                            $gender = $_POST['gender'];
                              $email = $_POST['email'];
                              $status = $_POST['status'];

                              if($status == "Activate"){
                                $stat = 1;
                              }else if($status == "Deactivate"){
                                $stat = 0;
                              }

                            $sql = "UPDATE enum_user SET 
                                firstname='$firstname',
                                lastname='$lastname',
                                email='$email',
                                 gender='$gender',
                                mobile='$mobile',
                                 status='$stat'
                                WHERE enumerator_id='$id'";
                            if ($con->query($sql) === TRUE) {
                                echo '<script>window.location.href="manage_enumerators.php"</script>';
                            } else {
                                echo "Error updating record: " . $con->error;
                            }
                        }

                        if(isset($_POST['delete'])){
                            // sql to delete a record
                            $delete_id = $_POST['delete_id'];
                            $sql = "DELETE FROM tbl_items WHERE id='$delete_id' ";
                            if ($conn->query($sql) === TRUE) {
                                $sql = "DELETE FROM tbl_inventory WHERE id='$delete_id' ";
                                if ($conn->query($sql) === TRUE) {
                                    $sql = "DELETE FROM tbl_inventory WHERE id='$delete_id' ";
                                    echo '<script>window.location.href="inventory.php"</script>';
                                } else {
                                    echo "Error deleting record: " . $conn->error;
                                }
                            } else {
                                echo "Error deleting record: " . $conn->error;
                            }
                        }
                    }

                    //Add Item        
                    if(isset($_POST['add_item'])){
                        $item_name = $_POST['item_name'];
                        $item_code = $_POST['item_code'];
                        $item_category = $_POST['item_category'];
                        $item_description = $_POST['item_description'];
                        $sql = "INSERT INTO tbl_items (item_name,item_code,item_description,item_category,item_critical_lvl,item_date)VALUES ('$item_name','$item_code','$item_description','$item_category','$item_critical_lvl','$date')";
                        if ($conn->query($sql) === TRUE) {
                            $add_inventory_query = "INSERT INTO tbl_inventory(item_name,item_code,date,qty)VALUES ('$item_name','$item_code','$date','0')";

                            if ($conn->query($add_inventory_query) === TRUE) {
                                echo '<script>window.location.href="inventory.php"</script>';
                            } else {
                                echo "Error: " . $sql . "<br>" . $conn->error;
                            }
                        } else {
                            echo "Error: " . $sql . "<br>" . $conn->error;
                        }
                    }

                    if(isset($_POST['add_inventory'])){
                        $add_stocks_id = clean($_POST['add_stocks_id']);
                        $remarks = clean($_POST["remarks"]);
                        $quantity = clean($_POST['quantity']);
                        $sql = "INSERT INTO tbl_issuance(date,item_name,item_code,qty, in_out,            remarks)VALUES ('$date_time','$item_name','$item_code','$quantity','in','$remarks')";
                        if ($conn->query($sql) === TRUE) {
                            $add_inv = "UPDATE tbl_inventory SET qty=(qty + '$quantity') WHERE id='$add_stocks_id' ";
                            if ($conn->query($add_inv) === TRUE) {
                                echo '<script>window.location.href="inventory.php"</script>';
                            } else {
                                echo "Error updating record: " . $conn->error;
                            }
                        } else {
                            echo "Error: " . $sql . "<br>" . $conn->error;
                        }
                    }

                    if(isset($_POST['minus_inventory'])) {
                        $minus_stocks_id = clean($_POST['minus_stocks_id']);
                        $remarks = clean($_POST["remarks"]);
                        $quantity = clean($_POST['quantity']);
                        $sql = "INSERT INTO tbl_issuance(date,item_name,item_code,qty, sender_receiver,in_out,            remarks)VALUES ('$date_time','$item_name','$item_code','$quantity','$received_by','out','$remarks')";
                        if ($conn->query($sql) === TRUE) {
                            $add_inv = "UPDATE tbl_inventory SET qty=(qty - '$quantity') WHERE id='$minus_stocks_id' ";
                            if ($conn->query($add_inv) === TRUE) {
                                echo '<script>window.location.href="inventory.php"</script>';
                            } else {
                                echo "Error updating record: " . $conn->error;
                            }
                        } else {
                            echo "Error: " . $sql . "<br>" . $conn->error;
                        }
                    }
?>
            </tbody>

                    </table>

                </div>





  </div>

</div>

<script src="js/jquery.min.js"></script>

<script type="text/javascript" src="js/paginathing.js"></script>

<script type="text/javascript">

  jQuery(document).ready(function($){

    for (var i = 1; i <= 150; i++) {

      $('.list-group').append('<li class="list-group-item"> Item ' + i + '</li>');

    }



    $('.list-group').paginathing({

      perPage: 5,

      limitPagination: 9,

      containerClass: 'panel-footer',

      pageNumbers: true

    })



    $('.table tbody').paginathing({

      perPage: 10,

      insertAfter: '.table',

      pageNumbers: true

    });

  });

</script>

</div>

    <script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>

    <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>

    <script type="text/javascript" src="js/theme.js"></script>

    

</body>

</html>

