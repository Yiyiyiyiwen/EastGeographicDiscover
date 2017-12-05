package com.example.even1.meat.details;

import android.content.Intent;
import android.icu.text.IDNA;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.even1.meat.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Friendlist extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friendlist);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ListView listView = (ListView)findViewById(R.id.listview);
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,getData(),R.layout.comeacrossitem,
                new String[]{"head","name","qianming"},
                new int[]{R.id.head,R.id.name,R.id.qianming});
        listView.setAdapter(simpleAdapter);
        listView.setOnItemClickListener(this);
    }

    private List<Map<String,Object>> getData(){
        List<Map<String,Object>> list = new ArrayList<>();
        for(int i=0;i<20;i++){
            Map<String,Object> map = new HashMap<>();
            map.put("head",R.mipmap.head2);
            map.put("name","用户"+i);
            map.put("qianming","描述"+i);
            list.add(map);
        }
        return list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search,menu);
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent(this, InformationDetail.class));
    }
}
