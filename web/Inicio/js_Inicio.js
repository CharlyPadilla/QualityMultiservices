let checkbox = document.getElementById("myCheck");

function cargarBarraNav() {
    console.log("Funcion cargarBarraNav");
    fetch("BarraYFooter/barraNavSinSesion.html")
            .then(function (callback) {
                return callback.text();
            })
            .then(html => {
                document.getElementById("insert_barraNav").innerHTML = html;
                      });
}
;

function cargarFooter() {
    console.log("Funcion cargarFooter");
    fetch("BarraYFooter/footer.html")
            .then(function (callback) {
                return callback.text();
            })
            .then(html => {
                document.getElementById("insert_footer").innerHTML = html;
                      });
}
;

function cargarBarraNavCon() {
    console.log("Funcion cargarBarraNavCon");
    fetch("../BarraYFooter/barraNavInicio.html")
            .then(function (callback) {
                return callback.text();
            })
            .then(html => {
                document.getElementById("insert_barraNav").innerHTML = html;
                      });
}
;

function cargarFooterCon() {
    console.log("Funcion cargarFooterCon");
    fetch("../BarraYFooter/footerDemas.html")
            .then(function (callback) {
                return callback.text();
            })
            .then(html => {
                document.getElementById("insert_footer").innerHTML = html;
                      });
}
;

checkbox.addEventListener("click", function () {
    if (checkbox.checked) {
        mostrarPublicaciones();
    } else {
        mostrarClientes();
    }
});





function mostrarVendedores() {
    console.log("Funcion mostrarVendedores");
    let url = 'http://localhost:8080/QualityMultiservices_v2/api/inicio/getAllVendedor';
    fetch(url)
            .then(function (respuesta) {
                return respuesta.json();
            })
            .then(function (cuerpo) {
                generarTablaVendedores(cuerpo);
            });
}
function buscarVendedores() {
    console.log("Funcion buscaVendedores");
    let palabra = document.getElementById("palabra").value;
    let urlBuscar = `http://localhost:8080/QualityMultiservices_v2/api/inicio/searchVendedor?palabra=${palabra}`;
    fetch(urlBuscar)
            .then(function (respuesta) {
                return respuesta.json();
            })
            .then(function (cuerpo) {
                generarTablaVendedores(cuerpo);
            });
}

function generarTablaVendedores(arreglo) {
    console.log("Funcion generarTablaVendedores");
    let vendedoresHTML = "";
    let vendedoresHTML1 = "";
    arreglo.forEach(function (vendedor) {
        vendedoresHTML +=
                `
        <div>
            <div class="container mt-4">
                <div class="card">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-md-2 col-sm-3">
                                <img src="../Images/user.png" class="profile-img img-fluid" alt="Avatar">
                            </div>
                            <div class="col-md-10 col-sm-9">
                                <h5 class="card-title" >${vendedor.usuario.nombreUsuario}</h5>
                                <h6 class="card-subtitle mb-2 text-muted">${vendedor.oficio.nombreOficio}</h6>
                                <p class="card-text">${vendedor.usuario.ciudad}</p>
                            </div>
                            <ul class="list-group list-group-flush col-md-12 text-center">
                                <li class="list-group-item">Telefono:                 ${vendedor.usuario.numeroCelular}</li>
                                <li class="list-group-item">Años de experiencia:    ${vendedor.aniosExperiencia}</li>
                                <li class="list-group-item">Correo:                 ${vendedor.usuario.correo}</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
            `;
        vendedoresHTML1 =
                `    
                <div id="carouselExample" class="carousel slide">
                    <div class="carousel-inner">
                        <div class="carousel-item active">
                            ${vendedoresHTML}
                        </div>
                    </div>
                </div>
            `;
        document.getElementById("mostrarVendedores").innerHTML = vendedoresHTML1;
        });

}

function mostrarClientes() {
    console.log("Funcion mostrarClientes");
    if (!checkbox.checked) {

        let url = 'http://localhost:8080/QualityMultiservices_v2/api/inicio/getAllCliente';
        fetch(url)
                .then(function (respuesta) {
                    return respuesta.json();
                })
                .then(function (cuerpo) {
                    generarTablaClientes(cuerpo);
                });
    }
}

function buscarClientes() {
    console.log("Funcion buscaClientes");
    let palabra = document.getElementById("palabra").value;
    let urlBuscar = `http://localhost:8080/QualityMultiservices_v2/api/inicio/searchCliente?palabra=${palabra}`;
    fetch(urlBuscar)
            .then(function (respuesta) {
                return respuesta.json();
            })
            .then(function (cuerpo) {
                generarTablaVendedores(cuerpo);
            });
}

function generarTablaClientes(arreglo) {
    console.log("Funcion generarTablaClientes");
    let clientesHTML = "";
    let clientesHTML1 = "";
    arreglo.forEach(function (cliente) {
        clientesHTML +=
                `
        <div>
            <div class="container mt-4">
                <div class="card">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-md-2 col-sm-3">
                                <img src="../Images/user.png" class="profile-img img-fluid" alt="Avatar">
                            </div>
                            <div class="col-md-10 col-sm-9">
                                <h5 class="card-title" >${cliente.usuario.nombreUsuario}</h5>
                                <p class="card-text">${cliente.usuario.ciudad}</p>
                            </div>
                            <ul class="list-group list-group-flush col-md-12 text-center">
                                <li class="list-group-item">Telefono:                 ${cliente.usuario.numeroCelular}</li>
                                <li class="list-group-item">Correo:                 ${cliente.usuario.correo}</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
            `;
        clientesHTML1 =
                `    
                <div id="carouselExample" class="carousel slide">
                    <div class="carousel-inner">
                        <div class="carousel-item active">
                            ${clientesHTML}
                        </div>
                    </div>
                </div>
            `;
        document.getElementById("mostrarTarjetas-Publicaciones").innerHTML = clientesHTML1;
        });

}

function mostrarPublicaciones() {
    console.log("Funcion mostrarPublicaciones");
    if (checkbox.checked) {
        let url = 'http://localhost:8080/QualityMultiservices_v2/api/inicio/getAllPublicacion';
        fetch(url)
                .then(function (respuesta) {
                    return respuesta.json();
                })
                .then(function (cuerpo) {
                    generarTablaPublicaciones(cuerpo);
                });
    }
}

//TODO:
function buscarPublicaciones() {
    console.log("Funcion buscaPublicaciones");
    let palabra = document.getElementById("palabra").value;
    let urlBuscar = `http://localhost:8080/QualityMultiservices_v2/api/inicio/searchCliente?palabra=${palabra}`;
    fetch(urlBuscar)
            .then(function (respuesta) {
                return respuesta.json();
            })
            .then(function (cuerpo) {
                generarTablaVendedores(cuerpo);
            });
}

function generarTablaPublicaciones(arreglo) {
    console.log("Funcion generarTablaPublicaciones");
    let publicacionesHTML = "";
    let publicacionesHTML1 = "";
    arreglo.forEach(function (publicacion) {
        let numero = publicacion.idPublicacion;
        let imagenes = "";
        let urlBuscar = `http://localhost:8080/QualityMultiservices_v2/api/inicio/getAllFotosPublicacion?numero=${numero}`;
        fetch(urlBuscar)
                .then(function (respuesta) {
                    return respuesta.json();
                })
                .then(function (cuerpo) {
                    imagenes = generarImagenes(cuerpo);
                    publicacionesHTML +=
                        `
                            <div>
                                <div class="container mt-4">
                                    <div class="card">
                                        <div class="card-body">
                                            <div class="row">
                                                <div class="col-md-2 col-sm-3">
                                                    <img src="../Images/post.png" class="profile-img img-fluid" alt="Avatar">
                                                </div>
                                                <div class="col-md-10 col-sm-9">
                                                    <h5 class="card-title" >${publicacion.titulo}</h5>
                                                    <h6 class="card-subtitle mb-2 text-muted">${publicacion.usuario.ciudad}</h6>
                                                    <p class="card-text">${publicacion.fechaEdicion}</p>
                                                </div>
                                                <ul class="list-group list-group-flush col-md-12 text-center">
                                                    <li class="list-group-item">Publicado por:         ${publicacion.usuario.nombreUsuario}</li>
                                                    <li class="list-group-item">Telefono:              ${publicacion.usuario.numeroCelular}</li>
                                                    <li class="list-group-item">Correo:                ${publicacion.usuario.correo}</li>
                                                    <li class="list-group-item">Descripción:<br>       ${publicacion.descripcion}</li>
                                                    <li class="list-group-item">
                                                        Fotos: <br><br>${imagenes}
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        `;
                        publicacionesHTML1 =
                            `    
                                <div id="carouselExample" class="carousel slide">
                                    <div class="carousel-inner">
                                        <div class="carousel-item active">
                                            ${publicacionesHTML}
                                        </div>
                                    </div>
                                </div>
                            `;
                        document.getElementById("mostrarTarjetas-Publicaciones").innerHTML = publicacionesHTML1;
                    });
    });
}

function generarImagenes(arreglo) {
    console.log("Funcion generarImagenes");
    let imagenesHTML = "";
    arreglo.forEach(function (imagen) {
        imagenesHTML +=
            `
                <img class="mx-3 my-1" src="${imagen.cadenaFoto}" alt="${imagen.cadenaFoto}"/>
            `;
        });
    return imagenesHTML;
}
// <img class="mx-3 my-1" src="../Images/Tiktok.png" alt="${imagen.cadenaFoto}"/>
