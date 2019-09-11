package com.example.mytodomanager;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mytodomanager.metier.Tache;

import java.util.List;

public class TacheListAdapter extends ArrayAdapter<Tache> {

    public TacheListAdapter(@NonNull Context context, @NonNull List<Tache> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Tache t = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
               .inflate(R.layout.tache_liste_item, parent, false);
        }
        TacheViewHolder viewHolder = (TacheViewHolder)convertView.getTag();
        if (viewHolder == null) {
            viewHolder = new TacheViewHolder();
            viewHolder.titre = convertView.findViewById(R.id.titreTache);
            viewHolder.contexte = convertView.findViewById(R.id.contexteTache);
            viewHolder.priorite = convertView.findViewById(R.id.prioriteIndicateur);
            convertView.setTag(viewHolder);
        }

        viewHolder.priorite.setImageDrawable(new ColorDrawable(t.getPrioriteColor()));
        viewHolder.titre.setText(t.getTitre());
        viewHolder.contexte.setText(t.getContexte());

        return convertView;
    }

    private class TacheViewHolder {
        public TextView titre;
        public TextView contexte;
        public ImageView priorite;
    }
}
