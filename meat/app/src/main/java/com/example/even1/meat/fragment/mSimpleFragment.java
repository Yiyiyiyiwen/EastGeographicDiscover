package com.example.even1.meat.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.example.even1.meat.R;
import com.example.even1.meat.details.InformationDetail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 简单的Fragment
 * <p/>
 * Created by wangchenlong on 15/11/9.
 */
public class mSimpleFragment extends Fragment implements AdapterView.OnItemClickListener {
    private static final String ARG_SELECTION_NUM = "arg_selection_num"; // 参数的Tag

    public mSimpleFragment() {
    }

    /**
     * 通过静态接口创建Fragment，规范参数的使用
     *
     * @param selectionNum 参数
     * @return 创建的Fragment
     */
    public static mSimpleFragment newInstance(int selectionNum) {
        mSimpleFragment simpleFragment = new mSimpleFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SELECTION_NUM, selectionNum);
        simpleFragment.setArguments(args);
        return simpleFragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);

        ListView listview = (ListView)view.findViewById(R.id.listview);
        SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(),getData(),R.layout.comeacrossitem,
                new String[]{"head","name","qianming"},
                new int[]{R.id.head,R.id.name,R.id.qianming});
        listview.setAdapter(simpleAdapter);
        setListViewHeightBasedOnChildren(listview);
        listview.setOnItemClickListener(this);
        return view;
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
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent(getActivity(), InformationDetail.class));
    }
}
