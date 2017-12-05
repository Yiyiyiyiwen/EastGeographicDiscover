package com.example.even1.meat.fragment;

import android.content.Intent;
import android.media.Image;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.even1.meat.Columns.ComeAcross;
import com.example.even1.meat.Columns.RunRoute;
import com.example.even1.meat.Columns.Trends;
import com.example.even1.meat.R;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;

public class Column extends Fragment implements View.OnClickListener{

    LinearLayout linearLayout1,linearLayout2,linearLayout3;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_column, container, false);

        linearLayout1=(LinearLayout)view.findViewById(R.id.column);
        linearLayout1.setOnClickListener(this);
        linearLayout2=(LinearLayout)view.findViewById(R.id.route);
        linearLayout2.setOnClickListener(this);
        linearLayout3=(LinearLayout)view.findViewById(R.id.story);
        linearLayout3.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.column:
                startActivity(new Intent(getActivity(),ComeAcross.class));
                break;
            case R.id.route:
                startActivity(new Intent(getActivity(), RunRoute.class));
                break;
            case R.id.story:
                startActivity(new Intent(getActivity(), Trends.class));
                break;
            default:
                break;
    }
}

}
