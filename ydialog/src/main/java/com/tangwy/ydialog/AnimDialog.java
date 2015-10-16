package com.tangwy.ydialog;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tangwy.ydialog.internal.BaseDrawable;

/**
 * Created by Troy Tang on 2015-10-16.
 */
public class AnimDialog extends Dialog {

    private static AnimDialog instance;

    private static AnimDialog getInstance(Context context) {
        if (null == instance) {
            instance = new AnimDialog(context);
        }
        return instance;
    }

    public static void show(Context context) {
        getInstance(context).setup().show();
    }

    public static void show(Context context, String msg) {
        getInstance(context).message(msg).setup().show();
    }

    public static void gone() {
        if (null != instance) {
            instance.dismiss();
            instance.message(null);
        }
    }

    private ImageView ivLoading;
    private TextView tvDesc;
    private BaseDrawable drawable;
    private String message;

    private AnimDialog(Context context) {
        super(context, R.style.AnimDialog);

        init();
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private void init() {
        setContentView(R.layout.layout_dialog);
        getWindow().setWindowAnimations(R.style.CustomDialogAnimation);
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        getWindow().setDimAmount(0);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        ivLoading = (ImageView) findViewById(R.id.ivLoading);
        tvDesc = (TextView) findViewById(R.id.tvDesc);
    }

    private AnimDialog setup() {
        if (TextUtils.isEmpty(message)) {
            tvDesc.setVisibility(View.GONE);
            ivLoading.setPadding(0, 0, 0, 0);
            ivLoading.getLayoutParams().height = getContext().getResources().getDimensionPixelSize(R.dimen.custom_dialog_height_big);
        } else {
            tvDesc.setVisibility(View.VISIBLE);
            tvDesc.setText(message);
            ivLoading.setPadding(0, getContext().getResources().getDimensionPixelSize(R.dimen.custom_dialog_padding_top), 0, 0);
            ivLoading.getLayoutParams().height = getContext().getResources().getDimensionPixelSize(R.dimen.custom_dialog_height_small);
        }

        drawable = new BallsDrawable(ivLoading);
        ivLoading.setImageDrawable(drawable);
        drawable.start();

        return this;
    }

    public AnimDialog message(String msg) {
        this.message = msg;
        return this;
    }

    public static class Builder {

        private Context mContext;
        private boolean mCancelOnKeyBack;
        private boolean mCancelOnTouchOutSide;
        private float mDimAmount;
        private int mAnimationsResId;

        private String mMessage;

        public Builder(Context context) {
            mContext = context;
            mCancelOnKeyBack = true;
            mCancelOnTouchOutSide = true;
            mDimAmount = 0.0f;
            mAnimationsResId = R.style.CustomDialogAnimation;
        }

        public Builder cancelOnKeyBack(boolean cancelOnKeyBack) {
            mCancelOnKeyBack = cancelOnKeyBack;
            return this;
        }

        public Builder cancelOnTouchOutSide(boolean cancelOnTouchOutSide) {
            mCancelOnTouchOutSide = cancelOnTouchOutSide;
            return this;
        }

        public Builder dimAmount(float amount) {
            mDimAmount = amount;
            return this;
        }

        public Builder animations(int resId) {
            mAnimationsResId = resId;
            return this;
        }

        public Builder message(String msg) {
            mMessage = msg;
            return this;
        }

        @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
        public AnimDialog build() {
            AnimDialog animDialog = new AnimDialog(mContext);
            animDialog.setCancelable(mCancelOnKeyBack);
            animDialog.setCanceledOnTouchOutside(mCancelOnTouchOutSide);
            animDialog.getWindow().setDimAmount(mDimAmount);
            animDialog.getWindow().setWindowAnimations(mAnimationsResId);
            animDialog.message(mMessage);
            animDialog.setup();
            return animDialog;
        }
    }
}
