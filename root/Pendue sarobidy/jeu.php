<?php
session_start();
$words=$_SESSION['mot'];

?>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="CSS.css">
	<title>Le pendue Version 1.0</title>
</head>
<body>

	<div id="ensemble">
		<div id="header">Jouer</div><br>

		<div id="word" style="width:500px"; >
			<br>
			<table border="1px" width="240px" height="50px">
				<tr>
					<?php for ($i=0; $i <strlen($words) ; $i++) { ?> 
						<td></td>
				<?php	} ?>
				</tr>
			</table>
		</div>	

		<div id="input">
			<form>
				<input type="text" name="letter"><br>
				<input type="submit" value="OK!!!">
			</form>
		</div>

		<div id="pic">
			OK
		</div>
		<br>

		<div id="footer">© Copyright Sarobidy 2021 ITU</div>

	</div>
</body>
</html>