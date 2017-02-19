package io.phoenyx.sail;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by terrance on 2/18/17.
 */

public class GoalsViewHolder extends RecyclerView.ViewHolder {
    protected TextView titleTextView;
    protected TextView descriptionTextView;
    protected TextView dateTextView;
    protected ImageButton starImageButton;
    protected ImageButton doneImageButton;

    public GoalsViewHolder(View itemView) {
        super(itemView);
        titleTextView = (TextView) itemView.findViewById(R.id.goalTitleTextView);
        descriptionTextView = (TextView) itemView.findViewById(R.id.goalDescriptionTextView);
        dateTextView = (TextView) itemView.findViewById(R.id.goalDateTextView);
        starImageButton = (ImageButton) itemView.findViewById(R.id.goalStarButton);
        doneImageButton = (ImageButton) itemView.findViewById(R.id.goalDoneButton);
    }
}
