<?php

//here begun the season
session_start();
include("./Data.php");
include("./fonction.php");
$a=count($mot);
$nombre=rand(0,100);
$word=WordAlea($nombre,$a,$mot);

$_SESSION['mot']=$word;

header('location:jeu.php')

?>
