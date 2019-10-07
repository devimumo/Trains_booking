package com.firstapp.david.trains;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ParameterActivity extends AppCompatActivity{

private Toolbar toolbar;

ArrayAdapter<String> kutoka;

    Calendar calendar,max_calendar;
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parameter);


        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        TextView dates=findViewById(R.id.dates);

        Date today= Calendar.getInstance().getTime();
        SimpleDateFormat format=new SimpleDateFormat("yyyy.MM.dd");
        String date=format.format(today);
       // dates.setText(date);
    //String today=new SimpleDateFormat("yyyy dd mm", Locale.getDefault().)





        getdetails();

        //ArrayList<String> from_=new ArrayList<>();
        final  ArrayList<String> from_all=new ArrayList();
        ArrayList<String> to_=new ArrayList<>();
       final  ArrayList<String> to_all=new ArrayList<>();
        ArrayList<String> type_=new ArrayList<>();
       final   ArrayList<String> type_all=new ArrayList<>();



        String URL_DETAILS="http://project-daudi.000webhostapp.com/android_login_register/details.php";
        StringRequest stringRequest=new StringRequest(Request.Method.GET,URL_DETAILS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                       // TextView date=findViewById(R.id.date);

                        try {
                            JSONObject jsonObject=new JSONObject(response);

                            JSONArray jsonArray=jsonObject.getJSONArray("details");
                            for (int i=0; i<jsonArray.length(); i++)
                            {
                                JSONObject details=jsonArray.getJSONObject(i);
                                  String from=details.getString("from");
                                String to=details.getString("to");
                                String type=details.getString("type");


                                from_all.add(from);
                                to_all.add(to);
                                type_all.add(type);
                              /*  for (int j=0; j<from_all.size();j++)
                                {date.append( from_all.get(i).toString());
                                }*/

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        } );

        RequestQueue requestQueue= Volley.newRequestQueue(ParameterActivity.this);
        requestQueue.add(stringRequest);



     /*  Intent intent=getIntent();
        ArrayList<String> from_adapt=intent.getStringArrayListExtra("from");
        ArrayList<String> to_adapt=intent.getStringArrayListExtra("to");
        ArrayList<String> type_adapt=intent.getStringArrayListExtra("type");*/



      /*  for (int i=0; i<from_adapt.size(); i++)
        {date.append( from_adapt.get(i).toString());
        }*/



      final Spinner from__=findViewById(R.id.from);
      Spinner to__=findViewById(R.id.to);
        Spinner train__=findViewById(R.id.type);




//spinner for from values




      ArrayAdapter<String>  kutoka=new ArrayAdapter<String>(this,R.layout.from_spinner,R.id.spinner_from,from_all);
     // kutoka.setDropDownViewResource(android.R.layout.simple_spinner_item);
        from__.setAdapter(kutoka);



      /*  ArrayAdapter from_adapter=new ArrayAdapter<String>(this,android.R.layout.simple_selectable_list_item, from_all);
        from_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        from__.setAdapter(from_adapter);*/

        //spinner for to value

        ArrayAdapter<String> to_adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,to_all);
       // to_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         to__.setAdapter(to_adapter);


         // spinner for type value

         ArrayAdapter<String> type_adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,type_all);
         type_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         train__.setAdapter(type_adapter);

        AdapterView.OnItemSelectedListener frooom=new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text=parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();


TextView ttt=findViewById(R.id.from_text);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };



from__.setOnItemSelectedListener(frooom);


//jjjjj


        to__.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                String selected_to=adapterView.getItemAtPosition(i).toString();
                TextView to_text=findViewById(R.id.to_text);
                to_text.setText(selected_to);

              //  Toast.makeText(ParameterActivity.this, "nnnnnnnnnnnnnn", Toast.LENGTH_SHORT).show();
                Toast.makeText(adapterView.getContext(),selected_to,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        train__.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String selected_type=adapterView.getItemAtPosition(i).toString();
                TextView type_text=findViewById(R.id.type_text);
                type_text.setText(selected_type);


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.app_bar_menu,menu);

        return true;
    }


    public  void getdetails()
    {





    }


    public void det(View view) {


        final TextView dates=findViewById(R.id.dates);
        calendar=Calendar.getInstance();

        int day=calendar.get(Calendar.DAY_OF_MONTH);
        int month=calendar.get(Calendar.MONTH);
        int year=calendar.get(Calendar.YEAR);



        max_calendar=Calendar.getInstance();
        int max_day=max_calendar.get(Calendar.DATE+10);
        int max_month=max_calendar.get(Calendar.MONTH+1);
        int max_year=max_calendar.get(Calendar.YEAR+1);






        datePickerDialog=new DatePickerDialog(ParameterActivity.this, new DatePickerDialog.OnDateSetListener() {







            @Override
            public void onDateSet(DatePicker datePicker, int myear, int mmonth, int mday) {

                dates.setText(myear+"-"+(mmonth+1)+"-"+mday);






            }
        },year,month,day    );


String maxdate=""+(day+10)+"-"+month+"-"+year;





        datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
        max_calendar.add(Calendar.YEAR,0);
        max_calendar.add(Calendar.MONTH,0);
        max_calendar.add(Calendar.DAY_OF_MONTH,10);

        //+84000000
        datePickerDialog.getDatePicker().setMaxDate(max_calendar.getTimeInMillis());




        datePickerDialog.show();

        String tarehe=dates.getText().toString();



    }


    public void seat(View view) {
        final TextView dates=findViewById(R.id.dates);
        final TextView from_text=findViewById(R.id.from_text);
        final TextView to_text=findViewById(R.id.to_text);
        final TextView type_text=findViewById(R.id.type_text);
        final TextView coach_text=findViewById(R.id.coach_text);


        String tarehe=dates.getText().toString();
        String from=from_text.getText().toString();
        String to=to_text.getText().toString();
        String type=type_text.getText().toString();
        String coach=coach_text.getText().toString();


        Intent intent=new Intent(ParameterActivity.this,FirstActivity.class);
        intent.putExtra("date",tarehe);
        intent.putExtra("from",from);
        intent.putExtra("to",to);
        intent.putExtra("type",type);
        intent.putExtra("coach",coach);
        startActivity(intent);


    }



}
