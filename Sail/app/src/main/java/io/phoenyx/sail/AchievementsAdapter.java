package io.phoenyx.sail;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

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

        String title = achievement.getTitle();
        String description = achievement.getDescription();

        if (title.length() > 40) {
            title = title.substring(0, 37) + "...";
        }
        if (description.length() > 90) {
            description = description.substring(0, 87) + "...";
        }

        holder.titleTextView.setText(title);

        if (description.equals("") || description.isEmpty()) {
            holder.descriptionTextView.setVisibility(View.GONE);
        } else {
            holder.descriptionTextView.setText(description);
        }

        holder.descriptionTextView.setText(achievement.getDescription());
        holder.dateTextView.setText(achievement.getDate());
        holder.achievementID = achievement.getId();
        if (achievement.isStarred()) {
            holder.starImageButton.setBackgroundResource(R.drawable.star);
        } else {
            holder.starImageButton.setBackgroundResource(R.drawable.star_outline);
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
