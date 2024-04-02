package com.example.foodorderingapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorderingapp.ModelData.CartData_Model;
import com.example.foodorderingapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder>{


    ArrayList<CartData_Model> mylistdata;
    private OnCartAdapterListener listener;
    Context context;


    public CartAdapter(ArrayList<CartData_Model> mylistdata,Context context) {
        this.mylistdata = mylistdata;
        this.context  = context;
    }

    public void OnCartAdapterListener(OnCartAdapterListener cartAdapterListener){
        listener = cartAdapterListener;
    }
    public interface OnCartAdapterListener {
        //        void onAddToCartClick(int position);
//        void onDeleteFromCartClick(int position);
        void  OnRemoveFromCart(int position);
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =  LayoutInflater.from(parent.getContext());
        View listitem = inflater.inflate(R.layout.cart_item_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(listitem,listener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {
        holder.cart_itemname.setText(mylistdata.get(position).getCartItems());
        holder.cart_price.setText(mylistdata.get(position).getCarItemPrice());
        Picasso.get().load(mylistdata.get(position).getCartimg()).into(holder.cart_img);
    }

    @Override
    public int getItemCount() {
        return mylistdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView cart_itemname, cart_price;
        ImageView cart_img;
        ImageView addToCartButton ,removeFromCartButton ,deletefromCartButton;

        public ViewHolder(@NonNull View itemView,OnCartAdapterListener listener) {
            super(itemView);

            cart_itemname = itemView.findViewById(R.id.cart_item);
            cart_img = itemView.findViewById(R.id.cart_img);
            cart_price = itemView.findViewById(R.id.cartitem_price);
//            addToCartButton  = itemView.findViewById(R.id.plus_icon);
            removeFromCartButton = itemView.findViewById(R.id.delete_icon);
//            deletefromCartButton = itemView.findViewById(R.id.minus_icon);

//            addToCartButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (listener != null) {
//                        listener.onAddToCartClick(getAdapterPosition());
//                    }
//                }
//            });

//            deletefromCartButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (listener != null) {
//                        listener.onDeleteFromCartClick(getAdapterPosition());
//
//                    }
//
//                }
//            });

            removeFromCartButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.OnRemoveFromCart(getAdapterPosition());
                }
            });
//            cart_img = itemView.findViewById(R.id.cart_img);

        }
    }
}
