package com.example.even1.meat.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import com.example.even1.meat.R;
import com.example.even1.meat.details.Friendlist;
import com.github.mikephil.charting.charts.BubbleChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BubbleData;
import com.github.mikephil.charting.data.BubbleDataSet;
import com.github.mikephil.charting.data.BubbleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBubbleDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import static com.example.even1.meat.R.drawable.buttonradius;

public class Home extends Fragment implements NavigationView.OnNavigationItemSelectedListener,OnChartValueSelectedListener{

    private BubbleChart mBubbleChart;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_home,container,false);

        Toolbar toolbar = (Toolbar)view.findViewById(R.id.toolbar);
        toolbar.setTitle("");
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        DrawerLayout drawerLayout = (DrawerLayout)view.findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(),drawerLayout,toolbar,R.string.app_name,R.string.app_name);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView)view.findViewById(R.id.nv_menu_left);
        navigationView.setNavigationItemSelectedListener(this);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.homemenu,menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.setting){
            return true;
        }
        return  super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.friend:
                startActivity(new Intent(getActivity(), Friendlist.class));
                break;
            default:
                break;
        }
        return false;
    }

    private void initView() {
        mBubbleChart = (BubbleChart)getActivity().findViewById(R.id.mBubbleChart);
        mBubbleChart.getDescription().setEnabled(false);
        mBubbleChart.setOnChartValueSelectedListener(this);
        mBubbleChart.setDrawGridBackground(false);
        mBubbleChart.setTouchEnabled(true);
        mBubbleChart.setDragEnabled(true);
        mBubbleChart.setScaleEnabled(true);
        mBubbleChart.setMaxVisibleValueCount(200);
        mBubbleChart.setPinchZoom(true);


        Legend l = mBubbleChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);

        YAxis yl = mBubbleChart.getAxisLeft();
        yl.setSpaceTop(30f);
        yl.setSpaceBottom(30f);
        yl.setDrawZeroLine(false);
        yl.setAxisMinimum(0);

        mBubbleChart.getAxisRight().setEnabled(false);

        XAxis xl = mBubbleChart.getXAxis();
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);

        setData();
    }

    private void setData() {

        ArrayList<BubbleEntry> yVals2 = new ArrayList<>();
        ArrayList<BubbleEntry> yVals3 = new ArrayList<>();


        for (int i = 1; i < 8; i++) {
            float val = (float) (Math.random() * 10);
            float size = (float) (Math.random() * 10);

            yVals2.add(new BubbleEntry(i, val, size));
        }

        BubbleDataSet set2 = new BubbleDataSet(yVals2, "目标");
        set2.setColor(ColorTemplate.COLORFUL_COLORS[1]);
        set2.setDrawValues(true);

        for (int i = 1; i < 8; i++) {
            float val = (float) (Math.random() * 10);
            float size = (float) (Math.random() * 10);

            yVals3.add(new BubbleEntry(i, val, size));
        }


        BubbleDataSet set3 = new BubbleDataSet(yVals3, "完成情况");
        set3.setColor(ColorTemplate.COLORFUL_COLORS[2]);
        set3.setDrawValues(true);

        ArrayList<IBubbleDataSet> dataSets = new ArrayList<>();
        dataSets.add(set2);
        dataSets.add(set3);

        BubbleData data = new BubbleData(dataSets);
        data.setDrawValues(false);
        data.setValueTextSize(8f);
        data.setValueTextColor(Color.WHITE);
        data.setHighlightCircleWidth(1.5f);

        mBubbleChart.setData(data);
        mBubbleChart.invalidate();

        //默认动画
        mBubbleChart.animateXY(3000, 3000);
    }


    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}
