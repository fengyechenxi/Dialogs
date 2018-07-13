package com.fycx.dialog.base;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * 作者：Created by fengyechenxi on 2018/7/13.
 * 邮箱：3160744624@qq.com
 * github：https://github.com/fengyechenxi
 */
public abstract class CustomDialog extends DialogFragment {

    /**
     * 没有设置dialog的弹出动画，这个是默认值
     */
    private static final int NO_SET_ANIMATION = -1;



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Activity activity = getActivity();
        View contentView = LayoutInflater.from(activity).inflate(dialogContentViewLayoutId(), null);
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        AlertDialog dialog = builder.show();
        Window window = dialog.getWindow();
        if (window != null) {
            window.setContentView(contentView);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            window.setLayout(dialogWidth(), dialogHeight());
            window.clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
            WindowManager.LayoutParams windowParams = window.getAttributes();
            if(x() != 0){
                windowParams.x = x();
            }
            if(y() != 0){
                windowParams.y = y();
            }

            windowParams.gravity = dialogGravity();

            windowParams.dimAmount = translucentAlpha();
            window.setAttributes(windowParams);

            if(windowAnimationStyle() != NO_SET_ANIMATION){
                window.setWindowAnimations(windowAnimationStyle());
            }
            windowOtherSettings(window);
        }
        bindButterKnife(this, contentView);
        setupContentView(contentView);
        return dialog;
    }

    public abstract float translucentAlpha();

    public abstract int dialogWidth();
    public abstract int dialogHeight();
    public abstract int dialogGravity();

    public abstract int x();
    public abstract int y();

    /**
     * 提供一个ButterKnife注入初始化的钩子
     * @param o
     * @param v
     */
    public abstract void bindButterKnife(Object o, View v);

    /**
     * 关于Window的其他配置
     * @param window
     */
    public void windowOtherSettings(Window window){

    }

    /**
     * @return dialog的布局文件id
     */
    public abstract @LayoutRes
    int dialogContentViewLayoutId();

    /**
     * 初始化dialog的控件，这里绑定了ButterKnife,直接使用@BindView
     */
    public abstract void setupContentView(View contentView);

    /**
     * 重写该方法，可以修改dialog的弹出动画
     * 比如 ：
     * <style name="BottomSheetDialogStyle">
     *     <item name="android:windowEnterAnimation">@anim/bottom_sheet_enter</item>
     *     <item name="android:windowExitAnimation">@anim/bottom_sheet_exit</item>
     * </style>
     * @return 返回dialog弹出动画的style
     */
    protected int windowAnimationStyle(){
        return NO_SET_ANIMATION;
    }


}
