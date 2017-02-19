package io.phoenyx.sail;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by terrance on 2/18/17.
 */

public class AchievementsViewHolder extends RecyclerView.ViewHolder {
    protected TextView titleTextView;
    protected TextView descriptionTextView;
    protected TextView dateTextView;
    protected ImageButton starImageButton;

    public AchievementsViewHolder(View itemView) {
        super(itemView);
        titleTextView = (TextView) itemView.findViewById(R.id.achievementTitleTextView);
        descriptionTextView = (TextView) itemView.findViewById(R.id.achievementDescriptionTextView);
        dateTextView = (TextView) itemView.findViewById(R.id.goalDateTextView);
        starImageButton = (ImageButton) itemView.findViewById(R.id.achievementStarButton);
    }
}
