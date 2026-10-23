package com.jeremykenedy.blackscreendream;
import android.app.Activity;
import android.os.Bundle;
public final class PreviewActivity extends Activity {
    @Override public void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(BlackScreen.create(this, getWindow()));
    }
}
