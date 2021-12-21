<?php



	//$con = mysqli_connect('localhost', 'root','','firstdb');

	include("config.php");

	$username = mysqli_real_escape_string($con,$_POST["username"]);

	$password = mysqli_real_escape_string($con,$_POST["password"]);

	$pass = md5($password);





	$statement = mysqli_prepare($con, "SELECT * FROM enum_user WHERE username = ? AND password = ? AND status = 1");

	mysqli_stmt_bind_param($statement, "ss", $username, $pass);

	mysqli_stmt_execute($statement);



	mysqli_stmt_store_result($statement);

	mysqli_stmt_bind_result($statement, $enumeratorID, $fname, $lname, $username, $pass, $email, $age,$mobile,$gender,$image,$status);



	$response = array();

	$response["success"]= false;



	while(mysqli_stmt_fetch($statement)){



		$response["success"] = true;

		$response["enumerator_id"] = $enumeratorID;

		$response["firstname"] = $fname;

		$response["lastname"] = $lname;

		$response["username"] = $username;

		$response["password"] = $pass;	

		$response["email"] = $email;

		$response["age"] = $age;

		$response["mobile"] = $mobile;

		$response["gender"] = $gender;

		$response["image"] = $image;

		$response["status"] = $status;

		





	}

	echo json_encode($response);

?>