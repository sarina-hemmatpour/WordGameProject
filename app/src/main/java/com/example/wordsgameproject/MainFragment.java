package com.example.wordsgameproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main , container , false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //levels recycler view
        RecyclerView rvLevels=(RecyclerView) view;
        GridLayoutManager layoutManager=new GridLayoutManager(this.getContext() , 3);
//        layoutManager.setReverseLayout(true);
        rvLevels.setLayoutManager(layoutManager);
        rvLevels.setAdapter(new LevelAdapter(GamePlayUtil.createLevels(),
                new OnRecyclerViewItemClickListener<Level>() {
                    @Override
                    public void onItemClicked(Level item, int position) {
                        Bundle bundle=new Bundle();
                        bundle.putParcelable("level" , item);
                        Navigation.findNavController(getView()).navigate(R.id.action_mainFragment2_to_gameFragment, bundle);
                    }
                }));


    }
}
