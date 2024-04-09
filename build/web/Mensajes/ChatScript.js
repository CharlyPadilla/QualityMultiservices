/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


function deleteChat(idChat) {
    const url = 'http://localhost:8080/DSM506_Integradora/api/administrarChat/eliminarChat';

    const data = {
        idChat
    };

    const options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams(new FormData(document.querySelector('form'))).toString()
    };

    fetch(url, options)
        .then(function (response) {
            if (!response.ok) {
                throw new Error('Hubo un problema al eliminar el chat.');
            }
            return response.json();
        })
        .then(function (json) {
            if (json.result === 'Chat eliminado exitosamente') {
                alert('Chat eliminado exitosamente.');
            } else {
                alert('Error al eliminar el chat.');
            }
        })
        .catch(function (error) {
            console.error('Error:', error);
            alert('Hubo un error al intentar eliminar el chat. Por favor, intenta de nuevo.');
        });
}

function guardarChat(idCliente, idVendedor, fechaInicioConversacion) {
    const url = 'http://localhost:8080/DSM506_Integradora/api/administrarChat/guardarChat';

    const data = {
        idCliente,
        idVendedor,
        fechaInicioConversacion
    };

    const options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams(new FormData(document.querySelector('form'))).toString()
    };

    fetch(url, options)
        .then(function (response) {
            if (!response.ok) {
                throw new Error('Hubo un problema al guardar el chat.');
            }
            return response.json();
        })
        .then(function (json) {
            if (json.result === 'Chat guardado exitosamente') {
                alert('Chat guardado exitosamente.');
            } else {
                alert('Error al guardar el chat.');
            }
        })
        .catch(function (error) {
            console.error('Error:', error);
            alert('Hubo un error al intentar guardar el chat. Por favor, intenta de nuevo.');
        });
}

function actualizarChat(idChat, idCliente, idVendedor, fechaInicioConversacion) {
    const url = 'http://localhost:8080/DSM506_Integradora/api/administrarChat/actualizarChat';

    const data = {
        idChat,
        idCliente,
        idVendedor,
        fechaInicioConversacion
    };

    const options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams(new FormData(document.querySelector('form'))).toString()
    };

    fetch(url, options)
        .then(function (response) {
            if (!response.ok) {
                throw new Error('Hubo un problema al actualizar el chat.');
            }
            return response.json();
        })
        .then(function (json) {
            if (json.result === 'Chat actualizado exitosamente') {
                alert('Chat actualizado exitosamente.');
            } else {
                alert('Error al actualizar el chat.');
            }
        })
        .catch(function (error) {
            console.error('Error:', error);
            alert('Hubo un error al intentar actualizar el chat. Por favor, intenta de nuevo.');
        });
}

function obtenerChats(idCliente, idVendedor) {
    const url = `http://localhost:8080/DSM506_Integradora/api/administrarChat/obtenerChats?idCliente=${idCliente}&idVendedor=${idVendedor}`;

    fetch(url)
        .then(function (response) {
            if (!response.ok) {
                throw new Error('Hubo un problema al obtener los chats.');
            }
            return response.json();
        })
        .then(function (json) {
            if (json.length > 0) {
                alert('Chats obtenidos exitosamente.');
                console.log(json);
            } else {
                alert('No se encontraron chats.');
            }
        })
        .catch(function (error) {
            console.error('Error:', error);
            alert('Hubo un error al intentar obtener los chats. Por favor, intenta de nuevo.');
        });
}

function obtenerChat(idChat) {
    const url = `http://localhost:8080/DSM506_Integradora/api/administrarChat/obtenerChat?idChat=${idChat}`;

    fetch(url)
        .then(function (response) {
            if (!response.ok) {
                throw new Error('Hubo un problema al obtener el chat.');
            }
            return response.json();
        })
        .then(function (json) {
            if (json) {
                alert('Chat obtenido exitosamente.');
                console.log(json);
            } else {
                alert('No se encontró el chat.');
            }
        })
        .catch(function (error) {
            console.error('Error:', error);
            alert('Hubo un error al intentar obtener el chat. Por favor, intenta de nuevo.');
        });
}

function chatsByClientId(idCliente) {
    const url = `http://localhost:8080/DSM506_Integradora/api/administrarChat/chatsByClientId?idCliente=${idCliente}`;

    fetch(url)
        .then(function (response) {
            if (!response.ok) {
                throw new Error('Hubo un problema al obtener los chats del cliente.');
            }
            return response.json();
        })
        .then(function (json) {
            if (json.length > 0) {
                alert('Chats del cliente obtenidos exitosamente.');
                console.log(json);
            } else {
                alert('No se encontraron chats del cliente.');
            }
        })
        .catch(function (error) {
            console.error('Error:', error);
            alert('Hubo un error al intentar obtener los chats del cliente. Por favor, intenta de nuevo.');
        });
}

function chatsByVendorId(idVendedor) {
    const url = `http://localhost:8080/DSM506_Integradora/api/administrarChat/chatsByVendorId?idVendedor=${idVendedor}`;

    fetch(url)
        .then(function (response) {
            if (!response.ok) {
                throw new Error('Hubo un problema al obtener los chats del vendedor.');
            }
            return response.json();
        })
        .then(function (json) {
            if (json.length > 0) {
                alert('Chats del vendedor obtenidos exitosamente.');
                console.log(json);
            } else {
                alert('No se encontraron chats del vendedor.');
            }
        })
        .catch(function (error) {
            console.error('Error:', error);
            alert('Hubo un error al intentar obtener los chats del vendedor. Por favor, intenta de nuevo.');
        });
}

function chatMessages(idChat) {
    const url = `http://localhost:8080/DSM506_Integradora/api/administrarChat/chatMessages?idChat=${idChat}`;

    fetch(url)
        .then(function (response) {
            if (!response.ok) {
                throw new Error('Hubo un problema al obtener los mensajes del chat.');
            }
            return response.json();
        })
        .then(function (json) {
            if (json.length > 0) {
                alert('Mensajes del chat obtenidos exitosamente.');
                console.log(json);
            } else {
                alert('No se encontraron mensajes del chat.');
            }
        })
        .catch(function (error) {
            console.error('Error:', error);
            alert('Hubo un error al intentar obtener los mensajes del chat. Por favor, intenta de nuevo.');
        });
}

function sendMessage(idChat, idUsuario, mensaje) {
    const url = 'http://localhost:8080/DSM506_Integradora/api/administrarChat/sendMessage';

    const options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams({
            idChat: idChat,
            idUsuario: idUsuario,
            mensaje: mensaje
        }).toString()
    };

    fetch(url, options)
        .then(function (response) {
            if (!response.ok) {
                throw new Error('Hubo un problema al enviar el mensaje.');
            }
            return response.json();
        })
        .then(function (json) {
            if (json.result === 'Mensaje enviado exitosamente') {
                alert('Mensaje enviado exitosamente.');
            } else {
                alert('Error al enviar el mensaje.');
            }
        })
        .catch(function (error) {
            console.error('Error:', error);
            alert('Hubo un error al intentar enviar el mensaje. Por favor, intenta de nuevo.');
        });
}


function markMessageAsRead(idMensaje) {
    const url = 'http://localhost:8080/DSM506_Integradora/api/administrarChat/markMessageAsRead';

    const options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams({
            idMensaje: idMensaje
        }).toString()
    };

    fetch(url, options)
        .then(function (response) {
            if (!response.ok) {
                throw new Error('Hubo un problema al marcar el mensaje como leído.');
            }
            return response.json();
        })
        .then(function (json) {
            if (json.result === 'Mensaje marcado como leído exitosamente') {
                alert('Mensaje marcado como leído exitosamente.');
            } else {
                alert('Error al marcar el mensaje como leído.');
            }
        })
        .catch(function (error) {
            console.error('Error:', error);
            alert('Hubo un error al intentar marcar el mensaje como leído. Por favor, intenta de nuevo.');
        });
}

function unreadMessagesCount(idUsuario) {
    const url = 'http://localhost:8080/DSM506_Integradora/api/administrarChat/unreadMessagesCount';

    const options = {
        method: 'GET',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        }
    };

    fetch(url + '?idUsuario=' + idUsuario, options)
        .then(function (response) {
            if (!response.ok) {
                throw new Error('Hubo un problema al obtener el recuento de mensajes no leídos.');
            }
            return response.json();
        })
        .then(function (json) {
            if (json.result === 'Recuento de mensajes no leídos obtenido exitosamente') {
                alert('Recuento de mensajes no leídos obtenido exitosamente.');
                console.log('Recuento de mensajes no leídos:', json.data.unreadMessagesCount);
            } else {
                alert('Error al obtener el recuento de mensajes no leídos.');
            }
        })
        .catch(function (error) {
            console.error('Error:', error);
            alert('Hubo un error al intentar obtener el recuento de mensajes no leídos. Por favor, intenta de nuevo.');
        });
}

function chatsWithUnreadMessages(idUsuario) {
    const url = 'http://localhost:8080/DSM506_Integradora/api/administrarChat/chatsWithUnreadMessages';

    const options = {
        method: 'GET',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        }
    };

    fetch(url + '?idUsuario=' + idUsuario, options)
        .then(function (response) {
            if (!response.ok) {
                throw new Error('Hubo un problema al obtener los chats con mensajes no leídos.');
            }
            return response.json();
        })
        .then(function (json) {
            if (json.result === 'Chats con mensajes no leídos obtenidos exitosamente') {
                alert('Chats con mensajes no leídos obtenidos exitosamente.');
                console.log('Chats con mensajes no leídos:', json.data.chatsWithUnreadMessages);
            } else {
                alert('Error al obtener los chats con mensajes no leídos.');
            }
        })
        .catch(function (error) {
            console.error('Error:', error);
            alert('Hubo un error al intentar obtener los chats con mensajes no leídos. Por favor, intenta de nuevo.');
        });
}

function deleteChatHistory(idChat) {
    const url = 'http://localhost:8080/DSM506_Integradora/api/administrarChat/deleteChatHistory';

    const options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams({
            idChat: idChat
        }).toString()
    };

    fetch(url, options)
        .then(function (response) {
            if (!response.ok) {
                throw new Error('Hubo un problema al eliminar el historial de chat.');
            }
            return response.json();
        })
        .then(function (json) {
            if (json.result === 'Historial de chat eliminado exitosamente') {
                alert('Historial de chat eliminado exitosamente.');
            } else {
                alert('Error al eliminar el historial de chat.');
            }
        })
        .catch(function (error) {
            console.error('Error:', error);
            alert('Hubo un error al intentar eliminar el historial de chat. Por favor, intenta de nuevo.');
        });
}

function updateChatDetails(idChat, idCliente, idVendedor, fechaInicioConversacion) {
    const url = 'http://localhost:8080/DSM506_Integradora/api/administrarChat/updateChatDetails';

    const options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams({
            idChat: idChat,
            idCliente: idCliente,
            idVendedor: idVendedor,
            fechaInicioConversacion: fechaInicioConversacion
        }).toString()
    };

    fetch(url, options)
        .then(function (response) {
            if (!response.ok) {
                throw new Error('Hubo un problema al actualizar los detalles del chat.');
            }
            return response.json();
        })
        .then(function (json) {
            if (json.result === 'Detalles del chat actualizados exitosamente') {
                alert('Detalles del chat actualizados exitosamente.');
            } else {
                alert('Error al actualizar los detalles del chat.');
            }
        })
        .catch(function (error) {
            console.error('Error:', error);
            alert('Hubo un error al intentar actualizar los detalles del chat. Por favor, intenta de nuevo.');
        });
}