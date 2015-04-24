<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>HAckthon Log Analyzer</title>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

<script type="text/javascript">

 $(document).ready(function()
         {

	$.ajax({

  url: "ActionServlet",

  context: document.body

}).done(function() {

 alert("done");
 
});
         
 /* window.setInterval(intervalFunction(), 5000);
 
 function intervalFunction(){
	    // Assuming we have #shoutbox
	  //  $('#shoutbox').load('latestShouts.php');
	    alert("hello");
	}
 */


     });

</script>

</head>
<body>

</body>
</html>