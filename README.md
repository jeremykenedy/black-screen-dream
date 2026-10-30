# Black Screen

A completely black Android TV and Fire TV screensaver, with no clock, text, animation, or sound.

## Contents

- [Features](#features)
- [Install](#install)
- [Build](#build)
- [License](#license)

## Features

| Feature | Behavior |
| --- | --- |
| Black display | Every pixel stays black; system navigation is hidden. |
| Minimum brightness | Requests the lowest supported brightness only while the saver or preview is visible. |
| Native preview | Open Black Screen and select Preview black screen. Back returns to settings. |
| Offline | No network permission, analytics, external media, or runtime dependencies. |

This screensaver does not turn the television off. Display backlighting depends on the television; the minimum-brightness request cannot guarantee that an LCD or QLED backlight turns off.

## Install

Requires Android 6.0 or newer. Download [black-screen-dream.apk](https://github.com/jeremykenedy/black-screen-dream/releases/latest/download/black-screen-dream.apk), install it, and select Black Screen in your device screensaver settings.

![Native black preview](art/screenshots/black-preview.png)

## Build

Install JDK 17+, Android SDK platform 36, and build-tools 36.0.0. Set `BLACK_SCREEN_KEYSTORE` and `BLACK_SCREEN_KEYPASS` to your own signing keystore and password file; the key alias is `black-screen-dream`. Run `bash build.sh`. Release keys are never stored in this repository.

The screensaver component is `com.jeremykenedy.blackscreendream/.BlackScreenDreamService`. Settings and preview are `.SettingsActivity` and `.PreviewActivity`. Version 1.0.1 uses Android version code 2. Credits and the complete license are accessible from Settings outside the black scene.

## License

This project is open-sourced software licensed under the [Apache License 2.0](LICENSE).
