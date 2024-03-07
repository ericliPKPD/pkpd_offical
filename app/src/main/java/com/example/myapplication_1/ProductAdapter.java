package com.example.myapplication_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {

    private Context ctx;
    private ArrayList pid, pname, pprice, pfromshop;
    private  SelectListener listener;

    ProductAdapter(Context ctx, ArrayList pid, ArrayList pname, ArrayList pprice, ArrayList pfromshop, SelectListener listener){
        this.ctx = ctx;
        this.pid = pid;
        this.pname = pname;
        this.pprice = pprice;
        this.pfromshop = pfromshop;
        this.listener = listener;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View v = inflater.inflate(R.layout.a_row, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tx_id.setText(String.valueOf(pid.get(position)));
        holder.tx_name.setText(String.valueOf(pname.get(position)));
        holder.tx_price.setText(String.valueOf(pprice.get(position)));
        holder.tx_fromshop.setText(String.valueOf(pfromshop.get(position)));

    }

    @Override
    public int getItemCount() {
        return pid.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tx_id, tx_name, tx_price, tx_fromshop;
        CardView product;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tx_id = itemView.findViewById(R.id.Prod_Id);
            tx_name = itemView.findViewById(R.id.Prod_Name);
            tx_price = itemView.findViewById(R.id.Prod_Price);
            tx_fromshop = itemView.findViewById(R.id.FromShop);
            product = itemView.findViewById(R.id.Product);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    listener.onItemClick(pos);
                }
            });
        }
    }

}