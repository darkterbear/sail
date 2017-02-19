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

import io.phoenyx.sail.AddPromiseActivity;
import io.phoenyx.sail.DBHandler;
import io.phoenyx.sail.PromisesAdapter;
import io.phoenyx.sail.R;

/**
 * Created by terrance on 2/18/17.
 */

public class PromisesFragment extends Fragment {
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    DBHandler dbHandler;
    FloatingActionButton addPromiseFAB;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);

        dbHandler = new DBHandler(getActivity());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_promises, container, false);

    addPromiseFAB = (FloatingActionButton) view.findViewById(R.id.addPromiseFAB);

        recyclerView = (RecyclerView) view.findViewById(R.id.promisesRecyclerView);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new PromisesAdapter(dbHandler.getAllPromises()));

        addPromiseFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addPromiseIntent = new Intent(getActivity().getApplicationContext(), AddPromiseActivity.class);
                startActivity(addPromiseIntent);
            }
        });

        return view;
    }
}
