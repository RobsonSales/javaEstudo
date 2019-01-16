<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>


<title>DataTable JQuery</title>
</head>
<body>

	<table id="minhatabela" class="display" style="width: 100%" cellspacing="0">
		<thead>
			<tr>
				<th>ID</th>
				<th>Login</th>
				<th>Nome</th>
				<th>Tel</th>
				<th>CEP</th>
				<th>Rua</th>
				<th>Bairro</th>
				<th>Cidade</th>
				<th>UF</th>
				<th>IBGE</th>
			</tr>
		</thead>
		
	</table>

</body>
<script type="text/javascript">

$(document).ready(function() { //faz o processamento na hora que abre a tela.
    $('#minhatabela').DataTable( {
        "processing": true,
        "serverSide": true,
        "ajax": "carregarDadosDataTable" //URL de retorno dos dados do servidor(Resposta do servidor)
    } );
} );
</script>
</html>