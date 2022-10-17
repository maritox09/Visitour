package com.example.visitour.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.visitour.Beans.Item;
import com.example.visitour.R;
import com.example.visitour.registro.pags.ItemDetailActivity;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {
    private List<Item> mItems;
    private Context context;

    Comparator<Item> compareByNombre = new Comparator<Item>() {
        @Override
        public int compare(Item o1, Item o2) {
            return o1.getmNombre().compareTo(o2.getmNombre());
        }
    };

    Comparator<Item> compareByRating = new Comparator<Item>() {
        @Override
        public int compare(Item o1, Item o2) {
            return o1.getmRating().compareTo(o2.getmRating());
        }
    };

    Comparator<Item> compareByDepto = new Comparator<Item>() {
        @Override
        public int compare(Item o1, Item o2) {
            return o1.getmDepto().compareTo(o2.getmDepto());
        }
    };

    public ItemsAdapter(List<Item> mItems) {
        this.mItems = mItems;
    }

    public void reloadData(List<Item> items) {
        this.mItems = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(this.context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_dinamico, parent, false);

        // Return a new holder instance
        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        Item item = mItems.get(position);

        // Set item views based on your views and data model
        TextView bookNameTextView = holder.mItemNombre;
        bookNameTextView.setText(item.mNombre);
        TextView bookAuthorTextView = holder.mItemDepto;
        bookAuthorTextView.setText(item.mDepto);
        TextView urlTextView = holder.mUrl;
        urlTextView.setText(item.mImageUrl);
        TextView ratingTextView = holder.mRating;
        ratingTextView.setText(String.format("%d/5", item.mRating));

        ImageView bookImage = holder.mItemImage;

        Glide.with(this.context).load(item.mImageUrl).into(bookImage);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ImageView mItemImage;
        private final TextView mItemNombre;
        private final TextView mItemDepto;
        private final TextView mUrl;
        private final TextView mRating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mItemImage = itemView.findViewById(R.id.item_image);
            mItemNombre = itemView.findViewById(R.id.item_nombre);
            mItemDepto = itemView.findViewById(R.id.item_depto);
            mUrl = itemView.findViewById(R.id.item_image_url);
            mRating = itemView.findViewById(R.id.item_rating);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), ItemDetailActivity.class);
            intent.putExtra("eNombre",mItemNombre.getText().toString());
            intent.putExtra("eDepto",mItemDepto.getText().toString());
            intent.putExtra("eUrl",mUrl.getText().toString());
            view.getContext().startActivity(intent);
        }
    }

    @SuppressLint("NewApi")
    public void Ord_Nom_Asc(boolean asc){
        if(asc){
            Collections.sort(mItems,compareByNombre.reversed());
        } else {
            Collections.sort(mItems, compareByNombre);
        }
        reloadData(mItems);
    }

    @SuppressLint("NewApi")
    public void Ord_Dep_Asc(boolean asc){
        if(asc){
            Collections.sort(mItems,compareByDepto);
        } else {
            Collections.sort(mItems, compareByDepto.reversed());
        }
        reloadData(mItems);
    }

    @SuppressLint("NewApi")
    public void Ord_Rat_Asc(boolean asc){
        try {
            if(asc){
                Collections.sort(mItems,compareByRating);
            } else {
                Collections.sort(mItems, compareByRating.reversed());
            }
            reloadData(mItems);
        } catch (Exception e ){}
    }
}
