package com.example.xiaoyangyu.comprehensiveproject;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
public class Main1Activity extends AppCompatActivity {
    private List<Building> buildingList;
    private DatabaseHelper dbhelper;
    ImageView imageView;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Marshall");
        toolbar.setSubtitle("University");
        setSupportActionBar(toolbar);
        imageView=(ImageView)findViewById(R.id.imageView);
        imageView.setBackgroundColor(Color.parseColor("#ff669900"));
        dbhelper=new DatabaseHelper(this);
        File database=getApplicationContext().getDatabasePath(DatabaseHelper.DBNAME);
        if(false==database.exists())
        {
            dbhelper.getReadableDatabase();
            if(copyDatabase(this))
            {
                Toast.makeText(this,"copy database success", Toast.LENGTH_SHORT).show();
            }else
            {
                Toast.makeText(this,"copy database error", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        SQLiteDatabase db=dbhelper.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from building",null);
        buildingList=new ArrayList<Building>();
        while (cursor.moveToNext())
        {
            String name=cursor.getString(cursor.getColumnIndex("name"));
            String introduction=cursor.getString(cursor.getColumnIndex("introduction"));
            double longitude=cursor.getFloat(cursor.getColumnIndex("longitude"));
            double latitude=cursor.getFloat(cursor.getColumnIndex("latitude"));
            Building building=new Building(name,introduction,longitude,latitude);
            buildingList.add(building);
        }
        /*LinearLayout ll = (LinearLayout)findViewById(R.id.ll);
        TextView textView=new TextView(this);*/
        TextView textView=(TextView)findViewById(R.id.textView);
        Bundle myBundle=this.getIntent().getExtras();
        int count=myBundle.getInt("values");
        switch(count)
        {
            case 0:
                imageView.setImageResource(R.drawable.image1);
                textView.setText(buildingList.get(count).toString());
                //ll.addView(textView);
                break;
            case 1:
                imageView.setImageResource(R.drawable.image2);
                textView.setText(buildingList.get(count).toString());
                //ll.addView(textView);
                break;
            case 2:
                imageView.setImageResource(R.drawable.image3);
                textView.setText(buildingList.get(count).toString());
                //ll.addView(textView);
                break;
            case 3:
                imageView.setImageResource(R.drawable.image4);
                textView.setText(buildingList.get(count).toString());
                //ll.addView(textView);
                break;
            case 4:
                imageView.setImageResource(R.drawable.image5);
                textView.setText(buildingList.get(count).toString());
                //ll.addView(textView);
                break;
            case 5:
                imageView.setImageResource(R.drawable.image6);
                textView.setText(buildingList.get(count).toString());
                //ll.addView(textView);
                break;
            case 6:
                imageView.setImageResource(R.drawable.image7);
                textView.setText(buildingList.get(count).toString());
                //ll.addView(textView);
                break;
            case 7:
                imageView.setImageResource(R.drawable.image8);
                textView.setText(buildingList.get(count).toString());
                //ll.addView(textView);
                break;
            case 8:
                imageView.setImageResource(R.drawable.image9);
                textView.setText(buildingList.get(count).toString());
                //ll.addView(textView);
                break;
            case 9:
                imageView.setImageResource(R.drawable.image10);
                textView.setText(buildingList.get(count).toString());
                //ll.addView(textView);
                break;
            case 10:
                imageView.setImageResource(R.drawable.image11);
                textView.setText(buildingList.get(count).toString());
                //ll.addView(textView);
                break;
            case 11:
                imageView.setImageResource(R.drawable.image12);
                textView.setText(buildingList.get(count).toString());
                //ll.addView(textView);
                break;
            case 12:
                imageView.setImageResource(R.drawable.image13);
                textView.setText(buildingList.get(count).toString());
                //ll.addView(textView);
                break;
            case 13:
                imageView.setImageResource(R.drawable.image14);
                textView.setText(buildingList.get(count).toString());
                //ll.addView(textView);
                break;
            case 14:
                imageView.setImageResource(R.drawable.image15);
                textView.setText(buildingList.get(count).toString());
                //ll.addView(textView);
                break;
            case 15:
                imageView.setImageResource(R.drawable.image16);
                textView.setText(buildingList.get(count).toString());
                //ll.addView(textView);
                break;
            case 16:
                imageView.setImageResource(R.drawable.image16);
                textView.setText(buildingList.get(count).toString());
                //ll.addView(textView);
                break;
            case 17:
                imageView.setImageResource(R.drawable.image16);
                textView.setText(buildingList.get(count).toString());
                //ll.addView(textView);
                break;
        }

    }
    private boolean copyDatabase(Context context)
    {
        try{
            InputStream inputStream=context.getAssets().open(DatabaseHelper.DBNAME);
            String outFileName=DatabaseHelper.DBLOCATION+DatabaseHelper.DBNAME;
            OutputStream outputStream=new FileOutputStream(outFileName);
            byte[]buff=new byte[1024];
            int length=0;
            while ((length=inputStream.read(buff))>0)
            {
                outputStream.write(buff,0,length);
            }
            outputStream.flush();
            outputStream.close();
            Log.v("Main1Activity","DB copied");
            return true;

        }catch(Exception e){
            e.printStackTrace();
            return false;

        }

    }
}
