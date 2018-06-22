package com.starklabs.sharemylocation;

import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.common.ConnectionResult;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener{

    TextView tvData;
    Button btnShare1;
    GoogleApiClient gac;
    Location loc;

    Button btnTakePic, btnShare2;
    ImageView iv1;
    Bitmap photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvData = (TextView)findViewById(R.id.tvData);
        btnShare1 = (Button)findViewById(R.id.btnShare1);

        btnTakePic = (Button)findViewById(R.id.btnTakePic);
        btnShare2 = (Button)findViewById(R.id.btnShare2);
        iv1 = (ImageView)findViewById(R.id.iv1);

        GoogleApiClient.Builder builder = new GoogleApiClient.Builder(this);
        builder.addApi(LocationServices.API);
        builder.addOnConnectionFailedListener(this);
        builder.addConnectionCallbacks(this);
        gac = builder.build();

        btnShare1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_TEXT,"My Address is "+tvData.getText().toString());
                startActivity(i);
            }
        });//if you create connection in onCreate it will be permanent. So create it in resume and disconnect in pause

        btnShare2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File f = new File(getExternalCacheDir(),"p1.png");
                try{
                    FileOutputStream fos = new FileOutputStream(f);
                    photo.compress(Bitmap.CompressFormat.PNG, 100, fos);
                    fos.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("image/*");
                i.putExtra(Intent.EXTRA_TEXT, tvData.getText().toString());
                i.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(f));
                startActivity(i);
            }
        });

        btnTakePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i, 123);
            }
        });
    }//end of onCreate

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ( requestCode == 123 && resultCode == RESULT_OK){
            photo = (Bitmap) data.getExtras().get("data");
            iv1.setImageBitmap(photo);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(gac != null)
            gac.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(gac!=null)
            gac.disconnect();
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        loc = LocationServices.FusedLocationApi.getLastLocation(gac); //get last location of my client
        if(loc != null){
            double lat = loc.getLatitude();
            double lon = loc.getLongitude();

            Geocoder g = new Geocoder(this, Locale.ENGLISH);
            try{
                List<Address> la = g.getFromLocation(lat, lon, 1);
                Address add = la.get(0);
                String msg = add.getCountryName()+" "+add.getAdminArea()+" "+add.getSubAdminArea()+" "+add.getLocality()
                        +" "+add.getSubLocality()+" "+add.getThoroughfare()+" "+add.getSubThoroughfare()+" "+add.getPostalCode();
                tvData.setText(msg);
            }catch (IOException e){
                e.printStackTrace();
            }

        }else{
            Toast.makeText(this,"bahar nikal/enable gps", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
