package com.unipu.mobapp.studentplanner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

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
    onBindViewHolder(@NonNull noteyViewholder holder,
                     int position, @NonNull Notey model)
    {

        // Add firstname from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
        holder.noteTitle.setText(model.getNoteTitle());

        // Add lastname from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
        holder.noteDate.setText(model.getNoteDate());

        // Add age from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
        holder.noteDesc.setText(model.getNoteDesc());
    }

    // Function to tell the class about the Card view (here
    // "person.xml")in
    // which the data will be shown
    @NonNull
    @Override
    public noteyViewholder
    onCreateViewHolder(@NonNull ViewGroup parent,
                       int viewType)
    {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notey, parent, false);
        return new noteyViewholder(view);
    }

    // Sub Class to create references of the views in Crad
    // view (here "person.xml")
    class noteyViewholder
            extends RecyclerView.ViewHolder {
        TextView noteTitle, noteDate, noteDesc;
        public noteyViewholder(@NonNull View itemView)
        {
            super(itemView);

            noteTitle = itemView.findViewById(R.id.noteTitle);
            noteDate = itemView.findViewById(R.id.noteDate);
            noteDesc = itemView.findViewById(R.id.noteDesc);
        }
    }
}