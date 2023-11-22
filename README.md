## 📋 JetTaskBoardKMP - Multiplatform Trello Clone

  <p align="left"> A clone of Trello app built using Compose Multiplatform for Android, iOS and Desktop.</p>

   This app was previously written in Jetpack Compose for Android in [JetTaskBoard](https://github.com/pushpalroy/jetTaskBoard). Now, it has been migrated to multiplatform.

  <p align="left">
      <a href = "https://github.com/JetBrains/compose-multiplatform/releases">
        <img src = "https://img.shields.io/badge/Compose%20Multiplatform-1.4.0-blue.svg?color=blue&style=for-the-badge" />
      </a>
      <a href="https://kotlinlang.org/docs/releases.html">
        <img src="https://img.shields.io/badge/Kotlin-1.8.20-blue.svg?color=blue&style=for-the-badge"/>
      </a>
      <a href = "https://github.com/pushpalroy/JetTaskBoardKMP/stargazers">
        <img src="https://img.shields.io/github/stars/pushpalroy/JetTaskBoardKMP?color=yellowgreen&style=for-the-badge" />
      </a>
      <a href = "https://github.com/pushpalroy/JetTaskBoardKMP/network/members">
          <img src="https://img.shields.io/github/forks/pushpalroy/JetTaskBoardKMP?color=yellowgreen&style=for-the-badge" />
      </a>
      <a href = "https://github.com/pushpalroy/JetTaskBoardKMP/watchers">
          <img src="https://img.shields.io/github/watchers/pushpalroy/JetTaskBoardKMP?color=yellowgreen&style=for-the-badge" />
      </a>
      <a href = "https://github.com/pushpalroy/JetTaskBoardKMP/pulls">
         <img src="https://img.shields.io/github/issues-pr/pushpalroy/JetTaskBoardKMP?color=yellowgreen&style=for-the-badge" alt="Pull Requests Badge"/>
      </a>
      <a href = "https://github.com/pushpalroy/JetTaskBoardKMP/issues">
          <img src="https://img.shields.io/github/issues/pushpalroy/JetTaskBoardKMP?color=orange&style=for-the-badge" />
      </a>
  </p>

### Card drag & drop

<img src = "art/board_card_move.gif" width="100%"/>

### Change board background

<img src = "art/board_change_bg.gif" width="100%"/>

### Add and archive card

<img src = "art/board_add_archive_card.gif"/>

### 👾 Features

* Dashboard - View different workspaces
* Create board/card from dashboard
* Board - Drag and Drop support
* Change board background - Uses [Unsplash API](https://unsplash.com/developers)
* Board background search feature
* Add list in Board
* Add card in Board and quick edit
* Drag and archive card from list

### ⚒️ Architecture

JetTaskBoardKMP follows the principles of Clean Architecture.

### 👨‍💻 Tech stack

| Tools                 |                                     Link                                      |
|:----------------------|:-----------------------------------------------------------------------------:|
| 🤖  Language          |                       [Kotlin](https://kotlinlang.org)                        |
| 🩶  Framework         |  [Compose Multiplatform](https://www.jetbrains.com/lp/compose-multiplatform)  |
| 🕜  Lifecycle         |                [Essenty](https://github.com/arkivanov/Essenty)                |
| 📁  State Management  |              [Decompose](https://github.com/arkivanov/decompose)              |
| 💉  DI                |          [Koin](https://insert-koin.io/docs/reference/koin-mp/kmp/)           |
| 🧭  Navigation        |              [xxfast/KRouter](https://github.com/xxfast/KRouter)              |
| 🌐  Networking        |                    [KTor](https://github.com/ktorio/ktor)                     |
| 🧶  Multi-threading   |     [Kotlin Coroutines](https://developer.android.com/kotlin/coroutines)      |
| 📼  Persistence       | [Multiplatform Settings](https://github.com/russhwolf/multiplatform-settings) |
| 📐  Window Insets     |              [InsetsX](https://github.com/mori-atsushi/insetsx)               |
| 🔍  Zooming Behaviour |               [Zoomables](https://github.com/Mr-Pine/Zoomables)               |

### 🖥️  Screenshots

<table style="width:100%">
  <tr>
    <th>Home</th>
    <th>Board</th>
  </tr>
  <tr>
    <td><img src = "art/home.png" width="100%"/></td> 
    <td><img src = "art/board.png" width="100%"/></td>
  </tr>
</table>

### How to run the project? ✅

To run this project, you need the following:

* A machine running a recent version of macOS
* [Xcode](https://apps.apple.com/us/app/xcode/id497799835)
* [Android Studio](https://developer.android.com/studio)
* The [Kotlin Multiplatform Mobile plugin](https://plugins.jetbrains.com/plugin/14936-kotlin-multiplatform-mobile)
* The [CocoaPods dependency manager](https://kotlinlang.org/docs/native-cocoapods.html)

### Check your environment

Before you start, use the [KDoctor](https://github.com/Kotlin/kdoctor) tool to ensure that your development environment is configured correctly:

1. Install KDoctor with [Homebrew](https://brew.sh/): ``brew install kdoctor``

2. Run KDoctor in your terminal: ``kdoctor``

   If everything is set up correctly, you'll see valid output:

   ```text
   Environment diagnose (to see all details, use -v option):
   [✓] Operation System
   [✓] Java
   [✓] Android Studio
   [✓] Xcode
   [✓] Cocoapods
   
   Conclusion:
     ✓ Your system is ready for Kotlin Multiplatform Mobile development!
   ```

Otherwise, KDoctor will highlight which parts of your setup still need to be configured and will suggest a way to fix them.

## Understanding the project structure

Open the project in Android Studio and switch the view from **Android** to **Project** to see all the files and targets belonging to the project:

Your Compose Multiplatform project includes 3 modules:

### shared

This is a Kotlin module that contains the logic common for both Android and iOS applications, that is, the code you share between platforms.

This `shared` module is also where you’ll write your Compose Multiplatform code.
In `shared/src/commonMain/kotlin/App.kt`, you can find the shared root `@Composable` function for your app.

It uses Gradle as the build system. You can add dependencies and change settings in `shared/build.gradle.kts`.
The `shared` module builds into an Android library and an iOS framework.

### androidApp

This is a Kotlin module that builds into an Android application. It uses Gradle as the build system.
The `androidApp` module depends on and uses the `shared` module as a regular Android library.

### iosApp

This is an Xcode project that builds into an iOS application.
It depends on and uses the `shared` module as a CocoaPods dependency.

## Star History

[![Star History Chart](https://api.star-history.com/svg?repos=pushpalroy/JetTaskBoardKMP)](https://star-history.com/#pushpalroy/JetTaskBoardKMP)

## Run your application

### On Android

To run your application on an Android emulator:

1. Ensure you have an Android virtual device available. Otherwise, [create one](https://developer.android.com/studio/run/managing-avds#createavd).
2. In the list of run configurations, select `androidApp`.
3. Choose your virtual device and click **Run**:

   <img src="art/run_on_android.png" height="60px">

<details>
  <summary>Alternatively, use Gradle</summary>

To install an Android application on a real Android device or an emulator, run `./gradlew installDebug` in the terminal.

</details>

### On iOS

#### Running on a simulator

To run your application on an iOS simulator in Android Studio, modify the `iosApp` run configuration:

1. In the list of run configurations, select **Edit Configurations**:

   <img src="art/edit_run_config.png" height="200px">

2. Navigate to **iOS Application** | **iosApp**.
3. In the **Execution target** list, select your target device. Click **OK**.
4. The `iosApp` run configuration is now available. Click **Run** next to your virtual device.

#### Running on a real device

You can run your Compose Multiplatform application on a real iOS device for free.
To do so, you'll need the following:

* The `TEAM_ID` associated with your [Apple ID](https://support.apple.com/en-us/HT204316)
* The iOS device registered in Xcode

> **Note**
> Before you continue, we suggest creating a simple "Hello, world!" project in Xcode to ensure you can successfully run apps on your device.
> You can follow the instructions below or watch this [Stanford CS193P lecture recording](https://youtu.be/bqu6BquVi2M?start=716&end=1399).

<details>
<summary>How to create and run a simple project in Xcode</summary>

1. On the Xcode welcome screen, select **Create a new project in Xcode**.
2. On the **iOS** tab, choose the **App** template. Click **Next**.
3. Specify the product name and keep other settings default. Click **Next**.
4. Select where to store the project on your computer and click **Create**. You'll see an app that displays "Hello, world!" on the device screen.
5. At the top of your Xcode screen, click on the device name near the **Run** button.
6. Plug your device into the computer. You'll see this device in the list of run options.
7. Choose your device and click **Run**.

</details>

### On Desktop

To run the application on desktop:

1. Execute the command from terminal: ``./gradlew :shared:run ``

### Find this project useful ? ❤️

- Support it by clicking the ⭐️ button on the upper right of this page. ✌️

### License

```
MIT License

Copyright (c) 2022 Pushpal Roy

Permission is hereby granted, free of charge, to any person obtaining a 
copy of this software and associated documentation files (the "Software"), 
to deal in the Software without restriction, including without limitation 
the rights to use, copy, modify, merge, publish, distribute, sublicense, 
and/or sell copies of the Software, and to permit persons to whom the 
Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included 
in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, 
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE 
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, 
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN 
CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
```
