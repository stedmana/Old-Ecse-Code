<?php
require_once '/Applications/XAMPP/xamppfiles/htdocs/HomeAudioSystem/controller/Controller.php';

session_start();


$_SESSION["errorRegisterEvent"] = "";
$c = new Controller();
try {

	$album = NULL;
	if (isset($_POST['albumspinner'])) {
		$album = $_POST['albumspinner'];
	}
	$c->register($album);
} catch (Exception $e) {
	$errors = explode("@", $e->getMessage());
	foreach ($errors as $error) {
		
		if (substr($error, 0, 1) == "2") {
			$_SESSION["errorRegisterEvent"] = substr($error, 1);
		}
	}
}
?>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="refresh" content="0; url= /HomeAudioSystem/" />
</head>
</html>