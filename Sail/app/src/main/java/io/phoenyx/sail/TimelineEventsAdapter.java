package io.phoenyx.sail;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by terrance on 2/18/17.
 */

public class TimelineEventsAdapter extends RecyclerView.Adapter<TimelineEventsViewHolder> {

    private List<TimelineEvent> timelineEvents;
    private DBHandler dbHandler;

    public TimelineEventsAdapter(List<TimelineEvent> timelineEvents) {
        this.timelineEvents = timelineEvents;
    }

    @Override
    public TimelineEventsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        dbHandler = new DBHandler(parent.getContext());
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.timeline_card, parent, false);
        return new TimelineEventsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final TimelineEventsViewHolder holder, int position) {
        final TimelineEvent timelineEvent = timelineEvents.get(position);
        holder.titleTextView.setText(timelineEvent.getTitle());
        holder.descriptionTextView.setText(timelineEvent.getDescription());
        holder.dateTextView.setText(timelineEvent.getDate());
    }

    @Override
    public int getItemCount() {
        return timelineEvents.size();
    }
}
