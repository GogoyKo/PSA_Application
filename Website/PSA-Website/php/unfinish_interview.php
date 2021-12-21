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

    <title>PSA Dashboard - Population</title>

<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css" />

   

    <link rel="stylesheet" type="text/css" href="css/theme.css" />

    <link rel="stylesheet" type="text/css" href="css/dashboard.css" />

      





  <style type="text/css">

    

.page{

  margin-left: 5px;

  margin-right: : 5px;

}



  </style>



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

                            <h3 class="panel-title"><i class="fa fa-line-chart"></i> Interview Records <a class="pull-right glyphicon glyphicon-option-horizontal" href="#" style="text-decoration:none;"></a></h3>

                        </div>

                        <div class="panel-body">









                           <div class="row">

  <div class="col-md-8">

       <form method="POST">

<table class="table table-bordered">

        <tr>

            

            <th class="col-md-2">

                Result visit

            </th>

            <th class="col-md-5">

                

                

                <select id="results" name="results" class="form-control">

                    <option value="">---Select---</option>

                    <option value="Refused">REFUSED</option>

                    <option value="NO RESPONDENT">NO RESPONDENT</option>

                     <option value="Entire Household Is Absent For Entented Period Of Time">ENTIRE HOUSEHOLD IS ABSENT FOR EXTENDED PERIOD OF TIME</option>

                      <option value="Partly Completed">PARTLY COMPLETED</option>

                       <option value="Postponed">Postponed</option>

                         <option value="Completed">Completed</option>

                </select>

            </th>

            <th class="col-md-1">

             <button type="submit" name="submit" value="Search">Submit</button>  

            </th>

            

        </tr>

         <tr>

            <th>#</th>

             <th>Address</th>

            <th>Lastname</th>

            <th>Result visit</th>

        </tr>



    

    <?php

       

/*



$sql= "SELECT geo_identification.Address, geo_identification.Lname,interview_rec.result_visit, interview_rec.result_of_visit

FROM geo_identification, interview_rec

WHERE (interview_rec.qr_number = geo_identification.qr_number) and (interview_rec.result_visit like '%".$search."%') ORDER BY interview_rec.interview_rec_id";



*/





if(isset($_POST['submit'])){

   // $search = mysqli_real_escape_string($con,$_POST['results']);

    $search = mysqli_real_escape_string($con, $_POST['results']);



$sql= "SELECT geo_identification.Address, geo_identification.Lname,interview_rec.result_visit

FROM geo_identification, interview_rec

WHERE (interview_rec.geo_iden_id = geo_identification.geo_iden_id) AND (interview_rec.result_visit = '$search') ORDER BY interview_rec.interview_rec_id";



   

$query = mysqli_query($con, $sql);

        $no = 1;

        while ($row = mysqli_fetch_array($query))

        {

            echo '<tr class="info">

                    <td>'.$no.'</td>

                     <td>'.$row['Address'].'</td>

                     <td>'.$row['Lname'].'</td>  

                      <td>'.$row['result_visit'].'</td>

                     

                </tr>';

            $no++;

        }

}







       

            

        ?>





       

       

      

    </table>

     </form>

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

      perPage: 20,

      insertAfter: '.table',

      pageNumbers: true

    });

  });

</script>



                    </div>

                 

                </div>

               

            </div>

            <!-- /.row -->

        </div>

        <!-- /#page-wrapper -->

        

    </div>

    <!-- /#wrapper -->

    <!-- Tables -->

           





    <script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>

    <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>

    <script type="text/javascript" src="js/theme.js"></script>

    

</body>

</html>

