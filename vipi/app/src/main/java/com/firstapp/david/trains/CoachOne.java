package com.firstapp.david.trains;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CoachOne extends AppCompatActivity {

    GridView gridView;
    ArrayList<ImageView> seatList;
    private LayoutInflater layoutInflater;
    ImageView imageView;






   public String seat_no[]={"1-A","1-B","1-C","1-D","2-A","2-B","2-C","2-D","3-A","3-B","3-C","3-D","4-A","4-B","4-C","4-D",
                 "5-A","5-B","5-C","5-D","6-A","6-B","6-C","6-D","7-A","7-B","7-C","7-D","8-A","8-B","8-C","8-D"};

    final int seat_icon[]={R.drawable.seat1_a,R.drawable.seat1_b,R.drawable.seat1_c,R.drawable.seat1_d,R.drawable.seat2_a,
            R.drawable.seat2_b,R.drawable.seat2_c,R.drawable.seat2_d,R.drawable.seat3_a,R.drawable.seat3_b,
            R.drawable.seat3_c,R.drawable.seat3_d,R.drawable.seat4_a,R.drawable.seat4_b,R.drawable.seat4_c,
            R.drawable.seat4_d,R.drawable.seat5_a,R.drawable.seat5_b,R.drawable.seat5_c,R.drawable.seat5_d,
            R.drawable.seat6_a,R.drawable.seat6_b,R.drawable.seat6_c,R.drawable.seat6_d,R.drawable.seat7_a,
            R.drawable.seat7_b,R.drawable.seat7_c,R.drawable.seat7_d,R.drawable.seat8_a,R.drawable.seat8_b,
            R.drawable.seat8_c, R.drawable.seat8_d};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_one);





        seatList = new ArrayList<>();
        seatList.add((ImageView) findViewById(R.id.seat1));
        seatList.add((ImageView) findViewById(R.id.seat2));

ImageView seaticon=findViewById(R.id.seat_icon);





    gridView= findViewById(R.id.grid_view__coach_one);
        final GridAdapter adapter=new GridAdapter(CoachOne.this,seat_icon,seat_no);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

               // imageView=(ImageView) findViewById(R.id.seat_icon);
                Toast.makeText(CoachOne.this, "Clicked seat no"+seat_no[i], Toast.LENGTH_SHORT).show();

               ImageView icon=view.findViewById(R.id.seat_icon);
                icon.setColorFilter(Color.parseColor("#ffffff"));


            }


        });







    }
}
