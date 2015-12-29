package com.tong.com.ndkdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends Activity {
    static {
        System.loadLibrary("JniTest");
    }
    GifDrawable drawable = null;
    public native String getStringFromNative();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView txtView = (TextView) findViewById(R.id.txt);
        txtView.setText(getStringFromNative());

        GifImageView gif = (GifImageView)findViewById(R.id.gif_view);
        try{
            drawable = new GifDrawable("/sdcard/test.gif");
            
        }catch (Exception e){
            e.printStackTrace();
        }
        gif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(drawable.isPlaying()){
                    drawable.stop();
                }else{
                    drawable.start();
                }
            }
        });
        gif.setImageDrawable(drawable);

//        GifImageButton gib = new GifImageButton( this );
//        setContentView( gib );
//        gib.setImageResource( R.drawable.test );
//        final MediaController mc = new MediaController( this );
//        mc.setMediaPlayer((GifDrawable) gib.getDrawable());
//        mc.setAnchorView(gib);
//        gib.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mc.show();
//            }
//        });
    }
}
