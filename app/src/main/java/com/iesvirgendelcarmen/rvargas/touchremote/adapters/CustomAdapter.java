package com.iesvirgendelcarmen.rvargas.touchremote.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.iesvirgendelcarmen.rvargas.touchremote.R;
import com.iesvirgendelcarmen.rvargas.touchremote.controller.FingerPrintActivity;
import com.iesvirgendelcarmen.rvargas.touchremote.model.Notification;
import com.iesvirgendelcarmen.rvargas.touchremote.model.User;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private Context context;
    private List<Notification> notifications;
    private User employee;

    public CustomAdapter (Context context, User employee, List<Notification> notifications) {
        this.context = context;
        this.notifications = notifications;
        this.employee = employee;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView address;
        private TextView hour;
        private AppCompatSpinner days;
        private FloatingActionButton sendLocate;

        public ViewHolder(View itemView) {
            super(itemView);

            address = (TextView) itemView.findViewById(R.id.address);
            hour = (TextView) itemView.findViewById(R.id.hour);
            days = (AppCompatSpinner) itemView.findViewById(R.id.days);
            sendLocate = (FloatingActionButton) itemView.findViewById(R.id.sendLocate);
        }
    }

    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notifications, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final CustomAdapter.ViewHolder holder, final int position) {
        holder.address.setText(notifications.get(position).getAddress());
        holder.hour.setText(notifications.get(position).getSendDateFormat());

        ArrayAdapter arrayAdapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, notifications.get(position).getDaysRepeat());
        holder.days.setAdapter(arrayAdapter);

        holder.sendLocate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), FingerPrintActivity.class);
                Notification notification = notifications.get(position);
                intent.putExtra("notification", notification);
                intent.putExtra("employee", employee);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.notifications.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}


