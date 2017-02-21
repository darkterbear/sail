package io.phoenyx.sail;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class AchievementsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    protected TextView titleTextView;
    protected TextView descriptionTextView;
    protected TextView dateTextView;
    protected ImageButton starImageButton;
    int achievementID;

    public AchievementsViewHolder(View itemView) {
        super(itemView);
        titleTextView = (TextView) itemView.findViewById(R.id.achievementTitleTextView);
        descriptionTextView = (TextView) itemView.findViewById(R.id.achievementDescriptionTextView);
        dateTextView = (TextView) itemView.findViewById(R.id.goalDateTextView);
        starImageButton = (ImageButton) itemView.findViewById(R.id.achievementStarButton);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent editAchievement = new Intent(view.getContext().getApplicationContext(), EditAchievementActivity.class);
        editAchievement.putExtra("achievement_id", achievementID);
        ((Activity) view.getContext()).startActivityForResult(editAchievement, 1337);
    }
}
