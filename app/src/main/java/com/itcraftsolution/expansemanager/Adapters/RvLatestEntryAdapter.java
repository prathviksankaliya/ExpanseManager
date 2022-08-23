package com.itcraftsolution.expansemanager.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.itcraftsolution.expansemanager.Models.LatestEntry;
import com.itcraftsolution.expansemanager.R;
import com.itcraftsolution.expansemanager.databinding.RvHistorySampleBinding;

import java.util.ArrayList;

public class RvLatestEntryAdapter extends RecyclerView.Adapter<RvLatestEntryAdapter.viewHolder> {

    Context context;
    ArrayList<LatestEntry> list;

    public RvLatestEntryAdapter(Context context, ArrayList<LatestEntry> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_history_sample, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        LatestEntry model = list.get(position);
        holder.binding.igFood.setImageResource(model.getImg());
        holder.binding.txExpenseName.setText(model.getCategory());
        holder.binding.txExpenseCurrency.setText(model.getCurrency());
        holder.binding.txExpensePrice.setText(model.getPrice());
        holder.binding.txDateMonth.setText(model.getDateMonth());
        holder.binding.txYear.setText(model.getYear());
        holder.binding.txTimeHHMM.setText(model.getTime());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        RvHistorySampleBinding binding;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            binding = RvHistorySampleBinding.bind(itemView);
        }
    }
}
