package com.ehighsun.camerademo;

import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.View;

import java.io.IOException;

public class CameraActivity extends AppCompatActivity implements SurfaceHolder.Callback {
    private Camera mCamera;
    private CameraFace mCameraFace;
    private SurfaceHolder mHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        mCameraFace = findViewById(R.id.tf_camerFace);
        mHolder = mCameraFace.getHolder();
        mHolder.addCallback(this);
    }

    public void TakePhoto(View view){

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mCamera==null){
            mCamera = getCamera();
            if(mHolder!=null){
                setStartPreview(mCamera,mHolder);
            }
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 获取相机对象
     * @return
     */
    private Camera getCamera(){
        Camera camera = null;
        try {
            camera = Camera.open();
        } catch (Exception e) {
            camera = null;
            e.printStackTrace();
        }
        return camera;
    }

    /**
     * 获取相机预览
     */
    private void setStartPreview(Camera camera,SurfaceHolder holder){
        try {
            camera.setPreviewDisplay(holder);
            camera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 释放相机资源
     */
    private void releaseCamera(){
        if(mCamera!=null){
            mCamera.setPreviewCallback(null);
            mCamera.stopPreview();
            mCamera.release();
            mCamera=null;
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        setStartPreview(mCamera,mHolder);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        mCamera.stopPreview();
        setStartPreview(mCamera,mHolder);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        releaseCamera();
    }
}
