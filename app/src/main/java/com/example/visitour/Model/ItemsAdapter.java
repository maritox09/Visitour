package com.example.visitour.Model;

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
import com.example.visitour.R;
import com.example.visitour.registro.pags.ItemDetailActivity;

import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {
    private List<Item> mItems;
    private Context context;

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
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
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
        ratingTextView.setText(String.valueOf(item.mRating)+"/5");

        ImageView bookImage = holder.mItemImage;

        Glide.with(this.context).load(item.mImageUrl).into(bookImage);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView mItemImage;
        private TextView mItemNombre, mItemDepto, mUrl, mRating;

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
}
