package com.jeremykenedy.blackscreendream;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
public final class SettingsActivity extends Activity {
    @Override public void onCreate(Bundle state) {
        super.onCreate(state);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(48, 32, 48, 32);
        TextView title = new TextView(this);
        title.setText("Black Screen"); title.setTextSize(28);
        layout.addView(title);
        TextView description = new TextView(this);
        description.setText("A completely black screensaver. No clock, text, animation, or sound. Requests the lowest available brightness while open. The TV stays on.");
        layout.addView(description);
        Button preview = new Button(this); preview.setText("Preview black screen");
        preview.setOnClickListener(v -> startActivity(new Intent(this, PreviewActivity.class)));
        layout.addView(preview);
        Button credits = new Button(this); credits.setText("Credits and license");
        credits.setOnClickListener(v -> showCredits()); layout.addView(credits);
        setContentView(layout); preview.setFocusableInTouchMode(true); preview.requestFocus();
    }
    private void showCredits() {
        TextView text = new TextView(this); text.setPadding(24, 16, 24, 16);
        text.setText(readAsset("NOTICE.txt") + "\n\n" + readAsset("LICENSE.txt"));
        ScrollView scroll = new ScrollView(this); scroll.addView(text);
        new AlertDialog.Builder(this).setTitle("Credits and license").setView(scroll).setPositiveButton("Close", (dialog, which) -> dialog.dismiss()).show();
    }
    private String readAsset(String name) {
        try (InputStream input = getAssets().open(name); ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[4096]; int count;
            while ((count = input.read(buffer)) != -1) output.write(buffer, 0, count);
            return output.toString("UTF-8");
        } catch (java.io.IOException error) { return "License unavailable: " + name; }
    }
}
