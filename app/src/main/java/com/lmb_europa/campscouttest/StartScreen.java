package com.lmb_europa.campscouttest;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;
import java.util.List;

public class StartScreen extends AppCompatActivity {

    private FloatingActionMenu menuRed;

    private FloatingActionButton fab0;
    private FloatingActionButton fab1;
    private FloatingActionButton fab2;
    private FloatingActionButton fab3;
    private FloatingActionButton fab4;

    private List<FloatingActionMenu> menus = new ArrayList<>();
    private Handler mUiHandler = new Handler();
    Boolean stuff = false;

    String sendData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        //Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        //setSupportActionBar(myToolbar);
        //myToolbar.setTitleTextColor(getResources().getColor(R.color.titlecolor));

        menuRed = (FloatingActionMenu) findViewById(R.id.menu_red);

        fab0 = (FloatingActionButton) findViewById(R.id.fab0);
        fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab3 = (FloatingActionButton) findViewById(R.id.fab3);
        fab4 = (FloatingActionButton) findViewById(R.id.fab4);

        fab0.setEnabled(true);
        fab1.setEnabled(true);
        fab2.setEnabled(true);
        fab3.setEnabled(true);
        fab4.setEnabled(true);

        menuRed.setClosedOnTouchOutside(true);

        menuRed.hideMenuButton(false);

        menus.add(menuRed);

        fab0.setOnClickListener(clickListener);
        fab1.setOnClickListener(clickListener);
        fab2.setOnClickListener(clickListener);
        fab3.setOnClickListener(clickListener);
        fab4.setOnClickListener(clickListener);

        int delay = 400;
        for (final FloatingActionMenu menu : menus) {
            mUiHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    menu.showMenuButton(true);
                }
            }, delay);
            delay += 150;
        }
        menuRed.setOnMenuButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (menuRed.isOpened()) {
                    Toast.makeText(StartScreen.this, "Have fun!", Toast.LENGTH_SHORT).show();
                }
                menuRed.toggle(true);

            }
        });

    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.fab0:
                    Intent i0 = new Intent(StartScreen.this, OrderStatus.class);
                    i0.putExtra("checkApp", sendData);
                    startActivity(i0);
                    break;
                case R.id.fab1:
                    Intent i1 = new Intent(StartScreen.this, DatePicker.class);
                    i1.putExtra("checkApp", sendData);
                    startActivity(i1);
                    break;
                case R.id.fab2:
                    Intent i2 = new Intent(StartScreen.this, MapActivity.class);
                    i2.putExtra("checkApp", sendData);
                    startActivity(i2);
                    break;
                case R.id.fab3:
                    Intent i3 = new Intent(StartScreen.this, GaleryActivity.class);
                    startActivity(i3);
                    break;
                case R.id.fab4:
                    Intent i4 = new Intent(StartScreen.this, HelpActivity.class);
                    startActivity(i4);
                    break;
            }
        }
    };

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        finish();
        System.exit(0);
    }
}
