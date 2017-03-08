package com.example.xiaoyangyu.comprehensiveproject;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Marshall");
        toolbar.setSubtitle("University");
        setSupportActionBar(toolbar);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id)
        {
            case R.id.search:
                Intent intent1=new Intent();
                intent1.setClass(MainActivity.this, SearchActivity.class);
                MainActivity.this.startActivity(intent1);
                break;
            case R.id.map:
                Intent intent2=new Intent();
                intent2.setClass(MainActivity.this, MapsActivity.class);
                MainActivity.this.startActivity(intent2);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}