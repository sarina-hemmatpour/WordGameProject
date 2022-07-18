package com.example.wordsgameproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CharacterAdaptor extends RecyclerView.Adapter<CharacterAdaptor.CharViewHolder> {


    private ArrayList<CharacterPlaceHolder> characterPlaceHolders;
    private OnRecyclerViewItemClickListener<CharacterPlaceHolder> listener;

    public ArrayList<CharacterPlaceHolder> getCharacterPlaceHolders() {
        return characterPlaceHolders;
    }

    public void setCharacterPlaceHolders(ArrayList<CharacterPlaceHolder> characterPlaceHolders) {
        this.characterPlaceHolders = characterPlaceHolders;
    }

    public OnRecyclerViewItemClickListener<CharacterPlaceHolder> getListener() {
        return listener;
    }

    public void setListener(OnRecyclerViewItemClickListener<CharacterPlaceHolder> listener) {
        this.listener = listener;
    }

    public CharacterAdaptor() {
        characterPlaceHolders=new ArrayList<>();
    }

    public CharacterAdaptor(ArrayList<CharacterPlaceHolder> characterPlaceHolders) {
        this.characterPlaceHolders = characterPlaceHolders;
    }

    public CharacterAdaptor(ArrayList<CharacterPlaceHolder> characterPlaceHolders ,
                            OnRecyclerViewItemClickListener<CharacterPlaceHolder> listener ) {
        this.characterPlaceHolders = characterPlaceHolders;
        this.listener=listener;
    }

    @NonNull
    @Override
    public CharViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CharViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_char , parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull CharViewHolder holder, int position) {
        holder.binder(position);
    }

    @Override
    public int getItemCount() {
        return characterPlaceHolders.size();
    }

    public class CharViewHolder extends RecyclerView.ViewHolder {

        TextView tvChar;

        public CharViewHolder(@NonNull View itemView) {
            super(itemView);
            tvChar=itemView.findViewById(R.id.tvChar);
        }
        public void binder(int position){
            if (!characterPlaceHolders.get(position).isNull())
            {
                if (characterPlaceHolders.get(position).isVisibility())
                {
                    tvChar.setVisibility(View.VISIBLE);
                }
                else
                {
                    tvChar.setVisibility(View.INVISIBLE);
                }
                tvChar.setText(characterPlaceHolders.get(position).getCharacter().toString());
            }
            else
            {
                itemView.setBackground(null);
            }


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener!=null)
                    {
                        listener.onItemClicked(characterPlaceHolders.get(position) , position);
                    }
                }
            });

        }
    }


    public void addCharacter(CharacterPlaceHolder item)
    {
        getCharacterPlaceHolders().add(item);
        notifyItemInserted(characterPlaceHolders.size()-1);
    }

    public void clear(){
        characterPlaceHolders.clear();
        notifyDataSetChanged();
    }

}
