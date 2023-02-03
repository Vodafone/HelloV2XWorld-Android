# HelloV2XWorld-Android

In this repository you can find an example of V2X application that shows how to use the V2X-SDK in a ready to use Android Application.

## Description

The HelloV2XWorld application demonstrates how easy it is to create a V2X application and exchange V2X messages with other road users .
This application is powered by the V2X-SDK and the STEP platform developed by Vodafone.
By cloning this project, you will be able to create and execute your first V2X application in less than 15 minutes.
The HelloV2XWorld application shows a map with all the road users close to your location.
You can also use this application as a model to start developing your own application.

## Getting Started

### Prerequisites

* Android Studio (2022.1.1 or upper) is already installed on your laptop.
* A software able to open rar & zip archives is already installed on your laptop.
* You have some experience in Android application development in Java using Android Studio.
* You already created One virtual device in Android Studio (Android 8 or higher).
* Your Internet connectivity is up and running.
* You are available for 15 minutes.

### Download the V2X-SDK library for Android

* Register yourself on the [STEP Web portal](https://step.vodafone.com/)
* Go to the [STEP Web portal](https://step.vodafone.com/) webpage and login.
* Navigate to the page "Documentation" and select the "SDK documentation for Android" option.
* In the "SDK documentation for Android" page , click on the "Download SDK" button.
=> Your browser downloads the archive "VodafoneV2X_Android_SDK.rar".
* Open the archive, extract the V2X-SDK library file called "v2xsdk-release.aar" and save it on your laptop. 

### Clone the HelloV2XWorld Application

* Open Android Studio.
* Select "File" then "New" then "Project from Version Control".
=> A window called "Get from Version Control" appears.
* In the field version Control select the option "Git".
* In the field URL set "https://github.com/Vodafone/HelloV2XWorld-Android.git".
* Click on the "Clone" button.
* Wait until the completion of this operation.

### Import the V2X-SDK library in your project

* Copy the "v2xsdk-release.aar" file to the app/libs folder of your project.

### Build the HelloV2XWorld application

* Click on "Build" then "Make Project" (or use the shortcut "Ctrl+F9").

### Run the HelloV2XWorld application on the virtual Android device

* Select your virtual Device in Android Studio.
* Press the RUN button.
* Wait until the "Emulator - HelloV2XWorld"  windows appears.
* Read the Term and Conditions, Scroll down and click on the "Accept" button.
=> A map is displayed centered on your current location
=> The blue icon represents yourself
=> The red icons represents the other road users close to you



## Version History

* 1.0
    * Initial Release

## License

This project is licensed under the MIT License - see the LICENSE.md file for details

