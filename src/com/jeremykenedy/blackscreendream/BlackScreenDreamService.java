package com.jeremykenedy.blackscreendream;
import android.service.dreams.DreamService;
public final class BlackScreenDreamService extends DreamService {
    @Override public void onAttachedToWindow() {
        super.onAttachedToWindow();
        setInteractive(false);
        setFullscreen(true);
        setScreenBright(false);
        setContentView(BlackScreen.create(this, getWindow()));
    }
    @Override public void onDreamingStarted() {
        super.onDreamingStarted();
        // DreamService does not forward changed window attributes to WindowManager.
        BlackScreen.applyBrightness(getWindow());
        getWindow().getWindowManager().updateViewLayout(
                getWindow().getDecorView(), getWindow().getAttributes());
    }
}
