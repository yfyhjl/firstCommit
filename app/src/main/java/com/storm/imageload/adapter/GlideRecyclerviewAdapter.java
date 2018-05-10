package com.storm.imageload.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.storm.imageload.R;
import com.storm.imageload.utils.ImgLoadUtil;
import com.storm.imageloadlib.listener.ImgLoaderListener;
import com.storm.imageloadlib.options.ImgLoaderOptions;

public class GlideRecyclerviewAdapter extends RecyclerView.Adapter<GlideRecyclerviewAdapter.ViewHolder> {
	private Context mContext;
	String[] mDatas;
	ImgLoaderOptions options;

	public GlideRecyclerviewAdapter(Context context, String[] datas) {
		mContext = context;
		mDatas = datas;
		// 显示数据
		int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 600f,
				mContext.getResources().getDisplayMetrics());
		int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200f,
				mContext.getResources().getDisplayMetrics());
		options = ImgLoadUtil.getOptions(R.mipmap.ic_launcher_round);
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemview = View.inflate(mContext, R.layout.item_glide_recyclerview, null);
		return new ViewHolder(itemview);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		// 获取当前item数据
		switch (position) {
			case 0 :
				options = ImgLoadUtil.getRoundConerOption(R.mipmap.ic_launcher, 20);
				changeImage(mDatas[position], holder.image, options);
				break;
			case 1 :
				options = ImgLoadUtil.getRoundConerOption(R.mipmap.ic_launcher, 6);
				changeImage(mDatas[position], holder.image, options);
				break;
			case 2 :
				options = ImgLoadUtil.getRotateOption(R.mipmap.ic_launcher, 60);
				changeImage(mDatas[position], holder.image, options);
				break;
			case 3 :
				options = ImgLoadUtil.getCircleOption(R.mipmap.ic_launcher);
				changeImage(mDatas[position], holder.image, options);
				break;
			default :
				options = ImgLoadUtil.getOptions(R.mipmap.ic_launcher_round);
				changeImage(mDatas[position], holder.image, options);
				// options = ImgLoadUtil.getNormalOption(mContext, R.mipmap.ic_launcher);
				break;
		}

		holder.textView.setText("POSITION:" + position);
	}

	private void changeImage(Object path, ImageView imageView, ImgLoaderOptions options) {
		// ImgLoadUtil.showImage(path, imageView, options);
		ImgLoadUtil.showImage(path, imageView, options, new ImgLoaderListener() {
			@Override
			public void onRequestSuccess(Object resource) {

			}

			@Override
			public void onRequestFailed(Exception e) {

			}

			@Override
			public void onLoadStarted() {

			}

			@Override
			public void onLoadFailed(Object resource) {

			}

			@Override
			public void onLoadSuccess(Object resource) {

			}

			@Override
			public void onLoadCancelled(Object resource) {

			}
		});
	}

	@Override
	public int getItemCount() {
		return mDatas == null ? 0 : mDatas.length;
	}

	static class ViewHolder extends RecyclerView.ViewHolder {
		ImageView image;
		TextView textView;

		public ViewHolder(View itemView) {
			super(itemView);
			image = (ImageView) itemView.findViewById(R.id.iv_glide_recyclerview);
			textView = (TextView) itemView.findViewById(R.id.text_recycleview);
		}
	}
}
