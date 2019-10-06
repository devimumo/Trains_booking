package com.firstapp.david.trains;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.firstapp.david.trains.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GridAdapter  extends BaseAdapter  {


    Button seat;





    public int icons[];
    public String letters[];

    private Context context;

    private LayoutInflater layoutInflater;


    public GridAdapter (Context context, int [] icons, String[] letters){

this.context=context;
this.icons=icons;
this.letters=letters;
    }



    @Override
    public int getCount() {


        return letters.length;
    }

    @Override
    public Object getItem(int i) {


       return letters[i];


    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

    View gridview=view;
    if (view==null)
    {

        layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;
    gridview=layoutInflater.inflate(R.layout.customlayout,null);



    }

        ImageView icon=gridview.findViewById(R.id.seat_icon);
        TextView letter=gridview.findViewById(R.id.seat_no);

        icon.setImageResource(icons[i]);
        letter.setText(letters[i]);
      //  Intent intent=new Intent(context,MainActivity.class);
      //  intent.putExtra("position",letters[i]);






        return gridview;
    }




}
