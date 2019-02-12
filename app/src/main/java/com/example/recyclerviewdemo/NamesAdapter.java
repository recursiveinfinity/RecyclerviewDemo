package com.example.recyclerviewdemo;

import android.content.res.ColorStateList;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.ImageViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NamesAdapter extends RecyclerView.Adapter<NamesAdapter.NameViewHolder> {

    private final List<String> data;

    public NamesAdapter(List<String> data) {
        this.data = new ArrayList<>();
        this.data.addAll(data);
    }


    @NonNull
    @Override
    public NameViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View rootView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_name, viewGroup, false);
        return new NameViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull NameViewHolder nameViewHolder,
                                 int position) {
        nameViewHolder.tvName.setText(data.get(position));

        int color;
        if (position %2 == 0) {
            color = ContextCompat.getColor(nameViewHolder.itemView.getContext(),
                            R.color.colorAccent);
        } else {
            color = ContextCompat.getColor(nameViewHolder.itemView.getContext(),
                    R.color.colorPrimary);
        }
        ImageViewCompat.setImageTintList(nameViewHolder.ivIcon,
                ColorStateList.valueOf(color));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class NameViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        ImageView ivIcon;

        public NameViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            ivIcon = itemView.findViewById(R.id.ivIcon);
        }

    }
}
