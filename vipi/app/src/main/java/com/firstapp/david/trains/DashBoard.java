package com.firstapp.david.trains;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DashBoard extends AppCompatActivity {

CardView book_ticket,check_ticku,setting,admin;

    private Toolbar toolbar;

    ProgressBar progress;
    public String seat_no[] = {"seat1_a", "seat1_b", "1-C", "seat1_c", "seat1_d", "2-B", "2-C", "2-D", "3-A", "3-B", "3-C", "3-D", "4-A", "4-B", "4-C", "4-D",
            "5-A", "5-B", "5-C", "5-D", "6-A", "6-B", "6-C", "6-D", "7-A", "7-B", "7-C", "7-D", "8-A", "8-B", "8-C", "8-D"};

    ProgressDialog  progressDoalog ;
    ArrayList<String> from_=new ArrayList<>();
    ArrayList<String> from_all=new ArrayList<>();
    ArrayList<String> to_=new ArrayList<>();
    ArrayList<String> to_all=new ArrayList<>();
    ArrayList<String> type_=new ArrayList<>();
    ArrayList<String> type_all=new ArrayList<>();

    public  int i;
    TextView status;
    int no;

    ImageView calendar, family_time, me_time, friends, seat;
    final String status_URL = "https://project-daudi.000webhostapp.com/android_login_request/state.php";
    final String STATUS_URL = "https://192.168.27.146/android_login_request/status.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dash);



toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        seat = findViewById(R.id.seat_icon);
        progress=findViewById(R.id.progress);
        // status=findViewById(R.id.status);
book_ticket=findViewById(R.id.book_ticket_card);
        check_ticku=findViewById(R.id.check_ticket_card);
        setting=findViewById(R.id.settings_card);
        admin=findViewById(R.id.admin_card);


        book_ticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Toast.makeText(MainActivity.this, "This is the calendar", Toast.LENGTH_SHORT).show();
                //   status();

                //  getdetails();
                Intent intent=new Intent(DashBoard.this,ParameterActivity.class);
                intent.putExtra("from",from_).toString();
                intent.putExtra("to",to_).toString();
                intent.putExtra("type",type_).toString();
                startActivity(intent);



            }
        });

        check_ticku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {








                /*dialog=new ProgressDialog(DashBoard.this);
                dialog.setMessage("Retreiving tickets");
                dialog.show();*/

                Intent intent = new Intent(DashBoard.this, CheckTickets.class);
                startActivity(intent);
//progressDoalog.dismiss();
                // Toast.makeText(MainActivity.this, "This is family time", Toast.LENGTH_SHORT).show();

            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  Intent intent = new Intent(MainActivity.this, ParameterActivity.class);
                //  startActivity(intent);
               // Toast.makeText(MainActivity.this, "This is me time", Toast.LENGTH_SHORT).show();

            }
        });

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




               // Intent intent = new Intent(DashBoard.this, FirstActivity.class);
               // startActivity(intent);
               // Toast.makeText(MainActivity.this, "This is friends time", Toast.LENGTH_SHORT).show();

            }
        });


    }


    @Override
    public  boolean onCreateOptionsMenu (Menu menu)
    {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.app_bar_menu,menu);



        return true;
    }



    //function to get value of seat clicked
    public void status() {


        for (i = 1; i <4; i++) {

            no=i;

            RequestQueue requestQueue= Volley.newRequestQueue(DashBoard.this);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, status_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {


                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String data = jsonObject.getString("data");

                                if (data.equals("booked")) {
                                    Toast.makeText(DashBoard.this, "booked....................", Toast.LENGTH_SHORT).show();

                                    status.setText(data);
                                    //  seats = seat_icon[i];
                                    //  seat.setColorFilter(Color.parseColor("#d9197f"));
                                }
                                else
                                {
                                    //Toast.makeText(MainActivity.this, "not booked....................", Toast.LENGTH_SHORT).show();
                                }


                            } catch (JSONException e) {

                               // Toast.makeText(MainActivity.this, "Activity error" + e.toString(), Toast.LENGTH_SHORT).show();


                                e.printStackTrace();
                            }

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                          //  Toast.makeText(MainActivity.this, "that error......", +no+ Toast.LENGTH_SHORT).show();

                        }


                    }) {

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();


                    params.put("no", String.valueOf(no));


                    //return super.getParams();
                    return params;

                }
            };

            requestQueue.add(stringRequest);
        }


    }






}
