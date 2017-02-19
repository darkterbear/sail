package io.phoenyx.sail;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by terrance on 2/18/17.
 */

public class TimelineEventsViewHolder extends RecyclerView.ViewHolder {
    protected TextView titleTextView;
    protected TextView descriptionTextView;
    protected TextView dateTextView;

    public TimelineEventsViewHolder(View itemView) {
        super(itemView);
        titleTextView = (TextView) itemView.findViewById(R.id.timelineTitleTextView);
        descriptionTextView = (TextView) itemView.findViewById(R.id.timelineDescriptionTextView);
        dateTextView = (TextView) itemView.findViewById(R.id.timelineDateTextView);
    }
}
