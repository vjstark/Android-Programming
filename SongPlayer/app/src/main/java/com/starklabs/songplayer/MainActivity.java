package com.starklabs.songplayer;

import android.content.ContentResolver;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    ListView lvSongs;
    Button btnPause,btnStop;
    MediaPlayer mp;
    String name[], path[];

    ImageButton ibtnSkipBack,ibtnSkipForward,ibtnNext,ibtnPrevious;
    int cs = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvSongs = (ListView)findViewById(R.id.lvSongs);
        btnPause = (Button)findViewById(R.id.btnPause);
        btnStop = (Button)findViewById(R.id.btnStop);
        mp = new MediaPlayer();

        ibtnPrevious = (ImageButton)findViewById(R.id.ibtnPrevious);
        ibtnSkipBack = (ImageButton)findViewById(R.id.ibtnSkipBack);
        ibtnSkipForward = (ImageButton)findViewById(R.id.ibtnSkipForward);
        ibtnNext = (ImageButton)findViewById(R.id.ibtnNext);

        ContentResolver cr = getContentResolver();
        final Cursor cursor = cr.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,null,null,null,null);

        name = new String[cursor.getCount()];
        path = new String[cursor.getCount()];

        int i = 0;

        while (cursor.moveToNext()){
            name[i] = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
            path[i] = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));

            i++;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,name);

        lvSongs.setAdapter(adapter);

        lvSongs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                cs = i;
                String p = path[i];
                mp.reset();

                try {
                    mp.setDataSource(p);
                    mp.prepare();
                    mp.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mp.isPlaying()){
                    mp.pause();
                    btnPause.setText("Resume");
                }else{
                    mp.start();
                    btnPause.setText("Pause");
                }
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mp.isPlaying()){
                    mp.stop();
                }
            }
        });

        ibtnSkipBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.seekTo(mp.getCurrentPosition() - 3000);
            }
        });

        ibtnSkipForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.seekTo(mp.getCurrentPosition() + 3000);
            }
        });

        ibtnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int ns = cs - 1;
                if (ns < 0){
                    Toast.makeText(MainActivity.this, "first song",Toast.LENGTH_SHORT).show();
                    return;
                }
                cs = ns;
                String p = path[cs];
                mp.reset();
                try {
                    mp.setDataSource(p);
                    mp.prepare();
                    mp.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        ibtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int ns = cs +1;
                if (ns == path.length){
                    Toast.makeText(MainActivity.this, "last song",Toast.LENGTH_SHORT).show();
                    return;
                }
                cs = ns;
                String p = path[cs];
                mp.reset();
                try {
                    mp.setDataSource(p);
                    mp.prepare();
                    mp.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
