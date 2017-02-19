package io.phoenyx.sail;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by terrance on 2/18/17.
 */

public class PromisesAdapter extends RecyclerView.Adapter<PromisesViewHolder> {

    private List<Promise> promises;
    private DBHandler dbHandler;

    public PromisesAdapter(List<Promise> promises) {
        this.promises = promises;
    }

    @Override
    public PromisesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        dbHandler = new DBHandler(parent.getContext());
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.promise_card, parent, false);
        return new PromisesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final PromisesViewHolder holder, int position) {
        final Promise promise = promises.get(position);
        holder.titleTextView.setText(promise.getTitle());
        holder.descriptionTextView.setText(promise.getDescription());
        holder.durationTextView.setText(promise.getDuration());
        holder.doneImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                promise.setCompleted(true);
                dbHandler.updatePromise(promise);
            }
        });
        holder.starImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (promise.isStarred()) {
                    holder.starImageButton.setBackgroundResource(R.drawable.star_outline);
                    promise.setStarred(false);
                } else {
                    holder.starImageButton.setBackgroundResource(R.drawable.star);
                    promise.setStarred(true);
                }
                dbHandler.updatePromise(promise);

            }
        });
    }

    @Override
    public int getItemCount() {
        return promises.size();
    }
}
