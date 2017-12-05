package com.example.even1.meat.fragment;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;

import com.example.even1.meat.MainActivity2;
import com.example.even1.meat.R;
import com.example.even1.meat.Run.Run;
import com.example.even1.meat.details.FriendDetail;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.R.id.list;

public class Discover extends Fragment implements AdapterView.OnItemClickListener{

    private ListView listView;
    private RollPagerView mRollViewPager;
    private ScrollView scrollView;

    private int[] head = {R.mipmap.head2,R.mipmap.head3,R.mipmap.head4,R.mipmap.head5};



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_discover,container,false);

        mRollViewPager = (RollPagerView)view.findViewById(R.id.mRollViewPager);
        mRollViewPager.setPlayDelay(2000);
        mRollViewPager.setAnimationDurtion(500);
        mRollViewPager.setAdapter(new TestNormalAdapter());

        listView = (ListView) view.findViewById(R.id.discoverlistview);
        scrollView = (ScrollView) view.findViewById(R.id.scrollview);
        SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(),getData(),R.layout.discoveritem,
                new String[]{"head","name","discribe","pic","detail"},
                new int[]{R.id.head,R.id.name,R.id.discribe,R.id.pic,R.id.detail});
        listView.setAdapter(simpleAdapter);
        listView.setOnItemClickListener(this);
        setListViewHeightBasedOnChildren(listView);
        scrollView.smoothScrollTo(0,0);

        FloatingActionButton floatingActionButton = (FloatingActionButton)view.findViewById(R.id.runicon);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Run.class));
            }
        });
        return view;
    }


    public static void setListViewHeightBasedOnChildren(ListView listView){
        ListAdapter listAdapter = listView.getAdapter();
        if(listAdapter==null){
            return;
        }
        int totalHeight=0;
        for(int i=0;i<listAdapter.getCount();i++){
            View listItem = listAdapter.getView(i,null,listView);
            listItem.measure(0,0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight()*(listAdapter.getCount()-1));
        listView.setLayoutParams(params);

    }

    private List<Map<String,Object>> getData(){
        List<Map<String,Object>> list = new ArrayList<>();
        for(int i=0;i<head.length;i++){
            Map<String,Object> map = new HashMap<>();
            map.put("head",head[i]);
            map.put("name","用户"+i);
            map.put("discribe","描述"+i);
            map.put("pic",R.mipmap.peitu);
            map.put("detail","动态详情"+i);
            list.add(map);
        }
        return list;
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(),FriendDetail.class);
        startActivity(intent);
    }

    private class TestNormalAdapter extends StaticPagerAdapter {

        private int[] imgs = {
                R.mipmap.back3,
                R.mipmap.back1,
                R.mipmap.back2,
        };

        @Override
        public View getView(ViewGroup container, int position) {
            ImageView view = new ImageView(container.getContext());
            view.setImageResource(imgs[position]);
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setLayoutParams(new ViewGroup.LayoutParams
                    (ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
            return view;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
