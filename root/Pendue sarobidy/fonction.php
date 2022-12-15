<?php

function WordAlea($var,$OthVar,$tab)
{
	while ($var>$OthVar) {
		$var=$var-$OthVar;
	}
		if ($var==$OthVar) {
			$var=rand(0,($OthVar-1));
				$mots=$tab[$var];
			}
				elseif ($var<$OthVar) {
						$mots=$tab[$var];
					}
		return $mots;
}

function FindLet($char,$word)
{
	$a=0;
	for ($i=0; $i <strlen($word) ; $i++) { 
		if ($char==$word[$i]) {
		$b[$a]=$char;
		$a++;	
		}
	}
}

?>