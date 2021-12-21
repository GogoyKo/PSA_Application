<?php 
    session_start();
?>
<?php
    if($_SESSION['logged'] != 'true'){
     header('location:../index.php');}

 ?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PSA Dashboard</title>

   

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
                <a class="navbar-brand" href="index.html" title="PB Dashboard">PSA Dashboard</a>
            </div>
            <div class="collapse navbar-collapse navbar-ex1-collapse">
                <ul class="nav navbar-nav side-nav">
                    <li class="active"><a href="index.html"><i class="glyphicon glyphicon-th"></i> Dashboard</a></li>
                    <li class="nav nav-list nav-list-expandable nav-list-expanded">
                        <a><i class="fa fa-user"></i> Tables <span class="caret"></span></a>
                        <ul class="nav navbar-nav">
                            <li><a href="datagrid.html"><i class="fa fa-table"></i> POPULATION TABLE </a></li>
                            <li><a href="editor.html"><i class="fa fa-edit"></i> HOUSING</a></li>
                            <li><a href="editor.html"><i class="fa fa-edit"></i> DEATHS IN THE LAST TWO YEARS</a></li>
                        </ul>
                    </li>
                   
                    <li class="nav nav-list nav-list-expandable">
                        <a><i class="fa fa-key"></i> Enumerator Settings <span class="caret"></span></a>
                        <ul class="nav navbar-nav">
                            <li><a href="#">Manage Enumerators</a></li>
                            <li><a href="#">---</a></li>
                        </ul>
                    <li>
                </ul>

                <?php
include("config.php");
                        $id =$_SESSION['userid'];
                        $result = mysqli_query($con,"SELECT * FROM admin WHERE id ='$id'");
                        $test = mysqli_fetch_array($result);
                        echo $test['fname'];
                        ?>
                
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
    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i>Yawa<b class="caret"></b></a>
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
                            <h3 class="panel-title"><i class="fa fa-line-chart"></i> Live Statistics <a class="pull-right glyphicon glyphicon-option-horizontal" href="#" style="text-decoration:none;"></a></h3>
                        </div>
                        <div class="panel-body">
                            <div id="chart_live" style="width:100%; height:250px;"></div>
                        </div>
                    </div>
                   <!-- <div class="panel">
                        <div class="panel-body">
                            <div class="col-md-3 col-sm-3 col-xs-3 text-center">
                                <h4><span class="fa fa-eye"></span> Views</h4>
                                <h3>1450</h3>
                            </div>
                            <div class="col-md-3 col-sm-3 col-xs-3 text-center">
                                <h4><span class="fa fa-file-video-o"></span> Pages</h4>
                                <h3>327</h3>
                            </div>
                            <div class="col-md-3 col-sm-3 col-xs-3 text-center">
                                <h4><span class="fa fa-users"></span> Users</h4>
                                <h3>119</h3>
                            </div>
                            <div class="col-md-3 col-sm-3 col-xs-3 text-center">
                                <h4><span class="fa fa-money"></span> Earnings</h4>
                                <h3>$14.58</h3>
                            </div>
                        </div> 
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="panel panel-primary">
                                <div class="panel-heading">
                                    <h3 class="panel-title"><i class="fa fa-calendar"></i> Calendar</h3>
                                </div>
                                <div class="panel-body text-center">
                                    <div id="calendar1"></div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h3 class="panel-title"><i class="fa fa-gear"></i> Settings</h3>
                                </div>
                                <div class="panel-body">
									<ul class="list-group">
										<li class="list-group-item">
											<span class="label label-default">Label Default</span>
											<div class="pull-right">
												<input id="switch1" name="switch1" type="checkbox" />
											</div>
										</li>
										<li class="list-group-item">
											<span class="label label-success">Label Success</span>
											<div class="pull-right">
												<input id="switch2" name="switch2" type="checkbox" />
											</div>
										</li>
										<li class="list-group-item">
											<span class="label label-primary">Label Primary</span>
											<div class="pull-right">
												<input id="switch3" name="switch3" type="checkbox" checked="checked" />
											</div>
										</li>
										<li class="list-group-item">
											<span class="label label-info">Label Info</span>
											<div class="pull-right">
												<input id="switch4" name="switch4" type="checkbox" checked="checked" />
											</div>
										</li>
										<li class="list-group-item">
											<span class="label label-warning">Label Warning</span>
											<div class="pull-right">
												<input id="switch5" name="switch4" type="checkbox" />
											</div>
										</li>
										<li class="list-group-item">
											<span class="label label-danger">Label Danger</span>
											<div class="pull-right">
												<input id="switch6" name="switch4" type="checkbox" checked="checked" />
											</div>
										</li>
									</ul>
                                </div>
                            </div>
                        </div>
                    </div> -->
                </div>
                <div class="col-md-4">
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <h3 class="panel-title"><i class="fa fa-comments"></i> -Anything- <a class="pull-right fa fa-gear" href="#" style="text-decoration:none;"></a></h3>
                        </div>
                      <!--  <div class="panel-body">
                            <div style="width:100%; height:250px;">
                                <ul class="media-list">
                                    <li class="media">
                                        <div class="media-left">
                                            <img class="media-object" src="images/user1.jpg" style="width:64px; height:64px; border-radius:50%; border:solid 1px #E1E1E1;" /> 
                                        </div>
                                        <div class="media-body">
                                            <h4 class="media-heading">Jack Sparrow <small class="pull-right" style="color:#b6b6b6; margin-right:10px;"><i class="fa fa-clock-o"></i> 14 min ago</small></h4>
                                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                                        </div> 
                                    </li>
                                    <li class="media">
                                        <div class="media-left">
                                            <img class="media-object" src="images/user2.jpg" style="width:64px; height:64px; border-radius:50%; border:solid 1px #E1E1E1;" />
                                        </div>
                                        <div class="media-body">
                                            <h4 class="media-heading">Captain Cook <small class="pull-right" style="color:#b6b6b6; margin-right:10px;"><i class="fa fa-clock-o"></i> 29 min ago</small></h4>
                                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                                        </div>
                                    </li>
                                </ul>
                                <a href="#" class="btn btn-primary pull-right">View All Messages</a>
                            </div>
                        </div>-->
                    </div>
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title"><i class="fa fa-keyboard-o"></i> Keywords</h3>
                        </div>
                        <div class="panel-body text-center">
                            <div id="tagcloud"></div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="panel panel-primary">
                                <div class="panel-heading">
                                    <h3 class="panel-title"><i class="fa fa-pie-chart"></i> Web Browser</h3>
                                </div>
                                <div class="panel-body">
                                    <div id="chart_browsers" style="width:100%; height:200px;"></div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="panel">
                                <div class="panel-heading">
                                    <h3 class="panel-title"><i class="glyphicon glyphicon-flag"></i> Overall Progress</h3>
                                </div>
                                <div class="panel-body">
                                    <div id="progress" style="width:100%; height:200px; margin:auto;"></div>
                                </div>
                            </div>
                        </div>
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
