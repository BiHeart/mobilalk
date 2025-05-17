package com.example.turaapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class DestinationItemAdapter extends RecyclerView.Adapter<DestinationItemAdapter.ViewHolder> implements Filterable {

    private ArrayList<DestinationItem> mDestionationItemsdata;
    private ArrayList<DestinationItem> mDestionationItemsdataAll;
    private Context mcontext;
    private int lastPosition = -1;
    public DestinationItemAdapter(Context context, ArrayList<DestinationItem> itemsData) {
        this.mDestionationItemsdata = itemsData;
        this.mDestionationItemsdataAll = itemsData;
        this.mcontext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mcontext).inflate(R.layout.lis_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DestinationItemAdapter.ViewHolder holder, int position) {
        DestinationItem currentItem = mDestionationItemsdata.get(position);

        holder.bindTo(currentItem);
    }

    @Override
    public int getItemCount() {
        return mDestionationItemsdata.size();
    }

    @Override
    public Filter getFilter() {
        return shoppingFilter;
    }

    private Filter shoppingFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<DestinationItem> filteredList = new ArrayList<>();
            FilterResults results = new FilterResults();

            if (charSequence == null || charSequence.length() == 0){
                results.count = mDestionationItemsdataAll.size();
                results.values = mDestionationItemsdataAll;
            }else {
                String filteredPattern = charSequence.toString().toLowerCase().trim();

                for (DestinationItem item : mDestionationItemsdataAll){
                    if (item.getName().toLowerCase().contains(filteredPattern)){
                        filteredList.add(item);
                    }
                }

                results.count = filteredList.size();
                results.values = filteredList;
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            mDestionationItemsdata = (ArrayList) filterResults.values;
            notifyDataSetChanged();
        }
    };

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView mTitletext;
        private TextView mInfotext;
        private ImageView mItemimage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

             mTitletext = itemView.findViewById(R.id.itemTitle);
             mInfotext = itemView.findViewById(R.id.info);
             mItemimage = itemView.findViewById(R.id.itemimage);

             itemView.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     Log.d("Activity", "Add to favourites clicked");
                 }
             });
        }

        public void bindTo(DestinationItem currentItem) {

             mTitletext.setText(currentItem.getName());
             mInfotext.setText(currentItem.getInfo());


            Glide.with(mcontext).load(currentItem.getImageResource()).into(mItemimage);
        }
    };

}

