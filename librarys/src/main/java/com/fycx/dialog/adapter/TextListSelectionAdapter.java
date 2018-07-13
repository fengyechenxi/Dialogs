package com.fycx.dialog.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fycx.dialog.R;
import com.fycx.dialog.TextListSelectionDialog;
import com.fycx.dialog.utils.DisplayUtils;

import java.util.List;

/**
 * 作者：Created by fengyechenxi on 2018/7/13.
 * 邮箱：3160744624@qq.com
 * github：https://github.com/fengyechenxi
 */
public class TextListSelectionAdapter extends BaseAdapter{

    private List<String> mStrings;
    private int mItemHeight;
    private int mTextColor;
    private int mTextSize;
    private Context mContext;

    public TextListSelectionAdapter(@NonNull TextListSelectionDialog.Builder builder){
        mContext = builder.getContext();
        mTextSize = builder.getTextSizeInSp();
        mTextColor = builder.getTextColor();
        mStrings = builder.getStrings();
        mItemHeight = builder.getTextItemHeight();
    }

    @Override
    public int getCount() {
        return mStrings == null ? 0 : mStrings.size();
    }

    @Override
    public Object getItem(int position) {
        return mStrings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        TextView textView = new TextView(mContext);
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, DisplayUtils.dp2px(mContext,55));
        textView.setLayoutParams(params);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(mTextColor == 0 ? Color.BLACK : mTextColor);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,mTextSize == 0 ? 17 : mTextSize);
        ViewCompat.setBackground(textView,ContextCompat.getDrawable(mContext, R.drawable.fycx_bg_frame_white));
        textView.setText(mStrings.get(position));
        return textView;
    }
}
