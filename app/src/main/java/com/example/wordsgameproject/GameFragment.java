package com.example.wordsgameproject;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GameFragment extends Fragment {

    private Level level;
    private static final String TAG = "GameFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_game , container , false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        level=getArguments().getParcelable("level");

        Log.i(TAG, "onCreate: ");

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //char RecyclerView
        ArrayList<CharacterPlaceHolder> characterPlaceHolders=new ArrayList<>();
        ArrayList<Character> uniqueChars=GamePlayUtil.extractUniqueCharacters(level.getWords());
        for (int i = 0; i <uniqueChars.size() ; i++) {
            CharacterPlaceHolder newCharHolder=new CharacterPlaceHolder(uniqueChars.get(i) , true);
            characterPlaceHolders.add(newCharHolder);
        }

        RecyclerView rvChar=getView().findViewById(R.id.rvGameCharacters);
        rvChar.setLayoutManager(new LinearLayoutManager(this.getContext() , RecyclerView.HORIZONTAL , false));
        rvChar.setAdapter(new CharacterAdaptor(characterPlaceHolders, new OnRecyclerViewItemClickListener<CharacterPlaceHolder>() {
            @Override
            public void onItemClicked(CharacterPlaceHolder item, int position) {

            }
        }));


    }
}
