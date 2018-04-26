package com.ehighsun.camerademo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class ShadowView2 extends View {

    private Rect mShowDrawRect;
    private int right_width=0;
    private int right_height=0;
    private float RIGHT_RATIO = 0.2f;
    private float CONTENT_RATIO = 1.6f;
    private int content_width=0;
    private int content_height=0;
    private int centerX;
    private int centerY;




    public ShadowView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mShowDrawRect = new Rect();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        right_width = (int)(width*RIGHT_RATIO);
        content_width = (int)(width*(1f-2*RIGHT_RATIO));
        content_height = (int)(content_width*CONTENT_RATIO);
        right_height = (height-content_height)/2;

        centerX = width/2;
        centerY = height/2;

        mShowDrawRect.left = right_width;
        mShowDrawRect.top = right_height;
        mShowDrawRect.right = right_width+content_width;
        mShowDrawRect.bottom = right_height+content_height;
    }

    private int mIdCardSide=0;//0正面，1反面
    public void setCardSideAndOrientation(boolean mIsVertical, int mIDCardSide) {
        this.mIdCardSide = mIDCardSide;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int bitmapId = 0;
        if (mIdCardSide == 0)
            bitmapId = R.drawable.idcard_front;
        else if (mIdCardSide == 1)
            bitmapId = R.drawable.idcard_back;
        Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(), bitmapId);
        Rect mSrcRect = new Rect(0, 0,bitmap.getWidth(),bitmap.getHeight());
        Rect mDesRect = new Rect(mShowDrawRect.left, mShowDrawRect.top, mShowDrawRect.right, mShowDrawRect.bottom);
        canvas.drawBitmap(bitmap, mSrcRect, mDesRect, null);
    }
}
