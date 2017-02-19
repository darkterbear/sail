package io.phoenyx.sail;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by terrance on 2/18/17.
 */

public class GoalsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    protected TextView titleTextView;
    protected TextView descriptionTextView;
    protected TextView dateTextView;
    protected ImageButton starImageButton;
    protected ImageButton doneImageButton;
    int goalID;

    public GoalsViewHolder(View itemView) {
        super(itemView);
        titleTextView = (TextView) itemView.findViewById(R.id.goalTitleTextView);
        descriptionTextView = (TextView) itemView.findViewById(R.id.goalDescriptionTextView);
        dateTextView = (TextView) itemView.findViewById(R.id.goalDateTextView);
        starImageButton = (ImageButton) itemView.findViewById(R.id.goalStarButton);
        doneImageButton = (ImageButton) itemView.findViewById(R.id.goalDoneButton);


        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent editGoal = new Intent(view.getContext().getApplicationContext(), EditGoalActivity.class);
        editGoal.putExtra("goal_id", goalID);
        view.getContext().startActivity(editGoal);
    }
}
