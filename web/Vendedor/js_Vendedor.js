

function cargarBarraNav() {
    fetch("../BarraYFooter/barraNav.html")
            .then(function (callback) {
                return callback.text();
            })
            .then(html => {
                document.getElementById("insert_barraNav").innerHTML = html;
                cargarClientes(); // Llama a cargarClientes después de cargar la barra de navegación
            });
}

function cargarFooter() {
    fetch("../BarraYFooter/footerDemas.html")
            .then(function (callback) {
                return callback.text();
            })
            .then(html => {
                document.getElementById("insert_footer").innerHTML = html;
                cargarClientes(); // Llama a cargarClientes después de cargar el footer
            });
}

function cargarClientes() {
    let url = 'http://localhost:8080/QualityMultiservices_v2/api/administrarCliente/obtenerTodoClientes?idUsuario=1';
    fetch(url)
            .then(function (response) {
                return response.json();
            })
            .then(function (data) {
                console.log(data);
                let perfilHTML = ""; // Variable para almacenar el HTML del perfil de usuario

                data.forEach(function (cliente) {
                    let nombreUsuario = cliente.usuario.nombreUsuario;
                    let imagenPerfil = cliente.usuario.imagenPerfil;
                    let correo = cliente.usuario.correo;
                    let ciudad = cliente.usuario.ciudad;
                    // Generar HTML para cada cliente
                    let clienteHTML = `
                    <div class="card col-md-6 mb-4 mx-auto position-relative">
    <!-- Botón para abrir el modal -->
    <button type="button" class="btn btn-custom position-absolute top-0 end-0 me-3 mt-3 d-none d-md-block" data-bs-toggle="modal" data-bs-target="#myModal">
        <img src="../Images/btnEditar.png" alt="Botón con Imagen">
    </button>

    <div class="card-body text-center">
        <h1>Perfil de Usuario</h1>
        <br>
        <img class="profile-pic rounded-circle mx-auto d-block mb-3" src="data:image/png;base64,${imagenPerfil}" alt="Foto de Perfil" width="150" height="150">
        <h2>${nombreUsuario}</h2>
        <div class="card-body text-center">
            <h4 class="card-title">Información de Contacto</h4>
            <h5><i class="bi bi-envelope"></i> ${correo}</h5>
            <h5><i class="bi bi-telephone"></i> ${ciudad}</h5>
                    <!-- Botón "¡Hazte Vendedor ya mismo!" en la esquina superior izquierda -->
        <button class="btn btn-success  top-0 start-0 m-3" onclick="hazteVendedor()">¡Hazte Vendedor ya mismo!</button>
        </div>
    </div>

    <!-- Botón para abrir el modal (visible en dispositivos pequeños) -->
    <button type="button" class="btn btn-custom d-md-none mb-3" data-bs-toggle="modal" data-bs-target="#myModal">
        Editar Perfil
    </button>
</div>

<!-- El modal -->
<div class="modal fade modal-dialog-scrollable" id="myModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header text-center">
                <h5 class="modal-title" id="exampleModalLabel">Edita tus datos</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="mb-3">
                    <label for="nombreUsuario" class="form-label">Nombre de Usuario</label>
                    <input type="text" class="form-control" id="nombreUsuario" name="nombreUsuario" placeholder="Ingrese el nuevo nombre de usuario" required>
                </div>
                <div class="mb-3">
                    <label for="imagenPerfil" class="form-label">Imagen de Perfil</label>
                    <li class="list-group-item"> <input type="file" id="imgFotoAGuardar" style="width: 100vh"></li>
                </div>
                <div class="mb-3">
                    <label for="ciudad" class="form-label">Ciudad</label>
                    <input type="text" class="form-control" id="ciudad" name="ciudad" placeholder="Ingrese la nueva ciudad" required>
                </div>
                <div class="mb-3">
                    <label for="numero" class="form-label">Número</label>
                    <input type="number" class="form-control" id="numero" name="numero" placeholder="Ingrese el numero de contacto" required>
                </div>
                <div class="mb-3">
                    <label for="contrasenia" class="form-label">Contraseña</label>
                    <input type="password" class="form-control" id="contrasenia" name="contrasenia" placeholder="Ingrese la nueva contraseña" required>
                </div>
                <input type="hidden" id="idUsuarioParam" name="idUsuarioParam" value="${cliente.usuario.idUsuario}">
                <button class="btn btn-primary" onclick="modificarCliente()">Guardar Cambios</button>
            </div>
        </div>
    </div>
</div>

<!-- Botón "¡Hazte Vendedor ya mismo!" -->
                `;
                    // Agregar el HTML del cliente al perfilHTML
                    perfilHTML += clienteHTML;
                });
                // Establecer el contenido de la sección con id="clientesContainer" con el perfilHTML
                document.getElementById("clientesContainer").innerHTML = perfilHTML;
            })
            .catch(function (error) {
                console.error('Error al cargar clientes:', error);
            });
}

// Llamar a las funciones al cargar la página
window.onload = function () {
    cargarBarraNav();
    cargarFooter();
};
// Función para modificar el cliente
function modificarCliente() {
    let url = "http://localhost:8080/QualityMultiservices_v2/api/administrarCliente/updateCliente";
    console.log("Hola desde editar Cliente");
    let nombreUsuario = document.getElementById("nombreUsuario").value;
    let imagenPerfil = document.getElementById("imagenPerfil").value;
    let ciudad = document.getElementById("ciudad").value;
    let contrasenia = document.getElementById("contrasenia").value;
    let idUsuarioParam = document.getElementById("idUsuarioParam").value;
    let datosCliente = {

        "idCliente": 1,
        "usuario": {
            "idUsuario": 1,
            "nombreUsuario": nombreUsuario,
            "imagenPerfil": imagenPerfil,
            "ciudad": ciudad,
            "contrasenia": contrasenia
        }
    };
    console.log(datosCliente);
    const requestOptions = {
        method: 'POST',
        headers: {'Content-Type': 'application/x-www-form-urlencoded'},
        body: new URLSearchParams({datosCliente: JSON.stringify(datosCliente)})
    };
    fetch(url, requestOptions)
            .then(function (data) {
                return data.json();
            })
            .then(function (json) {
                console.log(json);
                 // Después de guardar el ticket, llamar a la función para cargar el nuevo registro sin recargar la página
            });
}

