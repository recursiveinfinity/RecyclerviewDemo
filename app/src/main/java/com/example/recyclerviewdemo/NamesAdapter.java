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

    private final List<GithubProfile> data;

    private static OnNameSelectedListener listener = null;

    public NamesAdapter(OnNameSelectedListener listener) {
        this.data = new ArrayList<>();
        NamesAdapter.listener = listener;
    }

    public void setData(List<GithubProfile> newData) {
        data.clear();
        data.addAll(newData);
        notifyDataSetChanged();
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
        GithubProfile githubProfile = data.get(position);
        nameViewHolder.tvName.setText(githubProfile.getName());
        nameViewHolder.tvPublicRepos.setText(
                Integer.toString(githubProfile.getPublicRepos()));
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
        TextView tvPublicRepos;
        ImageView ivIcon;

        public NameViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvPublicRepos = itemView.findViewById(R.id.tvPublicRepos);
            ivIcon = itemView.findViewById(R.id.ivIcon);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onNameSelected(getAdapterPosition());
                }
            });
        }

    }
}
