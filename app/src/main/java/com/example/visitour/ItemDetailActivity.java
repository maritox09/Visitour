package com.example.visitour;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.visitour.R;
import com.example.visitour.databinding.ActivityItemDetailBinding;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class ItemDetailActivity extends AppCompatActivity {

    ActivityItemDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityItemDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle bundle = getIntent().getExtras();

        TextView tvNombre = binding.itemDetailNombre;
        TextView tvDepto = binding.itemDetailDepto;

        tvNombre.setText(bundle.getString("eNombre"));
        tvDepto.setText(bundle.getString("eDepto"));

        CollapsingToolbarLayout toolbarLayout = findViewById(R.id.collapsing_toolbar);
        toolbarLayout.setTitle("Toolbar colapsada");
        toolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.white));
        toolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));

        ImageView bookDetailImage = findViewById(R.id.item_detail_image);
        Glide.with(this).load(bundle.getString("eUrl")).into(bookDetailImage);
    }
}