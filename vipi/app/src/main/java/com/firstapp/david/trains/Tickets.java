package com.firstapp.david.trains;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tickets extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    private List<ListItem> listItemss;

    ProgressDialog  progressDoalog ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tickets);
final Gson gson=new Gson();
        recyclerView=findViewById(R.id.tickets);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        Button confirm_booking=findViewById(R.id.confir_booking);




        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listItemss=new ArrayList<>();









      /*  adapter=new TicketsAdapter(listItems,this);
        recyclerView.setAdapter(adapter);*/

      loadTicketsData();

      confirm_booking.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

confirm_booking(listItemss,gson);


          }
      });

    }



    private void loadTicketsData(){

ProgressBar processdialog=new ProgressBar(this);
processdialog.isShown();


        final String seat_no[] = {"seat1_a", "seat1_b", "seat1_c", "seat1_d", "seat2_a", "seat2_b", "seat2_c", "seat2_d", "seat3_a", "seat3_b", "seat3_c", "seat3_d", "seat4_a", "seat4_b", "seat4_c",
                "seat4_d", "seat5_a", "seat5_b", "seat5_c", "seat5_d", "seat6_a", "seat6_b", "seat6_c", "seat6_d", "seat7_a", "seat7_b", "seat7_c", "seat7_d", "seat8_a", "seat8_b", "seat8_c", "seat8_d"};

        String ticket_data_URL = "http://project-daudi.000webhostapp.com/android_login_register/tickets_info.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, ticket_data_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String tickets_data) {

                        try {

                            JSONObject jsonObject=new JSONObject(tickets_data);
                            // int log=jsonObject.getInt("log");
                            JSONArray array=jsonObject.getJSONArray("tickets");
                           // String status_responses=jsonObject.getString("return");
if (array.length()>0)
{
    for (int i=0; i<array.length(); i++)
    {

        JSONObject data=array.getJSONObject(i);

        ListItem item=new ListItem(

              //  data.getString("session_id"),

                data.getString("passenger_name"),
                data.getString("phone_number"),
                data.getString("tarehe"),
                data.getString("from"),
                data.getString("departure_time"),
                data.getString("to"),
                data.getString("arrival_time"),
                data.getString("seat_no"),
                data.getString("train_type"),
                data.getString("coach_no"),
                   data.getString("amount"));

        listItemss.add(item);

    }


    adapter=new TicketsAdapter(listItemss,getApplicationContext());
    recyclerView.setAdapter(adapter);

//     progressDoalog.dismiss();


}
else
{
    Toast.makeText(Tickets.this, "No chosen tickets", Toast.LENGTH_SHORT).show();
}

                        } catch (JSONException e) {



                            Toast.makeText(Tickets.this, " catch error" + e.toString(), Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Tickets.this, " error ya leo" + error.toString(), Toast.LENGTH_SHORT).show();

            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                String MyPreferences="mypref";
                SharedPreferences sharedPreferences=getSharedPreferences(MyPreferences, (Context.MODE_PRIVATE));

                String session_idsss= sharedPreferences.getString("sessions_ids","");
               // String phone_numberxx=sharedPreferences.getString("phone_numbers","");





               /* Intent datess=getIntent();
                String tarehes=  datess.getExtras().getString("date");
                String departure_from=  datess.getExtras().getString("from");
                String destination=  datess.getExtras().getString("to");
                String coach=  datess.getExtras().getString("coach");
                String type=  datess.getExtras().getString("type");*/
             //   String seatno=seat_no[j];

              /*  params.put("ids", tarehes);
                params.put("departure_from", departure_from);
                params.put("destination", destination);
                params.put("coach", coach);
                params.put("type", type);*/
                params.put("session_id",session_idsss);
              //  params.put("seat_no",seatno);


                // return super.getParams();
                return params;
            }

        };




        RequestQueue requestQueue= Volley.newRequestQueue(Tickets.this);
        requestQueue.add(stringRequest);







    }


    private void confirm_booking(List<ListItem> listItemss, Gson gson)
    {

final String new_tickets_array=gson.toJson(listItemss);

        final String seat_no[] = {"seat1_a", "seat1_b", "seat1_c", "seat1_d", "seat2_a", "seat2_b", "seat2_c", "seat2_d", "seat3_a", "seat3_b", "seat3_c", "seat3_d", "seat4_a", "seat4_b", "seat4_c",
                "seat4_d", "seat5_a", "seat5_b", "seat5_c", "seat5_d", "seat6_a", "seat6_b", "seat6_c", "seat6_d", "seat7_a", "seat7_b", "seat7_c", "seat7_d", "seat8_a", "seat8_b", "seat8_c", "seat8_d"};

        String confirm_booking_URL = "http://project-daudi.000webhostapp.com/android_login_register/confirm_booking_payment.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, confirm_booking_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String tickets_data) {

                        try {

                            JSONObject jsonObject=new JSONObject(tickets_data);
                            // int log=jsonObject.getInt("log");
                            String result=jsonObject.getString("result");
                            if(result.equals("success"))
                            {

                                Toast.makeText(Tickets.this, "successfully booked", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent (Tickets.this,ParameterActivity.class);
                                startActivity(intent);
                            }


                        } catch (JSONException e) {



                            Toast.makeText(Tickets.this, " catch error" + e.toString(), Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Tickets.this, " error ya leo" + error.toString(), Toast.LENGTH_SHORT).show();

            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                String MyPreferences="mypref";
                SharedPreferences sharedPreferences=getSharedPreferences(MyPreferences, (Context.MODE_PRIVATE));

                String session_idsss= sharedPreferences.getString("sessions_ids","");
                // String phone_numberxx=sharedPreferences.getString("phone_numbers","");





               /* Intent datess=getIntent();
                String tarehes=  datess.getExtras().getString("date");
                String departure_from=  datess.getExtras().getString("from");
                String destination=  datess.getExtras().getString("to");
                String coach=  datess.getExtras().getString("coach");
                String type=  datess.getExtras().getString("type");*/
                //   String seatno=seat_no[j];

              // params.put("ids", tarehes);
               /* params.put("departure_from", departure_from);
                params.put("destination", destination);
                params.put("coach", coach);
                params.put("type", type);*/

              String ticku=new_tickets_array;
                params.put("session_id", session_idsss);
                //  params.put("seat_no",seatno);


                // return super.getParams();
                return params;
            }

        };




        RequestQueue requestQueue= Volley.newRequestQueue(Tickets.this);
        requestQueue.add(stringRequest);



    }
}
