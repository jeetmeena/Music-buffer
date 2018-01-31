package com.example.geetmeena.music;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.net.sip.SipSession;
import android.os.Bundle;

import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.scalified.viewmover.movers.ViewMover;
import com.scalified.viewmover.movers.ViewMoverFactory;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static com.scalified.uitools.convert.DensityConverter.dpToPx;

public class MainActivity extends AppCompatActivity implements MainActivitya {

    private StaggeredGridLayoutManager gaggeredGridLayoutManager;
    RecyclerView recyclerView;
    ArrayList<CommonModel> gaggeredList;
    FloatingActionButton fab;
    Button bu;
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    Button but;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        //fab = (FloatingActionButton) findViewById(R.id.fab1);
        // but = findViewById(R.id.button4);

        // fab = findViewById(R.id.fab);


        // FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab1);
        // FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab1);

        // fab.attachToListView(recyclerView);
        //ArrayList<File> mysoing = findSong(Environment.getExternalStorageDirectory())
        gaggeredGridLayoutManager = new StaggeredGridLayoutManager(2, 1);
        recyclerView.setLayoutManager(gaggeredGridLayoutManager);


        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("We need read external storage permission to proceed")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // FIRE ZE MISSILES!
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                                dialog.dismiss();
                            }
                        });
                // Create the AlertDialog object
                builder.create();
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.


            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);


                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }

        onClick();
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

    @Override
    public void onClick() {
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.pause);
        //  FloatingActionButton floatingActionButton =findViewById(R.id.fab);
        //floatingActionButton.setContentView(imageView);
        //    floatingActionButton.build();
        try {
            gaggeredList = AllMusicPathList();


            try {

                Toast toast = Toast.makeText(this, "hii jee", Toast.LENGTH_SHORT);
                toast.show();

                // fab.setRippleColor(R.color.fab_material_amber_500);
                SolventRecyclerViewAdapter rcAdapter = new SolventRecyclerViewAdapter(MainActivity.this, gaggeredList);

                recyclerView.setAdapter(rcAdapter);

            } catch (Exception e) {

                Toast toast1 = Toast.makeText(this, "hii jeet4", Toast.LENGTH_SHORT);
                toast1.show();
            }


        } catch (Exception e) {
            Log.i("rtuio", e.getMessage());
        }
    }


    @Override
    public void onClick(View view) {


        SolventRecyclerViewAdapter jeet = new SolventRecyclerViewAdapter(view);


    }

    @Override
    public void onClick1(View view) {


    }

    @SuppressLint("ResourceAsColor")
    public ArrayList<CommonModel> AllMusicPathList() {
        ArrayList<CommonModel> musicPathArrList = new ArrayList<>();
        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";
        String[] projection = {
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.DATA,    // filepath of the audio file
                MediaStore.Audio.Media._ID,     // context id/ uri id of the file
                MediaStore.Audio.Media.DISPLAY_NAME,

        };
        byte[] art;
        Bitmap audioView = null;
        MediaMetadataRetriever metaRetriver = new MediaMetadataRetriever();
        Uri songUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        Cursor cursorAudio = getContentResolver().query(songUri, projection, selection, null, MediaStore.Audio.Media.DISPLAY_NAME);
        if (cursorAudio != null && cursorAudio.moveToFirst()) {

            //Cursor cursorAlbum;

            String jeet = null;

            try {

                int count = 0;
                // if ( cursorAudio.moveToFirst()) {
                do {

                    count++;
                    // Long albumId = Long.valueOf(cursorAudio.getString(cursorAudio.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID)));
                    //cursorAlbum = getContentResolver().query(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI,
                    //new String[]{MediaStore.Audio.Albums._ID, MediaStore.Audio.Albums.ALBUM_ART},
                    // MediaStore.Audio.Albums._ID + "=" + albumId, null, null);

                    String titel = cursorAudio.getString(0);
                    String artist = cursorAudio.getString(1);
                    String album = cursorAudio.getString(2);
                    String duretion = cursorAudio.getString(3);
                    String data = cursorAudio.getString(4);
                    String id = cursorAudio.getString(5);
                    String name = cursorAudio.getString(6);

                    //   String  uri=cursorAudio.getExtras();
                     /*   try{
                          audioView=  audioImage(data);
                           jeet =String.valueOf(audioView);
                        }catch(Exception e){
                            Toast toast1=Toast.makeText(this,"hii jeet2",Toast.LENGTH_SHORT);
                        }*/


                    musicPathArrList.add(new CommonModel(titel, artist, album, duretion, data, id, name, count));

                } while (cursorAudio.moveToNext());
                //  }


            } catch (Exception e) {
                Log.e("rtuio", e.getMessage());
                Toast toast1 = Toast.makeText(this, "hii jeet2", Toast.LENGTH_SHORT);
                toast1.show();

            }


        }
        cursorAudio.close();
        return musicPathArrList;
    }

    @SuppressLint("ResourceAsColor")
    public void audioImage(String data) {
        MediaMetadataRetriever mediaMetadata = new MediaMetadataRetriever();
        byte[] art;
        ImageView audioView1 = null;
        Bitmap songImage = null;


        mediaMetadata.setDataSource(data);
        try {
            art = mediaMetadata.getEmbeddedPicture();
            songImage = BitmapFactory.decodeByteArray(art, 0, art.length);


        } catch (Exception e) {
            songImage = BitmapFactory.decodeResource(getResources(), R.drawable.pause);
        }
    }

    public class FlotingActionButton {

    }

}