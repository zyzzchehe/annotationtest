package com.example.annotationtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.annotationlib.AnnoProcessor;
import com.example.annotationlib.MyAnno;
import com.example.annotationlib.OnClick;
import com.example.annotationlib.OnLongClick;

public class TestAnnoActivity extends AppCompatActivity{

    @MyAnno(R.id.tv1)
    private TextView tv1;
    @MyAnno(R.id.tv2)
    private TextView tv2;
    @MyAnno(R.id.tv3)
    private TextView tv3;
    @MyAnno(R.id.btn)
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_anno);

        AnnoProcessor.bind(this);

        tv1.setText("aaa");
        tv2.setText("bbb");
        tv3.setText("ccc");
        btn.setText("ppp");
    }

    @OnClick(R.id.btn)
    public void testBtnClick(View v){
        Toast.makeText(this,"hello anno, this is a click",Toast.LENGTH_SHORT).show();
    }

    @OnLongClick(R.id.btn)
    public void testBtnLongClick(View v){
        Toast.makeText(this,"hello anno, this is a long click",Toast.LENGTH_SHORT).show();
    }
}
