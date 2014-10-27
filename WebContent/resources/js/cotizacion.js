/**
 * 
 */
function limpiarCliente() {
	document.getElementById("tabs:clienteForm:nombresCliente").value = "";
	document.getElementById("tabs:clienteForm:correoCliente").value = "";
	document.getElementById("tabs:clienteForm:direccionCliente").value = "";
	document.getElementById("tabs:clienteForm:dniCliente").value = "";
	document.getElementById("tabs:clienteForm:rucCliente").value = "";
	habilitarCliente(false);
}

function habilitarCliente(item) {
	document.getElementById("tabs:clienteForm:nombresCliente").readOnly = item;
	document.getElementById("tabs:clienteForm:correoCliente").readOnly = item;
	document.getElementById("tabs:clienteForm:direccionCliente").readOnly = item;
	document.getElementById("tabs:clienteForm:dniCliente").readOnly = item;
	document.getElementById("tabs:clienteForm:rucCliente").readOnly = item;
}
function nrodepiezasValido() {
	alert('Y Yo estoy aqui');
	var nrodePiezas = document.getElementById().value;
	if (nrodepiezas != null) {
		if (nrodePiezas <= 0) {
			alert('El valor se cambiara por precaucion');
			document.getElementById().value = 1;
		}
	}
}