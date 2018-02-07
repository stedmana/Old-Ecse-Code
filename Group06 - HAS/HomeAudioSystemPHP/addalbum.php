<?php
require_once '/Applications/XAMPP/xamppfiles/htdocs/HomeAudioSystem/controller/Controller.php';

session_start();
//since I don't know how to catch exception, this code should also be wrong, cause error message for the add event part does not show correctly.
$c = new Controller();
try{
	$c->createAlbum($_POST['album_name'],$_POST['artist_name'],$_POST['genre'],$_POST['album_date']);
	$_SESSION["errorAlbumName"] = "";
	$_SESSION["errorArtistName"] = "";
	$_SESSION["errorGenre"] = "";
	$_SESSION["errorAlbumDate"] = "";
} catch (Exception $e) {
	$_SESSION["errorAlbumName"] = $e->getMessage();
}
catch (Exception $e) {
	$_SESSION["errorArtistName"] = $e->getMessage();
}
catch (Exception $e) {
	$_SESSION["errorGenre"] = $e->getMessage();
}
catch (Exception $e) {
	$_SESSION["errorAlbumDate"] = $e->getMessage();
}   
/*
try{
	$c->createEvent($_POST['event_date']);
	$_SESSION["errorEventDate"] = "";
} 

try{
	$c->createEvent($_POST['starttime']);
	$_SESSION["errorStartTime"] = "";
} 

try{
	$c->createEvent($_POST['endtime']);
	$_SESSION["errorEndTime"] = "";
} */
?>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="refresh" content="0; url= /HomeAudioSystem/" />
</head>
</html>