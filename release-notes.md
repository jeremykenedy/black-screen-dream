# Black Screen 1.0.1

Black Screen now requests the lowest available display brightness while its screensaver or native preview is visible. Leaving that window returns brightness control to the TV. The scene remains completely black and silent.

## Upgrade guide

Update an existing Black Screen 1.0.0 installation with the signed 1.0.1 APK. The package and signing certificate are unchanged; Android version code increases from 1 to 2. Android 6.0 or newer is still required. No account, new permission, migration or configuration is required.

For an ADB installation, replace `TV_IP` with the TV's saved address:

```sh
adb -s TV_IP:5555 install -r black-screen-dream.apk
```

Open **Black Screen > Preview black screen**, then use Back to verify normal settings brightness returns. The selected screensaver remains unchanged by updating the APK. Android may reject downgrades to version code 1; keep version 1.0.1 installed and select another screensaver if a particular TV ignores or mishandles its brightness request.

## Breaking changes

None. Package identity, signing certificate, Android minimum, screensaver component and preview entry remain unchanged. No global TV brightness, picture, power, HDMI or CEC setting is modified.

## Major changes

None. This is a focused brightness fix.

## Minor changes and fixes

- Apply the same minimum window brightness request to the native preview and DreamService.
- Use the smallest positive request on older Android versions whose integer display controller ignores zero.
- Explicitly update the attached Dream window so its brightness request reaches the display manager.
- Explain the minimum-brightness behavior in Settings without adding text to the black scene.
- Keep pure black pixels, hidden system bars and the existing remote exit behavior.

## Verification and limitations

Native preview and normal screensaver entry passed on integrated Insignia Fire TVs running Android API 28 and API 30, with their existing five-minute idle timers unchanged. Captured preview and Dream frames contained only RGB zero pixels.

| Device | Previous Dream brightness | Updated preview and Dream brightness | After exit |
| --- | --- | --- | --- |
| Insignia NS-32F201NA23, API 28 | 10 | 1 | Override cleared; normal brightness 102 restored. |
| Insignia NS85-UQFL27, API 30 | 0.05 | 0.0 after the brightness ramp settled | Override cleared; normal brightness approximately 1.0 restored. |

These are display-controller readbacks, not measurements of physical panel luminance. The release APK retains the existing signing certificate and has byte-identical application code and resources to the temporary native-test candidate. Version 1.0.1/code 2 was also installed and captured on an Android TV API 34 emulator. Original physical-TV APKs, selections, preferences, protected settings and launcher state were restored after testing. The final native screenshots were reviewed in the rendered documentation.

Android's `BRIGHTNESS_OVERRIDE_OFF` requests the lowest supported brightness; it does not mean panel power-off. LCD/QLED backlight behavior and whether this request is honored depend on the television and Fire OS. Physical backlight darkness has not been confirmed by the owner. This release does not change a television's global brightness or power controls.

Implementation references: [Android window brightness](https://developer.android.com/reference/android/view/WindowManager.LayoutParams#BRIGHTNESS_OVERRIDE_OFF), [Android 9 display-controller handling](https://android.googlesource.com/platform/frameworks/base/+/refs/tags/android-9.0.0_r1/services/core/java/com/android/server/display/DisplayPowerController.java), and [DreamService window updates](https://android.googlesource.com/platform/frameworks/base/+/refs/tags/android-9.0.0_r1/core/java/android/service/dreams/DreamService.java).

[Changes from 1.0.0](https://github.com/jeremykenedy/black-screen-dream/compare/v1.0.0...v1.0.1)
