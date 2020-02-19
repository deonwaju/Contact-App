package com.example.androidarchi.note;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.androidarchi.R;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {

    private List<Note> notes = new ArrayList<>();
    public OnItemClickListener listener;

    @Override
    public NoteAdapter.NoteHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);

        return new NoteHolder(view);
    }

    @Override
    public void onBindViewHolder(NoteAdapter.NoteHolder holder, int position) {

        Note currentNote = notes.get(position);

        holder.textTitle.setText(currentNote.getTitle());
        holder.textDescription.setText(currentNote.getDescription());
        holder.textPriority.setText(String.valueOf(currentNote.getPriority()));

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    public Note getNoteAt(int position) {
        return notes.get(position);
    }

    public class NoteHolder extends RecyclerView.ViewHolder {

        private TextView textTitle;
        private TextView textDescription;
        private TextView textPriority;

        public NoteHolder(View itemView) {
            super(itemView);

            textTitle = itemView.findViewById(R.id.text_view_title);
            textDescription = itemView.findViewById(R.id.text_view_description);
            textPriority = itemView.findViewById(R.id.text_view_priority);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.OnItemClick(notes.get(position));
                    }
                }
            });

        }
    }

    public interface OnItemClickListener {
        void OnItemClick(Note note);
    }

    public void SetOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
