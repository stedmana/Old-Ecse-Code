<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home Audio System</title>
<style>
.error {color: #FF0000;}
</style>
</head>
<body>
<?php 

require_once '/Applications/XAMPP/xamppfiles/htdocs/HomeAudioSystem//model/Album.php';
require_once '/Applications/XAMPP/xamppfiles/htdocs/HomeAudioSystem/model/Has.php';
require_once '/Applications/XAMPP/xamppfiles/htdocs/HomeAudioSystem/persistence/PersistenceHAS.php';

session_start();

//Retrieve the data from the model
$pm = new PersistenceHAS();
$rm = $pm ->loadDataFromStore();
/*
echo"<form action='register.php' method ='post'>";

echo"<p>Album? <select name='albumspinner'>";
foreach($rm->getAlbum() as $album){
	echo"<option>" . $album->getName() . "</option>";
}
echo"</select><span class='error'>";
if (isset($_SESSION['errorRegisterParticipant'])&&!empty($_SESSION['errorRegisterParticipant'])){
	echo" * " . $_SESSION["errorRegisterParticipant"];
}
echo"</span></p>";
*/
?>



<form action="addalbum.php" method = "post">
<p>Name? <input type= "text" name = "album_name" />
<span class="error">
<?php 
if (isset($_SESSION['errorAlbumName'])&& !empty($_SESSION['errorAlbumName'])){
	echo"*" . $_SESSION['errorAlbumName'];
}
?>
</span></p>

<p>Artist? <input type= "text" name = "artist_name" />
<span class="error">
<?php 
if (isset($_SESSION['errorArtistName'])&& !empty($_SESSION['errorArtistName'])){
	echo"*" . $_SESSION['errorArtistName'];
}
?>
</span>
</p>

<p>Genre? <input type= "text" name = "genre" />
<span class="error">
<?php 
if (isset($_SESSION['errorGenre'])&& !empty($_SESSION['errorGenre'])){
	echo"*" . $_SESSION['errorGenre'];
}
?>
</span>
</p>
<p>

<p>Release Date? <input type="date"name="album_date"value="<?php echo date('Y-m-d'); ?>" />

<span class="error">
<?php 
if (isset($_SESSION['errorAlbumDate'])&& !empty($_SESSION['errorAlbumDate'])){
	echo"*" . $_SESSION['errorAlbumDate'];
}
?>
</span>
</p>




<p><input type="submit" value="Add Album"/></p>
</form>

</body>
</html>