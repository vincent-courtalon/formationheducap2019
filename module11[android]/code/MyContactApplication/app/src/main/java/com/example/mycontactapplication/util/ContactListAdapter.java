package com.example.mycontactapplication.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mycontactapplication.R;
import com.example.mycontactapplication.metier.Contact;

import org.w3c.dom.Text;

import java.util.List;

public class ContactListAdapter extends ArrayAdapter<Contact> {

    public ContactListAdapter(@NonNull Context context,
                              @NonNull List<Contact> objects) {
        super(context, 0,  objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Contact contact = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                                        .inflate( R.layout.contact_layout, parent,false);
        }

        ViewHolder viewHolder = (ViewHolder)convertView.getTag();
        if (viewHolder == null) {
            viewHolder = new ViewHolder();
            viewHolder.denomination = convertView.findViewById(R.id.denomination_contact);
            viewHolder.email = convertView.findViewById(R.id.email_contact);
            convertView.setTag(viewHolder);
        }
        viewHolder.denomination.setText(contact.getNom() + " " + contact.getPrenom());
        viewHolder.email.setText(contact.getEmail());
        return convertView;
    }

    private class ViewHolder {
        public TextView denomination;
        public TextView email;
    }
}
