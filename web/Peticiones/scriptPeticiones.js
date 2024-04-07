
localStorage.setItem("idUsuario", 2);
function mostrarPublicacionesPeticiones() {

    console.log("Antes de consumir servicio para obtener peticiones");
    let url = 'http://localhost:8080/QualityMultiservices_v2/api/administrarPeticion/obtenerPeticiones?idUsuario=';
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

                //console.log(data);
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
                    
                                        <div class="dropdown position-absolute top-0 end-0 me-3 mt-3">
                                            <button class="btn btn-secondary" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                                                <i class="bi bi-three-dots-vertical dropdown-toggle "></i>
                                            </button>
                                        <ul class="dropdown-menu">
                                            <li onclick=mostrarInfoPeticion(${i}) ><a class="dropdown-item" data-bs-toggle="modal" data-bs-target="#modalPeticion" href="#">Editar</a></li>
                                            <li><a class="dropdown-item" href="#">Eliminar</a></li>
                                         </ul>
                                     </div>
                    
                              
                            </div>
                    
                                        <h6 class="card-subtitle mb-2 text-muted">${oficioBuscado}</h6>
                                        <p class="card-text">${descripcion}</p>
                                        
                                        <div id="contImagenAMostrar${i}" class="d-flex justify-content-center">
                                            <img src="data:image/png;base64,${imagen}" id="imgPeticion${i}" alt="imagen${i}"/>
                                        </div>
                                        <h6 class="card-subtitle mb-2 text-muted">Creado:${fechaCreacion}</h6>`;
                    if (fechaEdicion !== undefined) {
                        peticionAMostrar += `<br><h6 class="my-1 card-subtitle mb-2 text-muted">Editado:${fechaEdicion}</h6>`;

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
mostrarPublicacionesPeticiones();

function mostrarInfoPeticion(posicionPeticionSel) {
    let peticionSeleccionada = localStorage.getItem(`peticion${posicionPeticionSel}`);
    //console.log(peticionSeleccionada);
    let peticionObjeto = JSON.parse(peticionSeleccionada);

    let oficio = document.getElementById("txtOficio");
    let descripcion = document.getElementById("txtDescripcion");

    oficio.value = peticionObjeto.oficioBuscado.nombreOficio;
    descripcion.textContent = peticionObjeto.publicacion.descripcion;
    cargarOficiosParaEditar();
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

function cargarOficiosParaEditar() {

    let url = 'http://localhost:8080/QualityMultiservices_v2/api/administrarOficio/obtenerOficios';

    let oficioDePublicacion;
    fetch(url).then(
            function (jsonText) {
                return jsonText.json();
            }
    ).then(
            function (data) {
                console.log(data);
                for (let i = 0; i < data.length; i++) {
                    let oficio = data[i].nombreOficio;
                    let idOficio = data[i].idOficio;
                    let oficioAInsertar = `
                                                <option value=${idOficio}>${oficio}</option>`;
                    document.getElementById("selectOficiosAEditar").innerHTML += oficioAInsertar;
                    oficioDePublicacion = idOficio;
                    console.log(idOficio);
                    console.log(selectOficiosAEditar.options[i].value);
                    
                }
            }
    );
   

    let selectOficiosAEditar = document.getElementById("selectOficiosAEditar");
    
    for (var i = 0; i < selectOficiosAEditar.options.length; i++) {
        // Comprobar si el valor de la opción actual coincide con el valor que deseas seleccionar
        if (selectOficiosAEditar.options[i].value === oficioDePublicacion) {

            // Seleccionar la opción correspondiente
            selectOficiosAEditar.selectedIndex = i;
            // Opcionalmente, puedes detener la iteración si solo quieres seleccionar la primera coincidencia
            break;
        }
        console.log("Se seleccióno el oficio:");
    }
}