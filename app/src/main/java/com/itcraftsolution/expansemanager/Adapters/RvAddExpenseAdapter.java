package com.itcraftsolution.expansemanager.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.itcraftsolution.expansemanager.Models.Category;
import com.itcraftsolution.expansemanager.R;
import com.itcraftsolution.expansemanager.databinding.RvAddExpenseSampleBinding;

import java.util.ArrayList;

public class RvAddExpenseAdapter extends RecyclerView.Adapter<RvAddExpenseAdapter.viewHolder> {

    private Context context;
    private ArrayList<Category> list;

    public RvAddExpenseAdapter(Context context, ArrayList<Category> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_add_expense_sample, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        Category model = list.get(position);
        Glide.with(context).load(model.getCatImg()).into(holder.binding.igIconSample);
        holder.binding.txCatNameSample.setText(model.getCatName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        private RvAddExpenseSampleBinding binding;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            binding = RvAddExpenseSampleBinding.bind(itemView);
        }
    }
}
