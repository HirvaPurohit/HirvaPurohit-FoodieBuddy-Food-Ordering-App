package com.example.foodorderingapp;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.foodorderingapp.Adapters.foodlist_Adapter;
import com.example.foodorderingapp.Adapters.promotionrecycle_Adapter;
import com.example.foodorderingapp.ModelData.foodlistData_model;
import com.example.foodorderingapp.ModelData.promotion_list;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class frag_homemenu extends Fragment {

    RecyclerView recycleview_popular,recycleview_promotion;
    TextView textView;
    ArrayList<foodlistData_model> mylistdata;
    String url = "https://foododerappproject.000webhostapp.com/newapi.php";

    public frag_homemenu()  {
        // Required empty public constructor
    }


    public static frag_homemenu newInstance(String param1, String param2) {
        frag_homemenu fragment = new frag_homemenu();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint({"MissingInflatedId", "SuspiciousIndentation"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_frag_homemenu, container, false);


        recycleview_popular = v.findViewById(R.id.recycleview_popular);

        recycleview_promotion = v.findViewById(R.id.recycleview_promotion);
        textView = v.findViewById(R.id.textview);

        mylistdata =new ArrayList<>();

//        mylistdata.add(new foodlistData_model(R.drawable.pizza,"Pizza","$150"));
//        mylistdata.add(new foodlistData_model(R.drawable.burger,"Burger","$100"));
//        mylistdata.add(new foodlistData_model(R.drawable.cake_c,"Cake","$250"));
//        mylistdata.add(new foodlistData_model(R.drawable.coffee,"Coffee","$50"));
//        mylistdata.add(new foodlistData_model(R.drawable.french_fryf,"French Fry","$90"));
//        mylistdata.add(new foodlistData_model(R.drawable.juis,"Juice","$60"));
//        mylistdata.add(new foodlistData_model(R.drawable.popcorn_c,"PopCorn","$60"));


        recycleview_popular.setHasFixedSize(true);
//      foodlist_Adapter adapter = new foodlist_Adapter(mylistdata);
        recycleview_popular.setLayoutManager(new LinearLayoutManager(v.getContext(),LinearLayoutManager.HORIZONTAL,false));
//      recycleview_popular.setAdapter(adapter);


        ArrayList<promotion_list> promoListdata = new ArrayList<>();

        promoListdata.add(new promotion_list(R.drawable.fruit_juice_promotion));
        promoListdata.add(new promotion_list(R.drawable.fast_food_promotion));
        promoListdata.add(new promotion_list(R.drawable.healthy_food_prmotion));
        promoListdata.add(new promotion_list(R.drawable.promotion_food_img));
        promoListdata.add(new promotion_list(R.drawable.cookie_promotion));
        promoListdata.add(new promotion_list(R.drawable.pancake_promotion));

        recycleview_promotion.setHasFixedSize(true);
        promotionrecycle_Adapter promoAdapter = new promotionrecycle_Adapter(promoListdata);
        recycleview_promotion.setLayoutManager(new LinearLayoutManager(v.getContext(),LinearLayoutManager.HORIZONTAL,false));
        recycleview_promotion.setAdapter(promoAdapter);

//        request();
        try {
            fetch();

        }catch (Exception e){
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

//        addtocartbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                frag_cart fragCart = new frag_cart();
//
//                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.fragment_container, fragCart);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
//

//                Intent i = new Intent(getContext(), frag_cart.class);
//                startActivity(i);

//                SaveCart();
//            }
//        });

        return  v;
    }

    void request(){
        RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET,url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                textView.setText("Response "+response);
                String nmnmn="";
                try {
//                    JSONArray datas = new JSONArray(response);

                    /*for (int x=0;x<datas.length();x++)
                    {*/
                    JSONObject object = new JSONObject(response);
                    JSONArray ae = object.getJSONArray("name");
//                         nmnmn = object.get("name").toString();
                    Toast.makeText(getContext(), ""+object, Toast.LENGTH_SHORT).show();
                    //}
                    Toast.makeText(getContext(), nmnmn+" err", Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    Log.d("HOME",e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequest);
    }

    void fetch(){
        RequestQueue queue = Volley.newRequestQueue(getContext());
//        String url = "https://jsonplaceholder.typicode.com/posts";

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                recycleview_popular.setVisibility(View.VISIBLE);
                for(int x = 0; x<response.length(); x++){
                    try {
                        JSONObject object = response.getJSONObject(x);
                        String Name = object.getString("name");
                        String Price = object.getString("price");
                        String Imageurl = object.getString("img");
                        String Detail = object.getString("detail");
//                        int qty = object.getInt("qty");

                        mylistdata.add(new foodlistData_model(Imageurl,Name,Price,Detail));
                    } catch (JSONException e) {
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                    popularRecycle_indetail();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(jsonObjectRequest);

    }

    void popularRecycle_indetail(){
        foodlist_Adapter adapter = new foodlist_Adapter(mylistdata);
        recycleview_popular.setAdapter(adapter);
        adapter.setOnClickListener(new foodlist_Adapter.OnItemClickListener() {
            @Override
            public void OnImageClick(int position) {
                Intent intent = new Intent(getContext(), Food_Details_Activity.class);
                intent.putExtra("ItemName",mylistdata.get(position).getFoodname());
                intent.putExtra("ItemImge",mylistdata.get(position).getFoodimg());
                intent.putExtra("ItemDetail",mylistdata.get(position).getFooddetail());
//                intent.putExtra("Itemquantity",mylistdata.get(position).getFoodqyantity());
                startActivity(intent);

            }

            @Override
            public void AddToCart(int position) {

                Toast.makeText(getContext(), "Add In Cart Successfully", Toast.LENGTH_SHORT).show();
            }
        });

    }

}