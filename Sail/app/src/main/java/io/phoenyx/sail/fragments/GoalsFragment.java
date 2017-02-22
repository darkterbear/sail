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

import io.phoenyx.sail.AddGoalActivity;
import io.phoenyx.sail.DBHandler;
import io.phoenyx.sail.GoalsAdapter;
import io.phoenyx.sail.R;

public class GoalsFragment extends Fragment {

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    DBHandler dbHandler;
    FloatingActionButton addGoalFAB;
    TextView noGoalsTextView;
    OnItemTouchListener onItemTouchListener;

    public interface OnItemTouchListener {
        public void onStarClick(View view, int position, boolean isStarred);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);

        dbHandler = new DBHandler(getActivity());

        onItemTouchListener = new OnItemTouchListener() {
            @Override
            public void onStarClick(View view, int position, boolean isStarred) {
                //TODO handle star click
            }
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goals, container, false);

        addGoalFAB = (FloatingActionButton) view.findViewById(R.id.addGoalFAB);
        noGoalsTextView = (TextView) view.findViewById(R.id.noGoalsTextView);
        recyclerView = (RecyclerView) view.findViewById(R.id.goalsRecyclerView);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        refreshAdapter();

        addGoalFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addGoalIntent = new Intent(getActivity().getApplicationContext(), AddGoalActivity.class);
                startActivityForResult(addGoalIntent, 1337);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == 1337 || requestCode == 1337) {
            refreshAdapter();
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    private void refreshAdapter() {
        recyclerView.setAdapter(new GoalsAdapter(dbHandler.getAllGoals(), onItemTouchListener));
        if (recyclerView.getAdapter().getItemCount() > 0) {
            noGoalsTextView.setVisibility(View.GONE);
        } else {
            noGoalsTextView.setVisibility(View.VISIBLE);
        }
    }
}
