package com.lmb_europa.campscouttest;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lmb_europa.campscouttest.Common.Common;
import com.lmb_europa.campscouttest.Database.Database;
import com.lmb_europa.campscouttest.Model.Order;
import com.lmb_europa.campscouttest.Model.Spots;
import com.squareup.picasso.Picasso;

public class SpotDetails extends AppCompatActivity {

    TextView spot_name, spot_price, spot_description;
    ImageView spot_image;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnCart;

    String spotId = "";

    FirebaseDatabase database;
    DatabaseReference spots;

    Spots currentSpot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot_details);

        //Firebase
        database = FirebaseDatabase.getInstance();
        spots = database.getReference("Spots");

        //View
        btnCart = (FloatingActionButton)findViewById(R.id.btnCart);

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Database(getBaseContext()).addToCart(new Order(
                        spotId,
                        currentSpot.getNumber(),
                        currentSpot.getPrice(),
                        currentSpot.getDiscount()
                ));
                Toast.makeText(SpotDetails.this, "Added to the cart", Toast.LENGTH_SHORT).show();
            }
        });

        spot_description = (TextView)findViewById(R.id.spot_description);
        spot_name = (TextView)findViewById(R.id.spot_name);
        spot_price = (TextView)findViewById(R.id.spot_price);
        spot_image = (ImageView)findViewById(R.id.img_spot);

        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppbar);

        if (getIntent() != null)
            spotId = getIntent().getStringExtra("SpotId");

        if (!spotId.isEmpty())
        {
            if (Common.isConnectingToInternet(getBaseContext()))
                getDetailFood(spotId);
            else
            {
                Toast.makeText(SpotDetails.this, "Please check your internet connection", Toast.LENGTH_SHORT).show();
                return;
            }
        }
    }

    private void getDetailFood(String spotId) {

        spots.child(spotId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                currentSpot = dataSnapshot.getValue(Spots.class);

                Picasso.with(getBaseContext()).load(currentSpot.getImage()).into(spot_image);

                collapsingToolbarLayout.setTitle(currentSpot.getNumber());

                spot_price.setText(currentSpot.getPrice());

                spot_name.setText(currentSpot.getNumber());

                spot_description.setText(currentSpot.getDetails());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
