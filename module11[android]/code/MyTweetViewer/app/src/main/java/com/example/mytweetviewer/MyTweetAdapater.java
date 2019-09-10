package com.example.mytweetviewer;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MyTweetAdapater extends ArrayAdapter<Tweet> {

    public MyTweetAdapater(@NonNull Context context,  @NonNull List<Tweet> tweets) {
        super(context, 0, tweets);

    }


    // la méthode getView est appelée par le composant graphique ListView
    // elle est appelée quand une nouvelle ligne doit apparaitre a l'affichage
    // -> soit nouveau tweet
    // -> soit scroll de la liste
    // pour des raison de performance, le ListView veux, si possible, réutiliser
    // les composants graphique (d'une ligne) déjà existant
    // quand la listView a besoin d'afficher une nouvelle ligne, 2 cas se présente
    // 1-> il n'y a pas de vue "recyclable" à disposition, a ce moment le convertViewe
    //      sera a NULL, c.a.d il faut creer une nouvelle vue pour cette ligne
    // 2 -> il y a une vue prête à etre recyclée, il me la passe en parmetre
    //      dans convertView, je la réutilise avec les données de la nouvelle ligne apparaissant
    // autres parametres:
    //  position: l'index du tweet dans la collection
    // parent: ici notre ListView
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // je récuper le tweet a afficher
        Tweet t = getItem(position);
        if (convertView == null) {
            // il faut creer une vue
            // en général, c'est au démarrage
            Log.i("listview", "creation d'une vue pour une ligne du listView");
            convertView = LayoutInflater.from(getContext())
                                        .inflate(R.layout.tweet_layout, parent, false);
        }
        else {
            //recyclage de vue
            Log.i("listview", "recyclage d'une vue pour une ligne du listView");
        }

        // la propriété "tag" d'une vue est une sorte de fourre-tout, ou vous
        // pouvez stocker un objet associé de votre choix
        // je vais l'utiliser pour me rappeler des composant de la ligne sans
        // avoir a les rechercher a chaque fois
        // c'est une optimisation, pour eviter d'appeler trop findViewById
        // evidement, si c'est une nouvelle vue, le TweetViewHolder ne sera pas existant
        TweetViewHolder viewHolder = (TweetViewHolder)convertView.getTag();
        if (viewHolder == null) {
            // c'est une nouvelle ligne, je ne connais pas encore ses composants
            viewHolder = new TweetViewHolder();
            viewHolder.pseudo = convertView.findViewById(R.id.pseudo);
            viewHolder.texte = convertView.findViewById(R.id.texte);
            viewHolder.avatar = convertView.findViewById(R.id.avatar);
            // je l'associe a ma view/ligne
            convertView.setTag(viewHolder);
        }
        // avec mon viewHolder, j'ai mes 3 composants a disposition
        // plus qu'a les remplir avec les données
        viewHolder.pseudo.setText(t.getPseudo());
        viewHolder.texte.setText(t.getTexte());
        // rectangle plein
        viewHolder.avatar.setImageDrawable(new ColorDrawable(t.getColor()));

        return convertView;
    }

    // pas indispensable, mais mieux/recommandé pour les performances
    private class TweetViewHolder {
        public TextView pseudo;
        public TextView texte;
        public ImageView avatar;
    }
}
