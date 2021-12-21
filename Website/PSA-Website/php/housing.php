<?php session_start();?>

<?php 



include("config.php");

 $year = mysqli_real_escape_string($con,date("Y"));

                        $id =$_SESSION['id'];

                        $result = mysqli_query($con,"SELECT * FROM admin WHERE id ='$id'");

                        $test = mysqli_fetch_array($result);

                       

                        

?>

<?php

    if($_SESSION['logged'] != 'true'){

     header('location:../index.html');}



 ?>





<?php



//////////////////////////////////////////////////////////





$query = mysqli_query($con,"SELECT * FROM house_cen_ques WHERE b1 = 'Duplex' And year = '$year'");

$count = mysqli_num_rows($query);



$query2 = mysqli_query($con,"SELECT * FROM house_cen_ques WHERE b1 = 'Single house' And year = '$year'");

$count2 = mysqli_num_rows($query2);



$query3 = mysqli_query($con,"SELECT * FROM house_cen_ques WHERE b1 = 'Multi-unit residential (3 or more units)' And year = '$year'");
$count3 = mysqli_num_rows($query3);



$query4 = mysqli_query($con,"SELECT * FROM house_cen_ques WHERE b1 = 'Commercial/industrial/agricultural(office, factory,and others)' And year = '$year'");
$count4 = mysqli_num_rows($query4);



$query5 = mysqli_query($con,"SELECT * FROM house_cen_ques WHERE b1 = 'Institutional living quarter (hotel,hospital,prison, and others)' And year = '$year'");

$count5 = mysqli_num_rows($query5);



$query6 = mysqli_query($con,"SELECT * FROM house_cen_ques WHERE b1 = 'Tent' And year = '$year'");

$count6 = mysqli_num_rows($query6);





//////////////////////////////////////////////////////////





$query7 = mysqli_query($con,"SELECT * FROM house_cen_ques WHERE b2 = 'Galvanized iron/aluminum' And year = '$year'");

$count7 = mysqli_num_rows($query7);



$query8 = mysqli_query($con,"SELECT * FROM house_cen_ques WHERE b2 = 'Tile/concrete/clay tile' And year = '$year'");

$count8 = mysqli_num_rows($query8);



$query9 = mysqli_query($con,"SELECT * FROM house_cen_ques WHERE b2 = 'Half galvanized iron and half concrete' And year = '$year'");

$count9 = mysqli_num_rows($query9);



$query10 = mysqli_query($con,"SELECT * FROM house_cen_ques WHERE b2 = 'Bamboo/cogon/nipa/anahaw' And year = '$year'");

$count10 = mysqli_num_rows($query10);



$query11 = mysqli_query($con,"SELECT * FROM house_cen_ques WHERE b2 = 'Asbestos' And year = '$year'");

$count11 = mysqli_num_rows($query11);



$query12 = mysqli_query($con,"SELECT * FROM house_cen_ques WHERE b2 = 'Makeshift/salvaged/improvised materials' And year = '$year'");

$count12 = mysqli_num_rows($query12);



$query13 = mysqli_query($con,"SELECT * FROM house_cen_ques WHERE b2 = 'Trapal' And year = '$year'");

$count13 = mysqli_num_rows($query13);





//////////////////////////////////////////////////////////





$query14 = mysqli_query($con,"SELECT * FROM house_cen_ques WHERE b3 = 'Concrete/brick/stone' And year = '$year'");

$count14 = mysqli_num_rows($query14);



$query15 = mysqli_query($con,"SELECT * FROM house_cen_ques WHERE b3 = 'Wood' And year = '$year'");

$count15 = mysqli_num_rows($query15);



$query16 = mysqli_query($con,"SELECT * FROM house_cen_ques WHERE b3 = 'Half concrete/brick/stone and half wood' And year = '$year'");

$count16 = mysqli_num_rows($query16);



$query17 = mysqli_query($con,"SELECT * FROM house_cen_ques WHERE b3 = 'Galvanized iron/aluminum' And year = '$year'");

$count17 = mysqli_num_rows($query17);



$query18 = mysqli_query($con,"SELECT * FROM house_cen_ques WHERE b3 = 'Bamboo/sawali cogon/nipa' And year = '$year'");

$count18 = mysqli_num_rows($query18);



$query19 = mysqli_query($con,"SELECT * FROM house_cen_ques WHERE b3 = 'Asbestos' And year = '$year'");

$count19 = mysqli_num_rows($query19);



$query20 = mysqli_query($con,"SELECT * FROM house_cen_ques WHERE b3 = 'Glass' And year = '$year'");

$count20 = mysqli_num_rows($query20);



$query21 = mysqli_query($con,"SELECT * FROM house_cen_ques WHERE b3 = 'Makeshift/salvaged/improvised materials' And year = '$year'");

$count21 = mysqli_num_rows($query21);



$query22 = mysqli_query($con,"SELECT * FROM house_cen_ques WHERE b3 = 'Trapal' And year = '$year'");

$count22 = mysqli_num_rows($query22);



$query23 = mysqli_query($con,"SELECT * FROM house_cen_ques WHERE b3 = 'No walls' And year = '$year'");

$count23 = mysqli_num_rows($query23);







//////////////////////////////////////////////////////////





$query24 = mysqli_query($con,"SELECT * FROM house_cen_ques WHERE h1 = 'Electricity' And year = '$year'");

$count24 = mysqli_num_rows($query24);



$query25 = mysqli_query($con,"SELECT * FROM house_cen_ques WHERE h1 = 'Kerosene(gaas)' And year = '$year'");

$count25 = mysqli_num_rows($query25);



$query26 = mysqli_query($con,"SELECT * FROM house_cen_ques WHERE h1 = 'Liquefied petroleum gas(LPG)' And year = '$year'");

$count26 = mysqli_num_rows($query26);



$query27 = mysqli_query($con,"SELECT * FROM house_cen_ques WHERE h1 = 'Oil (vegetable, animal, and others)' And year = '$year'");

$count27 = mysqli_num_rows($query27);



$query28 = mysqli_query($con,"SELECT * FROM house_cen_ques WHERE h1 = 'Solar panel' And year = '$year'");

$count28 = mysqli_num_rows($query28);



$query29 = mysqli_query($con,"SELECT * FROM house_cen_ques WHERE h1 = 'Solar lamp' And year = '$year'");

$count29 = mysqli_num_rows($query29);



$query30 = mysqli_query($con,"SELECT * FROM house_cen_ques WHERE h1 = 'None' And year = '$year'");

$count30 = mysqli_num_rows($query30);





//////////////////////////////////////////////////////////







$query31 = mysqli_query($con,"SELECT * FROM house_cen_ques WHERE h2 = 'Own use faucet, community water system' And year = '$year'");

$count31 = mysqli_num_rows($query31);



$query32 = mysqli_query($con,"SELECT * FROM house_cen_ques WHERE h2 = 'Shared faucet, community water system' And year = '$year'");

$count32 = mysqli_num_rows($query32);



$query33 = mysqli_query($con,"SELECT * FROM house_cen_ques WHERE h2 = 'Own use, tubed/piped deep well' And year = '$year'");

$count33 = mysqli_num_rows($query33);



$query34 = mysqli_query($con,"SELECT * FROM house_cen_ques WHERE h2 = 'Shared tubed/piped deep well' And year = '$year'");

$count34 = mysqli_num_rows($query34);



$query35 = mysqli_query($con,"SELECT * FROM house_cen_ques WHERE h2 = 'Tubed/piped shallow wel' And year = '$year'");

$count35 = mysqli_num_rows($query35);



$query36 = mysqli_query($con,"SELECT * FROM house_cen_ques WHERE h2 = 'Dug well' And year = '$year'");

$count36 = mysqli_num_rows($query36);



$query37 = mysqli_query($con,"SELECT * FROM house_cen_ques WHERE h2 = 'Protected spring' And year = '$year'");

$count37 = mysqli_num_rows($query37);



$query38 = mysqli_query($con,"SELECT * FROM house_cen_ques WHERE h2 = 'Unprotected spring' And year = '$year'");

$count38 = mysqli_num_rows($query38);



$query39 = mysqli_query($con,"SELECT * FROM house_cen_ques WHERE h2 = 'Lake, river, rain, and lake' And year = '$year'");

$count39 = mysqli_num_rows($query39);



$query40 = mysqli_query($con,"SELECT * FROM house_cen_ques WHERE h2 = 'Peddler' And year = '$year'");

$count40 = mysqli_num_rows($query40);



$query41 = mysqli_query($con,"SELECT * FROM house_cen_ques WHERE h2 = 'Bottled water' And year = '$year'");

$count41 = mysqli_num_rows($query41);





//////////////////////////////////////////////////////////





$query42 = mysqli_query($con,"SELECT * FROM house_cen_ques WHERE h3 = 'Own use faucet, community water system' And year = '$year'");

$count42 = mysqli_num_rows($query42);



$query43 = mysqli_query($con,"SELECT * FROM house_cen_ques WHERE h3 = 'Shared faucet, community water system' And year = '$year'");

$count43 = mysqli_num_rows($query43);



$query44 = mysqli_query($con,"SELECT * FROM house_cen_ques WHERE h3 = 'Own use, tubed/piped deep well' And year = '$year'");

$count44 = mysqli_num_rows($query44);



$query45 = mysqli_query($con,"SELECT * FROM house_cen_ques WHERE h3 = 'Shared tubed/piped deep well' And year = '$year'");

$count45 = mysqli_num_rows($query45);



$query46 = mysqli_query($con,"SELECT * FROM house_cen_ques WHERE h3 = 'Tubed/piped shallow well' And year = '$year'");

$count46 = mysqli_num_rows($query46);



$query47 = mysqli_query($con,"SELECT * FROM house_cen_ques WHERE h3 = 'Dug well' And year = '$year'");

$count47 = mysqli_num_rows($query47);



$query48 = mysqli_query($con,"SELECT * FROM house_cen_ques WHERE h3 = 'Protected spring' And year = '$year'");

$count48 = mysqli_num_rows($query48);



$query49 = mysqli_query($con,"SELECT * FROM house_cen_ques WHERE h3 = 'Unprotected spring' And year = '$year'");

$count49 = mysqli_num_rows($query49);



$query50 = mysqli_query($con,"SELECT * FROM house_cen_ques WHERE h3 = 'Lake, river, rain, and lake' And year = '$year'");

$count50 = mysqli_num_rows($query50);



$query51 = mysqli_query($con,"SELECT * FROM house_cen_ques WHERE h3 = 'Peddler' And year = '$year'");
$count51 = mysqli_num_rows($query51);



$query52 = mysqli_query($con,"SELECT * FROM house_cen_ques WHERE h3 = 'Bottled water' And year = '$year'");

$count52 = mysqli_num_rows($query52);





//////////////////////////////////////////////////////////





$query53 = mysqli_query($con,"SELECT * FROM house_cen_ques WHERE h4 = 'Own or owner-like possession of house and lot' And year = '$year'");

$count53 = mysqli_num_rows($query53);



$query54 = mysqli_query($con,"SELECT * FROM house_cen_ques WHERE h4 = 'Rent house/room, including lot' And year = '$year'");

$count54 = mysqli_num_rows($query54);



$query55 = mysqli_query($con,"SELECT * FROM house_cen_ques WHERE h4 = 'Own house, rent lot' And year = '$year'");

$count55 = mysqli_num_rows($query55);



$query56 = mysqli_query($con,"SELECT * FROM house_cen_ques WHERE h4 = 'Own house, rent-free lot with consent of owner' And year = '$year'");

$count56 = mysqli_num_rows($query56);



$query57 = mysqli_query($con,"SELECT * FROM house_cen_ques WHERE h4 = 'Own house, rent-free lot without consent of owner' And year = '$year'");

$count57 = mysqli_num_rows($query57);



$query58 = mysqli_query($con,"SELECT * FROM house_cen_ques WHERE h4 = 'Rent-free house and lot with consent of owner' And year = '$year'");

$count58 = mysqli_num_rows($query58);



$query59 = mysqli_query($con,"SELECT * FROM house_cen_ques WHERE h4 = 'Rent-free house and lot without consent of owner' And year = '$year'");
$count59 = mysqli_num_rows($query59);


 $val1 = $year;
  $from = "house_cen_ques";





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
                                     <option value="<?php echo $val1;?>">---Select Year---</option>
                                       <?php

$year_query = "SELECT year FROM house_cen_ques_records GROUP BY year";
$result = mysqli_query($con, $year_query);
                          while($year_row = mysqli_fetch_array($result))  
                          {  
                               $value1 = $year_row['year'];

                               echo '<option value='.$value1.'>'.$value1.'</option>';
                          }  
?>
                                                 
 <?php         
                          if(isset($_POST['search_item'])){
                            $from = "house_cen_ques_records";
                             $year = mysqli_real_escape_string($con,$_POST['years']);
                            $year_sql = "Select * from rdlyhm_records WHERE year = '$year'";
                           $result = mysqli_query($con, $year_sql);
                      
$query = mysqli_query($con,"SELECT * FROM house_cen_ques_records WHERE b1 = 'Duplex' And year = '$year'");

$count = mysqli_num_rows($query);



$query2 = mysqli_query($con,"SELECT * FROM house_cen_ques_records WHERE b1 = 'Single house' And year = '$year'");

$count2 = mysqli_num_rows($query2);



$query3 = mysqli_query($con,"SELECT * FROM house_cen_ques_records WHERE b1 = 'Multi-unit residential (3 or more units)' And year = '$year'");
$count3 = mysqli_num_rows($query3);



$query4 = mysqli_query($con,"SELECT * FROM house_cen_ques_records WHERE b1 = 'Commercial/industrial/agricultural(office, factory,and others)' And year = '$year'");
$count4 = mysqli_num_rows($query4);



$query5 = mysqli_query($con,"SELECT * FROM house_cen_ques_records WHERE b1 = 'Institutional living quarter (hotel,hospital,prison, and others)' And year = '$year'");

$count5 = mysqli_num_rows($query5);



$query6 = mysqli_query($con,"SELECT * FROM house_cen_ques_records WHERE b1 = 'Tent' And year = '$year'");

$count6 = mysqli_num_rows($query6);





//////////////////////////////////////////////////////////





$query7 = mysqli_query($con,"SELECT * FROM house_cen_ques_records WHERE b2 = 'Galvanized iron/aluminum' And year = '$year'");

$count7 = mysqli_num_rows($query7);



$query8 = mysqli_query($con,"SELECT * FROM house_cen_ques_records WHERE b2 = 'Tile/concrete/clay tile' And year = '$year'");

$count8 = mysqli_num_rows($query8);



$query9 = mysqli_query($con,"SELECT * FROM house_cen_ques_records WHERE b2 = 'Half galvanized iron and half concrete' And year = '$year'");

$count9 = mysqli_num_rows($query9);



$query10 = mysqli_query($con,"SELECT * FROM house_cen_ques_records WHERE b2 = 'Bamboo/cogon/nipa/anahaw' And year = '$year'");

$count10 = mysqli_num_rows($query10);



$query11 = mysqli_query($con,"SELECT * FROM house_cen_ques_records WHERE b2 = 'Asbestos' And year = '$year'");

$count11 = mysqli_num_rows($query11);



$query12 = mysqli_query($con,"SELECT * FROM house_cen_ques_records WHERE b2 = 'Makeshift/salvaged/improvised materials' And year = '$year'");

$count12 = mysqli_num_rows($query12);



$query13 = mysqli_query($con,"SELECT * FROM house_cen_ques_records WHERE b2 = 'Trapal' And year = '$year'");

$count13 = mysqli_num_rows($query13);





//////////////////////////////////////////////////////////





$query14 = mysqli_query($con,"SELECT * FROM house_cen_ques_records WHERE b3 = 'Concrete/brick/stone' And year = '$year'");

$count14 = mysqli_num_rows($query14);



$query15 = mysqli_query($con,"SELECT * FROM house_cen_ques_records WHERE b3 = 'Wood' And year = '$year'");

$count15 = mysqli_num_rows($query15);



$query16 = mysqli_query($con,"SELECT * FROM house_cen_ques_records WHERE b3 = 'Half concrete/brick/stone and half wood' And year = '$year'");

$count16 = mysqli_num_rows($query16);



$query17 = mysqli_query($con,"SELECT * FROM house_cen_ques_records WHERE b3 = 'Galvanized iron/aluminum' And year = '$year'");

$count17 = mysqli_num_rows($query17);



$query18 = mysqli_query($con,"SELECT * FROM house_cen_ques_records WHERE b3 = 'Bamboo/sawali cogon/nipa' And year = '$year'");

$count18 = mysqli_num_rows($query18);



$query19 = mysqli_query($con,"SELECT * FROM house_cen_ques_records WHERE b3 = 'Asbestos' And year = '$year'");

$count19 = mysqli_num_rows($query19);



$query20 = mysqli_query($con,"SELECT * FROM house_cen_ques_records WHERE b3 = 'Glass' And year = '$year'");

$count20 = mysqli_num_rows($query20);



$query21 = mysqli_query($con,"SELECT * FROM house_cen_ques_records WHERE b3 = 'Makeshift/salvaged/improvised materials' And year = '$year'");

$count21 = mysqli_num_rows($query21);



$query22 = mysqli_query($con,"SELECT * FROM house_cen_ques_records WHERE b3 = 'Trapal' And year = '$year'");

$count22 = mysqli_num_rows($query22);



$query23 = mysqli_query($con,"SELECT * FROM house_cen_ques_records WHERE b3 = 'No walls' And year = '$year'");

$count23 = mysqli_num_rows($query23);







//////////////////////////////////////////////////////////





$query24 = mysqli_query($con,"SELECT * FROM house_cen_ques_records WHERE h1 = 'Electricity' And year = '$year'");

$count24 = mysqli_num_rows($query24);



$query25 = mysqli_query($con,"SELECT * FROM house_cen_ques_records WHERE h1 = 'Kerosene(gaas)' And year = '$year'");

$count25 = mysqli_num_rows($query25);



$query26 = mysqli_query($con,"SELECT * FROM house_cen_ques_records WHERE h1 = 'Liquefied petroleum gas(LPG)' And year = '$year'");

$count26 = mysqli_num_rows($query26);



$query27 = mysqli_query($con,"SELECT * FROM house_cen_ques_records WHERE h1 = 'Oil (vegetable, animal, and others)' And year = '$year'");

$count27 = mysqli_num_rows($query27);



$query28 = mysqli_query($con,"SELECT * FROM house_cen_ques_records WHERE h1 = 'Solar panel' And year = '$year'");

$count28 = mysqli_num_rows($query28);



$query29 = mysqli_query($con,"SELECT * FROM house_cen_ques_records WHERE h1 = 'Solar lamp' And year = '$year'");

$count29 = mysqli_num_rows($query29);



$query30 = mysqli_query($con,"SELECT * FROM house_cen_ques_records WHERE h1 = 'None' And year = '$year'");

$count30 = mysqli_num_rows($query30);





//////////////////////////////////////////////////////////







$query31 = mysqli_query($con,"SELECT * FROM house_cen_ques_records WHERE h2 = 'Own use faucet, community water system' And year = '$year'");

$count31 = mysqli_num_rows($query31);



$query32 = mysqli_query($con,"SELECT * FROM house_cen_ques_records WHERE h2 = 'Shared faucet, community water system' And year = '$year'");

$count32 = mysqli_num_rows($query32);



$query33 = mysqli_query($con,"SELECT * FROM house_cen_ques_records WHERE h2 = 'Own use, tubed/piped deep well' And year = '$year'");

$count33 = mysqli_num_rows($query33);



$query34 = mysqli_query($con,"SELECT * FROM house_cen_ques_records WHERE h2 = 'Shared tubed/piped deep well' And year = '$year'");

$count34 = mysqli_num_rows($query34);



$query35 = mysqli_query($con,"SELECT * FROM house_cen_ques_records WHERE h2 = 'Tubed/piped shallow wel' And year = '$year'");

$count35 = mysqli_num_rows($query35);



$query36 = mysqli_query($con,"SELECT * FROM house_cen_ques_records WHERE h2 = 'Dug well' And year = '$year'");

$count36 = mysqli_num_rows($query36);



$query37 = mysqli_query($con,"SELECT * FROM house_cen_ques_records WHERE h2 = 'Protected spring' And year = '$year'");

$count37 = mysqli_num_rows($query37);



$query38 = mysqli_query($con,"SELECT * FROM house_cen_ques_records WHERE h2 = 'Unprotected spring' And year = '$year'");

$count38 = mysqli_num_rows($query38);



$query39 = mysqli_query($con,"SELECT * FROM house_cen_ques_records WHERE h2 = 'Lake, river, rain, and lake' And year = '$year'");

$count39 = mysqli_num_rows($query39);



$query40 = mysqli_query($con,"SELECT * FROM house_cen_ques_records WHERE h2 = 'Peddler' And year = '$year'");

$count40 = mysqli_num_rows($query40);



$query41 = mysqli_query($con,"SELECT * FROM house_cen_ques_records WHERE h2 = 'Bottled water' And year = '$year'");

$count41 = mysqli_num_rows($query41);





//////////////////////////////////////////////////////////





$query42 = mysqli_query($con,"SELECT * FROM house_cen_ques_records WHERE h3 = 'Own use faucet, community water system' And year = '$year'");

$count42 = mysqli_num_rows($query42);



$query43 = mysqli_query($con,"SELECT * FROM house_cen_ques_records WHERE h3 = 'Shared faucet, community water system' And year = '$year'");

$count43 = mysqli_num_rows($query43);



$query44 = mysqli_query($con,"SELECT * FROM house_cen_ques_records WHERE h3 = 'Own use, tubed/piped deep well' And year = '$year'");

$count44 = mysqli_num_rows($query44);



$query45 = mysqli_query($con,"SELECT * FROM house_cen_ques_records WHERE h3 = 'Shared tubed/piped deep well' And year = '$year'");

$count45 = mysqli_num_rows($query45);



$query46 = mysqli_query($con,"SELECT * FROM house_cen_ques_records WHERE h3 = 'Tubed/piped shallow well' And year = '$year'");

$count46 = mysqli_num_rows($query46);



$query47 = mysqli_query($con,"SELECT * FROM house_cen_ques_records WHERE h3 = 'Dug well' And year = '$year'");

$count47 = mysqli_num_rows($query47);



$query48 = mysqli_query($con,"SELECT * FROM house_cen_ques_records WHERE h3 = 'Protected spring' And year = '$year'");

$count48 = mysqli_num_rows($query48);



$query49 = mysqli_query($con,"SELECT * FROM house_cen_ques_records WHERE h3 = 'Unprotected spring' And year = '$year'");

$count49 = mysqli_num_rows($query49);



$query50 = mysqli_query($con,"SELECT * FROM house_cen_ques_records WHERE h3 = 'Lake, river, rain, and lake' And year = '$year'");

$count50 = mysqli_num_rows($query50);



$query51 = mysqli_query($con,"SELECT * FROM house_cen_ques_records WHERE h3 = 'Peddler' And year = '$year'");
$count51 = mysqli_num_rows($query51);



$query52 = mysqli_query($con,"SELECT * FROM house_cen_ques_records WHERE h3 = 'Bottled water' And year = '$year'");

$count52 = mysqli_num_rows($query52);





//////////////////////////////////////////////////////////





$query53 = mysqli_query($con,"SELECT * FROM house_cen_ques_records WHERE h4 = 'Own or owner-like possession of house and lot' And year = '$year'");

$count53 = mysqli_num_rows($query53);



$query54 = mysqli_query($con,"SELECT * FROM house_cen_ques_records WHERE h4 = 'Rent house/room, including lot' And year = '$year'");

$count54 = mysqli_num_rows($query54);



$query55 = mysqli_query($con,"SELECT * FROM house_cen_ques_records WHERE h4 = 'Own house, rent lot' And year = '$year'");

$count55 = mysqli_num_rows($query55);



$query56 = mysqli_query($con,"SELECT * FROM house_cen_ques_records WHERE h4 = 'Own house, rent-free lot with consent of owner' And year = '$year'");

$count56 = mysqli_num_rows($query56);



$query57 = mysqli_query($con,"SELECT * FROM house_cen_ques_records WHERE h4 = 'Own house, rent-free lot without consent of owner' And year = '$year'");

$count57 = mysqli_num_rows($query57);



$query58 = mysqli_query($con,"SELECT * FROM house_cen_ques_records WHERE h4 = 'Rent-free house and lot with consent of owner' And year = '$year'");

$count58 = mysqli_num_rows($query58);



$query59 = mysqli_query($con,"SELECT * FROM house_cen_ques_records WHERE h4 = 'Rent-free house and lot without consent of owner' And year = '$year'");
$count59 = mysqli_num_rows($query59);


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







<div class="container-fluid">

    <div class="row">

        <div class="col-8" >

   



<div id="columnchart_material-1" style="width: 800px; height: 300px;"></div>

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

    <script type="text/javascript">

      google.charts.load('current', {'packages':['bar']});

      google.charts.setOnLoadCallback(drawChart);



      function drawChart() {

        var data = google.visualization.arrayToDataTable([

          ['Materials', 'Number',],





   //   echo "['".$row["p3"]."', ".$row["number"]."],";       



        //  ['2014', 1000,1212]

        

<?php  

                         include("config.php");

$query = "SELECT b1, count(*) as number FROM $from GROUP BY b1";  

$result = mysqli_query($con, $query);

                          while($row = mysqli_fetch_array($result))  

                          {  

                              echo "['".$row["b1"]."', ".$row["number"]."],";  

                          }  

                          ?> 

        ]);



        var options = {

          chart: {

            title: 'Type of Building',

            subtitle: 'Housing Census',

          }

        };



        var chart = new google.charts.Bar(document.getElementById('columnchart_material-1'));



        chart.draw(data, google.charts.Bar.convertOptions(options));

      }

    </script>

               

        </div>

        <div class="col-4" style="margin-top: 40px;">

       

<div id="columnchart_material-2" style="width: 800px; height: 300px;"></div>

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

    <script type="text/javascript">

      google.charts.load('current', {'packages':['bar']});

      google.charts.setOnLoadCallback(drawChart);



      function drawChart() {

        var data = google.visualization.arrayToDataTable([

          ['Materials', 'Number',],





   //   echo "['".$row["p3"]."', ".$row["number"]."],";       



        //  ['2014', 1000,1212]

        

 <?php  

                         include("config.php");

$query = "SELECT b2, count(*) as number FROM $from GROUP BY b2";  

$result = mysqli_query($con, $query);

                          while($row = mysqli_fetch_array($result))  

                          {  

                              echo "['".$row["b2"]."', ".$row["number"]."],";  

                          }  

                          ?> 

        ]);



        var options = {

          chart: {

            title: 'Construction Materials of the Roof of the Building',

            subtitle: 'Housing Census',

          }

        };



        var chart = new google.charts.Bar(document.getElementById('columnchart_material-2'));



        chart.draw(data, google.charts.Bar.convertOptions(options));

      }

    </script>



        </div>

    </div>







<div class="row">



    <div class="col-6" style="margin-top: 40px;">

  

<div id="columnchart_material-3" style="width: 800px; height: 300px;"></div>

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

    <script type="text/javascript">

      google.charts.load('current', {'packages':['bar']});

      google.charts.setOnLoadCallback(drawChart);



      function drawChart() {

        var data = google.visualization.arrayToDataTable([

          ['Materials', 'Number',],





   //   echo "['".$row["p3"]."', ".$row["number"]."],";       



        //  ['2014', 1000,1212]

        

 <?php  

                         include("config.php");

$query = "SELECT b3, count(*) as number FROM $from GROUP BY b3";  

$result = mysqli_query($con, $query);

                          while($row = mysqli_fetch_array($result))  

                          {  

                              echo "['".$row["b3"]."', ".$row["number"]."],";  

                          }  

                          ?> 

        ]);



        var options = {

          chart: {

            title: 'Construction Materials of the Outer Walls of the Building/Housing Unit',

            subtitle: 'Housing Census',

          }

        };



        var chart = new google.charts.Bar(document.getElementById('columnchart_material-3'));



        chart.draw(data, google.charts.Bar.convertOptions(options));

      }

    </script>



        </div>





<div class="col-6" style="margin-top: 40px;">

   



<div id="columnchart_material-4" style="width: 800px; height: 300px;"></div>

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

    <script type="text/javascript">

      google.charts.load('current', {'packages':['bar']});

      google.charts.setOnLoadCallback(drawChart);



      function drawChart() {

        var data = google.visualization.arrayToDataTable([

          ['Materials', 'Number',],





   //   echo "['".$row["p3"]."', ".$row["number"]."],";       



        //  ['2014', 1000,1212]

        

 <?php  

                         include("config.php");

$query = "SELECT h1, count(*) as number FROM $from GROUP BY h1";  

$result = mysqli_query($con, $query);

                          while($row = mysqli_fetch_array($result))  

                          {  

                              echo "['".$row["h1"]."', ".$row["number"]."],";  

                          }  

                          ?> 

        ]);



        var options = {

          chart: {

            title: 'Fuel for Lighting',

            subtitle: 'Housing Census',

          }

        };



        var chart = new google.charts.Bar(document.getElementById('columnchart_material-4'));



        chart.draw(data, google.charts.Bar.convertOptions(options));

      }

    </script>











        </div>



</div>



















<div class="row">

        

        <div class="col-6" style="margin-top: 40px;">

       

<div id="columnchart_material-5" style="width: 800px; height: 300px;"></div>

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

    <script type="text/javascript">

      google.charts.load('current', {'packages':['bar']});

      google.charts.setOnLoadCallback(drawChart);



      function drawChart() {

        var data = google.visualization.arrayToDataTable([

          ['Materials', 'Number',],





   //   echo "['".$row["p3"]."', ".$row["number"]."],";       



        //  ['2014', 1000,1212]

        

  <?php  

                         include("config.php");

$query = "SELECT h2, count(*) as number FROM $from GROUP BY h2";  

$result = mysqli_query($con, $query);

                          while($row = mysqli_fetch_array($result))  

                          {  

                              echo "['".$row["h2"]."', ".$row["number"]."],";  

                          }  

                          ?> 

        ]);



        var options = {

          chart: {

            title: 'Source of Water Supply for Drinking',

            subtitle: 'Housing Census',

          }

        };



        var chart = new google.charts.Bar(document.getElementById('columnchart_material-5'));



        chart.draw(data, google.charts.Bar.convertOptions(options));

      }

    </script>



        </div>



        <div class="col-6" style="margin-top: 40px;">

    











<div id="columnchart_material-6" style="width: 800px; height: 300px;"></div>

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

    <script type="text/javascript">

      google.charts.load('current', {'packages':['bar']});

      google.charts.setOnLoadCallback(drawChart);



      function drawChart() {

        var data = google.visualization.arrayToDataTable([

          ['Materials', 'Number',],





   //   echo "['".$row["p3"]."', ".$row["number"]."],";       



        //  ['2014', 1000,1212]

        

  <?php  

                         include("config.php");

$query = "SELECT h3, count(*) as number FROM $from GROUP BY h3";  

$result = mysqli_query($con, $query);

                          while($row = mysqli_fetch_array($result))  

                          {  

                              echo "['".$row["h3"]."', ".$row["number"]."],";  

                          }  

                          ?> 

        ]);



        var options = {

          chart: {

            title: 'Source of Water Supply for Cooking',

            subtitle: 'Housing Census',

          }

        };



        var chart = new google.charts.Bar(document.getElementById('columnchart_material-6'));



        chart.draw(data, google.charts.Bar.convertOptions(options));

      }

    </script>



















        </div>

    </div>







<div class="row">

  <div class="col-6" style="margin-top: 40px;">

  <div id="columnchart_material-7" style="width: 800px; height: 300px;"></div>

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

    <script type="text/javascript">

      google.charts.load('current', {'packages':['bar']});

      google.charts.setOnLoadCallback(drawChart);



      function drawChart() {

        var data = google.visualization.arrayToDataTable([

          ['Materials', 'Number',],





   //   echo "['".$row["p3"]."', ".$row["number"]."],";       



        //  ['2014', 1000,1212]

        

 <?php  

                         include("config.php");

$query = "SELECT h4, count(*) as number FROM $from GROUP BY h4";  

$result = mysqli_query($con, $query);

                          while($row = mysqli_fetch_array($result))  

                          {  

                              echo "['".$row["h4"]."', ".$row["number"]."],";  

                          }  

                          ?>  

        ]);



        var options = {

          chart: {

            title: 'Tenure Status of the Housing Unit/Lot',

            subtitle: 'Housing Census',

          }

        };



        var chart = new google.charts.Bar(document.getElementById('columnchart_material-7'));



        chart.draw(data, google.charts.Bar.convertOptions(options));

      }

    </script>

</div>

 

</div>





































<div class="col-lg-12">

                    <div class="page-header">

                        <h1>Housing Records <?php echo $val1;?></h1>

                    </div>

                    <table class="table table-striped table-hover">

                        <thead>

                            <tr class="info">

                                <th>#</th>

                                <th>Type of Building</th>

                                <th>Number</th>

                            </tr>

                        </thead>

                        <tbody>

                            <tr>

                                <td>1</td>

                                <td>Single house</td> 

                               <?php echo '<td>'.$count2.'</td>';?> 

                            </tr>

                            <tr>

                                <td>2</td>

                                <td>Duplex</td>  

                                <?php echo '<td>'.$count.'</td>';?>                         

                            </tr>

                             <tr>

                                <td>3</td>

                                <td> Multi-unit residential (3 or more units)</td>      

                                <?php echo '<td>'.$count3.'</td>';?>    



                            </tr>

                             <tr>

                                <td>4</td>

                                <td>Commercial/industrial/agricultural (office, factory,and others)</td> 

                                 <?php echo '<td>'.$count4.'</td>';?>                          

                            </tr>

                             <tr>

                                <td>5</td>

                                <td>Institutional living quarter (hotel, hospital, prison, and others)</td>   

                               <?php echo '<td>'.$count5.'</td>';?>                       

                            </tr>



                              <tr>

                                <td>6</td>

                                <td>Tent</td> 

                               <?php echo '<td>'.$count6.'</td>';?>                     

                            </tr>

                        </tbody>

                    </table>  

                      &nbsp



             





 <table class="table table-striped table-hover">

                        <thead>

                            <tr class="success">

                                <th>#</th>

                                <th>Construction Materials of the Roof of the Building</th>

                                <th>Number</th>

                            </tr>

                        </thead>

                        <tbody>

                            <tr>

                                <td>1</td>

                                <td>Galvanized iron/aluminum</td>

                                 <?php echo '<td>'.$count7.'</td>';?>  

                            </tr>

                            <tr>

                                <td>2</td>

                                <td>Tile/concrete/clay tile</td>

                                <?php echo '<td>'.$count8.'</td>';?>   

                            </tr>

                             <tr>

                                <td>3</td>

                                <td>Half galvanized iron and half concrete</td>

                                 <?php echo '<td>'.$count9.'</td>';?>  

                            </tr>

                             <tr>

                                <td>4</td>

                                <td>Bamboo/cogon/nipa/anahaw</td>

                                 <?php echo '<td>'.$count10.'</td>';?>  

                            </tr>

                             <tr>

                                <td>5</td>

                                <td>Asbestos</td>

                                <?php echo '<td>'.$count11.'</td>';?>  

                            </tr>

                            <tr>

                                <td>6</td>

                                <td>Makeshift/salvaged/ improvised materials</td>

                                 <?php echo '<td>'.$count12.'</td>';?>   

                            </tr>

                            <tr>

                                <td>7</td>

                                <td>Trapal</td>

                                 <?php echo '<td>'.$count13.'</td>';?>    

                            </tr>

                           



                        </tbody>

                    </table>

  &nbsp





<table class="table table-striped table-hover">

                        <thead>

                            <tr class="active">

                                <th>#</th>

                                <th>Construction Materials of the Outer Walls of the Building/Housing Unit</th>

                                <th>Number</th>

                            </tr>

                        </thead>

                        <tbody>

                            <tr>

                                <td>1</td>

                                <td>Concrete/brick/stone</td>

                                <?php echo '<td>'.$count14.'</td>';?>

                            </tr>

                             <tr>

                                <td>2</td>

                                <td>Wood</td>

                                <?php echo '<td>'.$count15.'</td>';?>

                            </tr>

                             <tr>

                                <td>3</td>

                                <td>Half concrete/brick/stone and half wood</td>

                                <?php echo '<td>'.$count16.'</td>';?>

                            </tr>

                             <tr>

                                <td>4</td>

                                <td>Galvanized iron/aluminum</td>

                                <?php echo '<td>'.$count17.'</td>';?> 

                            </tr>

                             <tr>

                                <td>5</td>

                                <td>Bamboo/sawali cogon/nipa</td>

                                <?php echo '<td>'.$count18.'</td>';?> 

                            </tr>

                            <tr>

                                <td>6</td>

                                <td>Asbestos</td>

                                <?php echo '<td>'.$count19.'</td>';?>

                            </tr>

                            <tr>

                                <td>7</td>

                                <td>Glass</td>

                                <?php echo '<td>'.$count20.'</td>';?>

                            </tr>

                            <tr>

                                <td>8</td>

                                <td>Makeshift/salvaged/improvised materials</td>

                               <?php echo '<td>'.$count21.'</td>';?>  

                            </tr>

                            <tr>

                                <td>9</td>

                                <td>Trapal</td>

                                <?php echo '<td>'.$count22.'</td>';?>

                            </tr>

                            <tr>

                                <td>10</td>

                                <td>No walls</td>

                               <?php echo '<td>'.$count23.'</td>';?>

                            </tr>

                        </tbody>

                    </table>

  &nbsp



               



                <table class="table table-striped table-hover">

                        <thead>

                            <tr class="danger">

                                <th>#</th>

                                <th>Fuel for Lighting</th>

                                <th>Number</th>

                            </tr>

                        </thead>

                        <tbody>

                            <tr>

                                <td>1</td>

                                <td>Electricity</td>

                                 <?php echo '<td>'.$count24.'</td>';?> 

                            </tr>

                            <tr>

                                <td>2</td>

                                <td>Kerosene (gaas)</td>

                                <?php echo '<td>'.$count25.'</td>';?>

                            </tr>

                            <tr>

                                <td>3</td>

                                <td>Liquefied petroleum gas(LPG)</td>

                                 <?php echo '<td>'.$count26.'</td>';?> 

                            </tr>

                            <tr>

                                <td>4</td>

                                <td>Oil (vegetable, animal, and others)</td>

                                 <?php echo '<td>'.$count27.'</td>';?>

                            </tr>

                            <tr>

                                <td>5</td>

                                <td>Solar panel</td>

                                <?php echo '<td>'.$count28.'</td>';?>

                            </tr>

                            <tr>

                                <td>6</td>

                                <td>Solar lamp</td>

                                <?php echo '<td>'.$count29.'</td>';?>

                            </tr>

                            <tr>

                                <td>7</td>

                                <td>None</td>

                                 <?php echo '<td>'.$count30.'</td>';?> 

                            </tr>

                        </tbody>

                    </table>

  &nbsp



                      <table class="table table-striped table-hover">

                        <thead>

                            <tr class="warning">

                                <th>#</th>

                                <th>Source of Water Supply for Drinking</th>

                                <th>Number</th>

                            </tr>

                        </thead>

                        <tbody>

                            <tr>

                                <td>1</td>

                                <td>Own use faucet, community water system</td>

                               <?php echo '<td>'.$count31.'</td>';?> 

                            </tr>



                            <tr>

                                <td>2</td>

                                <td>Shared faucet, community water system</td>

                               <?php echo '<td>'.$count32.'</td>';?>  

                            </tr>

                             <tr>

                                <td>3</td>

                                <td>Own use, tubed/piped deep well </td>

                               <?php echo '<td>'.$count33.'</td>';?>   

                            </tr>

                             <tr>

                                <td>4</td>

                                <td>Shared tubed/piped deep well</td>

                                <?php echo '<td>'.$count34.'</td>';?> 

                            </tr>

                             <tr>

                                <td>5</td>

                                <td>Tubed/piped shallow wel</td>

                               <?php echo '<td>'.$count35.'</td>';?>   

                            </tr>

                             <tr>

                                <td>6</td>

                                <td>Dug well</td>

                               <?php echo '<td>'.$count36.'</td>';?> 

                            </tr>

                             <tr>

                                <td>7</td>

                                <td>Protected spring</td>

                               <?php echo '<td>'.$count37.'</td>';?>   

                            </tr>

                             <tr>

                                <td>8</td>

                                <td>Unprotected spring</td>

                                <?php echo '<td>'.$count38.'</td>';?>  

                            </tr>

                             <tr>

                                <td>9</td>

                                <td>Lake, river, rain, and lake</td>

                                <?php echo '<td>'.$count39.'</td>';?> 

                            </tr>

                             <tr>

                                <td>10</td>

                                <td>Peddler</td>

                               <?php echo '<td>'.$count40.'</td>';?> 

                            </tr>

                             <tr>

                                <td>11</td>

                                <td>Bottled water</td>

                                <?php echo '<td>'.$count41.'</td>';?> 

                            </tr>



                        </tbody>

                    </table>

  &nbsp



                     <table class="table table-striped table-hover">

                        <thead>

                            <tr class="info">

                                <th>#</th>

                                <th>Source of Water Supply for Cooking</th>

                                <th>Number</th>

                            </tr>

                        </thead>

                        <tbody>

                            <tr>

                                <td>1</td>

                                <td>Own use faucet, community water system</td>

                                <?php echo '<td>'.$count42.'</td>';?> 

                            </tr>



                             <tr>

                                <td>2</td>

                                <td>Shared faucet, community water system</td>

                               <?php echo '<td>'.$count43.'</td>';?> 

                            </tr>

                             <tr>

                                <td>3</td>

                                <td>Own use, tubed/piped deep well</td>

                               <?php echo '<td>'.$count44.'</td>';?>  

                            </tr>

                             <tr>

                                <td>4</td>

                                <td>Shared tubed/piped deep well</td>

                                <?php echo '<td>'.$count45.'</td>';?>   

                            </tr>

                             <tr>

                                <td>5</td>

                                <td>Tubed/piped shallow well</td>

                               <?php echo '<td>'.$count46.'</td>';?> 

                            </tr>

                             <tr>

                                <td>6</td>

                                <td>Dug well</td>

                               <?php echo '<td>'.$count47.'</td>';?> 

                            </tr>

                             <tr>

                                <td>7</td>

                                <td>Protected spring</td>

                              <?php echo '<td>'.$count48.'</td>';?>  

                            </tr>

                             <tr>

                                <td>8</td>

                                <td>Unprotected spring</td>

                               <?php echo '<td>'.$count49.'</td>';?>  

                            </tr>

                             <tr>

                                <td>9</td>

                                <td>Lake, river, rain, and lake</td>

                                <?php echo '<td>'.$count50.'</td>';?>   

                            </tr>

                             <tr>

                                <td>10</td>

                                <td>Peddler</td>

                                <?php echo '<td>'.$count52.'</td>';?>  

                            </tr>

                             <tr>

                                <td>11</td>

                                <td>Bottled water</td>

                                <?php echo '<td>'.$count52.'</td>';?> 

                            </tr>

                        </tbody>

                    </table>

  &nbsp





                     <table class="table table-striped table-hover">

                        <thead>

                            <tr class="success">

                                <th>#</th>

                                <th>Tenure Status of the Housing Unit/Lot</th>

                                <th>Number</th>

                            </tr>

                        </thead>

                        <tbody>

                            <tr>

                                <td>1</td>

                                <td>Own or owner-like possession of house and lot</td>

                                <?php echo '<td>'.$count53.'</td>';?>

                            </tr>

                            <tr>

                                <td>2</td>

                                <td>Rent house/room, including lot</td>

                                <?php echo '<td>'.$count54.'</td>';?> 

                            </tr>

                             <tr>

                                <td>3</td>

                                <td>Own house, rent lot</td>

                               <?php echo '<td>'.$count55.'</td>';?>

                            </tr>

                             <tr>

                                <td>4</td>

                                <td>Own house, rent-free lot with consent of owner</td>

                               <?php echo '<td>'.$count56.'</td>';?>

                            </tr>

                            <tr>

                                <td>5</td>

                                <td>Own house, rent-free lot without consent of owner</td>

                                <?php echo '<td>'.$count57.'</td>';?>

                            </tr>

                            <tr>

                                <td>6</td>

                                <td>Rent-free house and lot with consent of owner</td>

                                <?php echo '<td>'.$count58.'</td>';?>

                            </tr>

                               <tr>

                                <td>7</td>

                                <td>Rent-free house and lot without consent of owner</td>

                               <?php echo '<td>'.$count59.'</td>';?> 

                            </tr>



                        </tbody>

                    </table>

  &nbsp



                </div>



 



 

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





















 