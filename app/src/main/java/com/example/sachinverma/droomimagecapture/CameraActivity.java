package com.example.sachinverma.droomimagecapture;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import mehdi.sakout.fancybuttons.FancyButton;

public class CameraActivity extends Activity {
    private static final int CAMERA_REQUEST = 1888;
    private ImageView imageView;
    private ImageView ivClickPhoto;
    private ImageView ivForward, ivBack;
    private int GLOBAL_COUNTER=1;
    //GLOBAL COUNTER is for allowing device to click only 4 photos
    String bitmapPath1, bitmapPath2, bitmapPath3, bitmapPath4;
    private int IMAGE_DISPLAY_COUNTER=1;
    String bitmapPathArray[]=new String[4];


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_cameraactivity);

        GLOBAL_COUNTER=1;
        IMAGE_DISPLAY_COUNTER=1;

        this.imageView = (ImageView)this.findViewById(R.id.imageViewMain);
        ivClickPhoto=(ImageView)findViewById(R.id.cameraactivity_ivClickPhoto);
        ivForward=(ImageView)findViewById(R.id.cameraactivity_ivForward);
        ivBack=(ImageView)findViewById(R.id.cameraactivity_ivBack);

        //ivBack.setEnabled(false);
        //ivForward.setEnabled(false);

        FancyButton bAllImages = (FancyButton) this.findViewById(R.id.cameraactivity_bAllImages);
        ivClickPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(GLOBAL_COUNTER<=4) {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }
                else{
                    Toast.makeText(getApplicationContext(), "4 Pictures already clicket", Toast.LENGTH_SHORT).show();
                }

                if(GLOBAL_COUNTER==4)
                {
                    Log.i("checking", "Count is 4 now");
                    bitmapPathArray= new String[]{bitmapPath1, bitmapPath2, bitmapPath3, bitmapPath4};
                   // ivBack.setEnabled(true);
                    //ivForward.setEnabled(true);
                }

            }
        });

        ivForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //forward arrow pressed
                Log.i("checking", "Forward button pressed");
                IMAGE_DISPLAY_COUNTER=(IMAGE_DISPLAY_COUNTER+1)%4;
                Log.i("counter", ""+IMAGE_DISPLAY_COUNTER);
                loadImageFromStorage(bitmapPathArray[IMAGE_DISPLAY_COUNTER]);
                Log.i("checking", "Image to be loaded from :"+bitmapPathArray[IMAGE_DISPLAY_COUNTER]);
            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //backward arrow pressed
                Log.i("checking", "Back button pressed");
                IMAGE_DISPLAY_COUNTER=(4+IMAGE_DISPLAY_COUNTER-1)%4;
                Log.i("counter", ""+IMAGE_DISPLAY_COUNTER);
                loadImageFromStorage(bitmapPathArray[IMAGE_DISPLAY_COUNTER]);
                Log.i("checking", "Image to be loaded from :"+bitmapPathArray[IMAGE_DISPLAY_COUNTER]);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {

            Bitmap photo = (Bitmap) data.getExtras().get("data");


            switch (GLOBAL_COUNTER)
            {
                case 1: bitmapPath1=saveToInternalStorage(photo);
                    Log.i("checking", "Bitmap 1:"+bitmapPath1);
                        break;
                case 2: bitmapPath2=saveToInternalStorage(photo);
                    Log.i("checking", "Bitmap 2:"+bitmapPath2);
                        break;
                case 3: bitmapPath3=saveToInternalStorage(photo);
                    Log.i("checking", "Bitmap 3:"+bitmapPath3);
                        break;
                case 4: bitmapPath4=saveToInternalStorage(photo);
                    Log.i("checking", "Bitmap 4:"+bitmapPath4);
                        break;
            }

            imageView.setImageBitmap(photo);
            GLOBAL_COUNTER++;
        }
    }

    private String saveToInternalStorage(Bitmap bitmapImage){
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory,"picture"+GLOBAL_COUNTER+".jpg");

        Log.i("checking", "Final file directory :"+directory+"picture"+GLOBAL_COUNTER+".jpg");
        Log.i("imagename", "Saving :"+"picture"+GLOBAL_COUNTER+".jpg");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
            Log.i("checking", "Image saved");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Log.i("checking", "Location saved is :"+directory.getAbsolutePath());
        return directory.getAbsolutePath();
    }


    private void loadImageFromStorage(String path)
    {

        try {
            Log.i("checking", "Path for loading is :"+path);
            File f=new File(path, "picture"+(IMAGE_DISPLAY_COUNTER+1)+".jpg");
            Log.i("imagename", "Fetching :"+"picture"+(IMAGE_DISPLAY_COUNTER+1)+".jpg");
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            imageView.setImageBitmap(b);
            Log.i("checking", "Image Loaded");
            Log.i("checking", "Image is loaded from :"+path+"picture"+(IMAGE_DISPLAY_COUNTER+1)+".jpg");
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

    }

}