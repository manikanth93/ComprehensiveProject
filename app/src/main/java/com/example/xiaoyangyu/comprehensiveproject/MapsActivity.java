package com.example.xiaoyangyu.comprehensiveproject;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback
{
    private List<Building> buildingList;
    private GoogleMap mMap;
    private Building building;
    private Map<Marker, Integer> allMarkersMap = new HashMap<Marker, Integer>();
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Marshall");
        toolbar.setSubtitle("University");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        TextView textView=(TextView)findViewById(R.id.textView);
        textView.setText("1:Dot Hicks Field\n2:Third Avenue Parking Garage\n3:Robert c. Byrd Biotechnology Science Building(BBSC)\n4:Arthur Weisberg Family Applied Engineering Complex(WAEC)\n5:Arthur Weisberg Family Engineering Laboratories(EL)\n6:Chris Cline Athletic Complex\n7:John C. Edwards Stadium\n8: Sorrell Maintenance Building" +
                "9:Multipurpose Field\n10:Cam Henderson Center(HC)\n11:Laidley Hall (LA)\n12:East Hall (HH)\n13:Harris Hall\n14:Science Building\n15:Morrow Library\n16:Communications Building\n17:Smith Hall (SH)\n18:Fresh Man Residence Hall(FN)\n19:Gullickson Hall(GH)\n20:Prichard Hall(PH)\n21:Buskirk Hall(BU)\n22:Old Main(OM)\n23:Recreation Center\n24:Brian D. Fox Tennis Center" +
                "\n25:Jenkins Hall\n26:Memorial Fountain\n27:John Marshall Statue\n28:Drinko Library\n29:Twin Towers\n30:Holderby Hall\n31:One Room Schoolhouse\n32:Campus Christian Center\n33:Memorial Student Center\n34:Corbly Hall\n35:Bliss Charles Public Safety Building\n36:Harless Dining Hall\n37:Career Services Center\n38:Jomie Jazz Center\n39:Joan C. Edwards Performing Arts Center" +
                "\n40:Brad D. Smith Foundation Hall/Erickson Alumni Center\n41:Marshall Newman Center\n42:Joseph M. Gillette Welcome Center\n43:Wilber E. Myers Hall\n44:Marshall Commons \n45:Sixth Avenue Parking Facility"
        );
    }
    public void mapMarker(int i)
    {
        Bitmap bp= BitmapFactory.decodeResource(getResources(),R.drawable.marker32).copy(Bitmap.Config.ARGB_8888, true);
        Canvas canvas=new Canvas(bp);
        Paint paint=new Paint();
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(26);
        Rect bounds = new Rect();
        int x = (bp.getWidth() - bounds.width())/2;
        int y = (bp.getHeight() - bounds.height())/2;
        LatLng location = new LatLng(buildingList.get(i).getLongtitude(), buildingList.get(i).getLatitude());
        MarkerOptions options = new MarkerOptions( );
        options.position(location);
        options.title(buildingList.get(i).getName());
        String s=String.valueOf(i+1);
        canvas.drawText(s,x,y,paint);
        BitmapDescriptor icon = BitmapDescriptorFactory.fromBitmap(bp);
        options.icon(icon);
        Marker marker= mMap.addMarker(options);
        allMarkersMap.put(marker,i);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 16));
        /*Bitmap bp= BitmapFactory.decodeResource(getResources(),R.drawable.marker32).copy(Bitmap.Config.ARGB_8888, true);
        Canvas canvas=new Canvas(bp);
        Paint paint=new Paint();
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(26);
        Rect bounds = new Rect();
        int x = (bp.getWidth() - bounds.width())/2;
        int y = (bp.getHeight() - bounds.height())/2;
        LatLng location = new LatLng(38.426658, -82.419376);
        MarkerOptions options = new MarkerOptions( );
        options.position(location);
        options.title("dasdasd");
        BitmapDescriptor icon = BitmapDescriptorFactory.fromBitmap(bp);
        options.icon(icon);
        String s=String.valueOf(i+1);
        canvas.drawText(s,x,y,paint);
        Marker marker= mMap.addMarker(options);
        allMarkersMap.put(marker,i);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 16));*/
    }
    public void onMapReady(GoogleMap googleMap)
    {
        mMap = googleMap;
        mMap.setMyLocationEnabled(true);
        DatabaseHelper dbhelper=new DatabaseHelper(this);
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
        int i=0;
        while(i<45)
        {
            mapMarker(i);
            mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener()
            {
                public void onInfoWindowClick(Marker marker)
                {
                    int d=allMarkersMap.get(marker);
                    Intent intent = new Intent(MapsActivity.this, Main1Activity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("values", d);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
            i++;
        }
    }
}



