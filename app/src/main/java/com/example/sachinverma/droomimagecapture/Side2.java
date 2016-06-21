package com.example.sachinverma.droomimagecapture;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by Sachin Verma on 6/17/2016.
 */
public class Side2 extends Activity {

    Button button;
    ImageView imageView;
    Button back,finish;
    static final int CAM_REQUEST=0;
    Bitmap bmp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.side2);
        back =(Button) findViewById(R.id.button2);
        finish =(Button) findViewById(R.id.button3);
        button = (Button) findViewById(R.id.button);
        imageView = (ImageView) findViewById(R.id.image_view);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file = getFile();
                camera_intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(camera_intent, CAM_REQUEST);
            }
        });



        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent openbackview=new Intent("com.example.sachinverma.droomimagecapture.FINISH");
                startActivity(openbackview);

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent openbackview=new Intent("com.example.sachinverma.droomimagecapture.SIDE1");
                startActivity(openbackview);

            }
        });
    }
    private File getFile()
    {
        File folder= new File("sdcard/camera_app");
        if(!folder.exists())
        {
            folder.mkdir();
        }
        File image_file=new File(folder,"cam_image_side2.jpg");
        return image_file;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
     //   String path="sdcard/camera_app/cam_image_side2.jpg";
       // imageView.setImageDrawable(Drawable.createFromPath(path));
        if(resultCode==RESULT_OK){
            Bundle extras=data.getExtras();
            bmp=(Bitmap) extras.get("data");
            imageView.setImageBitmap(bmp);
        }

    }

}
