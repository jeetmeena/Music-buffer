package com.example.geetmeena.music;

/**
 * Created by Jeetmeena on 1/19/2018.
 */
import android.annotation.SuppressLint;
import android.content.Context;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.MediaMetadata;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.net.sip.SipSession;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.scalified.viewmover.movers.ViewMover;
import com.scalified.viewmover.movers.ViewMoverFactory;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Locale;


import static android.text.TextUtils.substring;
import static com.scalified.uitools.convert.DensityConverter.dpToPx;

public class SolventRecyclerViewAdapter  extends RecyclerView.Adapter<SolventRecyclerViewAdapter.SolventViewHolders> implements SolventRecyclerViewAdaptera {
public static MediaPlayer mediaPlayer;
    private int resumePosition;
    private ArrayList<CommonModel> itemList;
    private Context context;
    private  FloatingActionButton fab2;
    public static   int count=0;
    public static String perpath=null;
public Uri newUei;
    public SolventRecyclerViewAdapter(MainActivity context, ArrayList<CommonModel> gaggeredList) {
        this.itemList = gaggeredList;
        this.context = context;
        //this.flotyingbutton=button1;

    }

     int a=2;

    public SolventRecyclerViewAdapter(View v) {
        onClick( v);
    }

    @SuppressLint("ResourceType")
    @Override
    public SolventViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.contenaa_main,null);
         try {
           //  fab2  =   layoutView.findViewById(R.id.fab);
         }catch (Exception e){}

        //fab2.show();
        SolventViewHolders rcv = new SolventViewHolders(layoutView);
        return rcv;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(SolventViewHolders holder, int position) {

        try {

           // newUei=newUrie(itemList.get(position).getU());


           Bitmap bit=audioImage(itemList.get(position).getFilePath1());
            String nam=name(itemList.get(position).getQueryName());

          //To enable or disable Shadow Responsive Effect:

            //.setShadowResponsiveEffectEnabled(false);
          //  int flagResId = Context.getResources().getIdentifier(itemList, "drawable", Context.getPackageName());;


            //holder.jee1.setText(itemList.get(position).getFilepath1());
         //holder.countryPhoto.setImageResource(Integer.parseInt(itemList.get(position).getDuretion()));
            if(bit==null) {
                holder.countryPhoto.setImageBitmap(BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.avocado_music));
                holder.countryName.setText(nam);
            }else {
                holder.countryName.setText(nam);
                holder.countryPhoto.setImageBitmap(bit);



                //holder.countryPhoto.setImageURI(Uri.parse((itemList.get(position).getFilepath1())));
               }
        }catch (Exception e){
            Log.i("jee",e.getMessage());
        }
    }


    public Uri newUrie(Uri uri){

        return uri;
    }

    @Override
    public int getItemCount() {

        //returns the number of elements the RecyclerView will display
        return this.itemList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    // Insert a new item to the RecyclerView on a predefined position
    public void insert(int position, CommonModel data) {
        itemList.add(position, data);

        notifyItemInserted(position);
    }

    // Remove a RecyclerView item containing a specified Data object
    public void remove(CommonModel data) {
        int position = itemList.indexOf(data);
        itemList.remove(position);
        notifyItemRemoved(position);
    }




    public String name(String name){
        String jeet = null;

        char[] namea=name.toCharArray();

        if(namea.length>=23){
            name=name.substring(0,22);
             jeet=new String(name)+"..";
        }
          else {
            jeet = new String(name);
         }
        return jeet;
    }



    @SuppressLint("ResourceAsColor")
    public  Bitmap audioImage(String data){
        MediaMetadataRetriever mediaMetadata = new MediaMetadataRetriever();
        byte[] art;
        ImageView audioView1 = null;
        Bitmap songImage = null;



        try {
            mediaMetadata.setDataSource(data);
            art=mediaMetadata.getEmbeddedPicture();
            songImage = BitmapFactory.decodeByteArray(art, 0, art.length);


        }catch (Exception e){

            //audioView1.setBackgroundColor(R.color.fab_material_amber_500);
            songImage= BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.nightclub_w);
        }
        return  songImage;
    }




    @SuppressLint("ResourceAsColor")
    public MediaPlayer palay(int index){

         final
        String media_path = String.valueOf(itemList.get(index).getFilePath1());

         mediaPlayer = new MediaPlayer();

        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);


        // Toast.makeText(view.getContext(),jee2 + "  hii", Toast.LENGTH_SHORT).show();
        try {




              if(mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                mediaPlayer.release();

                  mediaPlayer.setDataSource(media_path);
                  mediaPlayer.prepare();
                mediaPlayer.start();




            }
      else if(!mediaPlayer.isPlaying() || mediaPlayer==null) {
                  mediaPlayer.stop();
                  mediaPlayer.setDataSource(media_path);


           perpath=media_path;
           mediaPlayer.prepare();
           mediaPlayer.start();
           mediaPlayer.isPlaying();
           mediaPlayer.isLooping();


         }

        } catch (IOException e) {
            //  mediaPlayer[0].reset();
            // mediaPlayer[0].release();
            //  mediaPlayer[0] = null;



           }catch (Exception e){
          }
         return  mediaPlayer;
      }


       public void isStop(){

        mediaPlayer.stop();
        count--;

          }
        public void  isPlay() throws IOException {

            mediaPlayer.prepare();
            mediaPlayer.start();
            mediaPlayer.isPlaying();
            mediaPlayer.isLooping();
        }




   @Override
   public void onClick(View view){
    if(mediaPlayer==null) {
    Toast.makeText(view.getContext(), "  Hii Ashok  = ", Toast.LENGTH_SHORT).show();
     }
    else {
           if(mediaPlayer.isPlaying()){
              pauseMedia();
           }
           else if(!mediaPlayer.isPlaying()){
              resumeMedia();
           }

       }
    }


    private void playMedia() {
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
    }

    private void stopMedia() {
        if (mediaPlayer == null) return;
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
    }

    private void pauseMedia() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            resumePosition = mediaPlayer.getCurrentPosition();
        }
    }

    private void resumeMedia() {
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.seekTo(resumePosition);
            mediaPlayer.start();
        }
    }

    public class SolventViewHolders extends RecyclerView.ViewHolder implements SolventViewHolders1 {
        SeekBar seekBar;
        public TextView countryName;
        public ImageView countryPhoto;
        public FloatingActionButton fab;
        private String[] filePathStrings;
        String jee2;
        TextView jee1;
        Button button;
        Annotation  anim;




        public SolventViewHolders(View itemView) {

            super(itemView);

            itemView.setOnClickListener(this);

            countryName = (TextView) itemView.findViewById(R.id.country_name);
            countryPhoto = (ImageView) itemView.findViewById(R.id.country_photo);
           // FloatingActionButton fab = new FloatingActionButton.DragShadowBuilder().bulid();
         fab = (FloatingActionButton) itemView.findViewById(R.id.fab);
           //fab.show();
         //  Button ba=itemView.findViewById(R.id.button);

        //   fab.setShadowResponsiveEffectEnabled(true);
           // fab.setOnClickListener(this);
           // fab.attach();

            // button.setEnabled(false);
        }

        @Override
        public void onClick2(View view) {
           // jee2 = jee1.getText().toString();


           try {

           if (R.id.fab ==view.getId()) {
                   Toast.makeText(view.getContext(), "Clicked Position = " , Toast.LENGTH_SHORT).show();

               // media.stop();
             }
            } catch (Exception e) {

            }
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @SuppressLint("ResourceAsColor")
        @Override
        public void onClick(View v) {



            int ind = getPosition();
            palay(ind);
        }
    }





}

/*public class Recycler_View_Adapter extends RecyclerView.Adapter<View_Holder> {

    List<Data> list = Collections.emptyList();
    Context context;

    public Recycler_View_Adapter(List<Data> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public View_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflate the layout, initialize the View Holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        View_Holder holder = new View_Holder(v);
        return holder;

    }

    @Override
    public void onBindViewHolder(View_Holder holder, int position) {

        //Use the provided View Holder on the onCreateViewHolder method to populate the current row on the RecyclerView
        holder.title.setText(list.get(position).title);
        holder.description.setText(list.get(position).description);
        holder.imageView.setImageResource(list.get(position).imageId);

        //animate(holder);

    }

    @Override
    public int getItemCount() {
        //returns the number of elements the RecyclerView will display
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    // Insert a new item to the RecyclerView on a predefined position
    public void insert(int position, Data data) {
        list.add(position, data);
        notifyItemInserted(position);
    }

    // Remove a RecyclerView item containing a specified Data object
    public void remove(Data data) {
        int position = list.indexOf(data);
        list.remove(position);
        notifyItemRemoved(position);
    }

}*/
