
TODOMANAGER = {
    "init" : function () {
        //mettre en place les listener de click
        TODOMANAGER.todolist = [];
        TODOMANAGER.champTitre = document.getElementById("titre");
        TODOMANAGER.champDescription = document.getElementById("description");
        TODOMANAGER.champPriorite = document.getElementById("priorite");
        TODOMANAGER.divListe = document.getElementById("todoliste");
        document.getElementById("addTodo")
                .addEventListener("click", TODOMANAGER.creerTodo);

    },
    "creerTodo" : function(evt) {
        TODOMANAGER.todolist.push(new Todo(
                TODOMANAGER.champTitre.value,
                TODOMANAGER.champDescription.value,
                Number(TODOMANAGER.champPriorite.value))
                );
        TODOMANAGER.refreshList();
        evt.preventDefault();
        return false;
    },
    "refreshList" : function() {
        TODOMANAGER.todolist.sort(function(td1, td2) {
            if (td1.termine == td2.termine) {
                if (td1.priorite > td2.priorite) return 1;
                if (td1.priorite < td2.priorite) return -1;
                return 0;
            }
            if (td1.termine) return 1;
            return -1;
        });
        TODOMANAGER.divListe.innerHTML = "";
        for (var idx in TODOMANAGER.todolist) {
            var div = TODOMANAGER.todolist[idx].createDiv();
            TODOMANAGER.divListe.appendChild(div);
            div.addEventListener("click", TODOMANAGER.createClickListener(idx));
        }
    },
    "createClickListener" : function(pos) {
        return function() {
            if (TODOMANAGER.todolist[pos].clickOnDiv()) {
                TODOMANAGER.todolist.splice(pos, 1);
            }
            TODOMANAGER.refreshList();
        }
    }
}


