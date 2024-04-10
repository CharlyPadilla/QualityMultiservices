/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function iniciarSesion() {
    let correo = document.getElementById('email').value.trim();
    let contrasenia = document.getElementById('password').value.trim();

    if (!correo || !contrasenia) {
        alert("Por favor, ingresa el correo y la contraseña.");
        return;
    }

    let url = 'http://localhost:8080/DSM506_Integradora/api/usuario/iniciarSesion';
    url += '?email=' + encodeURIComponent(correo) + '&password=' + encodeURIComponent(contrasenia);

    fetch(url)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Hubo un problema con la solicitud.');
                }
                return response.json();
            })
            .then(data => {
                if (data.success) {
                    upDateToken(correo, contrasenia, data.idUsuario);
                    let idUsuario = data.idUsuario;
                    sessionStorage.setItem("idUsuario", idUsuario);
                    alert("Inicio de sesión exitoso");
                    window.location.href = 'principal.html';
                } else {
                    alert("Credenciales incorrectas. Por favor, verifica tu correo y contraseña.");
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert("Hubo un error al intentar iniciar sesión. Por favor, intenta de nuevo.");
            });
}

function upDateToken(correo, contrasenia, idUsuario) {
    const url = `http://localhost:8080/DSM506_Integradora/usuario/validarLogin?email=${encodeURIComponent(correo)}&password=${encodeURIComponent(contrasenia)}`;

    fetch(url, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Hubo un problema al validar el inicio de sesión.');
                }
                return response.json();
            })
            .then(data => {
                if (data.token && data.token !== 'INVALIDO') {
                    console.log('Token generado y actualizado en la base de datos:', data.token);
                    sessionStorage.setItem("token", data.token);
                } else {
                    console.log('No se pudo generar el token o las credenciales son inválidas.');
                }
            })
            .catch(error => {
                console.error('Error al intentar actualizar el token:', error);
            });
}

/* global Swal */

const btnSignIn = document.getElementById("sign-in"),
        btnSignUp = document.getElementById("sign-up"),
        formRegister = document.querySelector(".register"),
        formLogin = document.querySelector(".login");

btnSignIn.addEventListener("click", e => {
    formRegister.classList.add("hide");
    formLogin.classList.remove("hide");
});

btnSignUp.addEventListener("click", e => {
    formLogin.classList.add("hide");
    formRegister.classList.remove("hide");
});

async function encriptar(contrasenia) {
    try {
        let encrypt = await sha256(contrasenia);
        save(encrypt);
    } catch (error) {
        console.error("Error al encriptar la contraseña:", error);
    }
}

function sha256(contrasenia) {
    let encoder = new TextEncoder();
    const data = encoder.encode(contrasenia);
    return crypto.subtle.digest('SHA-256', data)
            .then(buffer => {
                const hashArray = Array.from(new Uint8Array(buffer));
                const hashHex = hashArray.map(byte =>
                    byte.toString(16).padStart(2, '0')).join('');
                return hashHex;
            });
}

function save(encryptPass) {
    let nombreUsuario = document.getElementById("nombreUsuario").value;
    let imagenPerfil = document.getElementById("imagenPerfil").value;
    let ciudad = document.getElementById("ciudad").value;
    let correo = document.getElementById("correo").value;
    let contrasenia = document.getElementById("contrasenia").value;
    let lastConnection = new Date().toISOString();
    let token = "";

    let usuario = {
        correo: correo,
        contrasenia: encryptPass,
        lastConnection: lastConnection,
        token: token
    };

    let params = {
        nombreUsuario: nombreUsuario,
        imagenPerfil: imagenPerfil,
        ciudad: ciudad,
        correo: correo,
        contrasenia: contrasenia,
        usuario: JSON.stringify(usuario)
    };

    let url = "http://localhost:8080/DSM506_Integradora/api/usuario/insertarUsuario";

    let requestOptions = {
        method: 'POST',
        headers: {'Content-Type': 'application/x-www-form-urlencoded'},
        body: new URLSearchParams(params)
    };

    fetch(url, requestOptions)
            .then(function (data) {
                return data.json();
            })
            .then(function (json) {
                console.log(json);
                Swal.fire({
                    icon: "success",
                    title: "User Register",
                    text: "Se registró Correctamente"
                });
                limpiarCampos();
            })
            .catch(function (error) {
                console.error("Error al guardar el usuario:", error);
                Swal.fire("Hubo un error al almacenar los datos.");
            });
}

function verificarSeleccion() {
    let chbtnVendedor = document.getElementById("chbtnVendedor");
    let chbtnCliente = document.getElementById("chbtnCliente");
    
    if (chbtnVendedor.checked) {
        console.log("Opción 1 seleccionada");
            guardarUsuarioCliente();
    } else if (chbtnCliente.checked) {
        console.log("Opción 2 seleccionada");
            guardarUsuarioVendedor();
    }
}


function guardarUsuarioCliente(){
    let nombreUsuario = document.getElementById("nombreUsuario").value;
    let imagenPerfil = document.getElementById("imagenPerfil").value;
    let ciudad = document.getElementById("ciudad").value;
    let correo = document.getElementById("correo").value;
    let contrasenia = document.getElementById("contrasenia").value;
    
    let params = {
        nombreUsuario: nombreUsuario,
        imagenPerfil: imagenPerfil,
        ciudad: ciudad,
        correo: correo,
        contrasenia: contrasenia
    };
    
    let url = "http://localhost:8080/QualityMultiservices_v2/api/usuario/insertarUsuarioCliente";

    const queryString = new URLSearchParams(params).toString();
                const requestOptions = {
                method: 'POST',
                        headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                        body: queryString // Enviar la cadena de consulta como el cuerpo de la solicitud
                };
                fetch(url, requestOptions).then(
                function (response) {
                return response.json(); // Analiza la respuesta JSON
                }
        ).then(
                function (data) {
                    
                    if(data.response.equals("Ya existe una cuenta con el correo ingreasado")){
                        Swal.fire({
                            icon: "error",
                            title: "Ops...",
                            text: "Ya existe una cuenta con el correo ingreasado"
                        });
                    }else{
                        localStorage.setItem("token", data.token);
                        localStorage.setItem("fotoPerfilCadena", data.imagenPerfil);
                        localStorage.setItem("idUsuario", data.idUsuario);
                        window.location.href = 'InicioCliente.html';
                    }
                }
        );
}

function guardarUsuarioVendedor(){
    let nombreUsuario = document.getElementById("nombreUsuario").value;
    let imagenPerfil = document.getElementById("imagenPerfil").value;
    let ciudad = document.getElementById("ciudad").value;
    let correo = document.getElementById("correo").value;
    let contrasenia = document.getElementById("contrasenia").value;
    let idOficio = document.getElementById("selectOficios").value;
    let aniosExperiencia = document.getElementById("aniosExperiencia").value;
    
    let params = {
        nombreUsuario: nombreUsuario,
        imagenPerfil: imagenPerfil,
        ciudad: ciudad,
        correo: correo,
        contrasenia: contrasenia,
        idOficio: idOficio,
        aniosExperiencia: aniosExperiencia
    };
    
    let url = "http://localhost:8080/QualityMultiservices_v2/api/usuario/insertarUsuarioVendedor";

    const queryString = new URLSearchParams(params).toString();
                const requestOptions = {
                method: 'POST',
                        headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                        body: queryString // Enviar la cadena de consulta como el cuerpo de la solicitud
                };
                fetch(url, requestOptions).then(
                function (response) {
                return response.json(); // Analiza la respuesta JSON
                }
        ).then(
                function (data) {
                    
                    if(data.response.equals("Ya existe una cuenta con el correo ingreasado")){
                        Swal.fire({
                            icon: "error",
                            title: "Ops...",
                            text: "Ya existe una cuenta con el correo ingreasado"
                        });
                    }else{
                        localStorage.setItem("token", data.token);
                        localStorage.setItem("fotoPerfilCadena", data.imagenPerfil);
                        localStorage.setItem("idUsuario", data.idUsuario);
                        window.location.href = 'InicioVendedor.html';
                    }
                }
        );
}


function iniciarSesion(){
    let email = document.getElementById("email").value;
    let password = document.getElementById("password").value;
    
    
    let params = {
        correo: email,
        contrasenia: password
    };
    
    let url = "http://localhost:8080/QualityMultiservices_v2/api/usuario/loginConToken";

    const queryString = new URLSearchParams(params).toString();
                const requestOptions = {
                method: 'POST',
                        headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                        body: queryString // Enviar la cadena de consulta como el cuerpo de la solicitud
                };
                fetch(url, requestOptions).then(
                function (response) {
                return response.json(); // Analiza la respuesta JSON
                }
        ).then(
                function (data) {
                    
                    if(data.response.equals("Credenciales no válidas")){
                        Swal.fire({
                            icon: "error",
                            title: "Ops...",
                            text: "Credenciales no válidas"
                        });
                    }else if(data.response.equals("ERROR")){
                        Swal.fire({
                            icon: "error",
                            title: "Ops...",
                            text: "Error inesperado. Intentelo más tarde"
                        });
                        
                    }else{
                        localStorage.setItem("token", data.token);
                        localStorage.setItem("fotoPerfilCadena", data.imagenPerfil);
                        localStorage.setItem("idUsuario", data.idUsuario);
                        if(data.vendedor===true){
                            window.location.href = 'InicioUsuarioVendedor.html';
                        }else{
                            window.location.href = 'InicioUsuarioVendedor.html';
                        }
                        
                    }
                }
        );
}
                    
