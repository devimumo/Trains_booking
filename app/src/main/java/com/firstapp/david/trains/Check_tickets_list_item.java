package com.firstapp.david.trains;

public class Check_tickets_list_item {



    private String seat_no;
    private String tarehe;
    private String from;
    private String to;
    private String train_type;
    private String coach_no;
    public String passenger_name;
    public String phone_number;
    public String arrival_time;
    public String departure_time;
    public String amount;




    public Check_tickets_list_item(String passenger_name,String phone_number, String tarehe, String from,  String departure_time,String to, String arrival_time, String seat_no, String train_type, String coach_no,String amount) {



        this.passenger_name=passenger_name;
        this.phone_number = phone_number;
        this.tarehe = tarehe;
        this.from = from;
        this.departure_time = departure_time;
        this.to = to;
        this.arrival_time = arrival_time;
        this.seat_no = seat_no;
        this.train_type = train_type;
        this.coach_no = coach_no;
        this.amount=amount;


    }





    public String getPassenger_name()
    {
        return passenger_name;
    }
    public String getPhone_number()
    {
        return phone_number;
    }
    public String getTarehe()
    {
        return tarehe;
    }

    public String getFrom()
    {
        return from;
    }
    public String getDeparture_time()
    {
        return departure_time;
    }

    public String getTo()
    {
        return to;
    }

    public String getArrival_time()
    {
        return arrival_time;
    }

    public String getSeat_no()
    {
        return seat_no;
    }

    public String getTrain_type()
    {
        return train_type;
    }

    public String getCoach_no()
    {
        return coach_no;
    }
    public String getAmount()
    {
        return amount;
    }
}
