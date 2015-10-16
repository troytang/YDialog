package com.tangwy.ydialogdemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.tangwy.ydialog.AnimDialog;
import com.tangwy.ydialog.BallsDrawable;

public class MainActivity extends AppCompatActivity {

    ImageView iv;
    BallsDrawable ballsDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//                ballsDrawable.start();
                AnimDialog.show(MainActivity.this, "  请稍候...");
//                YDialog.show(MainActivity.this);
            }
        });

        iv = (ImageView) findViewById(R.id.iv);
        ballsDrawable = new BallsDrawable(iv);
        iv.setImageDrawable(ballsDrawable);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimDialog.Builder builder = new AnimDialog.Builder(MainActivity.this);
                builder.message("请稍候").build().show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
