package com.helpee.helpee.Adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.helpee.helpee.Class.Order;
import com.helpee.helpee.R;

import java.util.ArrayList;

public class UpcommingAdapter extends RecyclerView.Adapter<UpcommingAdapter.UpcomingViewHolder> {
    private ArrayList<Order> upcomings;
    private Activity activity;

    public UpcommingAdapter(ArrayList<Order> upcomings, Activity activity) {
        this.upcomings = upcomings;
        this.activity = activity;

    }

    @NonNull
    @Override
    public UpcommingAdapter.UpcomingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        View historyrow = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.upcoming_order_row
                , viewGroup, false);

        UpcommingAdapter.UpcomingViewHolder holder = new UpcommingAdapter.UpcomingViewHolder(historyrow);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull UpcommingAdapter.UpcomingViewHolder upcomingViewHolder, int i) {

        final Order upcoming = new Order();
        upcoming.setPaid(upcomings.get(i).isPaid());
            upcomingViewHolder.imgTask.setImageResource(R.drawable.paid);
            upcomingViewHolder.tvTaskType.setText(R.string.paid);


    }


    @Override
    public int getItemCount() {
        return upcomings.size();
    }

    class UpcomingViewHolder extends RecyclerView.ViewHolder {

        TextView tvTaskTime, tvTaskDescription, tvTaskDuration, tvTaskPrice, tvTaskState, tvTaskType, tvTaskEdit, tvTaskCancel;
        CardView cardUpcoming;
        ImageView imgTask;

        public UpcomingViewHolder(View itemView) {
            super(itemView);

            tvTaskTime = itemView.findViewById(R.id.tvTaskTime);
            tvTaskDescription = itemView.findViewById(R.id.tvTaskDescription);
            tvTaskDuration = itemView.findViewById(R.id.tvTaskDuration);
            tvTaskPrice = itemView.findViewById(R.id.tvTaskPrice);
            tvTaskState = itemView.findViewById(R.id.tvTaskState);
            tvTaskType = itemView.findViewById(R.id.tvTaskType);
            tvTaskEdit = itemView.findViewById(R.id.tvTaskEdit);
            tvTaskCancel = itemView.findViewById(R.id.tvTaskCancel);
            cardUpcoming = itemView.findViewById(R.id.cardUpcoming);
            imgTask = itemView.findViewById(R.id.imgTask);

        }
    }
}