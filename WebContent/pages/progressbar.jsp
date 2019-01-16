<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Barra de Progresso</title>

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"
	integrity="sha256-T0Vest3yCU7pafRw9r+settMBX6JkKN06dqBnpQ8d30="
	crossorigin="anonymous"></script>

<style type="text/css">
/*-- fundo da barra de progresso */
#myprogress {
	width: 100%;
	background-color: #ddd;
}

.ui-progressbar {
	position: relative;
}

.progress-label {
	position: relative;
	left: 50%;
	top: 4px;
	font-weight: bold;
	text-shadow: 1 px 1px 0 #fff;
}

/*-- cor da barra de progresso */
#myBar {
	width: 1%;
	height: 30px;
	background-color: #4CAF50;
}
</style>

</head>
<body>
	<h1>Exemplo com javascript</h1>
	<div id="myProgress">
		<!-- fundo da barra -->
		<div id="myBar"></div>
		<!-- barra de progresso -->
	</div>
	<br />
	<button onclick="exibirBarra();">Iniciar a barra de progresso</button>

	<br />
	<h1>Barra de Progresso com JQuery</h1>
	<div id="progressbar">
		<div class="progress-label">Carregando...</div>

	</div>

	<script type="text/javascript">
		//script da barra de progresso por jquery
		$(function() {
			var progressbar = $("progressbar"), progresslabel = $(".progress-label");

			progressbar.progressbar({ //cria a barra no div
				value : false,
				change : function() {
					progresslabel.text(progressbar.progressbar('value') + "%");
				},
				complete : function() {
					progresslabel.text('Completo!');
				}
			});

			function progress() {
				var val = progressbar.progressbar("value") || 0;

				progressbar.progressbar("value", val + 2);
				if (val < 99) {
					setTimeout(progress, 80);
				}
			}
			setTimeout(progress, 2000);
		});

		//script da barra de progresso por javascript
		function exibirBarra() {
			var elem = document.getElementById("myBar");
			var width = 1;
			var id = setInterval(frame, 10);

			function frame() {
				if (width >= 100) {
					clearInterval(id);
				} else {
					width++;
					elem.style.width = width + "%";
				}
			}

		}
	</script>
</body>
</html>