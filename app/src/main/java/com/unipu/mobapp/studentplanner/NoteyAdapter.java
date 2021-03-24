package com.unipu.mobapp.studentplanner;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.io.Serializable;

// FirebaseRecyclerAdapter is a class provided by
// FirebaseUI. it provides functions to bind, adapt and show
// database contents in a Recycler View
public class NoteyAdapter extends FirebaseRecyclerAdapter<Notey, NoteyAdapter.noteyViewholder> {

    public NoteyAdapter(
            @NonNull FirebaseRecyclerOptions<Notey> options)
    {
        super(options);
    }

    // Function to bind the view in Card view(here
    // "person.xml") iwth data in
    // model class(here "person.class")
    @Override
    protected void
    onBindViewHolder(@NonNull final noteyViewholder holder,
                     int position, @NonNull Notey model)
    {

        // Add firstname from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
        holder.noteTitle.setText(String.valueOf(model.getNoteTitle()));

        holder.noteDate.setText(String.valueOf(model.getNoteDate()));

        holder.noteDesc.setText(String.valueOf(model.getNoteDesc()));

        //dodano za povlacenje iz baze u edit
        final String noteTitle = String.valueOf(model.getNoteTitle());
        final String noteDate = String.valueOf(model.getNoteDate());
        final String noteDesc = String.valueOf(model.getNoteDesc());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent intent = new Intent (v.getContext(), EditNotesActivity.class);

                    //dodano za povlacenje iz baze u edit
                    intent.putExtra("noteTitle", String.valueOf(noteTitle));
                    intent.putExtra("noteDate", String.valueOf(noteDate));
                    intent.putExtra("noteDesc", String.valueOf(noteDesc));
                    v.getContext().startActivity(intent);

                }

        });

    }

   
    // Function to tell the class about the Card view (here
    // "person.xml")in
    // which the data will be shown
    @NonNull
    @Override
    public noteyViewholder
    onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notey, parent, false);
        return new noteyViewholder(view);
    }

    // Sub Class to create references of the views in Crad
    // view (here "person.xml")
    class noteyViewholder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        TextView noteTitle, noteDate, noteDesc;
        public noteyViewholder(@NonNull View itemView)
        {
            super(itemView);
            itemView.setClickable(true);
            itemView.setOnClickListener(this);

            noteTitle = itemView.findViewById(R.id.noteTitle);
            noteDate = itemView.findViewById(R.id.noteDate);
            noteDesc = itemView.findViewById(R.id.noteDesc);




        }

//ovaj dio je mozda visak je je vec gore dodan ,ali ga za sada ne brisem jer kad ga obrisem javlja error
// zbog class noteyViewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent (v.getContext(), EditNotesActivity.class);

            //dodano za povlacenje iz baze u edit
            intent.putExtra("noteTitle", String.valueOf(noteTitle));
            intent.putExtra("noteDate", String.valueOf(noteDate));
            intent.putExtra("noteDesc", String.valueOf(noteDesc));
                    v.getContext().startActivity(intent);

        }
       
    }

}