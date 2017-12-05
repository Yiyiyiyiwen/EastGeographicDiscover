package com.example.even1.meat.Columns;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;

import com.example.even1.meat.R;
import com.example.even1.meat.details.FriendDetail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Trends extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView listView;
    private int[] head = {R.mipmap.head2,R.mipmap.head3,R.mipmap.head4,R.mipmap.head5};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trends);

        listView = (ListView)findViewById(R.id.listView);
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,getData(),R.layout.discoveritem,
                new String[]{"head","name","discribe","pic","detail"},
                new int[]{R.id.head,R.id.name,R.id.discribe,R.id.pic,R.id.detail});
        listView.setAdapter(simpleAdapter);
        listView.setOnItemClickListener(this);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
        startActivity(new Intent(Trends.this,FriendDetail.class));
    }
}
