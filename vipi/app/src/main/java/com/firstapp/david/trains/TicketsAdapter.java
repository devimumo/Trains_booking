package com.firstapp.david.trains;


import android.content.ClipData;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class TicketsAdapter extends RecyclerView.Adapter<TicketsAdapter.ViewHolder> {
    private List<ListItem> listItems;
    private Context context;

    public TicketsAdapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    // @NonNull
    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
       View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);


       return  new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
ListItem listItem=listItems.get(position);
        holder.textView_passenger_name.setText(listItem.getPassenger_name());
        holder.textViewPhone_number.setText(listItem.getPhone_number());
        holder.textViewtarehe.setText(listItem.getTarehe());
        holder.textViewfrom.setText(listItem.getFrom());
        holder.textViewDeparture_time.setText(listItem.getDeparture_time());
        holder.textViewto.setText(listItem.getTo());
        holder.textViewArrival_time.setText(listItem.getArrival_time());
        holder.textViewseat_no.setText(listItem.getSeat_no());
        holder.textViewtrain_type.setText(listItem.getTrain_type());
        holder.textViewcoach_no.setText(listItem.getCoach_no());
        holder.textView_kshs.setText("Kshs.");
        holder.textView_amount.setText(listItem.getAmount());


    }

    @Override
    public int getItemCount() {



        return listItems.size();
    }

    public  class  ViewHolder extends RecyclerView.ViewHolder
    {


        public  TextView textViewPhone_number;
        public  TextView textViewArrival_time;
        public  TextView textViewDeparture_time;
        public  TextView textViewseat_no;
        public  TextView textViewtarehe;
        public  TextView textViewfrom;
        public  TextView textViewto;
        public  TextView textViewtrain_type;
        public  TextView textViewcoach_no;
        public  TextView textView_passenger_name;
        public  TextView textView_kshs;
        public  TextView textView_amount;




        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_passenger_name=itemView.findViewById(R.id.Daudi);
            textViewPhone_number=itemView.findViewById(R.id.phone_number_view);
            textViewtarehe=itemView.findViewById(R.id.date_view);
            textViewfrom=itemView.findViewById(R.id.departure_from_view);
            textViewDeparture_time=itemView.findViewById(R.id.departure_time_view);
            textViewto=itemView.findViewById(R.id.destination_view);
            textViewArrival_time=itemView.findViewById(R.id.arrival_time_view);
            textViewseat_no=itemView.findViewById(R.id.seat_no_view);
            textViewtrain_type=itemView.findViewById(R.id.train_type_view);
            textViewcoach_no=itemView.findViewById(R.id.coach_no_view);
            textView_kshs=itemView.findViewById(R.id.kshs_view);
            textView_amount=itemView.findViewById(R.id.ticket_amount_view);



        }
    }

}



