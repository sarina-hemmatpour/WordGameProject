package com.example.wordsgameproject;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GameFragment extends Fragment {

    private Level level;
    private static final String TAG = "GameFragment";
    private RecyclerView rvGuess;
    private CharacterAdaptor rvGuessAdaptor;
    private View guessActionContainer;
    private Button btnCancel;
    private Button btnAccept;
    private RecyclerView rvWords;
    private CharacterAdaptor wordsAdapter;

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

        findViews();

        //char RecyclerView
        ArrayList<CharacterPlaceHolder> characterPlaceHolders=new ArrayList<>();
        ArrayList<Character> uniqueChars=GamePlayUtil.extractUniqueCharacters(level.getWords());
        for (int i = 0; i <uniqueChars.size() ; i++) {
            CharacterPlaceHolder newCharHolder=new CharacterPlaceHolder(uniqueChars.get(i) , true);
            characterPlaceHolders.add(newCharHolder);
        }

        RecyclerView rvChar=getView().findViewById(R.id.rvGameCharacters);
        rvChar.setLayoutManager(new LinearLayoutManager(this.getContext() , RecyclerView.HORIZONTAL , false));

        //recycler view guess
        rvGuess=getView().findViewById(R.id.rvGuess);
        rvGuess.setLayoutManager(new LinearLayoutManager(getContext() , LinearLayoutManager.HORIZONTAL , false));
        rvGuessAdaptor=new CharacterAdaptor();
        rvGuess.setAdapter(rvGuessAdaptor);

        rvChar.setAdapter(new CharacterAdaptor(characterPlaceHolders, new OnRecyclerViewItemClickListener<CharacterPlaceHolder>() {
            @Override
            public void onItemClicked(CharacterPlaceHolder item, int position) {
                guessActionContainer.setVisibility(View.VISIBLE);
                rvGuessAdaptor.addCharacter(item);
            }
        }));

        actionBtnClicked();

        //recycler view words
        rvWords=getView().findViewById(R.id.rvWords);
        int maxLength=level.maxLength();
        rvWords.setLayoutManager(new GridLayoutManager(getContext() , maxLength , RecyclerView.VERTICAL , false));

        ArrayList<CharacterPlaceHolder> chars=new ArrayList<>();
        for (int i = 0; i < level.getWords().size(); i++) {
            for (int j = 0; j < maxLength; j++) {
                CharacterPlaceHolder c;
                //has letter
                if (j<level.getWords().get(i).length())
                {
                    c=new CharacterPlaceHolder(level.getWords().get(i).charAt(j) , false);
                    c.setTag(level.getWords().get(i));
                }
                else
                {
                    c=new CharacterPlaceHolder('0' , false);
                    c.setNull(true);
                }
                chars.add(c);
            }
        }

        wordsAdapter=new CharacterAdaptor(chars);
        rvWords.setAdapter(wordsAdapter);


    }

    public void findViews()
    {
        guessActionContainer=getView().findViewById(R.id.frameGameFragGuessActionContainer);
        btnAccept=getView().findViewById(R.id.btnAccept);
        btnCancel=getView().findViewById(R.id.btnCancel);

    }

    public void actionBtnClicked()
    {
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guessActionContainer.setVisibility(View.GONE);
                rvGuessAdaptor.clear();
            }
        });

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String guess=rvGuessAdaptor.returnWord();

                for (int i = 0; i < level.getWords().size(); i++) {
                    if (guess.equals(level.getWords().get(i)))
                    {
                        Toast.makeText(getContext(),  "کلمه درست حدس زده شد: " + guess.trim(), Toast.LENGTH_SHORT).show();
                        btnCancel.performClick();
                        wordsAdapter.makeWordVisible(guess.trim());
                        return;
                    }
                }

                btnCancel.performClick();
                Toast.makeText(getContext(), "کلمه اشتباه است.", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void correctGuess(String word)
    {
        for (int i = 0; i < wordsAdapter.getItemCount(); i++) {
            if (word.equals(wordsAdapter.getCharacterPlaceHolders().get(i).getTag()))
            {
                wordsAdapter.getCharacterPlaceHolders().get(i).setVisibility(true);
                wordsAdapter.notifyItemChanged(i);
            }
        }
//        wordsAdapter.notifyDataSetChanged();

    }

}
