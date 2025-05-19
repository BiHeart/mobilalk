package com.example.turaapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    public static final Map<String, Integer> categoryIcons = new HashMap<>();

    public interface OnCategoryClickListener {
        void onCategoryClick(String category);
    }

    private List<String> categories;
    private OnCategoryClickListener listener;

    public CategoryAdapter(List<String> categories, OnCategoryClickListener listener) {
        this.categories = categories;
        this.listener = listener;

        categoryIcons.put("Kékes", R.drawable.kekes);
        categoryIcons.put("Mátra", R.drawable.matra);
        categoryIcons.put("Zala", R.drawable.zala2);
        categoryIcons.put("Velencei-tó", R.drawable.velencei);
        categoryIcons.put("Balaton",R.drawable.balaton);
        categoryIcons.put("Dunakanyar",R.drawable.dunakanyar);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String category = categories.get(position);
        holder.categoryName.setText(category);

        Integer iconRes = categoryIcons.get(category);
        if (iconRes != null) {
            holder.categoryIcon.setImageResource(iconRes);
        } else {
            holder.categoryIcon.setImageResource(R.drawable.tura);
        }

        holder.itemView.setOnClickListener(v -> listener.onCategoryClick(category));
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView categoryName;
        ImageView categoryIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.categoryName);
            categoryIcon = itemView.findViewById(R.id.categoryIcon);
        }
    }
}
