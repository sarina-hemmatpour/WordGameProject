package com.example.wordsgameproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LevelAdapter extends RecyclerView.Adapter<LevelAdapter.LevelViewHolder> {

    private ArrayList<Level> levels;
    private OnRecyclerViewItemClickListener<Level> listener;

    public ArrayList<Level> getLevels() {
        return levels;
    }

    public void setLevels(ArrayList<Level> levels) {
        this.levels = levels;
    }

    public LevelAdapter(ArrayList<Level> levels , OnRecyclerViewItemClickListener<Level> listener) {
        this.levels = levels;
        this.listener=listener;
    }

    @NonNull
    @Override
    public LevelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_level , parent , false);
        return new LevelViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull LevelViewHolder holder, int position) {
        holder.onBindView(position);
    }

    @Override
    public int getItemCount() {
        return levels.size();
    }

    public class LevelViewHolder extends RecyclerView.ViewHolder{

        TextView tvLevel;
        public LevelViewHolder(@NonNull View itemView) {
            super(itemView);

            tvLevel=itemView.findViewById(R.id.tvLevelNumber);

        }

        public void onBindView(int index)
        {
            tvLevel.setText(String.valueOf(levels.get(index).getId()));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClicked(levels.get(index) , index);
                }
            });
        }
    }

}
