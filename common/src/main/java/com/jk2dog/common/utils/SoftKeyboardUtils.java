package com.jk2dog.common.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import java.lang.reflect.Method;

/**
 * 软件盘工具
 * Created by ZC on 2017/8/24.
 */

public class SoftKeyboardUtils {
    private static final String TAG = SoftKeyboardUtils.class.getSimpleName();

    /**
     * 关闭键盘
     *
     * @param activity
     */
    public static void closeKeyboard(Activity activity) {
        if (activity.getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (activity.getCurrentFocus() != null) {
                InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    /**
     * 判断是否有虚拟底部按钮
     *
     * @return
     */
    public static boolean checkDeviceHasNavigationBar(Context context) {
        boolean hasNavigationBar = false;
        Resources rs = context.getResources();
        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {
            hasNavigationBar = rs.getBoolean(id);
        }
        try {
            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if ("1".equals(navBarOverride)) {
                hasNavigationBar = false;
            } else if ("0".equals(navBarOverride)) {
                hasNavigationBar = true;
            }
        } catch (Exception e) {
            Log.w(TAG, e);
        }
        return hasNavigationBar;
    }

    /**
     * 获取底部虚拟按键高度
     *
     * @return
     */
    public static int getNavigationBarHeight(Context context) {
        int navigationBarHeight = 0;
        Resources rs = context.getResources();
        int id = rs.getIdentifier("navigation_bar_height", "dimen", "android");
        if (id > 0 && checkDeviceHasNavigationBar(context)) {
            navigationBarHeight = rs.getDimensionPixelSize(id);
        }
        return navigationBarHeight;
    }




    /**
     * 1、获取main在窗体的可视区域
     * 2、获取main在窗体的不可视区域高度
     * 3、判断不可视区域高度
     * 1、大于100：键盘显示  获取Scroll的窗体坐标
     * 算出main需要滚动的高度，使scroll显示。
     * 2、小于100：键盘隐藏
     *
     * @param main   根布局
     * @param scroll 需要显示的最下方View
     */
//    public static void addLayoutListener(final View main, final View scroll) {
//        main.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                Rect rect = new Rect();
//                main.getWindowVisibleDisplayFrame(rect);
//                int mainInvisibleHeight = main.getRootView().getHeight() - rect.bottom;
//                if (mainInvisibleHeight > 100) {
//                    int[] location = new int[2];
//                    scroll.getLocationInWindow(location);
//                    int srollHeight = (location[1] + scroll.getHeight()) - rect.bottom;
//                    main.scrollTo(0, srollHeight);
//                } else {
//                    main.scrollTo(0, 0);
//                }
//            }
//        });
//    }

//    public void addLayoutLinstener(final View main, final View scroll) {
//        main.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                Rect rect = new Rect();
//                main.getWindowVisibleDisplayFrame(rect);
//                //不可视高度
//                int mainInvisibleHeight = main.getRootView().getHeight() - rect.bottom;
//                //说明键盘弹起
//                if (mainInvisibleHeight > 200) {
//                    int[] location = new int[2];
//                    scroll.getLocationInWindow(location);
//                    int scrollViewHeight = scroll.getHeight();
//                    int location1 = location[1];
//                    int scrollHeight = (location1 + scrollViewHeight + 10) - rect.bottom;
//                    if (scrollHeight > 0) {
//                        main.scrollTo(0, scrollHeight);
//                    }
//                } else {
//                    main.scrollTo(0, 0);
//                }
//            }
//        });
//    }


    public static void addLayoutLinstener2(final View main, final View scroll) {
        main.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener(){
            private int[] location;
            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();
                // 获取ll_login在窗体的可视区域
                main.getWindowVisibleDisplayFrame(rect);
                // 获取ll_login在窗体的不可视区域高度(被其他View遮挡的区域高度)
                int rootInvisibleHeight = main.getRootView().getHeight() - rect.bottom;
                // 键盘显示
                if (rootInvisibleHeight > 100) {
                    //只记录btn_login位置未变化时的位置
                    if (location==null){
                        location = new int[2];
                        // 获取btn_login在窗体的坐标
                        scroll.getLocationInWindow(location);
                    }
                    // 计算ll_login滚动高度，使btn_login在可见区域
                    int srollHeight = location[1] + scroll.getHeight() - rect.bottom;
                    if (srollHeight > 0) {//当键盘高度覆盖按钮时
                        main.scrollTo(0, srollHeight);
                    }
                } else {
                    // 键盘隐藏
                    main.scrollTo(0, 0);
                }
            }
        });
    }




    /**
     * 保持登录按钮始终不会被覆盖
     *
     * @param root
     * @param subView
     */
//    public static void keepLoginBtnNotOver(final View root, final View subView) {
//        root.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                Rect rect = new Rect();
//                // 获取root在窗体的可视区域
//                root.getWindowVisibleDisplayFrame(rect);
//                // 获取root在窗体的不可视区域高度(被其他View遮挡的区域高度)
//                int rootInvisibleHeight = root.getRootView().getHeight() - rect.bottom;
//                // 若不可视区域高度大于200，则键盘显示,其实相当于键盘的高度
//                if (rootInvisibleHeight > 100) {
//                    // 显示键盘时
//                    int srollHeight = rootInvisibleHeight - (root.getHeight() - subView.getHeight()) - getNavigationBarHeight(root.getContext());
//                    if (srollHeight > 0) {//当键盘高度覆盖按钮时
//                        root.scrollTo(0, srollHeight);
//                    }
//                } else {
//                    // 隐藏键盘时
//                    root.scrollTo(0, 0);
//                }
//            }
//        });
//    }


    /**
     * 关闭键盘事件.
     *
     * @param context the context
     */
    public static void closeSoftInput(Context context) {
        InputMethodManager inputMethodManager = (InputMethodManager) context
            .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null
            && ((Activity) context).getCurrentFocus() != null) {
            inputMethodManager.hideSoftInputFromWindow(((Activity) context)
                    .getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 弹出输入法
     *
     * @param context context
     * @param view    编辑控件
     */
    public static void setEditFocusable(final Context context, final View view) {
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) context
            .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(view, 0);
    }

}
