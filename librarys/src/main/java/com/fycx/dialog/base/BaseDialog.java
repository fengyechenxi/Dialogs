package com.fycx.dialog.base;

import android.content.DialogInterface;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.WindowManager;

import com.fycx.dialog.R;
import com.fycx.dialog.cons.Constants;

/**
 * 作者：Created by fengyechenxi on 2018/7/13.
 * 邮箱：3160744624@qq.com
 * github：https://github.com/fengyechenxi
 */
public abstract class BaseDialog extends CustomDialog implements DialogInterface.OnKeyListener{

    private OnKeyBackCallback mKeyBackCallback;

    @Override
    public int dialogWidth() {
        return getResources().getDimensionPixelSize(R.dimen.fycx_dialog_width_default);
    }

    @Override
    public int dialogHeight() {
        return WindowManager.LayoutParams.WRAP_CONTENT;
    }

    @Override
    public float translucentAlpha() {
        return Constants.WINDOW_ALPHA;
    }



    @Override
    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if (mKeyBackCallback != null) {
                dialog.dismiss();
                mKeyBackCallback.onKeyBackPress();
            }
            return true;
        }else {
            //这里注意当不是返回键时需将事件扩散，否则无法处理其他点击事件
            return false;
        }
    }

    @Override
    public int x() {
        return 0;
    }

    @Override
    public int y() {
        return 0;
    }

    @Override
    public int dialogGravity() {
        return Gravity.CENTER;
    }

    public void setOnKeyBackCallback(OnKeyBackCallback keyBackCallback) {
        mKeyBackCallback = keyBackCallback;
    }

    public interface OnKeyBackCallback{
        void onKeyBackPress();
    }


}