cargarBarraNav();
cargarFooter();
function cargarBarraNav() {
    fetch("BarraYFooter/barraNavSinSesion.html")
            .then(function (callback) {
                return callback.text();
            })
            .then(html => {
                document.getElementById("insert_barraNav").innerHTML = html;
                cargarTickets();
            });
};

function cargarFooter() {
    fetch("BarraYFooter/footer.html")
            .then(function (callback) {
                return callback.text();
            })
            .then(html => {
                document.getElementById("insert_footer").innerHTML = html;
                cargarTickets();
            });
};