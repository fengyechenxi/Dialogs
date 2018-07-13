package com.fycx.dialog;

import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.fycx.dialog.adapter.TextListSelectionAdapter;
import com.fycx.dialog.base.BaseDialog;

import java.util.List;

/**
 * 作者：Created by fengyechenxi on 2018/7/13.
 * 邮箱：3160744624@qq.com
 * github：https://github.com/fengyechenxi
 */
public class TextListSelectionDialog extends BaseDialog implements AdapterView.OnItemClickListener{

    private ListView mListView;
    private Builder mBuilder;

    @Override
    public void bindButterKnife(Object o, View v) {

    }

    @Override
    public int dialogContentViewLayoutId() {
        return R.layout.fycx_dialog_text_list_selection;
    }

    @Override
    public int dialogWidth() {
        return WindowManager.LayoutParams.MATCH_PARENT;
    }

    @Override
    public int dialogGravity() {
        return Gravity.BOTTOM;
    }

    @Override
    protected int windowAnimationStyle() {
        return R.style.BottomSheetAnimStyle;
    }

    @Override
    public void setupContentView(View contentView) {
        mListView = contentView.findViewById(R.id.listview);
        if(mBuilder != null){
            TextListSelectionAdapter adapter = new TextListSelectionAdapter(mBuilder);
            mListView.setAdapter(adapter);
            mListView.setOnItemClickListener(this);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        dismiss();
        if (mBuilder != null && mBuilder.mOnTextItemClickListener != null) {
            mBuilder.mOnTextItemClickListener.onTextItemClick(this,mBuilder.mStrings.get(position),position);
        }
    }

    public void show(){
        if(mBuilder != null){
            if(mBuilder.mContext instanceof FragmentActivity){
                FragmentActivity activity = (FragmentActivity) mBuilder.mContext;
                show(activity.getSupportFragmentManager(),getClass().getName());
            }
        }
    }

    public static class Builder{

        private Context mContext;
        private List<String> mStrings;
        private int mTextColor;
        private int mTextSizeInSp;
        private int mTextItemHeight;
        private OnTextItemClickListener mOnTextItemClickListener;

        public Builder(Context context){
            mContext = context;
        }

        public Context getContext() {
            return mContext;
        }

        public Builder setTextColor(int textColor) {
            mTextColor = textColor;
            return this;
        }

        public int getTextColor() {
            return mTextColor;
        }

        public Builder setTextItemHeight(int textItemHeight) {
            mTextItemHeight = textItemHeight;
            return this;
        }

        public int getTextItemHeight() {
            return mTextItemHeight;
        }

        public Builder setTextSizeInSp(int textSizeInSp) {
            mTextSizeInSp = textSizeInSp;
            return this;
        }

        public int getTextSizeInSp() {
            return mTextSizeInSp;
        }

        public Builder setStrings(List<String> strings) {
            mStrings = strings;
            return this;
        }

        public List<String> getStrings() {
            return mStrings;
        }

        public Builder setOnTextItemClickListener(OnTextItemClickListener onTextItemClickListener) {
            mOnTextItemClickListener = onTextItemClickListener;
            return this;
        }

        public TextListSelectionDialog create(){
            TextListSelectionDialog dialog = new TextListSelectionDialog();
            dialog.mBuilder = this;
            return dialog;
        }
    }

    public interface OnTextItemClickListener{
        void onTextItemClick(DialogFragment dialog, String itemText, int position);
    }

}
