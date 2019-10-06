package com.firstapp.david.trains;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private EditText phone_number,password;
    private TextView account;
    public ProgressBar progress;
    public Button login;
    private String MyPreferences="mypref";
//public SharedPreferenceConfig sharedPreferenceConfig;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        String MyPreferences="mypref";
        SharedPreferences sharedPreferences=getSharedPreferences(MyPreferences, (Context.MODE_PRIVATE));
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.clear().commit();





        phone_number=findViewById(R.id.phone_number);
        password=findViewById(R.id.password);
        account=findViewById(R.id.account);
        login=findViewById(R.id.login);
        progress=findViewById(R.id.progress);


/*if (sharedPreferenceConfig.readLoginStatus(false))
{

    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
    startActivity(intent);
    finish();

}*/


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                Login();


            }
        });


        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    public void Login() {

       progress.setVisibility(View.VISIBLE);
        login.setVisibility(View.GONE);



        final String password_ = this.password.getText().toString().trim();
        final String phone_number_= this.phone_number.getText().toString().trim();

        if (!phone_number_.isEmpty()||!password_.isEmpty())
        {

            String URL_LOGIN = "https://project-daudi.000webhostapp.com/android_login_register/login.php";
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String s) {

                            try{

                                JSONObject jsonObject=new JSONObject(s);
                                String log=jsonObject.getString("success");
                               String session_ids=jsonObject.getString("session_id");
                                if(log.equals("none"))
                                {
                                    Toast.makeText(LoginActivity.this,
                                            "Please click on account to register", Toast.LENGTH_SHORT).show();
                                    progress.setVisibility(View.GONE);
                                    login.setVisibility(View.VISIBLE);

                                }
                                else if (log.equals("login"))
                                {


                                    progress.setVisibility(View.GONE);
                                    login.setVisibility(View.VISIBLE);
                                    Intent intent=new Intent(LoginActivity.this,DashBoard.class);
                                    startActivity(intent);
                                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                    //writing phonenumber and session_id to shared preferences data

                                    String MyPreferences="mypref";
                                    SharedPreferences sharedPreferences=getSharedPreferences(MyPreferences, (Context.MODE_PRIVATE));
                                   // String session_id= sharedPreferences.getString("sessions_ids","");


                                    SharedPreferences.Editor editor=sharedPreferences.edit();
                                    phone_number=findViewById(R.id.phone_number);
                                    String phone_number_= phone_number.getText().toString().trim();

                                    editor.putString("sessions_ids",session_ids);
                                    editor.putString("phone_numbers",phone_number_);
                                    editor.commit();


                                }
                                else {

                                    Toast.makeText(LoginActivity.this,
                                            "Please Enter the Right password", Toast.LENGTH_SHORT).show();
                                    progress.setVisibility(View.GONE);
                                    login.setVisibility(View.VISIBLE);
                                }

                            } catch (JSONException e) {
                                Toast.makeText(LoginActivity.this, "login error" + e.toString(), Toast.LENGTH_SHORT).show();
                                progress.setVisibility(View.GONE);
                                login.setVisibility(View.VISIBLE);



                                e.printStackTrace();
                            }


                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {


                            Toast.makeText(LoginActivity.this, "Login error....." + volleyError.toString(), Toast.LENGTH_SHORT).show();
                            progress.setVisibility(View.GONE);
                            login.setVisibility(View.VISIBLE);

                        }
                    })

            {


                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params=new HashMap<>();


                    params.put("password",password_);

                    params.put("phone_number",phone_number_);


                   // return super.getParams();
                  return  params;
                }





            };
            RequestQueue requestQueue= Volley.newRequestQueue(LoginActivity.this);
            requestQueue.add(stringRequest);



        }
        else
        {

            //execute if user has entered the wrong phonenumber and password

            phone_number.setError("required");
            password.setError("required");
            progress.setVisibility(View.GONE);
            login.setVisibility(View.VISIBLE);




        }







    }




}
