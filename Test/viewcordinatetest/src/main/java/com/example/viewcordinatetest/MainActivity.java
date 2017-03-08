package com.example.viewcordinatetest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;

public class MainActivity extends AppCompatActivity {

    View mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mView = getWindow().getDecorView();
        mView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                
            }
        });
        int[] location= new int[2];
        location[0]= 0;
        location[1]= 0;
        setValue(location);
        Log.d("hzp","x= "+location[0]+",y = "+location[1]);
    }

    public void setValue(int[] location){
        location[0] = 1;
        location[1] = 1;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        mView = getWindow().getDecorView();
        super.onWindowFocusChanged(hasFocus);
        int[] location = new int[2];
        mView.getLocationOnScreen(location);
        Log.d("hzp onfocuschanged","x= "+location[0]+",y = "+location[1]);
    }

}
