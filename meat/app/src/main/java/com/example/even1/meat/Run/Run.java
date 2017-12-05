package com.example.even1.meat.Run;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Message;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.view.View.OnClickListener;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.PolylineOptions;

import com.amap.api.maps2d.UiSettings;
import com.amap.api.maps2d.model.MarkerOptions;
import com.example.even1.meat.Adapter.DbAdapter;
import com.example.even1.meat.R;
import com.example.even1.meat.record.PathRecord;
import com.example.even1.meat.recordutil.Util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Run extends AppCompatActivity implements AMapLocationListener, View.OnClickListener, LocationSource {

    private MapView mMapView;
    private AMap mAMap;

    //定位需要的声明
    private AMapLocationClient mLocationClient = null;//定位发起端
    private AMapLocationClientOption mLocationOption = null;//定位参数
    private LocationSource.OnLocationChangedListener mListener = null;//定位监听器

    //标识，用于判断是否只显示一次定位信息和用户重新定位

    private boolean isFirstLoc=true;
    private String status = "开始跑步！";
    private TextView textView3,textView2;
    private Chronometer chronometer;

    int current = 0;

    //获取位置
    private LatLng start,end;
    private int i=1;
    private float mdistance,distance=0;
    private CharSequence dis="0";

    //获取实时时间
    private String runtime;
    private int xiaoshi;

    //获取速度
    private float speed;
    private String mspeed;

    private PolylineOptions mPolyoptions;
    private PathRecord record;
    private ToggleButton btn;
    private long mStartTime;
    private long mEndTime;
    private DbAdapter DbHepler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run);
        mMapView = (MapView) findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);// 此方法必须重写
        init();
        initpolyline();

        btn = (ToggleButton) findViewById(R.id.run);
        btn.setOnClickListener(this);
        //imageView = (ImageView)findViewById(R.id.run);
        chronometer = (Chronometer)findViewById(R.id.time);
        chronometer.setText(FormatMiss(current));
        xiaoshi = (int) ((SystemClock.elapsedRealtime() - chronometer.getBase()) / 1000 / 60);
        chronometer.setFormat("0"+String.valueOf(xiaoshi)+":%s");
        //imageView.setOnClickListener(this);

    }
    /**
     * 初始化AMap对象
     */
    private void init() {
        if (mAMap == null) {
            mAMap = mMapView.getMap();
            setUpMap();
        }

        /*btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btn.isChecked()) {
                    Log.i("MY", "isChecked");

                    mAMap.clear(true);
                    if (record != null) {
                        record = null;
                    }
                    record = new PathRecord();
                    mStartTime = System.currentTimeMillis();
                    record.setDate(getcueDate(mStartTime));
                } else {
                    mEndTime = System.currentTimeMillis();
                    saveRecord(record.getPathline(), record.getDate());
                    startActivity(new Intent(Run.this,Runlist.class));
                }
            }
        });*/

    }
    private void figureout() {
        textView2 = (TextView)findViewById(R.id.route);
        textView3= (TextView)findViewById(R.id.speed);
        android.os.Handler handler = new android.os.Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what==0){
                    //distance = AMapUtils.calculateLineDistance(start,end)/1000;
                    //distance=mdistance+distance;
                    DecimalFormat df = new DecimalFormat("0.00");
                    //dis = df.format(distance);
                    //dis = pathRecord.getDistance();
                    //textView2.setText(dis+"m");

                    runtime=chronometer.getText().toString();
                    String[] split = runtime.split(":");
                    int hour = Integer.parseInt(split[0]);
                    int hours=hour*60;
                    int min=Integer.parseInt(split[1]);
                    int second = Integer.parseInt(split[2]);
                    float seconds = (float)second/60;
                    float total = hours+min+seconds;

                    distance = getDistance(record.getPathline());
                    dis = String.valueOf(distance);
                    textView2.setText(dis+"km");

                    speed = total/distance;
                    mspeed=df.format(speed);
                    textView3.setText(mspeed);
                    sendEmptyMessageDelayed(0,1000);
                }
            }
        };
        handler.sendEmptyMessageAtTime(0,1000);
    }

    private static String FormatMiss(int time) {
            String hh=time/3600>9?time/3600+"":"0"+time/3600;
            String mm=(time% 3600)/60>9?(time% 3600)/60+"":"0"+(time% 3600)/60;
            String ss=(time% 3600) % 60>9?(time% 3600) % 60+"":"0"+(time% 3600) % 60;
            return hh+":"+mm+":"+ss;

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.run:
                Toast.makeText(this,status, Toast.LENGTH_SHORT).show();
                if(status=="开始跑步！"){
                    chronometer.start();
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    chronometer.setText(FormatMiss(current));

                    figureout();
                    Log.i("MY", "isChecked");

                    if (record != null) {
                        record = null;
                    }
                    record = new PathRecord();
                    mStartTime = System.currentTimeMillis();
                    record.setDate(getcueDate(mStartTime));
                    status="歇会吗";
                    break;
                }
                if(status=="歇会吗"){showstatusdialog();}
                if(status=="继续跑步！") {
                    Double temp = Double.parseDouble(chronometer.getText().toString().split(":")[2]) * 1000;
                    chronometer.setBase((long) (SystemClock.elapsedRealtime() - temp));
                    chronometer.start();
                    status = "歇会吗";
                    break;
                }
                break;
        }
    }

    private void showstatusdialog() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(Run.this);
        dialog.setIcon(R.mipmap.run1).setTitle("提示").setMessage("确定要结束跑步吗？").setPositiveButton("结束跑步", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                btn.setBackgroundResource(R.mipmap.stop);
                chronometer.stop();
                status="开始跑步！";
                distance=0;
                i=1;
                mEndTime = System.currentTimeMillis();
                saveRecord(record.getPathline(), record.getDate());
                Intent intent2 = new Intent(Run.this,Runlist.class);
                intent2.putExtra("time",chronometer.getText().toString());
                intent2.putExtra("distance",textView2.getText().toString());
                intent2.putExtra("speed",textView3.getText().toString());
                startActivity(intent2);
                chronometer.setBase(SystemClock.elapsedRealtime());

                btn.setBackgroundResource(R.mipmap.runicon);
            }
        }).setNegativeButton("暂停跑步", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                chronometer.stop();
                status="继续跑步！";
                btn.setBackgroundResource(R.mipmap.runicon);
            }
        });
        dialog.show();
    }
    protected void saveRecord(List<AMapLocation> list, String time) {
        if (list != null && list.size() > 0) {
            DbHepler = new DbAdapter(this);
            DbHepler.open();
            String duration = getDuration();
            float distance = getDistance(list);
            String average = getAverage(distance);
            String pathlineSring = getPathLineString(list);
            AMapLocation firstLocaiton = list.get(0);
            AMapLocation lastLocaiton = list.get(list.size() - 1);
            String stratpoint = amapLocationToString(firstLocaiton);
            String endpoint = amapLocationToString(lastLocaiton);
            DbHepler.createrecord(String.valueOf(distance), duration, average,
                    pathlineSring, stratpoint, endpoint, time);
            DbHepler.close();
        } else {
            Toast.makeText(Run.this, "没有记录到路径", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    private String getDuration() {
        return String.valueOf((mEndTime - mStartTime) / 1000f);
    }

    private String getAverage(float distance) {
        return String.valueOf(distance / (float) (mEndTime - mStartTime));
    }

    private float getDistance(List<AMapLocation> list) {
        float distance = 0;
        if (list == null || list.size() == 0) {
            return distance;
        }
        for (int i = 0; i < list.size() - 1; i++) {
            AMapLocation firstpoint = list.get(i);
            AMapLocation secondpoint = list.get(i + 1);
            LatLng firstLatLng = new LatLng(firstpoint.getLatitude(),
                    firstpoint.getLongitude());
            LatLng secondLatLng = new LatLng(secondpoint.getLatitude(),
                    secondpoint.getLongitude());
            double betweenDis = AMapUtils.calculateLineDistance(firstLatLng,
                    secondLatLng);
            distance = (float) (distance + betweenDis);
        }
        return distance;
    }

    private String getPathLineString(List<AMapLocation> list) {
        if (list == null || list.size() == 0) {
            return "";
        }
        StringBuffer pathline = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            AMapLocation location = list.get(i);
            String locString = amapLocationToString(location);
            pathline.append(locString).append(";");
        }
        String pathLineString = pathline.toString();
        pathLineString = pathLineString.substring(0,
                pathLineString.length() - 1);
        return pathLineString;
    }

    private String amapLocationToString(AMapLocation location) {
        StringBuffer locString = new StringBuffer();
        locString.append(location.getLatitude()).append(",");
        locString.append(location.getLongitude()).append(",");
        locString.append(location.getProvider()).append(",");
        locString.append(location.getTime()).append(",");
        locString.append(location.getSpeed()).append(",");
        locString.append(location.getBearing());
        return locString.toString();
    }

    private void initpolyline() {
        mPolyoptions = new PolylineOptions();
        mPolyoptions.width(10f);
        mPolyoptions.color(Color.BLUE);
    }

    /**
     * 设置一些amap的属性
     */
    private void setUpMap() {
        mAMap.setLocationSource(this);// 设置定位监听
        mAMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
        mAMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        // 设置定位的类型为定位模式 ，可以由定位、跟随或地图根据面向方向旋转几种
        mAMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void activate(OnLocationChangedListener listener) {
        mListener = listener;
        startlocation();
    }

    @Override
    public void deactivate() {
        mListener = null;
        if (mLocationClient != null) {
            mLocationClient.stopLocation();
            mLocationClient.onDestroy();

        }
        mLocationClient = null;
    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (mListener != null && amapLocation != null) {
            if (amapLocation != null && amapLocation.getErrorCode() == 0) {
                mListener.onLocationChanged(amapLocation);// 显示系统小蓝点
                LatLng mylocation = new LatLng(amapLocation.getLatitude(),
                        amapLocation.getLongitude());
                mAMap.moveCamera(CameraUpdateFactory.zoomTo(17));
                mAMap.moveCamera(CameraUpdateFactory.changeLatLng(mylocation));
                if (btn.isChecked()) {
                    record.addpoint(amapLocation);
                    mPolyoptions.add(mylocation);
                    redrawline();
                }
            } else {
                String errText = "定位失败," + amapLocation.getErrorCode() + ": "
                        + amapLocation.getErrorInfo();
                Log.e("AmapErr", errText);
            }
        }
    }

    private void startlocation() {
        if (mLocationClient == null) {
            mLocationClient = new AMapLocationClient(this);
            mLocationOption = new AMapLocationClientOption();
            // 设置定位监听
            mLocationClient.setLocationListener(this);
            // 设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);

            mLocationOption.setInterval(2000);
            // 设置定位参数
            mLocationClient.setLocationOption(mLocationOption);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mLocationClient.startLocation();

        }
    }

    private void redrawline() {
        if (mPolyoptions.getPoints().size() > 0) {
            mAMap.clear(true);
            mAMap.addPolyline(mPolyoptions);
        }
    }

    @SuppressLint("SimpleDateFormat")
    private String getcueDate(long time) {
        SimpleDateFormat formatter = new SimpleDateFormat(
                "yyyy-MM-dd  HH:mm:ss ");
        Date curDate = new Date(time);
        String date = formatter.format(curDate);
        return date;
    }

    public void record(View view) {
        Intent intent = new Intent(Run.this, Runlist.class);
        startActivity(intent);
    }
}
