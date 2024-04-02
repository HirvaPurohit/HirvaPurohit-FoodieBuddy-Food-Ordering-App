package com.example.foodorderingapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.foodorderingapp.Adapters.CartAdapter;
import com.example.foodorderingapp.ModelData.CartData_Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class frag_cart extends Fragment {

    RecyclerView cartRecycleview;
    ArrayList<CartData_Model> cartdata;
    Button proccedbtn;
    String url = "https://foododerappproject.000webhostapp.com/newapi.php";


//    int quantity;


    public frag_cart() {
        // Required empty public constructor
    }


    public static frag_cart newInstance(String param1, String param2) {
        frag_cart fragCart = new frag_cart();

        return fragCart;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_frag_cart, container, false);

        cartRecycleview = v.findViewById(R.id.cartRecycleview);

        proccedbtn = v.findViewById(R.id.proccedbtn);


//        totalTextView = v.findViewById(R.id.totalTextView);
//        cart_quantitynumber = v.findViewById(R.id.cart_quantitynumber);
//        quantity = Integer.parseInt(cart_quantitynumber.getText().toString());

        cartdata = new ArrayList<>();

        cartRecycleview.setHasFixedSize(true);
        cartRecycleview.setLayoutManager(new LinearLayoutManager(getContext()));

//        cartdata.add(new CartData_Model("Burger", "$100"));
//        cartdata.add(new CartData_Model("Pizza", "$150"));
//        cartdata.add(new CartData_Model("french fry", "$90"));
//        cartdata.add(new CartData_Model("popcorn", "$60"));

        try {
            fetch();

        } catch (Exception e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

//        proccedbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Toast.makeText(getContext(), "Your Order Placed Successfully", Toast.LENGTH_SHORT).show();
//                Intent i = new Intent(getContext(), OrderPlacedMsg.class);
//                startActivity(i);
//                double total = calculateTotal(cartdata);
//                displayTotal(total);
//            }
//        });

        return v;
    }

    void fetch() {
        RequestQueue queue = Volley.newRequestQueue(getContext());
//        String url = "https://jsonplaceholder.typicode.com/posts";

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                cartRecycleview.setVisibility(View.VISIBLE);
                for (int x = 0; x < response.length(); x++) {
                    try {
                        JSONObject object = response.getJSONObject(x);
                        String Name = object.getString("name");
                        String Price = object.getString("price");
                        String Imageurl = object.getString("img");
//                        String Detail = object.getString("detail");
//                        int qty = object.getInt("qty");

                        cartdata.add(new CartData_Model(Name, Price,Imageurl));
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
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


//    private double calculateTotal(ArrayList<CartData_Model> cartItems) {
//        double total = 0.0;
//        for (CartData_Model item : cartItems) {
//            // Parse the price as a double and add to the total
//            total += Double.parseDouble(item.getCarItemPrice());
//        }
//        return total;
//    }

//    void DisplayQuantity(){
//        cart_quantitynumber.setText(String.valueOf(quantity));
//    }

//    private void displayTotal(double total) {
    // Format total and display in the TextView
//        float decimalFormat = new float("#.##");
//        totalTextView.setText("Total: $" + total);
//    }

    void popularRecycle_indetail() {
        CartAdapter adapter = new CartAdapter(cartdata, getContext());
        cartRecycleview.setAdapter(adapter);
        adapter.OnCartAdapterListener(new CartAdapter.OnCartAdapterListener() {
//        @Override
//        public void onAddToCartClick(int position) {

            //            CartData_Model model = cartdata.get(position);
//                quantity++;
//                DisplayQuantity();
//            Toast.makeText(getContext(), "Add Quantity In Cart", Toast.LENGTH_SHORT).show();
//                 }
//
//        @Override
//        public void onDeleteFromCartClick(int position) {
//            if(quantity == 0){
//                Toast.makeText(getContext(), "Can't Decrese Quantity < 0", Toast.LENGTH_SHORT).show();
//            }
//            else {
//                quantity--;
//                DisplayQuantity();
//
//            }
//            Toast.makeText(getContext(), "Delete Quantity In Cart ", Toast.LENGTH_SHORT).show();
//        }
//
            @Override
            public void OnRemoveFromCart(int position) {
                cartdata.remove(position);
                adapter.notifyDataSetChanged();
                Toast.makeText(getContext(), "Remove Item From the Cart", Toast.LENGTH_SHORT).show();
            }

        });


    }
}