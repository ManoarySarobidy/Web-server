<?php
	$base = mysqli_connect("localhost","sarobidy","manoary","test");
	$query = "Select * from haha";
	$query = mysqli_query( $base , $query );
	$val = array();
	while( $v = mysqli_fetch_assoc($query) ){
		$val[] = $v;
	}
	// var_dump($base);
?>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" type="text/css" href="style.css">
	<title>Test page PHP</title>
	
</head>
<body>

	<h2>
		
		Bienvenue : 
		<div class="red" style="color: red;">
				le reel :
				<p class="text-black">
					<?php
						echo $_POST['name']; 
						
					?>
				</p>

		</div>

	</h2>
		<img src="haha.jpg" alt="haha">
	
	
</body>
</html>