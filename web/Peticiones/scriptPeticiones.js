

localStorage.setItem("idUsuario", 2);
function mostrarPeticiones() {

    console.log("Antes de consumir servicio para obtener peticiones");
    let url = 'http://localhost:8080/QualityMultiservices/api/administrarPeticion/obtenerPeticiones?idUsuario=';
    let idUsuario = localStorage.getItem("idUsuario");

    url += idUsuario;

    fetch(url).then(
            function (jsonText) {
                return jsonText.json();
            }
    ).then(
            function (data) {
                document.getElementById("tarjetasPeticion").innerHTML = "";
                // let imagenPeticion = getElementById("imgPeticion");

                console.log(data);
                for (let i = 0; i < data.length; i++) {
                    localStorage.setItem("peticion" + i, JSON.stringify(data[i]));

                    let idPeticion = data[i].idPeticion;
                    let nombreUsuario = data[i].publicacion.usuario.nombreUsuario;
                    let oficioBuscado = data[i].oficioBuscado.nombreOficio;
                    let descripcion = data[i].publicacion.descripcion;
                    let fechaCreacion = data[i].publicacion.fechaCreacion;
                    let fechaEdicion = data[i].publicacion.fechaEdicion;
                    let imagen = data[i].publicacion.listaFotos[0].cadenaFoto;



                    let peticionAMostrar = `
                    <div class="container mt-4">
                        <div class="card">
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-md-2 col-sm-3">
                                         <img src="/placeholder.svg" class="profile-img img-fluid" alt="Avatar">
                                    </div>
                    
                                    <div class="col-md-10 col-sm-9">
                                        <h5 class="card-title" >${nombreUsuario}</h5>
                    
                                        <div class="dropdown">
                                            <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                                                Dropdown button
                                            </button>
                                        <ul class="dropdown-menu">
                                            <li><a class="dropdown-item" href="#">Action</a></li>
                                            <li><a class="dropdown-item" href="#">Another action</a></li>
                                            <li><a class="dropdown-item" href="#">Something else here</a></li>
                                         </ul>
                                     </div>
                    
                                <div class="collapse multi-collapse row" id="multiCollapsePeticion">
                                    <button onclick="mostrarPeticion(${i})" data-bs-toggle="modal" data-bs-target="#modalPeticion"  fill="currentColor" class=" m-1 bi bi-pencil-square btn btn-dark" >Editar </button>                    
                                    <button onclick=confirmarEliminarPeticion(${i}) class="bi bi-trash m-1 btn btn-danger" style="color: black;")>Eliminar</button>
                                </div>
                            </div>
                    
                                        <h6 class="card-subtitle mb-2 text-muted">${oficioBuscado}</h6>
                                        <p class="card-text">${descripcion}</p>
                                        
                                        <div class="d-flex justify-content-center">
                                            <img src="#" id="imgPeticion${i}" alt="alt"/>
                                        </div>`;
                    if (fechaEdicion !== null) {
                        peticionAMostrar += `<h6 class="card-subtitle mb-2 text-muted">Creado:${fechaCreacion}</h6>`;
                    }
                    peticionAMostrar += `
                                    </div>
                                </div>
                            </div>
                            
                         </div>
                    </div>
                    `;

                    document.getElementById("tarjetasPeticion").innerHTML += peticionAMostrar;
                }
            }
    );
}
mostrarPeticiones();

function mostrarPeticion(posicionPeticionSel) {
    let peticionSeleccionada = localStorage.getItem(`peticion${posicionPeticionSel}`);
    console.log(peticionSeleccionada);
    let peticionObjeto = JSON.parse(peticionSeleccionada);

    let oficio = document.getElementById("txtOficio");
    let descripcion = document.getElementById("txtDescripcion");

    oficio.value = peticionObjeto.oficioBuscado.nombreOficio;
    descripcion.value = peticionObjeto.publicacion.descripcion;
}

function confirmarEliminarPeticion(posicionPeticionSel) {
    Swal.fire({
        title: "¿Estás segur@ de eliminar la publicación?",
        text: "Esto ya no se podrá revertir!",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Si, eliminar!"
    }).then((result) => {
        if (result.isConfirmed) {
            Swal.fire({
                title: "Hecho!",
                text: "La publicación ha sido eliminada",
                icon: "success"
            });
        }
    });
}

function eliminarPeticion(posicionPeticionSel) {

}