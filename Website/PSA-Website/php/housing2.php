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
    <title>PSA Dashboard - Housing</title>
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
                            <li><a href="housing.php" style="background-color: #375595;"><i class="fa fa-edit"></i> HOUSING</a></li>
                            <li><a href="rdlty.php"><i class="fa fa-edit"></i> DEATHS IN THE LAST TWO YEARS</a></li>
                             <li><a href="unfinish_interview.php"><i class="fa fa-edit"></i> Unfinish Interviews</a></li>
                        </ul>
                    </li>
                   
                    <li class="nav nav-list nav-list-expandable">
                        <a><i class="fa fa-key"></i> Enumerator Settings <span class="caret"></span></a>
                        <ul class="nav navbar-nav">
                            <li><a href="manage_enumerators.php">Manage Enumerators</a></li>
                            <li><a href="add_user.php">Add New Enumerator</a></li>
                        </ul>
                    <li>
                </ul>

                
                
                <ul class="nav navbar-nav navbar-right navbar-user">
                    <li class="dropdown messages-dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-envelope"></i> Messages <span class="label label-danger">2</span> <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li class="dropdown-header">2 New Messages</li>
                            <li class="message-preview">
                                <a href="#">
                                    <span class="avatar"><i class="fa fa-bell"></i></span>
                                    <span class="message">Security alert</span>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li class="message-preview">
                                <a href="#">
                                    <span class="avatar"><i class="fa fa-bell"></i></span>
                                    <span class="message">Security alert</span>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li><a href="#">Go to Inbox <span class="badge">2</span></a></li>
                        </ul>
                    </li> 



            <li class="dropdown user-dropdown">
    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i><?php echo $test['fname'];?><b class="caret"></b></a>
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

        <div id="page-wrapper">
            <div class="row">
                <div class="col-md-8">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title"><i class="fa fa-line-chart"></i> Statistics <a class="pull-right glyphicon glyphicon-option-horizontal" href="#" style="text-decoration:none;"></a></h3>
                        </div>
                        <div class="panel-body">



<div class="container-fluid">
    <div class="row">
        <div class="col-6">
                       <div id="piechart1" style="width: 700px; height: 400px;"></div>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

   
    <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {

        var data = google.visualization.arrayToDataTable([
          ['Gender', 'Number'],
     



 <?php  
                         include("config.php");
$query = "SELECT b1, count(*) as number FROM house_cen_ques GROUP BY b1";  
$result = mysqli_query($con, $query);
                          while($row = mysqli_fetch_array($result))  
                          {  
                              echo "['".$row["b1"]."', ".$row["number"]."],";  
                          }  
                          ?> 


                         
        ]);

        var options = {
          title: 'Type of Building'
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart1'));

        chart.draw(data, options);
      }
    </script>
                          
        </div>
        <div class="col-6">
                 <div id="piechart2" style="width: 700px; height: 400px;"></div>
    <script type="text/javascript" src="/php/charts/loader.js"></script>

   
    <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {

        var data = google.visualization.arrayToDataTable([
          ['Gender', 'Number'],
     
                       <?php  
                         include("config.php");
$query = "SELECT b2, count(*) as number FROM house_cen_ques GROUP BY b2";  
$result = mysqli_query($con, $query);
                          while($row = mysqli_fetch_array($result))  
                          {  
                              echo "['".$row["b2"]."', ".$row["number"]."],";  
                          }  
                          ?> 
         
        ]);

        var options = {
          title: 'Construction Materials of the Roof of the Building'
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart2'));

        chart.draw(data, options);
      }
    </script>
        </div>
    </div>






<div class="row">

    <div class="col-6">
                 <div id="piechart3" style="width: 700px; height: 400px;"></div>
    <script type="text/javascript" src="/php/charts/loader.js"></script>

   
    <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {

        var data = google.visualization.arrayToDataTable([
          ['Gender', 'Number'],
     
                       <?php  
                         include("config.php");
$query = "SELECT b3, count(*) as number FROM house_cen_ques GROUP BY b3";  
$result = mysqli_query($con, $query);
                          while($row = mysqli_fetch_array($result))  
                          {  
                              echo "['".$row["b3"]."', ".$row["number"]."],";  
                          }  
                          ?> 
     
         
         
        ]);

        var options = {
          title: 'Construction Materials of the Outer Walls of the Building/Housing Unit'
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart3'));

        chart.draw(data, options);
      }
    </script>
        </div>


<div class="col-6">
                       <div id="piechart4" style="width: 700px; height: 400px;"></div>
    <script type="text/javascript" src="/php/charts/loader.js"></script>

   
    <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {

        var data = google.visualization.arrayToDataTable([
          ['Gender', 'Number'],
     
                         <?php  
                         include("config.php");
$query = "SELECT h1, count(*) as number FROM house_cen_ques GROUP BY h1";  
$result = mysqli_query($con, $query);
                          while($row = mysqli_fetch_array($result))  
                          {  
                              echo "['".$row["h1"]."', ".$row["number"]."],";  
                          }  
                          ?> 
     
                         
     
         
         
        ]);

        var options = {
          title: 'Fuel for Lighting'
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart4'));

        chart.draw(data, options);
      }
    </script>
                          
        </div>

</div>









<div class="row">
        
        <div class="col-6">
                 <div id="piechart5" style="width: 700px; height: 400px;"></div>
    <script type="text/javascript" src="/php/charts/loader.js"></script>

   
    <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {

        var data = google.visualization.arrayToDataTable([
          ['Gender', 'Number'],
     
                       <?php  
                         include("config.php");
$query = "SELECT h2, count(*) as number FROM house_cen_ques GROUP BY h2";  
$result = mysqli_query($con, $query);
                          while($row = mysqli_fetch_array($result))  
                          {  
                              echo "['".$row["h2"]."', ".$row["number"]."],";  
                          }  
                          ?> 
     
         
         
        ]);

        var options = {
          title: 'Source of Water Supply for Drinking'
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart5'));

        chart.draw(data, options);
      }
    </script>
        </div>

        <div class="col-6">
                 <div id="piechart6" style="width: 700px; height: 400px;"></div>
    <script type="text/javascript" src="/php/charts/loader.js"></script>

   
    <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {

        var data = google.visualization.arrayToDataTable([
          ['Gender', 'Number'],
       <?php  
                         include("config.php");
$query = "SELECT h3, count(*) as number FROM house_cen_ques GROUP BY h3";  
$result = mysqli_query($con, $query);
                          while($row = mysqli_fetch_array($result))  
                          {  
                              echo "['".$row["h3"]."', ".$row["number"]."],";  
                          }  
                          ?> 
     
     
         
         
        ]);

        var options = {
          title: 'Source of Water Supply for Cooking'
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart6'));

        chart.draw(data, options);
      }
    </script>
        </div>
    </div>
<div class="row">
  <div class="col-md-12 "><div id="piechart8" style="width: 700px; height: 400px;"></div>
    <script type="text/javascript" src="/php/charts/loader.js"></script>

   
    <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

     
     function drawChart() {

        var data = google.visualization.arrayToDataTable([
          ['Gender', 'Number'],
     
                          <?php  
                         include("config.php");
$query = "SELECT h4, count(*) as number FROM house_cen_ques GROUP BY h4";  
$result = mysqli_query($con, $query);
                          while($row = mysqli_fetch_array($result))  
                          {  
                              echo "['".$row["h4"]."', ".$row["number"]."],";  
                          }  
                          ?> 
     
                               
     
         
         
        ]);

        var options = {
          title: 'Tenure Status of the Housing Unit/Lot'
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart8'));

        chart.draw(data, options);
      }
    </script></div>
 
</div>


<div class="row">
  <div class="col-md-12 "><div id="piechart9" style="width: 700px; height: 400px;"></div>
    <script type="text/javascript" src="/php/charts/loader.js"></script>

   
    <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {

        var data = google.visualization.arrayToDataTable([
          ['Gender', 'Number'],
     
                          <?php  
                         include("config.php");
$query = "SELECT p10, count(*) as number FROM popu_cen_ques GROUP BY p10";  
$result = mysqli_query($con, $query);
                          while($row = mysqli_fetch_array($result))  
                          {  
                              echo "['".$row["p10"]."', ".$row["number"]."],";  
                          }  
                          ?> 
     
                          
                               
     
         
         
        ]);

        var options = {
          title: 'Attending School'
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart9'));

        chart.draw(data, options);
      }
    </script></div>
 
</div>



    
 
 
                        </div>
                    </div>
                 
                </div>
               
            </div>
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->


    <script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/theme.js"></script>
    
</body>
</html>










 