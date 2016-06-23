package com.example.sachinverma.droomimagecapture;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
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
public class back extends Activity {

    Button button, next, back;
    ImageView imageView;

    static final int CAM_REQUEST = 0;
    Bitmap bmp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.back);

        next = (Button) findViewById(R.id.button3);
        back = (Button) findViewById(R.id.button2);
        button = (Button) findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file = getFile();
                camera_intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(camera_intent, CAM_REQUEST);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent openbackview = new Intent("com.example.sachinverma.droomimagecapture.SIDE1");
                startActivity(openbackview);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent openbackview = new Intent("android.intent.action.MAIN");
                startActivity(openbackview);

            }
        });


    }

    private File getFile() {
        File folder = new File("sdcard/camera_app");
        if (!folder.exists()) {
            folder.mkdir();
        }
        File image_file = new File(folder, "cam_image_back.jpg");
        return image_file;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //String path = "sdcard/camera_app/cam_image_back.jpg";
        //imageView.setImageDrawable(Drawable.createFromPath(path));
        if(resultCode==RESULT_OK){

                Bundle extras = data.getExtras();
                bmp = (Bitmap) extras.get("data");
                imageView.setImageBitmap(bmp);
        }





    }
}
