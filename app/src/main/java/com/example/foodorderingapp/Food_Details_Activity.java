package com.example.foodorderingapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Food_Details_Activity extends AppCompatActivity {

    ImageView back_arrow, detailfoodimg;
    TextView detailfoodname,descriptiontxtview,quantitynumber;
    ImageView plus_icon,minus_icon;
    int qty ;
    Button addtocartbtn;
    String url = "https://foododerappproject.000webhostapp.com/newapi.php";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);


        back_arrow = findViewById(R.id.back_arrow);
        detailfoodimg = findViewById(R.id.detailfoodimg);
        detailfoodname = findViewById(R.id.detailfoodname);
        descriptiontxtview = findViewById(R.id.descriptiontxtview);
        quantitynumber = findViewById(R.id.quantitynumber);
        plus_icon = findViewById(R.id.plus_icon);
        minus_icon = findViewById(R.id.minus_icon);

        qty = Integer.parseInt(quantitynumber.getText().toString());
        addtocartbtn = findViewById(R.id.addtocart);

        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        String foodname = getIntent().getStringExtra("ItemName");
        String foodImage = getIntent().getStringExtra("ItemImge");
        String foodDetail = getIntent().getStringExtra("ItemDetail");
//        int foodquantity = getIntent().getIntExtra("Itemquantity",0);

        detailfoodname.setText(foodname);
        Picasso.get().load(foodImage).into(detailfoodimg);

        descriptiontxtview.setText(foodDetail);
//        quantitynumber.setText(foodquantity);


        addtocartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(Food_Details_Activity.this, "Add In To Cart Successfully", Toast.LENGTH_SHORT).show();

//                Intent i = new Intent(Food_Details_Activity.this, frag_cart.class);
//                startActivity(i);
            }
        });

        plus_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qty++;
                quantitynumber.setText(String.valueOf(qty));
            }
        });

        minus_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (qty < 1) {
                    Toast.makeText(Food_Details_Activity.this, "Item is not  < 1", Toast.LENGTH_SHORT).show();
                } else {
                    qty--;
                    quantitynumber.setText(String.valueOf(qty));

                }
            }
        });
    }

    void request() {
        RequestQueue queue = Volley.newRequestQueue(Food_Details_Activity.this);

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int x = 0; x < response.length(); x++) {
                    try {
                        JSONObject object = response.getJSONObject(x);
                        String Name = object.getString("name");
                        String Price = object.getString("detail");
                        String Imageurl = object.getString("img");

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                }
//                foodlist_Adapter adapter = new foodlist_Adapter(mylistdata);
//                recycleview_popular.setAdapter(adapter);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(jsonObjectRequest);

    }

    void fetch(){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
//        String url = "https://jsonplaceholder.typicode.com/posts";

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int x = 0; x<response.length(); x++){
                    try {
                        JSONObject object = response.getJSONObject(x);
//                        String Name = object.getString("name");
//                        String Price = object.getString("price");
//                        String Imageurl = object.getString("img");
//                        String Detail = object.getString("detail");
//                        int qty = object.getInt("qty");

//                        descriptiontxtview.setText(Detail);
//                        quantitynumber.setText(qty);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(jsonObjectRequest);

    }
}