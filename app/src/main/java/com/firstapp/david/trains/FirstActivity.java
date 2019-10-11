package com.firstapp.david.trains;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FirstActivity extends AppCompatActivity {
  LinearLayout linearLayout;
  private Toolbar toolbar;

  ArrayList<String> unselectedSeats; //Selected by this user.

  ArrayList<String> filledSeats; //Already booked by other users.

  ArrayList<String> selectedSeats; //Already booked by other users.


  ArrayList<ImageView> seatList;
  // int i;
 ProgressDialog  progressDoalog ;
    private static ProgressDialog mProgressDialog;

  public String seat_no[] = {"seat1_a", "seat1_b", "seat1_c", "seat1_d", "seat2_a", "seat2_b", "seat2_c", "seat2_d", "seat3_a", "seat3_b", "seat3_c", "seat3_d", "seat4_a", "seat4_b", "seat4_c",
          "seat4_d", "seat5_a", "seat5_b", "seat5_c", "seat5_d", "seat6_a", "seat6_b", "seat6_c", "seat6_d", "seat7_a", "seat7_b", "seat7_c", "seat7_d", "seat8_a", "seat8_b", "seat8_c", "seat8_d"};
  //final String ids=seat_no[i].toString().trim();
  // final String ids="2019-3-19";

  ImageView s1, free, reserved, booked;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_first);
      showSimpleProgressDialog(this, "Loading...","Fetching Json",false);


      setTitle("Coach One");
    toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
RelativeLayout cart=findViewById(R.id.cart);


checking_log();

    s1 = findViewById(R.id.seat1);
    free = findViewById(R.id.free);
    reserved = findViewById(R.id.reserved);
    booked = findViewById(R.id.booked);
    //  s1.setColorFilter(Color.parseColor("#BD1E1E"));
    free.setColorFilter(Color.parseColor("#56C412"));
    reserved.setColorFilter(Color.parseColor("#d9197f"));
    booked.setColorFilter(Color.parseColor("#443737"));


    Intent datess = getIntent();


cart.setOnClickListener(new View.OnClickListener() {
  @Override
  public void onClick(View view) {
      if(selectedSeats.size()>0)
      {Intent intent=new Intent(FirstActivity.this,Tickets.class);
          startActivity(intent);}
   else
      {
          Toast.makeText(FirstActivity.this, "Sorry...no tickets to show", Toast.LENGTH_SHORT).show();
      }
  }
});


    // linearLayout=findViewById(R.id.view1);

    seatList = new ArrayList<>();
    seatList.add((ImageView) findViewById(R.id.seat1));
    seatList.add((ImageView) findViewById(R.id.seat2));
    seatList.add((ImageView) findViewById(R.id.seat3));
    seatList.add((ImageView) findViewById(R.id.seat4));
    seatList.add((ImageView) findViewById(R.id.seat5));
    seatList.add((ImageView) findViewById(R.id.seat6));
    seatList.add((ImageView) findViewById(R.id.seat7));
    seatList.add((ImageView) findViewById(R.id.seat8));
    seatList.add((ImageView) findViewById(R.id.seat9));
    seatList.add((ImageView) findViewById(R.id.seat10));
    seatList.add((ImageView) findViewById(R.id.seat11));
    seatList.add((ImageView) findViewById(R.id.seat12));
    seatList.add((ImageView) findViewById(R.id.seat13));
    seatList.add((ImageView) findViewById(R.id.seat14));
    seatList.add((ImageView) findViewById(R.id.seat15));
    seatList.add((ImageView) findViewById(R.id.seat16));
    seatList.add((ImageView) findViewById(R.id.seat17));
    seatList.add((ImageView) findViewById(R.id.seat18));
    seatList.add((ImageView) findViewById(R.id.seat19));
    seatList.add((ImageView) findViewById(R.id.seat20));
    seatList.add((ImageView) findViewById(R.id.seat21));
    seatList.add((ImageView) findViewById(R.id.seat22));
    seatList.add((ImageView) findViewById(R.id.seat23));
    seatList.add((ImageView) findViewById(R.id.seat24));
    seatList.add((ImageView) findViewById(R.id.seat25));
    seatList.add((ImageView) findViewById(R.id.seat26));
    seatList.add((ImageView) findViewById(R.id.seat27));
    seatList.add((ImageView) findViewById(R.id.seat28));
    seatList.add((ImageView) findViewById(R.id.seat29));
    seatList.add((ImageView) findViewById(R.id.seat30));
    seatList.add((ImageView) findViewById(R.id.seat31));
    seatList.add((ImageView) findViewById(R.id.seat32));

//Setstatus_for_current_user();
    Setstatus();

    unselectedSeats = new ArrayList<>();
    filledSeats = new ArrayList<>();
    selectedSeats = new ArrayList<>();


  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater menuInflater = getMenuInflater();
    menuInflater.inflate(R.menu.app_bar_menu, menu);

    return true;
  }


  boolean isSelected(String seatNo) {
    int i;
    for (i = 0; i < filledSeats.size(); i++) {
      if (filledSeats.get(i).equalsIgnoreCase(String.valueOf(seatNo))) {
        return true;
      }
    }
    return false;
  }

  boolean isSelectedByUser(String seatNo) {
    int i;
    for (i = 0; i < selectedSeats.size(); i++) {
      if (selectedSeats.get(i).equalsIgnoreCase(String.valueOf(seatNo))) {
        return true;
      }
    }
    return false;
  }
//on click for any seat image clicked
  public void onClickListener(View view) {

    seat_onclick(view);

  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    for (int i=0; i<seatList.size(); i++)
    {
     ImageView dd;

     dd=seatList.get(i);

      String color= null;
      if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
        color = String.valueOf(dd.getSolidColor());
      }
     // Toast.makeText(this, ""+color, Toast.LENGTH_SHORT).show();
      outState.putString("collor",color);



    }

  }

 // @Override
 /* protected void onRestoreInstanceState(Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);

    for (int i=0; i<seatList.size(); i++)
    {
      ImageView dd;

      dd=seatList.get(i);

     String on_restore_color=savedInstanceState.getString("collor");

     dd.setColorFilter(Color.parseColor(on_restore_color));



    }

  }*/

  //function to handle image/seat click events
  public void seat_onclick(View view){

//final  String nn=session_idsss;
    final String seat_no[] = {"seat1_a", "seat1_b", "seat1_c", "seat1_d", "seat2_a", "seat2_b", "seat2_c", "seat2_d", "seat3_a", "seat3_b", "seat3_c", "seat3_d", "seat4_a", "seat4_b", "seat4_c",
            "seat4_d", "seat5_a", "seat5_b", "seat5_c", "seat5_d", "seat6_a", "seat6_b", "seat6_c", "seat6_d", "seat7_a", "seat7_b", "seat7_c", "seat7_d", "seat8_a", "seat8_b", "seat8_c", "seat8_d"};


    for ( int i = 0; i < seatList.size(); i++) {

      final int j=i;
      if (seatList.get(i).getId() == view.getId()) {


        String seat__no = seat_no[i];

        //Unselecting selected seat
        if (isSelectedByUser(seat__no)) {


          //calling function to update unconfirmed seats table for seat i
          delete_chosen_seat(j);

          //calling function to update status of seatno at i to free
          update_status_free(i);

        } else if (!isSelected(seat__no)) {

          choosing_seat( j);

//


        }

      }

    }




  }

  //function to delete chosen seat record on second click in tickets unconfirmed
  public void delete_chosen_seat(final int j)
  {

    String DELETE_URL="http://project-daudi.000webhostapp.com/android_login_register/tickets_unconfirmed_remove.php";

    StringRequest stringRequest=new StringRequest(Request.Method.POST, DELETE_URL,
            new Response.Listener<String>() {
              @Override
              public void onResponse(String delete) {


                try {
                  JSONObject jsonObject=new JSONObject(delete);
                  String delete_response=jsonObject.getString("return");
                  if (delete_response.equals("deleted"))
                  {


                  }




                } catch (JSONException e) {
                  e.printStackTrace();
                }

              }
            }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {

      }
    })



    {


      @Override
      protected Map<String, String> getParams() throws AuthFailureError {
        Map<String, String> params = new HashMap<>();


        String MyPreferences="mypref";
        SharedPreferences sharedPreferences=getSharedPreferences(MyPreferences, (Context.MODE_PRIVATE));

        String session_idsss= sharedPreferences.getString("sessions_ids","");
        String phone_numberxx=sharedPreferences.getString("phone_numbers","");



        //   Toast.makeText(FirstActivity.this, "bbbb"+phone_number+","+session_id, Toast.LENGTH_SHORT).show();

        Intent datess = getIntent();
        String tarehes = datess.getExtras().getString("date");
        String departure_from = datess.getExtras().getString("from");
        String destination = datess.getExtras().getString("to");
        String coach = datess.getExtras().getString("coach");
        String type = datess.getExtras().getString("type");
        String seatno=seat_no[j];
        String sess=session_idsss;
        String phone=phone_numberxx;
        params.put("ids", tarehes);
        params.put("departure_from", departure_from);
        params.put("destination", destination);
        params.put("coach", coach);
        params.put("type", type);
        params.put("seat_no", seatno);
        params.put("session_id",sess);
        params.put("phone_number",phone);


        //  Toast.makeText(FirstActivity.this, "bbbb"+phone_number+","+session_id, Toast.LENGTH_SHORT).show();


        // return super.getParams();
        return params;
      }


    };
    RequestQueue requestQueue = Volley.newRequestQueue(FirstActivity.this);
    requestQueue.add(stringRequest);

  }


  //function called to choose seat on train coach
  public void choosing_seat(final int j)
  {



    String STATUS_URL = "http://project-daudi.000webhostapp.com/android_login_register/tickets_unconfirmed.php";
    StringRequest stringRequest = new StringRequest(Request.Method.POST, STATUS_URL,
            new Response.Listener<String>() {
              @Override
              public void onResponse(String responsez) {

                try {
                  JSONObject jsonObject = new JSONObject(responsez);
                  // int log=jsonObject.getInt("log");

                  //JSONArray jsonArray=new JSONArray(response);
                  //  JSONArray jsonArray = jsonObject.getJSONArray("status");
                  //  JSONArray jsonArray=jsonObject.getJSONArray("login");


                  String reg=jsonObject.getString("return");

                  if(reg.equals("booked"))
                  {

                    seatList.get(j).setColorFilter(Color.parseColor("#61555C"));
                    seatList.get(j).setClickable(false);

                    String ff= seat_no[j];

                    filledSeats.add(ff);


                    //   Toast.makeText(FirstActivity.this, "seat"+ j+ "booked", Toast.LENGTH_SHORT).show();



                  }
                  //code executed when
                  else if(reg.equals("success"))
                  {


                    seatList.get(j).setColorFilter(Color.parseColor("#d9197f"));

                    update_status_booked(j);

                  }


                } catch (JSONException e) {


                  Toast.makeText(FirstActivity.this, " catch error" + e.toString(), Toast.LENGTH_LONG).show();
                  e.printStackTrace();
                }


              }
            }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {

        Toast.makeText(FirstActivity.this, " error" + error.toString(), Toast.LENGTH_SHORT).show();

      }
    }) {







      @Override
      protected Map<String, String> getParams() throws AuthFailureError {
        Map<String, String> params = new HashMap<>();


        String MyPreferences="mypref";
        SharedPreferences sharedPreferences=getSharedPreferences(MyPreferences, (Context.MODE_PRIVATE));

        String session_idsss= sharedPreferences.getString("sessions_ids","");
        String phone_numberxx=sharedPreferences.getString("phone_numbers","");



        //   Toast.makeText(FirstActivity.this, "bbbb"+phone_number+","+session_id, Toast.LENGTH_SHORT).show();

        Intent datess = getIntent();
        String tarehes = datess.getExtras().getString("date");
        String departure_from = datess.getExtras().getString("from");
        String destination = datess.getExtras().getString("to");
        String coach = datess.getExtras().getString("coach");
        String type = datess.getExtras().getString("type");
        String seatno=seat_no[j];
        String sess=session_idsss;
        String phone=phone_numberxx;


        params.put("ids", tarehes);
        params.put("departure_from", departure_from);
        params.put("destination", destination);
        params.put("coach", coach);
        params.put("type", type);
        params.put("seat_no", seatno);
        params.put("session_id",sess);
        params.put("phone_number",phone);


        //  Toast.makeText(FirstActivity.this, "bbbb"+phone_number+","+session_id, Toast.LENGTH_SHORT).show();


        // return super.getParams();
        return params;
      }

    };


    RequestQueue requestQueue = Volley.newRequestQueue(FirstActivity.this);
    requestQueue.add(stringRequest);

  }


  //function to update status table (coach_one_status) to booked on successful booking during seat choosing
  public void update_status_booked(final int j) {


    final String seat_no[] = {"seat1_a", "seat1_b", "seat1_c", "seat1_d", "seat2_a", "seat2_b", "seat2_c", "seat2_d", "seat3_a", "seat3_b", "seat3_c", "seat3_d", "seat4_a", "seat4_b", "seat4_c",
            "seat4_d", "seat5_a", "seat5_b", "seat5_c", "seat5_d", "seat6_a", "seat6_b", "seat6_c", "seat6_d", "seat7_a", "seat7_b", "seat7_c", "seat7_d", "seat8_a", "seat8_b", "seat8_c", "seat8_d"};

    String STATUS_URL = "http://project-daudi.000webhostapp.com/android_login_register/update_status_booked.php";
    StringRequest stringRequest = new StringRequest(Request.Method.POST, STATUS_URL,
            new Response.Listener<String>() {
              @Override
              public void onResponse(String status_response) {

                try {
                  JSONObject jsonObject=new JSONObject(status_response);
                  // int log=jsonObject.getInt("log");
                  String status_responses=jsonObject.getString("return");



                  if (status_responses.equals("success"))
                  {
                    String selected_string = seat_no[j];

                    selectedSeats.add(selected_string);

                    String unselected = seat_no[j];
                    // unselectedSeats.add(unselected);

                    unselectedSeats.remove(unselected);

                    int cart1=selectedSeats.size();




                    TextView cart=findViewById(R.id.cartss);
                    if (cart.getText().toString()==null)
                    {

                      cart.setText(String.valueOf(cart1));
                    }
                    else
                    {

                      cart.setText(String.valueOf(cart1));

                    }

                  }


                  else
                  {

                    Toast.makeText(FirstActivity.this, "Unable to update details", Toast.LENGTH_SHORT).show();

                  }

                } catch (JSONException e) {



                  Toast.makeText(FirstActivity.this, " catch error" + e.toString(), Toast.LENGTH_LONG).show();
                  e.printStackTrace();
                }


              }
            }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {

        Toast.makeText(FirstActivity.this, " error" + error.toString(), Toast.LENGTH_SHORT).show();

      }
    }) {

      @Override
      protected Map<String, String> getParams() throws AuthFailureError {
        Map<String, String> params = new HashMap<>();

        String MyPreferences="mypref";
        SharedPreferences sharedPreferences=getSharedPreferences(MyPreferences, (Context.MODE_PRIVATE));

        String session_idsss= sharedPreferences.getString("sessions_ids","");
        String phone_numberxx=sharedPreferences.getString("phone_numbers","");





        Intent datess=getIntent();
        String tarehes=  datess.getExtras().getString("date");
        String departure_from=  datess.getExtras().getString("from");
        String destination=  datess.getExtras().getString("to");
        String coach=  datess.getExtras().getString("coach");
        String type=  datess.getExtras().getString("type");
        String seatno=seat_no[j];

        params.put("ids", tarehes);
        params.put("departure_from", departure_from);
        params.put("destination", destination);
        params.put("coach", coach);
        params.put("type", type);
        params.put("seat_no",seatno);
        params.put("session_id",session_idsss);
        params.put("phone_number",phone_numberxx);



        // return super.getParams();
        return params;
      }

    };




    RequestQueue requestQueue= Volley.newRequestQueue(FirstActivity.this);
    requestQueue.add(stringRequest);
  }


  //function to update status table to free on successful booking cancellation during seat choosing
  public void update_status_free(final int i) {


    final String seat_no[] = {"seat1_a", "seat1_b", "seat1_c", "seat1_d", "seat2_a", "seat2_b", "seat2_c", "seat2_d", "seat3_a", "seat3_b", "seat3_c", "seat3_d", "seat4_a", "seat4_b", "seat4_c",
            "seat4_d", "seat5_a", "seat5_b", "seat5_c", "seat5_d", "seat6_a", "seat6_b", "seat6_c", "seat6_d", "seat7_a", "seat7_b", "seat7_c", "seat7_d", "seat8_a", "seat8_b", "seat8_c", "seat8_d"};

    String STATUS_URL = "http://project-daudi.000webhostapp.com/android_login_register/update_status_free.php";
    StringRequest stringRequest = new StringRequest(Request.Method.POST, STATUS_URL,
            new Response.Listener<String>() {
              @Override
              public void onResponse(String status_response_free) {

                try {
                  JSONObject jsonObject=new JSONObject(status_response_free);
                  // int log=jsonObject.getInt("log");
                  String status_responsess=jsonObject.getString("return");



                  if (status_responsess.equals("success"))
                  {


                    seatList.get(i).setColorFilter(Color.parseColor("#56C412"));
                    // seatList.get(i).setSelected(true);
                    selectedSeats.remove(seat_no[i]);

                    String unselected = seat_no[i];
                    unselectedSeats.add(unselected);

                    // Toast.makeText(FirstActivity.this, "de-selected " + seat_no[i], Toast.LENGTH_LONG).show();


                    TextView cart=findViewById(R.id.cartss);


                    int cart2=selectedSeats.size();


                    cart.setText(String.valueOf(cart2));


                  }


                  else
                  {

                    Toast.makeText(FirstActivity.this, "Unable to update details", Toast.LENGTH_SHORT).show();

                  }






                } catch (JSONException e) {



                  Toast.makeText(FirstActivity.this, " catch error" + e.toString(), Toast.LENGTH_LONG).show();
                  e.printStackTrace();
                }


              }
            }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {

        Toast.makeText(FirstActivity.this, " error" + error.toString(), Toast.LENGTH_SHORT).show();

      }
    }) {

      @Override
      protected Map<String, String> getParams() throws AuthFailureError {
        Map<String, String> params = new HashMap<>();


        String MyPreferences="mypref";
        SharedPreferences sharedPreferences=getSharedPreferences(MyPreferences, (Context.MODE_PRIVATE));
        String session_id= sharedPreferences.getString("session_id","");
        String phone_number=sharedPreferences.getString("phone_number","");

        Intent datess=getIntent();
        String tarehes=  datess.getExtras().getString("date");
        String departure_from=  datess.getExtras().getString("from");
        String destination=  datess.getExtras().getString("to");
        String coach=  datess.getExtras().getString("coach");
        String type=  datess.getExtras().getString("type");
        String seatno=seat_no[i];

        params.put("ids", tarehes);
        params.put("departure_from", departure_from);
        params.put("destination", destination);
        params.put("coach", coach);
        params.put("type", type);
        params.put("seat_no",seatno);
        //  params.put("phone_number", phone_number);


        // return super.getParams();
        return params;
      }

    };




    RequestQueue requestQueue= Volley.newRequestQueue(FirstActivity.this);
    requestQueue.add(stringRequest);
  }


  //function to get status of all seats on opening of coach activity
  public  void  Setstatus () {

     progressDoalog = new ProgressDialog(this);
      progressDoalog.setMax(100);
      progressDoalog.setMessage("Tickets loading....");
      progressDoalog.setIndeterminate(true);
      progressDoalog.setTitle("ProgressDialog bar example");
      progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
      progressDoalog.show();

    String STATUS_URL = "http://project-daudi.000webhostapp.com/android_login_register/params.php";
    StringRequest stringRequest = new StringRequest(Request.Method.POST, STATUS_URL,
            new Response.Listener<String>() {
              @Override
              public void onResponse(String s) {

                try {
                  JSONObject jsonObject=new JSONObject(s);


                  //JSONArray jsonArray=new JSONArray(response);
                  JSONArray jsonArray=jsonObject.getJSONArray("status");
                 // JSONArray phone_number_array=jsonObject.getJSONArray("phone_number");


                  //  JSONArray jsonArray=jsonObject.getJSONArray("login");


                  if(jsonArray.length()>1) {

                    for (int i = 0; i < jsonArray.length(); i++) {

                      JSONObject status = jsonArray.getJSONObject(i);
                      JSONObject phone_number_i = jsonArray.getJSONObject(i);
                      JSONObject session_id_i = jsonArray.getJSONObject(i);

                      String state = status.getString("coach_one_status");
                      String phone_number_for_state = phone_number_i.getString("phone_number");
                      String session_ide = session_id_i.getString("session_id");


                      if (state.equals("booked")) {
                        ////////////////////////////////////////////////////////////////

                        seatList.get(i).setColorFilter(Color.parseColor("#443737"));
                        seatList.get(i).setClickable(false);

                        String ff= seat_no[i];
                         // "#61555C"
                        filledSeats.add(ff);

                        //////////////////////////////////////////////////////////////////////////////////

//if the state is free then set the color of all those seats at i to green for all users
                      } else if(state.equals("free")) {

                        //   Toast.makeText(FirstActivity.this, ""+i, Toast.LENGTH_SHORT).show();

                        seatList.get(i).setColorFilter(Color.parseColor("#56C412"));

                        String unselected=seat_no[i];
                        unselectedSeats.add(unselected);

                        // seatList.get(i).setClickable(false);
                      }
                      else
                      {   String MyPreferences="mypref";
                        SharedPreferences sharedPreferences=getSharedPreferences(MyPreferences, (Context.MODE_PRIVATE));

                        String session_idsss= sharedPreferences.getString("sessions_ids","");
                        String phone_numberxx=sharedPreferences.getString("phone_numbers","");
                        if (phone_number_for_state.equals(phone_numberxx) || session_ide.equals(session_idsss))
                        {


Setstatus_for_current_user(i);




                          //changing the color of seat that was previously selected by user to green(free)
                        /*  else
                          {
                            seatList.get(i).setColorFilter(Color.parseColor("#56C412"));

                            String unselected=seat_no[i];
                            unselectedSeats.add(unselected);

                          }*/
                        }
                        //changing the color of seat that was
                        else
                        {
                          seatList.get(i).setColorFilter(Color.parseColor("#61555C"));
                          seatList.get(i).setClickable(false);

                         // update_status_free(i);

                        }







                        // Toast.makeText(FirstActivity.this, ""+i, Toast.LENGTH_SHORT).show();
//if the state of the sear at i is equal to booked then u set the color of the seat for all users to grey and the sit is not clickabe



                      }
                    }
                  }
                /*  else
                  {

                    for (int i = 0; i < seatList.size(); i++) {

                        seatList.get(i).setColorFilter(Color.parseColor("#BD1E1E"));
                      seatList.get(i).setClickable(false);

                    }



                  }*/


                    progressDoalog.dismiss();
                } catch (JSONException e) {



                  Toast.makeText(FirstActivity.this, " catch error" + e.toString(), Toast.LENGTH_LONG).show();
                  e.printStackTrace();
                }


              }
            }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {

        Toast.makeText(FirstActivity.this, " error" + error.toString(), Toast.LENGTH_SHORT).show();

      }
    }) {





      @Override
      protected Map<String, String> getParams() throws AuthFailureError {
        Map<String, String> params = new HashMap<>();



          String MyPreferences="mypref";
          SharedPreferences sharedPreferences=getSharedPreferences(MyPreferences, (Context.MODE_PRIVATE));

          String session_idsss= sharedPreferences.getString("sessions_ids","");
          String phone_numberxx=sharedPreferences.getString("phone_numbers","");

        Intent datess=getIntent();
        String tarehes=  datess.getExtras().getString("date");
        String departure_from=  datess.getExtras().getString("from");
        String destination=  datess.getExtras().getString("to");
        String coach=  datess.getExtras().getString("coach");
        String type=  datess.getExtras().getString("type");


        params.put("ids", tarehes);
        params.put("departure_from", departure_from);
        params.put("destination", destination);
        params.put("coach", coach);
        params.put("type", type);
        params.put("session_id",session_idsss);
          params.put("phone_number",phone_numberxx);
        //  params.put("phone_number", phone_number);


        // return super.getParams();
        return params;
      }

    };




    RequestQueue requestQueue= Volley.newRequestQueue(FirstActivity.this);
    requestQueue.add(stringRequest);

    removeSimpleProgressDialog();
  }

  public  void  Setstatus_for_current_user (final int i) {



    String STATUS_URL_ = "http://project-daudi.000webhostapp.com/android_login_register/param.php";
    StringRequest stringRequest = new StringRequest(Request.Method.POST, STATUS_URL_,
            new Response.Listener<String>() {
              @Override
              public void onResponse(String subject) {

                try {
                  JSONObject jsonObject=new JSONObject(subject);
                   String log=jsonObject.getString("log");

                  //JSONArray jsonArray=new JSONArray(response);

                  // JSONArray phone_number_array=jsonObject.getJSONArray("phone_number");

if (log.equals("greater"))
{
  seatList.get(i).setColorFilter(Color.parseColor("#d9197f"));
  seatList.get(i).setClickable(true);

  String selected_string = seat_no[i];

  selectedSeats.add(selected_string);

  TextView cart=findViewById(R.id.cartss);


  int cart2=selectedSeats.size();


  cart.setText(String.valueOf(cart2));
}
else
{
  seatList.get(i).setColorFilter(Color.parseColor("#61555C"));
  seatList.get(i).setClickable(false);
}








                } catch (JSONException e) {



                  Toast.makeText(FirstActivity.this, " catch error" + e.toString(), Toast.LENGTH_LONG).show();
                  e.printStackTrace();
                }


              }
            }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {

        Toast.makeText(FirstActivity.this, " error" + error.toString(), Toast.LENGTH_SHORT).show();

      }
    }) {





      @Override
      protected Map<String, String> getParams() throws AuthFailureError {
        Map<String, String> params = new HashMap<>();



        String MyPreferences="mypref";
        SharedPreferences sharedPreferences=getSharedPreferences(MyPreferences, (Context.MODE_PRIVATE));

        String session_idsss= sharedPreferences.getString("sessions_ids","");
        String phone_numberxx=sharedPreferences.getString("phone_numbers","");

        Intent datess=getIntent();
        String tarehes=  datess.getExtras().getString("date");
        String departure_from=  datess.getExtras().getString("from");
        String destination=  datess.getExtras().getString("to");
        String coach=  datess.getExtras().getString("coach");
        String type=  datess.getExtras().getString("type");


        params.put("ids", tarehes);
        params.put("departure_from", departure_from);
        params.put("destination", destination);
        params.put("coach", coach);
        params.put("type", type);
        params.put("session_id",session_idsss);
        params.put("phone_number",phone_numberxx);
        //  params.put("phone_number", phone_number);


        // return super.getParams();
        return params;
      }

    };




    RequestQueue requestQueue= Volley.newRequestQueue(FirstActivity.this);
    requestQueue.add(stringRequest);
  }


    public  void  checking_log () {



        String STATUS_URL_ = "http://project-daudi.000webhostapp.com/android_login_register/checking.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, STATUS_URL_,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String subject) {


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(FirstActivity.this, " error" + error.toString(), Toast.LENGTH_SHORT).show();

            }
        }) {





            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();



                String MyPreferences="mypref";
                SharedPreferences sharedPreferences=getSharedPreferences(MyPreferences, (Context.MODE_PRIVATE));

                String session_idsss= sharedPreferences.getString("sessions_ids","");
                String phone_numberxx=sharedPreferences.getString("phone_numbers","");

                Intent datess=getIntent();
                String tarehes=  datess.getExtras().getString("date");
                String departure_from=  datess.getExtras().getString("from");
                String destination=  datess.getExtras().getString("to");
                String coach=  datess.getExtras().getString("coach");
                String type=  datess.getExtras().getString("type");


                params.put("ids", tarehes);
                params.put("departure_from", departure_from);
                params.put("destination", destination);
                params.put("coach", coach);
                params.put("type", type);
                params.put("session_id",session_idsss);
                params.put("phone_number",phone_numberxx);
                //  params.put("phone_number", phone_number);


                // return super.getParams();
                return params;
            }

        };




        RequestQueue requestQueue= Volley.newRequestQueue(FirstActivity.this);
        requestQueue.add(stringRequest);
    }



    public static void removeSimpleProgressDialog() {
        try {
       // Dialog mProgressDialog = null;
        if (mProgressDialog != null) {
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                    mProgressDialog = null;
                }
            }
        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();

        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void showSimpleProgressDialog(Context context, String title,
                                                String msg, boolean isCancelable) {
        try {
            if (mProgressDialog == null) {
                mProgressDialog = ProgressDialog.show(context, title, msg);
                mProgressDialog.setCancelable(isCancelable);
            }

            if (!mProgressDialog.isShowing()) {
                mProgressDialog.show();
            }

        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();
        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}





