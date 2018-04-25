package com.ehighsun.camerademo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView mImage;
    private static final int TAKE_PHOTO_REQUEST = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImage = findViewById(R.id.iv_iamge);
    }


    public void OpenCamera(View view){
        Intent intent = new Intent(this,CameraActivity.class);
        startActivityForResult(intent,TAKE_PHOTO_REQUEST);
    }
}
