package com.example.even1.meat.Run;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.even1.meat.Adapter.DbAdapter;
import com.example.even1.meat.Adapter.RecordAdapter;
import com.example.even1.meat.R;
import com.example.even1.meat.record.PathRecord;

import java.util.ArrayList;
import java.util.List;

public class Runlist extends AppCompatActivity  implements AdapterView.OnItemClickListener {
    private RecordAdapter mAdapter;
    private ListView mAllRecordListView;
    private DbAdapter mDataBaseHelper;
    private List<PathRecord> mAllRecord = new ArrayList<PathRecord>();
    public static final String RECORD_ID = "record_id";
    private PathRecord record;

    public String time,distance,speed;
    private TextView settime,setdistance,setspped;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runlist);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setTitle("历史记录");
        mAllRecordListView = (ListView) findViewById(R.id.recordlist);
        mDataBaseHelper = new DbAdapter(this);
        mDataBaseHelper.open();
        searchAllRecordFromDB();
        mAdapter = new RecordAdapter(this, mAllRecord);
        mAllRecordListView.setAdapter(mAdapter);
        mAllRecordListView.setOnItemClickListener(this);

        Intent intent = getIntent();
        time = intent.getStringExtra("time");
        distance = intent.getStringExtra("distance");
        speed = intent.getStringExtra("speed");

        /*settime = (TextView)findViewById(R.id.time);
        settime.setText(time);
        setdistance = (TextView)findViewById(R.id.distance);
        setdistance.setText(distance);
        setspped = (TextView)findViewById(R.id.speed);
        setspped.setText(speed);*/
    }

    private void searchAllRecordFromDB() {
        mAllRecord = mDataBaseHelper.queryRecordAll();
    }

    public void onBackClick(View view) {
        this.finish();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        PathRecord recorditem = (PathRecord) parent.getAdapter().getItem(
                position);
        Intent intent2 = new Intent(Runlist.this,
                RunResult.class);
        intent2.putExtra("time",time);
        intent2.putExtra("distance",distance);
        intent2.putExtra("speed",speed);
        intent2.putExtra(RECORD_ID, recorditem.getId());
        startActivity(intent2);
    }
}
