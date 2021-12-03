package com.example.jokesapart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE= 1;
    private final Context context;
    private final List<Object>listRecyclerItem;
    private RecyclerViewClickListener listener;

    public RecyclerAdapter(Context context, List<Object> listRecyclerItem, RecyclerViewClickListener listener) {
        this.context = context;
        this.listRecyclerItem = listRecyclerItem;
        this.listener=listener;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView jokeid;
        private TextView type;
        private TextView description;
       private TextView punch;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            jokeid= itemView.findViewById(R.id.jokeid);
            type= itemView.findViewById(R.id.joketype);
            description = itemView.findViewById(R.id.jokeinfo);
            punch= itemView.findViewById(R.id.jokepunch);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View itemView) {

            listener.onClick(itemView,getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        switch (i){
            case TYPE:
            default:
                View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                        R.layout.list_item,viewGroup,false);

                return new ItemViewHolder((layoutView));
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        int viewType = getItemViewType(i);

        switch (viewType){

            case TYPE:
            default:
                ItemViewHolder itemViewHolder = (ItemViewHolder) viewHolder;
                Jokes jokes= (Jokes) listRecyclerItem.get(i);

                itemViewHolder.jokeid.setText("Joke ID: " +jokes.getJokeID());
                //itemViewHolder.type.setText("Joke Type: " + jokes.getJokeType());
                itemViewHolder.description.setText("Setup: " + jokes.getJokeDes());
                itemViewHolder.punch.setText("Punchline: " + jokes.getJokePunchline());

        }
    }

    @Override
    public int getItemCount() {
        return listRecyclerItem.size();
    }

    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }
}
