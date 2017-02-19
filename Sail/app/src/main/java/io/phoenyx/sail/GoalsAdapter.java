package io.phoenyx.sail;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Calendar;
import java.util.List;

/**
 * Created by terrance on 2/18/17.
 */

public class GoalsAdapter extends RecyclerView.Adapter<GoalsViewHolder> {

    private List<Goal> goals;
    private DBHandler dbHandler;

    public interface OnClickListener {
        void onClick();
    }

    public GoalsAdapter(List<Goal> goals) {
        this.goals = goals;
    }

    @Override
    public GoalsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        dbHandler = new DBHandler(parent.getContext());
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.goal_card, parent, false);
        return new GoalsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final GoalsViewHolder holder, int position) {
        final Goal goal = goals.get(position);
        holder.titleTextView.setText(goal.getTitle());
        holder.descriptionTextView.setText(goal.getDescription());
        holder.dateTextView.setText(goal.getDate());
        holder.doneImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goal.setCompleted(true);
                dbHandler.updateGoal(goal);
            }
        });
        holder.goalID = goal.getId();
        if (goal.isStarred()) {
            holder.starImageButton.setBackgroundResource(R.drawable.star);
        } else {
            holder.starImageButton.setBackgroundResource(R.drawable.star_outline);
        }
        holder.starImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (goal.isStarred()) {
                    holder.starImageButton.setBackgroundResource(R.drawable.star_outline);
                    goal.setStarred(false);
                } else {
                    holder.starImageButton.setBackgroundResource(R.drawable.star);
                    goal.setStarred(true);
                }
                dbHandler.updateGoal(goal);

            }
        });
        holder.doneImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] months = new String[]{"Jan.","Feb.","Mar.","Apr.","May","Jun.","Jul.","Aug.","Sep.","Oct.","Nov.","Dec."};
                String date = months[Calendar.getInstance().get(Calendar.MONTH)] + " " + Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + " " + Calendar.getInstance().get(Calendar.YEAR);
                Achievement achievement = new Achievement(goal.getTitle(), goal.getDescription(), date, goal.isStarred());
                dbHandler.createAchievement(achievement);
                dbHandler.deleteGoal(goal.getId());

            }
        });


    }

    @Override
    public int getItemCount() {
        return goals.size();
    }
}
