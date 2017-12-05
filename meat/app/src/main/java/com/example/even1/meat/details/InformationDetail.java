package com.example.even1.meat.details;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.even1.meat.Adapter.MyVpAdater;
import com.example.even1.meat.R;
import com.example.even1.meat.Transform.AlphaTransformer;
import com.example.even1.meat.Transform.ScaleTransformer;

import java.util.ArrayList;
import java.util.List;

public class InformationDetail extends AppCompatActivity {

    private ViewPager viewPager;

    int pic[]={R.mipmap.back1,R.mipmap.back2,R.mipmap.back3,R.mipmap.cardbg};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_detail);
        viewPager = (ViewPager)findViewById(R.id.viewpager);
        viewPager.setPageMargin(60);
        viewPager.setOffscreenPageLimit(3);
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<pic.length;i++){
            list.add(pic[i]);
        }
        viewPager.setAdapter(new MyVpAdater(this,list));
        viewPager.setPageTransformer(false,new AlphaTransformer());
        viewPager.setPageTransformer(false,new ScaleTransformer());
    }
}
