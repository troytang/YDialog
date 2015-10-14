package com.tangwy.ydialog;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.tangwy.ydialog.internal.BallsDrawable;

/**
 * Created by Troy Tang on 2015-10-13.
 */
public class YDialog extends DialogFragment {

    private static YDialog instance;

    private static YDialog getInstance() {
        if (null == instance) {
            instance = new YDialog();
        }
        return instance;
    }

    public static void show(FragmentActivity activity) {
        getInstance().show(activity.getSupportFragmentManager(), "YDialog");
    }

    public static void show(FragmentActivity activity, String msg) {
        getInstance().setMessage(msg).show(activity.getSupportFragmentManager(), "YDialog");
    }

    public static void gone() {
        if (null != instance) {
            instance.dismiss();
            instance.setMessage(null);
        }
    }

    private View rootView;
    private ImageView ivLoading;
    private TextView tvDesc;
    private BallsDrawable drawable;
    private String message;

    public YDialog() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCancelable(false);
        getDialog().setCanceledOnTouchOutside(false);
        getDialog().getWindow().setWindowAnimations(R.style.CustomDialogAnimation);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
        getDialog().getWindow().setDimAmount(0);
        rootView = inflater.inflate(R.layout.layout_dialog, null);
        ivLoading = (ImageView) rootView.findViewById(R.id.ivLoading);
        tvDesc = (TextView) rootView.findViewById(R.id.tvDesc);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setup();
    }

    private YDialog setup() {
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

    private YDialog setMessage(String msg) {
        this.message = msg;
        return this;
    }

}
