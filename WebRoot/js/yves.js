function ajaxRequest(){
	var request;
	var reqs = [
	    function(){return new XMLHttpRequest();},
	    function(){return new ActiveXObject("Msxml2.XMLHTTP");},
	    function(){return new ActiveXObject("Msxml3.XMLHTTP");},
	    function(){return new ActiveXObject("Microsoft.XMLHTTP");}     
	];
	for(var i=0; i<reqs.length; i++){
		try{
			request = reqs[i]();
			break;
		}catch (e) {
			continue;
		}
	}
	return request;
}