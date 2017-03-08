package com.example.administrator.test;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spannableDemo();
    }
    public void spannableDemo(){
        SpannableStringBuilder spannableStringBuilder=new SpannableStringBuilder("西门吹雪");
        spannableStringBuilder.append("已经开始升仙了");

        ImageSpan imageSpan=new ImageSpan(this,R.mipmap.ic_launcher);
        spannableStringBuilder.setSpan(imageSpan,4,7, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        ClickableSpan clickableSpan=new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(MainActivity.this, "请不要点我", Toast.LENGTH_SHORT).show();
            }
        };
        spannableStringBuilder.setSpan(clickableSpan,4,7, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        ForegroundColorSpan foregroundColorSpan=new ForegroundColorSpan(Color.parseColor("#FFFFFF"));
        spannableStringBuilder.setSpan(foregroundColorSpan,4,7,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        BackgroundColorSpan bgColorSpan = new BackgroundColorSpan(Color.parseColor("#009ad6"));
        spannableStringBuilder.setSpan(bgColorSpan, 5, 8, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);

        TextView textView=(TextView)findViewById(R.id.spannableString);
        textView.setText(spannableStringBuilder);
        textView.setMovementMethod(LinkMovementMethod.getInstance());

    }
}
