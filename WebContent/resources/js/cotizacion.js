/**
 * 
 */
function limpiarCliente() {
	document.getElementById("clienteForm:nombresCliente").value = "";
	document.getElementById("clienteForm:lastnameCliente").value = "";
	document.getElementById("clienteForm:correoCliente").value = "";
	document.getElementById("clienteForm:dniCliente").value = "";
	document.getElementById("clienteForm:direccionCliente").value = "";
	document.getElementById("clienteForm:rucCliente").value = "";
	desHabilitarCliente(false);
}

function desHabilitarCliente(item) {
	document.getElementById("clienteForm:nombresCliente").readOnly = item;
	document.getElementById("clienteForm:lastnameCliente").readOnly = item;
	document.getElementById("clienteForm:correoCliente").readOnly = item;
	document.getElementById("clienteForm:dniCliente").readOnly = item;
	document.getElementById("clienteForm:direccionCliente").readOnly = item;
	document.getElementById("clienteForm:rucCliente").readOnly  = item ;
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