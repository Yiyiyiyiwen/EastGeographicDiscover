package com.example.even1.meat;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.even1.meat.fragment.Column;
import com.example.even1.meat.fragment.Discover;
import com.example.even1.meat.fragment.Home;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener{

    private BottomNavigationBar bnb;
    private Discover f1;
    private Column f2;
    private Home f3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bnb = (BottomNavigationBar)findViewById(R.id.navigationbar);
        bnb.setMode(BottomNavigationBar.MODE_SHIFTING);
        bnb.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);
        bnb.setBarBackgroundColor(R.color.colorPrimary);
        bnb.setInActiveColor(R.color.colorPrimaryDark);
        bnb.addItem(new BottomNavigationItem(R.mipmap.find2,"发现").setActiveColorResource(R.color.white))
                .addItem(new BottomNavigationItem(R.mipmap.special2,"专栏").setActiveColorResource(R.color.white))
                .addItem(new BottomNavigationItem(R.mipmap.my2,"我的").setActiveColorResource(R.color.white))
                .setFirstSelectedPosition(0)
                .initialise();
        onTabSelected(0);
        bnb.setTabSelectedListener(this);
    }


    @Override
    public void onTabSelected(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (position){
            case 0:
                f1 = new Discover();
                transaction.replace(R.id.fragment,f1);
                break;
            case 1:
                f2 = new Column();
                transaction.replace(R.id.fragment,f2);
                break;
            case 2:
                f3 = new Home();
                transaction.replace(R.id.fragment,f3);
                break;
            default:
                break;
        }
        transaction.commit();
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
