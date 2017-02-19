package io.phoenyx.sail;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by terrance on 2/18/17.
 */

public class PromisesViewHolder extends RecyclerView.ViewHolder {
    protected TextView titleTextView;
    protected TextView descriptionTextView;
    protected TextView dateTextView;
    protected TextView personTextView;
    protected ImageButton starImageButton;
    protected ImageButton doneImageButton;

    public PromisesViewHolder(View itemView) {
        super(itemView);
        titleTextView = (TextView) itemView.findViewById(R.id.promiseTitleTextView);
        descriptionTextView = (TextView) itemView.findViewById(R.id.promiseDescriptionTextView);
        dateTextView = (TextView) itemView.findViewById(R.id.goalDateTextView);
        personTextView = (TextView) itemView.findViewById(R.id.promisePersonTextView);
        starImageButton = (ImageButton) itemView.findViewById(R.id.promiseStarButton);
        doneImageButton = (ImageButton) itemView.findViewById(R.id.promiseDoneButton);
    }
}
