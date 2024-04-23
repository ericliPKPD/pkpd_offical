package com.example.myapplication_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ComparsionAdapter extends RecyclerView.Adapter<ComparsionAdapter.ComparsionViewHolder>{
    private Context ctx;
    private ArrayList<String> pname, pprice, fromshop;
    private ArrayList<Double> diff;
    private double oriPrice;

    ComparsionAdapter(Context ctx, ArrayList pname, ArrayList pprice, ArrayList fromshop, ArrayList diff) {
        this.ctx = ctx;
        this.pname = pname;
        this.pprice = pprice;
        this.fromshop = fromshop;
        this.diff = diff;
    }

    @NonNull
    @Override
    public ComparsionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View v = inflater.inflate(R.layout.compare_row, parent, false);
        return new ComparsionViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull ComparsionViewHolder holder, int position) {
        holder.tx_name.setText(String.valueOf(pname.get(position)));
        holder.tx_price.setText(String.valueOf(pprice.get(position)));
        holder.tx_fromshop.setText(String.valueOf(fromshop.get(position)));
        holder.tx_diff.setText(String.valueOf(diff.get(position)));

        if (diff.get(position) > 0) {
            holder.imageView.setImageResource(R.drawable.baseline_arrow_drop_up_24);
        } else if (diff.get(position) < 0) {
            holder.imageView.setImageResource(R.drawable.baseline_arrow_drop_down_24);
        } else {
            holder.imageView.setImageResource(R.drawable.baseline_change_history_24);
        }
    }

    @Override
    public int getItemCount() {
        return pname.size();
    }

    public static class ComparsionViewHolder extends RecyclerView.ViewHolder {
        TextView tx_name, tx_price, tx_fromshop, tx_diff;
        ImageView imageView;


        public ComparsionViewHolder(View itemView) {
            super(itemView);
            tx_name = itemView.findViewById(R.id.Prod_Name);
            tx_price = itemView.findViewById(R.id.Prod_Price);
            tx_fromshop = itemView.findViewById(R.id.FromShop);
            tx_diff = itemView.findViewById(R.id.textView3);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
