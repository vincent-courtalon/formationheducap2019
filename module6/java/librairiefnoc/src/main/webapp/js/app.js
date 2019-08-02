$(document).ready(function() {
    console.log("demarrage...");
    requestLivres("");
    $("#createForm").hide();
    $("#btShow").click(function() {
        fillFormWith(0, "", "", 0, "");
        $("#createForm").slideToggle();
    });
    $("#saveLivre").click(saveLivre);
    $("#searchform button").click(function() {
        $("input#search").val("");
        if (typesearch == "titre") {
            typesearch = "nbpages";
            $("#searchform button").text("par titre");
            $("#searchform label").text("recherche par nbPages ");
        }
        else {
            typesearch = "titre";
            $("#searchform button").text("par nb pages");
            $("#searchform label").text("recherche par titre ");
        }
        return false;
    });
});

var typesearch = "titre";
var forceRefresh = false;
var searchPrevious = "";


function fillFormWith(id, titre, isbn, nbPages, auteur) {
    $("input#id").val(id);
    $("input#titre").val(titre);
    $("input#isbn").val(isbn);
    $("input#nbPages").val(nbPages);
    $("input#auteur").val(auteur);
}

function saveLivre() {
    var id = $("input#id").val();
    var titre = $("input#titre").val();
    var isbn = $("input#isbn").val();
    var nbPages = $("input#nbPages").val();
    var auteur = $("input#auteur").val();

    jQuery.post('livre', 
                { "id" : id,
                  "titre" : titre,
                  "isbn" : isbn,
                  "nbPages" : nbPages,
                  "auteur" : auteur },
                function(data) {
                    // quand reponse du serveur
                    console.log("nb lignes = " + data.nblignesSauvees);
                    forceRefresh = true;
                    $("#createForm").slideToggle();
                });
    return false;
}




function checkSearch() {
    var newsearch = $("input#search").val();
    if (newsearch != searchPrevious || forceRefresh) {
        forceRefresh = false;
        console.log("nouvelle recherche: " + newsearch);
        searchPrevious = newsearch;
        requestLivres(newsearch);
    }
    else {
        console.log("pas de changement");
        // revérifier dans une seconde
        window.setTimeout(checkSearch, 1000);
    }
}



function requestLivres(search) {
    var url = 'livre';
    if (search != null && search.length > 0) {
        if (typesearch == "titre") {
            url += '?search=' + search;
        }
        else {
            url += '?nbPages=' + search;
        }
    }
    jQuery.getJSON(url, function(data) {
        fillLivreTable(data);
        window.setTimeout(checkSearch, 1000);
    });
}

function fillLivreTable(data) {
    console.log(data);
    var tbody = $("table#tableLivres tbody");
    tbody.empty();
    for(let livre of data) {
        var tr = $("<tr></tr>");
        tr.append("<td>" + livre.id + "</td>");
        tr.append("<td>" + livre.titre + "</td>");
        tr.append("<td>" + livre.isbn + "</td>");
        tr.append("<td>" + livre.nbPages + "</td>");
        tr.append("<td>" + livre.auteur + "</td>");
        var td = $("<td></td>");
        var editButton = $("<button class='btn btn-primary'>editer</button>");
        td.append(editButton);
        tr.append(td);
        editButton.click(function() {
           let editlivre = livre;
           //console.log(editlivre);
           fillFormWith(editlivre.id,
                        editlivre.titre,
                        editlivre.isbn,
                        editlivre.nbPages,
                        editlivre.auteur);
            $("#createForm").slideDown();
        });
        // td avec bouton
        tbody.append(tr);
    }
}

