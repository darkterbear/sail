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

import io.phoenyx.sail.AddPromiseActivity;
import io.phoenyx.sail.DBHandler;
import io.phoenyx.sail.PromisesAdapter;
import io.phoenyx.sail.R;

public class PromisesFragment extends Fragment {
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    DBHandler dbHandler;
    FloatingActionButton addPromiseFAB;
    TextView noPromisesTextView;

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
        noPromisesTextView = (TextView) view.findViewById(R.id.noPromisesTextView);
        recyclerView = (RecyclerView) view.findViewById(R.id.promisesRecyclerView);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new PromisesAdapter(dbHandler.getAllPromises()));

        if (recyclerView.getAdapter().getItemCount() > 0) {
            noPromisesTextView.setVisibility(View.GONE);
        }

        addPromiseFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addPromiseIntent = new Intent(getActivity().getApplicationContext(), AddPromiseActivity.class);
                startActivityForResult(addPromiseIntent, 1337);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {


        if (resultCode == 1337 || requestCode == 1337) {
            recyclerView.setAdapter(new PromisesAdapter(dbHandler.getAllPromises()));
            if (recyclerView.getAdapter().getItemCount() > 0) {
                noPromisesTextView.setVisibility(View.GONE);
            } else {
                noPromisesTextView.setVisibility(View.VISIBLE);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);

    }
}
