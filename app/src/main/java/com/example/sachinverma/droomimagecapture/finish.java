package com.example.sachinverma.droomimagecapture;


import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * Created by Sachin Verma on 6/17/2016.
 */
public class finish extends Activity {

    ImageView im1,im2,im3,im4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finish);

        im1 = (ImageView) findViewById(R.id.image_view);
        im2 = (ImageView) findViewById(R.id.imageView);
        im3 = (ImageView) findViewById(R.id.imageView2);
        im4 = (ImageView) findViewById(R.id.imageView3);



    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String path="sdcard/camera_app/cam_image.jpg";
        String path1="sdcard/camera_app/cam_image_back.jpg";
        String path2="sdcard/camera_app/cam_image_side1.jpg";
        String path3="sdcard/camera_app/cam_image_side2.jpg";
        im1.setImageDrawable(Drawable.createFromPath(path));
        im2.setImageDrawable(Drawable.createFromPath(path1));
        im3.setImageDrawable(Drawable.createFromPath(path2));
        im4.setImageDrawable(Drawable.createFromPath(path3));

    }
}
