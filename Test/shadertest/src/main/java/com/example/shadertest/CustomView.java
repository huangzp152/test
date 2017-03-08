package com.example.shadertest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/1/18.
 */
public class CustomView extends View {

    private Paint mPaint;
    private Shader mShader;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(400,400);
    }

    public CustomView(Context context) {
        super(context);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int w = getWidth();
        int h = getHeight();
        int radius = w < h ?w/2:h/2;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher_gitv_litchi);
        Bitmap result = Bitmap.createScaledBitmap(bitmap,w,h,false);
        mShader = new BitmapShader(result, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP);
        mPaint.setTextSize(200.0f);
        mPaint.setTypeface(Typeface.MONOSPACE);
        mPaint.setShader(mShader);
        canvas.drawText("iqiyi",0,h/2,mPaint);

    }
}
