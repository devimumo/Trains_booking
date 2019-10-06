package com.firstapp.david.trains;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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

public class RegisterActivity extends AppCompatActivity {


    private EditText name,email,phone_number,password;
    public ProgressBar progress;
    public Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = (EditText) findViewById(R.id.name);
        email= (EditText) findViewById(R.id.email);
        password=(EditText) findViewById(R.id.password);
        phone_number=(EditText)findViewById(R.id.phone_number);
        progress=(ProgressBar)findViewById(R.id.progress);

        register =(Button) findViewById(R.id.button);



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Regist();
            }
        });


    }

    public  void Regist()
    {

        progress.setVisibility(View.VISIBLE);
        register.setVisibility(View.GONE);

        final  String name_=this.name.getText().toString().trim();
        final  String email_ =this.email.getText().toString().trim();
        final  String password_=this.password.getText().toString().trim();
        final  String phone_number_=this.phone_number.getText().toString().trim();




        if (!name_.isEmpty()||!email_.isEmpty()||phone_number_.isEmpty()||password_.isEmpty())
        {

            String URL_REGIST="http://project-daudi.000webhostapp.com/android_login_register/register.php";
            StringRequest stringRequest=new StringRequest(Request.Method.POST, URL_REGIST,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String s) {

                            try{
                                JSONObject jsonObject=new JSONObject(s);
                                String reg=jsonObject.getString("return");

                                if(reg.equals("exist"))
                                {
  Toast.makeText(RegisterActivity.this,"Please Enter another number,that one is already used", Toast.LENGTH_SHORT).show();
                                    progress.setVisibility(View.GONE);
                                    register.setVisibility(View.VISIBLE);

                                }
                                else if(reg.equals("regist"))
                                {

                                    Toast.makeText(RegisterActivity.this, "Registered Successful", Toast.LENGTH_SHORT).show();
                                    progress.setVisibility(View.GONE);
                                    register.setVisibility(View.VISIBLE);

                                    Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                                    startActivity(intent);

                                }

                            } catch (JSONException e) {

                                Toast.makeText(RegisterActivity.this, "RegisterActivity error......" + e.toString(), Toast.LENGTH_SHORT).show();
                                progress.setVisibility(View.GONE);
                                register.setVisibility(View.VISIBLE);
                                e.printStackTrace();
                            }

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(RegisterActivity.this, " error.........." + error.toString(), Toast.LENGTH_SHORT).show();
                            progress.setVisibility(View.GONE);
                            register.setVisibility(View.VISIBLE);
                        }
                    })
            {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params=new HashMap<>();

                    params.put("name",name_);
                    params.put("email",email_);
                    params.put("password",password_);
                    params.put("phone_number",phone_number_);


                   // return super.getParams();
                    return  params;
                }
            };

            RequestQueue requestQueue= Volley.newRequestQueue(RegisterActivity.this);
            requestQueue.add(stringRequest);
        }
        else
        {
            name.setError("Please enter name");
            email.setError("Please enter email");
            phone_number.setError("Please enter phone number");
            password.setError("Please enter password");


        }




    }


}
