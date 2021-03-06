package com.example.sachinverma.droomimagecapture;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

public class MainActivity extends Activity {

    Button button;
    ImageView imageView;
    static final int CAM_REQUEST=0;
    Button next;
    Bitmap bmp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        next =(Button) findViewById(R.id.button3);
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
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent openbackview=new Intent("com.example.sachinverma.droomimagecapture.BACK");
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
        File image_file=new File(folder,"cam_image.jpg");
        return image_file;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       // String path="sdcard/camera_app/cam_image.jpg";
        if(resultCode==RESULT_OK){
            if(data!=null) {
                Log.i("dataa", "recieved non null intent");
                Bundle extras = data.getExtras();
                bmp = (Bitmap) extras.get("data");
                imageView.setImageBitmap(bmp);
            }
        }
        //imageView.setImageDrawable(Drawable.createFromPath(path));
    }
    }

