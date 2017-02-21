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

import io.phoenyx.sail.AddTimelineEventActivity;
import io.phoenyx.sail.DBHandler;
import io.phoenyx.sail.R;
import io.phoenyx.sail.TimelineEventsAdapter;

public class TimelineFragment extends Fragment {
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    DBHandler dbHandler;
    FloatingActionButton addTimelineEventFAB;
    TextView noTimelineEventsTextView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);

        dbHandler = new DBHandler(getActivity());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timeline, container, false);

        addTimelineEventFAB = (FloatingActionButton) view.findViewById(R.id.addTimelineEventFAB);
        noTimelineEventsTextView = (TextView) view.findViewById(R.id.noTimelineEventsTextView);
        recyclerView = (RecyclerView) view.findViewById(R.id.timelineEventsRecyclerView);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new TimelineEventsAdapter(dbHandler.getAllTimelineEvents()));

        if (recyclerView.getAdapter().getItemCount() > 0) {
            noTimelineEventsTextView.setVisibility(View.GONE);
        }

        addTimelineEventFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addTimelineEventIntent = new Intent(getActivity().getApplicationContext(), AddTimelineEventActivity.class);
                startActivityForResult(addTimelineEventIntent, 1337);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {


        if (resultCode == 1337 || requestCode == 1337) {
            recyclerView.setAdapter(new TimelineEventsAdapter(dbHandler.getAllTimelineEvents()));
            if (recyclerView.getAdapter().getItemCount() > 0) {
                noTimelineEventsTextView.setVisibility(View.GONE);
            } else {
                noTimelineEventsTextView.setVisibility(View.VISIBLE);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);

    }
}
