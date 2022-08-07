package com.itcraftsolution.expansemanager.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.itcraftsolution.expansemanager.R;
import com.itcraftsolution.expansemanager.databinding.SlideLayoutBinding;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.viewHolder> {

    Context context;

    public int[] sliderImages ={
            R.drawable.trackexpanse,
            R.drawable.financecontroller,
            R.drawable.financegoal
    };
    public String[] sliderHeadings = {
            "Track Expanse",
            "Control Finance",
            "Finance Goal"
    };

    public int[] sliderDesc = {

            R.string.onBoarding1,
            R.string.onBoarding2,
            R.string.onBoarding3
    };

    public SliderAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.slide_layout, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        holder.binding.igSliderImage.setImageResource(sliderImages[position]);
        holder.binding.txSliderHeading.setText(sliderHeadings[position]);
        holder.binding.txSliderDesc.setText(sliderDesc[position]);
    }

    @Override
    public int getItemCount() {
        return sliderHeadings.length;
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        SlideLayoutBinding binding;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            binding = SlideLayoutBinding.bind(itemView);
        }
    }
}
