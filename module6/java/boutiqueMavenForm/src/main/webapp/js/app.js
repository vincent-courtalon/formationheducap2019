$(document).ready(function(){
    console.log("demarrage de l'application");
    requestFilms("");
    //checkSearch();
    $("#createForm").hide();
    $("#btShow").click(function() {
        $("#createForm").slideToggle();
    });
    // click sur le bouton du formulaire
    $("#addFilm").click(addFilm);
});

function addFilm() {
    var titre = $("input#titre").val();
    var annee = $("input#annee").val();
    var longueur = $("input#longueur").val();
    var genre = $("input#genre").val();

    jQuery.post('film', 
                { "titre" : titre,
                  "annee" : annee,
                  "longueur" : longueur,
                  "genre" : genre },
                function(data) {
                    // quand reponse du serveur
                    console.log("nb lignes = " + data.nblignesSauvees);
                    forceRefresh = true;
                });
    return false;
}
// force le rafraichissement de la liste au prochain checkSearch
var forceRefresh = false;
var searchPrevious = "";

function checkSearch() {
    var newsearch = $("input#search").val();
    if (newsearch != searchPrevious || forceRefresh) {
        forceRefresh = false;
        console.log("nouvelle recherche: " + newsearch);
        searchPrevious = newsearch;
        requestFilms(newsearch);
    }
    else {
        console.log("pas de changement");
        // revérifier dans une seconde
        window.setTimeout(checkSearch, 1000);
    }
}

function requestFilms(search) {
    var url = 'film';
    if (search != null && search.length > 0) {
        url += '?search=' + search;
    }
    jQuery.getJSON(url, function(data) {
        fillFilmTable(data);
        window.setTimeout(checkSearch, 1000);
    });
}

function fillFilmTable(data) {
    console.log(data);
    var tbody = $("table#tableFilms tbody");
    tbody.empty();
    for(let film of data) {
        var tr = $("<tr></tr>");
        tr.append("<td>" + film.id + "</td>");
        tr.append("<td>" + film.titre + "</td>");
        tr.append("<td>" + film.longueur + "</td>");
        tr.append("<td>" + film.annee + "</td>");
        tr.append("<td>" + film.genre + "</td>");
        tbody.append(tr);
    }
}