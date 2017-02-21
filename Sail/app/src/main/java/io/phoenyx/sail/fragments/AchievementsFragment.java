package io.phoenyx.sail.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.phoenyx.sail.AchievementsAdapter;
import io.phoenyx.sail.AddAchievementActivity;
import io.phoenyx.sail.DBHandler;
import io.phoenyx.sail.R;

public class AchievementsFragment extends Fragment {
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    DBHandler dbHandler;
    FloatingActionButton addAchievementFAB;
    TextView noAchievementsTextView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);

        dbHandler = new DBHandler(getActivity());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_achievements, container, false);

        addAchievementFAB = (FloatingActionButton) view.findViewById(R.id.addAchievementFAB);
        noAchievementsTextView = (TextView) view.findViewById(R.id.noAchievementsTextView);
        recyclerView = (RecyclerView) view.findViewById(R.id.achievementsRecyclerView);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new AchievementsAdapter(dbHandler.getAllAchievements()));

        if (recyclerView.getAdapter().getItemCount() > 0) {
            noAchievementsTextView.setVisibility(View.GONE);
        }

        addAchievementFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addAchievementIntent = new Intent(getActivity().getApplicationContext(), AddAchievementActivity.class);
                startActivityForResult(addAchievementIntent, 1337);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {


        if (resultCode == 1337 || requestCode == 1337) {
            recyclerView.setAdapter(new AchievementsAdapter(dbHandler.getAllAchievements()));
            if (recyclerView.getAdapter().getItemCount() > 0) {
                noAchievementsTextView.setVisibility(View.GONE);
            } else {
                noAchievementsTextView.setVisibility(View.VISIBLE);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);

    }
}
