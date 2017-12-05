package com.example.even1.meat.Adapter;

import android.animation.ValueAnimator;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.even1.meat.R;
import com.example.even1.meat.Run.Runlist;
import com.example.even1.meat.record.PathRecord;

import java.util.List;

public class RecordAdapter extends BaseAdapter {

	private Context mContext;
	private List<PathRecord> mRecordList;
	Runlist runlist;

	public RecordAdapter(Context context, List<PathRecord> list) {
		this.mContext = context;
		this.mRecordList = list;
	}

	@Override
	public int getCount() {
		return mRecordList.size();
	}

	@Override
	public Object getItem(int position) {
		return mRecordList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			if(position==0){
				convertView = View.inflate(mContext, R.layout.recorditemfirst, null);
				startScaleTo(convertView,0.6f,1.0f);
				holder.time = (TextView)convertView.findViewById(R.id.time);
				holder.distance = (TextView)convertView.findViewById(R.id.distance);
				holder.speed = (TextView)convertView.findViewById(R.id.speed);
				PathRecord item = mRecordList.get(position);
				holder.distance.setText(item.getDistance()+"km");
				holder.speed.setText(item.getAveragespeed());
				holder.time.setText("123");
			}
			else{convertView = View.inflate(mContext, R.layout.recorditem, null);}

			holder.date = (TextView) convertView.findViewById(R.id.date);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		PathRecord item = mRecordList.get(position);
		holder.date.setText(item.getDate());

		return convertView;
	}

	public void startScaleTo(final View view, float start, float end) {
		ValueAnimator animator = ValueAnimator.ofFloat(start, end);
		animator.setDuration(500);
		animator.start();
		animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				float value = (float) animation.getAnimatedValue();
				view.setScaleX(value);
				view.setScaleY(0.4f + (0.6f * value));
			}
		});
	}

	private class ViewHolder {
		TextView date;
		TextView record;
		TextView time;
		TextView distance;
		TextView speed;
	}


}
