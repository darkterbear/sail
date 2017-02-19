package io.phoenyx.sail;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by terrance on 2/18/17.
 */

public class AchievementsAdapter extends RecyclerView.Adapter<AchievementsViewHolder> {

    private List<Achievement> achievements;
    private DBHandler dbHandler;

    public AchievementsAdapter(List<Achievement> achievements) {
        this.achievements = achievements;
    }

    @Override
    public AchievementsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        dbHandler = new DBHandler(parent.getContext());
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.achievement_card, parent, false);
        return new AchievementsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final AchievementsViewHolder holder, int position) {
        final Achievement achievement = achievements.get(position);
        holder.titleTextView.setText(achievement.getTitle());
        holder.descriptionTextView.setText(achievement.getDescription());
        holder.dateTextView.setText(achievement.getDate());
        if (achievement.isStarred()) {
            holder.starImageButton.setBackgroundResource(R.drawable.star_outline);
            achievement.setStarred(false);
        } else {
            holder.starImageButton.setBackgroundResource(R.drawable.star);
            achievement.setStarred(true);
        }
        holder.starImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (achievement.isStarred()) {
                    holder.starImageButton.setBackgroundResource(R.drawable.star_outline);
                    achievement.setStarred(false);
                } else {
                    holder.starImageButton.setBackgroundResource(R.drawable.star);
                    achievement.setStarred(true);
                }
                dbHandler.updateAchievement(achievement);
            }
        });
    }

    @Override
    public int getItemCount() {
        return achievements.size();
    }
}
