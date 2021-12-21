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
 <?php
$year = mysqli_real_escape_string($con,date("Y"));

  $sql2 = "SELECT * FROM rdlyhm WHERE gender = 'Female' and year = '$year'";

             $query2 = mysqli_query($con, $sql2);
             $count2 = mysqli_num_rows($query2);
             $sql3 = "SELECT * FROM rdlyhm WHERE gender = 'Male' and year = '$year'";
             $query3 = mysqli_query($con, $sql3);
             $count3 = mysqli_num_rows($query3);

             $val1 = $year;

             $from = "rdlyhm";


 ?>



 


 



<!DOCTYPE html>

<html lang="en">

<head>

    <meta charset="utf-8">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>PSA Dashboard - Death in last two years</title>

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

                            <h3 class="panel-title"><i class="fa fa-line-chart"></i> Statistics <a class="pull-right glyphicon glyphicon-option-horizontal" href="#" style="text-decoration:none;"></a></h3>
                        </div>

 <div class="row">

  <div class="col-6">
                           
                            <form action="" method="POST">



                               <div class="form-group">
                              <label for="text" class="col-lg-1 control-label" style="font-size: 25px; margin-left: 2%;">Year</label>
                                    <div class="col-lg-5">
                                       <select class="form-control" name="years" id="select" >
                                     <option value="<?php echo $val1;?>">---Select---</option>
                                       <?php
                                   
$year_query = "SELECT year FROM rdlyhm_records GROUP BY year";
$result = mysqli_query($con, $year_query);
                          while($year_row = mysqli_fetch_array($result))  
                          {  
                               $value1 = $year_row['year'];

                               echo '<option value='.$value1.'>'.$value1.'</option>';
                          }  
?>
                                                 
 <?php         
                          if(isset($_POST['search_item'])){
                           $from = "rdlyhm_records";
                             $year = mysqli_real_escape_string($con,$_POST['years']);
                            $year_sql = "Select * from rdlyhm_records WHERE year = '$year'";
                           $result = mysqli_query($con, $year_sql);
                       $sql2 = "SELECT * FROM rdlyhm_records WHERE gender = 'Female' And year = '$year'";
                       $query2 = mysqli_query($con, $sql2);
                     $count2 = mysqli_num_rows($query2);
                      $sql3 = "SELECT * FROM rdlyhm_records WHERE gender = 'Male' And year = '$year'";
                       $query3 = mysqli_query($con, $sql3);
                     $count3 = mysqli_num_rows($query3);
                           $YR = mysqli_num_rows($result);
                             while ($row = mysqli_fetch_array($result)){
                              $val1 = $row['year'];
                             // $count2 = $row['lastname'];

                              //echo '<option value='.$val1.'>'.$val1.'</option>';
        }
             }
                 ?>

                                          
                                        </select>
                                    </div>
                                     <div class="col-lg-4">
  <button type="submit" class="btn btn-primary" name="search_item">Search</button>


                                      </div>

                                </div>

                               

                            </form>
</div>
</div>

                                    

                        <div class="panel-body">



                             <div id="piechart" style="width: 1000px; height: 300px;"></div>

    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>



   

    <script type="text/javascript">

      google.charts.load('current', {'packages':['corechart']});

      google.charts.setOnLoadCallback(drawChart);



      function drawChart() {



        var data = google.visualization.arrayToDataTable([

          ['Gender', 'Number'],

     

                          <?php  

                         include("config.php");

$query = "SELECT gender, count(*) as number FROM $from WHERE year = '$year' GROUP BY gender";  

$result = mysqli_query($con, $query);

                          while($row = mysqli_fetch_array($result))  

                          {  

                              echo "['".$row["gender"]."', ".$row["number"]."],";  

                          }  

                          ?> 

         

         

        ]);



        var options = {

          title: 'Gender Statistics'

        };



        var chart = new google.visualization.PieChart(document.getElementById('piechart'));



        chart.draw(data, options);

      }

    </script>

                            

                        </div>

                    </div>  

                </div>

            </div>

            <!-- /.row -->

        </div>



        <!-- /#page-wrapper -->

        <div class="col-lg-12">

                    <div class="page-header">

                        <h1>Death Records as of <?php echo $val1;?></h1>

                    </div>

                    <table class="table table-striped table-hover">

                        <thead>

                            <tr class="info">

                                

                                <th>Gender</th>

                                <th>Gender Count</th>

                            </tr>

                        </thead>

                        <tbody>

                            <tr>

                                <td>Male</td> 

                               <?php echo '<td>'.$count3.'</td>';?> 

                            </tr>

                             <tr>

                                <td>Female</td> 

                               <?php echo '<td>'.$count2.'</td>';?>
                              
                               



                            </tr>

                          

                        </tbody>

                    </table>  

                </div>

    </div>

    <!-- /#wrapper -->

    <!-- Tables -->

           





    <script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>

    <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>

    <script type="text/javascript" src="js/theme.js"></script>

    

</body>

</html>

