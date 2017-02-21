package io.phoenyx.sail;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class PromisesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    protected TextView titleTextView;
    protected TextView descriptionTextView;
    protected TextView dateTextView;
    protected TextView personTextView;
    protected ImageButton starImageButton;
    protected ImageButton doneImageButton;
    int promiseID;

    public PromisesViewHolder(View itemView) {
        super(itemView);
        titleTextView = (TextView) itemView.findViewById(R.id.promiseTitleTextView);
        descriptionTextView = (TextView) itemView.findViewById(R.id.promiseDescriptionTextView);
        dateTextView = (TextView) itemView.findViewById(R.id.goalDateTextView);
        personTextView = (TextView) itemView.findViewById(R.id.promisePersonTextView);
        starImageButton = (ImageButton) itemView.findViewById(R.id.promiseStarButton);
        doneImageButton = (ImageButton) itemView.findViewById(R.id.promiseDoneButton);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent editPromise = new Intent(view.getContext().getApplicationContext(), EditPromiseActivity.class);
        editPromise.putExtra("promise_id", promiseID);
        ((Activity) view.getContext()).startActivityForResult(editPromise, 1337);
    }
}
