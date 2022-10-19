package com.example.visitour;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.example.visitour.Beans.Item;
import com.example.visitour.MVP_Details.DetailsPresenter.DetailsPresenter;
import com.example.visitour.MVP_Details.DetailsPresenter.IDetailsPresenter;
import com.example.visitour.MVP_Details.DetailsView.IDetailsView;
import com.example.visitour.MVP_FavButton.FavButtonModel.FavButtonModel;
import com.example.visitour.MVP_FavButton.FavButtonModel.IFavButtonModel;
import com.example.visitour.R;
import com.example.visitour.databinding.ActivityItemDetailBinding;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class ItemDetailActivity extends AppCompatActivity implements IDetailsView {

    ActivityItemDetailBinding binding;
    IDetailsPresenter detailsPresenter;
    TextView detailsNombre, detailsDepto, detailsDescripcion;
    ImageView detailsImage;
    ImageButton detailsWaze, detailsMaps;
    ToggleButton detailsFav;
    SharedPreferences preferences;
    RatingBar detailsRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityItemDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        detailsPresenter = new DetailsPresenter(this);
        preferences = getSharedPreferences("user",Context.MODE_PRIVATE);

        detailsNombre = binding.itemDetailNombre;
        detailsDepto = binding.itemDetailDepto;
        detailsDescripcion = binding.itemDetailDescripcion;
        detailsImage = binding.itemDetailImage;
        detailsFav = binding.favoriteToggleDetalle;
        detailsWaze = binding.itemDetailWaze;
        detailsMaps = binding.itemDetailMaps;
        detailsRating = binding.itemRatingDetails;

        Bundle bundle = getIntent().getExtras();

        SharedPreferences preferences = getSharedPreferences("user", Context.MODE_PRIVATE);

        detailsPresenter.GetDetails(Integer.valueOf(bundle.getString("Item_id")),preferences.getInt("userId",0));

    }

    @Override
    public void OnDetailSuccess(Item item) {
        detailsNombre.setText(item.mNombre);
        detailsDepto.setText(item.mTipo + " ubicado en " + item.mDepto);
        detailsDescripcion.setText(item.mDescripcion);
        Glide.with(this).load(item.mImageUrl).into(detailsImage);
        detailsFav.setChecked(item.mFavorito);
        detailsRating.setRating(item.mRating);

        IFavButtonModel favButtonModel = new FavButtonModel();

        detailsFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!item.mFavorito){
                    favButtonModel.Fav(Integer.valueOf(item.mId),preferences.getInt("userId",0));
                    item.mFavorito = true;
                } else {
                    favButtonModel.UnFav(Integer.valueOf(item.mId),preferences.getInt("userId",0));
                    item.mFavorito = false;
                }
            }
        });

        detailsWaze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(item.mWaze)));
            }
        });

        detailsMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(item.mMaps));
                intent.setPackage("com.google.android.apps.maps");
                startActivity(intent);
            }
        });
    }

    @Override
    public void OnDetailFailure(String msg) {
        Toast.makeText(ItemDetailActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}