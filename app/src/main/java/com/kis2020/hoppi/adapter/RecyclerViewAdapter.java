package com.kis2020.hoppi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kis2020.hoppi.R;
import com.kis2020.hoppi.model.BrandOpportunity;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<BrandOpportunity> brandOpportunityArrayList;

    private OnOpportunityListiner onOpportunityListiner;

    public RecyclerViewAdapter(Context context, ArrayList<BrandOpportunity> brandOpportunityArrayList,OnOpportunityListiner onOpportunityListiner) {
        this.context = context;
        this.brandOpportunityArrayList = brandOpportunityArrayList;
        this.onOpportunityListiner = onOpportunityListiner;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView opportunityTitle;
        TextView opportunityDiscount;
        LinearLayout linearLayout;

        OnOpportunityListiner onOpportunityListiner;

        public ViewHolder(@NonNull View itemView,OnOpportunityListiner onOpportunityListiner ) {
            super(itemView);
            opportunityTitle = itemView.findViewById(R.id.tv_opportunity_title);
            opportunityDiscount =itemView.findViewById(R.id.tv_opportunity_discount);
            linearLayout = itemView.findViewById(R.id.ll_base_layout);

            this.onOpportunityListiner = onOpportunityListiner;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onOpportunityListiner.onOpportunityClick(getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_item, parent,false);
        ViewHolder holder =new ViewHolder(view,onOpportunityListiner);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BrandOpportunity brandOpportunity = brandOpportunityArrayList.get(position);

        holder.opportunityTitle.setText(brandOpportunity.getTitle());
        holder.opportunityDiscount.setText(brandOpportunity.getDiscount());
        if(position % 2 ==0){
            holder.linearLayout.setBackgroundResource(R.color.softBlue);
        }

    }

    @Override
    public int getItemCount() {
        return brandOpportunityArrayList.size();
    }


    public interface OnOpportunityListiner {
        void onOpportunityClick(int position);
    }


}
