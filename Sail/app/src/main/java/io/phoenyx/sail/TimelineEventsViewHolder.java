package io.phoenyx.sail;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class TimelineEventsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    protected TextView titleTextView;
    protected TextView descriptionTextView;
    protected TextView dateTextView;
    int timelineEventID;

    public TimelineEventsViewHolder(View itemView) {
        super(itemView);
        titleTextView = (TextView) itemView.findViewById(R.id.timelineTitleTextView);
        descriptionTextView = (TextView) itemView.findViewById(R.id.timelineDescriptionTextView);
        dateTextView = (TextView) itemView.findViewById(R.id.timelineDateTextView);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent editTimelineEvent = new Intent(view.getContext().getApplicationContext(), EditTimelineEventActivity.class);
        editTimelineEvent.putExtra("timeline_event_id", timelineEventID);
        ((Activity) view.getContext()).startActivityForResult(editTimelineEvent, 1337);
    }
}
