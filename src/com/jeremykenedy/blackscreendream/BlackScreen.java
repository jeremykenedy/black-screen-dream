package com.jeremykenedy.blackscreendream;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
final class BlackScreen {
    private BlackScreen() {}
    static void applyBrightness(Window window) {
        WindowManager.LayoutParams attributes = window.getAttributes();
        // Android 9 and 10 ignore a zero override; their integer controller accepts 1.
        attributes.screenBrightness = Build.VERSION.SDK_INT < 30
                ? 1f / 255f : WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_OFF;
        attributes.buttonBrightness = WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_OFF;
        window.setAttributes(attributes);
    }
    static View create(Context context, Window window) {
        applyBrightness(window);
        window.setBackgroundDrawable(new ColorDrawable(Color.BLACK));
        window.setStatusBarColor(Color.BLACK);
        window.setNavigationBarColor(Color.BLACK);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        View view = new View(context);
        view.setBackgroundColor(Color.BLACK);
        return view;
    }
}
