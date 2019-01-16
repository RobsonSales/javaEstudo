<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<title>Upload files</title>
</head>
<body>

	<input type="file" id="file" name="file" onchange="uploadFile();">
	<img alt="Imagem" src="" id="target" width="200" height="200">


</body>

<script type="text/javascript">
	function uploadFile() {

		var target = document.querySelector("img");
		var file = document.querySelector("input[type=file]").files[0];
		var reader = new FileReader();

		reader.onloadend = function() {
			target.src = reader.result;
			
		//////upload ajax
			
			$.ajax({
				method : "POST",
				url : "fileUpload",
				data : { fileUpload : target.src
				}
			}).done(function(response){
				alert("Sucesso: " + response);
				
			}).fail(function(xhr, status, errorThrown){
				alert("Error: " + xhr.responseText);
				
			});
		
		///////
			
		};

		if (file) {
			reader.readAsDataURL(file);
			
		} else {
			target.src = "";
		}
	}
</script>
</html>