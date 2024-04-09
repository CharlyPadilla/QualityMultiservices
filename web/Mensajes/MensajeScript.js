/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

document.addEventListener('DOMContentLoaded', () => {
    const form = document.querySelector('form');
    const inputIdUsuario = document.getElementById('idUsuario');
    const inputMensajeTexto = document.getElementById('mensajeTexto');
    const inputIdChat = document.getElementById('idChat');

    form.addEventListener('submit', async (e) => {
        e.preventDefault();

        const idUsuario = inputIdUsuario.value;
        const mensajeTexto = inputMensajeTexto.value;
        const idChat = inputIdChat.value;

        if (!idUsuario || !mensajeTexto || !idChat) {
            alert('Por favor, completa todos los campos.');
            return;
        }

        try {
            const response = await saveMessage(idUsuario, mensajeTexto, idChat);
            if (response.result === 'Mensaje guardado exitosamente') {
                alert('Mensaje guardado exitosamente.');
                form.reset();
            } else {
                alert('Error al guardar el mensaje.');
            }
        } catch (error) {
            console.error('Error:', error);
            alert('Error en el servidor, favor de intentarlo de nuevo más tarde.');
        }
    });
});

async function saveMessage(idUsuario, mensajeTexto, idChat) {
    const url = 'http://localhost:8080/DSM506_Integradora/api/administrarMensaje/guardarMensaje';

    const data = {
        idUsuario,
        mensajeTexto,
        idChat
    };

    const options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams(new FormData(document.querySelector('form'))).toString()
    };

    try {
        const response = await fetch(url, options);
        const json = await response.json();
        return json;
    } catch (error) {
        console.error('Error:', error);
        throw error;
    }
}


document.addEventListener('DOMContentLoaded', () => {
    const form = document.querySelector('form');
    const inputIdMensaje = document.getElementById('idMensaje');

    form.addEventListener('submit', async (e) => {
        e.preventDefault();

        const idMensaje = inputIdMensaje.value;

        if (!idMensaje) {
            alert('Por favor, ingresa el ID del mensaje.');
            return;
        }

        try {
            const response = await deleteMessage(idMensaje);
            if (response.result === 'Mensaje eliminado exitosamente') {
                alert('Mensaje eliminado exitosamente.');
                form.reset();
            } else {
                alert('Error al eliminar el mensaje.');
            }
        } catch (error) {
            console.error('Error:', error);
            alert('Error en el servidor, favor de intentarlo de nuevo más tarde.');
        }
    });
});

async function deleteMessage(idMensaje) {
    const url = 'http://localhost:8080/DSM506_Integradora/api/administrarMensaje/eliminarMensaje';

    const data = {
        idMensaje
    };

    const options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams(new FormData(document.querySelector('form'))).toString()
    };

    try {
        const response = await fetch(url, options);
        const json = await response.json();
        return json;
    } catch (error) {
        console.error('Error:', error);
        throw error;
    }
}

document.addEventListener('DOMContentLoaded', () => {
    const form = document.querySelector('form');
    const inputIdChat = document.getElementById('idChat');

    form.addEventListener('submit', async (e) => {
        e.preventDefault();

        const idChat = inputIdChat.value;

        if (!idChat) {
            alert('Por favor, ingresa el ID del chat.');
            return;
        }

        try {
            const messages = await getMessages(idChat);
            if (messages) {
                alert('Mensajes obtenidos exitosamente.');
                console.log(messages);
            } else {
                alert('No se encontraron mensajes.');
            }
        } catch (error) {
            console.error('Error:', error);
            alert('Error en el servidor, favor de intentarlo de nuevo más tarde.');
        }
    });
});

async function getMessages(idChat) {
    const url = 'http://localhost:8080/DSM506_Integradora/api/administrarMensaje/obtenerMensajes';

    const data = {
        idChat
    };

    const options = {
        method: 'GET',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        }
    };

    try {
        const response = await fetch(`${url}?${new URLSearchParams(data)}`, options);
        const json = await response.json();
        return json;
    } catch (error) {
        console.error('Error:', error);
        throw error;
    }
}

document.addEventListener('DOMContentLoaded', () => {
    const form = document.querySelector('form');
    const inputIdMensaje = document.getElementById('idMensaje');

    form.addEventListener('submit', async (e) => {
        e.preventDefault();

        const idMensaje = inputIdMensaje.value;

        if (!idMensaje) {
            alert('Por favor, ingresa el ID del mensaje.');
            return;
        }

        try {
            const message = await getMessage(idMensaje);
            if (message) {
                alert('Mensaje obtenido exitosamente.');
                console.log(message);
            } else {
                alert('No se encontró el mensaje.');
            }
        } catch (error) {
            console.error('Error:', error);
            alert('Error en el servidor, favor de intentarlo de nuevo más tarde.');
        }
    });
});

async function getMessage(idMensaje) {
    const url = 'http://localhost:8080/DSM506_Integradora/api/administrarMensaje/obtenerMensaje';

    const data = {
        idMensaje
    };

    const options = {
        method: 'GET',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        }
    };

    try {
        const response = await fetch(`${url}?${new URLSearchParams(data)}`, options);
        const json = await response.json();
        return json;
    } catch (error) {
        console.error('Error:', error);
        throw error;
    }
}

document.addEventListener('DOMContentLoaded', () => {
    const form = document.querySelector('form');
    const inputIdMensaje = document.getElementById('idMensaje');
    const inputMensajeTexto = document.getElementById('mensajeTexto');

    form.addEventListener('submit', async (e) => {
        e.preventDefault();

        const idMensaje = inputIdMensaje.value;
        const mensajeTexto = inputMensajeTexto.value;

        if (!idMensaje || !mensajeTexto) {
            alert('Por favor, ingresa el ID del mensaje y el nuevo texto del mensaje.');
            return;
        }

        try {
            const response = await updateMessage(idMensaje, mensajeTexto);
            if (response.result === 'Mensaje actualizado exitosamente') {
                alert('Mensaje actualizado exitosamente.');
                form.reset();
            } else {
                alert('Error al actualizar el mensaje.');
            }
        } catch (error) {
            console.error('Error:', error);
            alert('Error en el servidor, favor de intentarlo de nuevo más tarde.');
        }
    });
});

async function updateMessage(idMensaje, mensajeTexto) {
    const url = 'http://localhost:8080/DSM506_Integradora/api/administrarMensaje/actualizarMensaje';

    const data = {
        idMensaje,
        mensajeTexto
    };

    const options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams(new FormData(document.querySelector('form'))).toString()
    };

    try {
        const response = await fetch(url, options);
        const json = await response.json();
        return json;
    } catch (error) {
        console.error('Error:', error);
        throw error;
    }
}