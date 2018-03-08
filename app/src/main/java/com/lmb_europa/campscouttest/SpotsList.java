package com.lmb_europa.campscouttest;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lmb_europa.campscouttest.Common.Common;
import com.lmb_europa.campscouttest.Interface.ItemClickListener;
import com.lmb_europa.campscouttest.Model.Spots;
import com.lmb_europa.campscouttest.ViewHolder.SpotsViewHolder;
import com.squareup.picasso.Picasso;

public class SpotsList extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference spots;

    RecyclerView recycler_spot;
    RecyclerView.LayoutManager layoutManager;

    FirebaseRecyclerAdapter<Spots, SpotsViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spots_list);

        //Init Firebase
        database = FirebaseDatabase.getInstance();
        spots = database.getReference("Spots");

        //Load spots
        recycler_spot = (RecyclerView) findViewById(R.id.recycler_spots);
        recycler_spot.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recycler_spot.setLayoutManager(layoutManager);

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.btnCart);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cartIntent = new Intent(SpotsList.this, Cart.class);
                startActivity(cartIntent);
            }
        });

        if (Common.isConnectingToInternet(this))
            loadSpots();
        else
        {
            Toast.makeText(SpotsList.this, "Please check your internet connection", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    private void loadSpots() {

         adapter = new FirebaseRecyclerAdapter<Spots, SpotsViewHolder>(Spots.class, R.layout.spot, SpotsViewHolder.class, spots) {
            @Override
            protected void populateViewHolder(SpotsViewHolder viewHolder, Spots model, int position) {
                viewHolder.txtSpotName.setText(model.getNumber());
                Picasso.with(getBaseContext()).load(model.getImage()).into(viewHolder.imageView);
                final Spots clickItem = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        //Start details Activity
                        Intent spotDetails = new Intent(SpotsList.this, SpotDetails.class);
                        spotDetails.putExtra("SpotId", adapter.getRef(position).getKey());
                        startActivity(spotDetails);
                    }
                });
            }
        };
        recycler_spot.setAdapter(adapter);
    }
}
