
$(document).submit(function(){

var name = $('#computerName').val();
var introduced = $('#introduced').val();
var discontinued = $('#discontinued').val();

	console.log(name);
	
    if (!name.trim()) {
		console.log("nom vide");
		return false;
	} else {
		console.log("nom pas vide");
	}
	
	if ( !(introduced === "" )){
		if (!(discontinued === "") && (introduced.replace(/-/g,'') > discontinued.replace(/-/g,''))){
			console.log("date avant");
			return false;
		}
	}
	
	console.log("Ordinateur bon a envoyer");

	return true;
});


