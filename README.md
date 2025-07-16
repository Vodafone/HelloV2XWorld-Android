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
![STEPWP_Signup_Annoted](https://user-images.githubusercontent.com/124599387/219050113-50e5a149-1559-4776-af0f-30d3ad16c5ef.png)

* Go to the [STEP Web portal](https://step.vodafone.com/) webpage and login.  
**=>Enter your credentials**   
![STEPWP_Login1_Annoted](https://user-images.githubusercontent.com/124599387/219050444-cf48a53c-60e4-458a-a021-5841a789ccc6.png)
**=>Enter your pin Code**   
![STEPWP_Login2_annoted](https://user-images.githubusercontent.com/124599387/219347713-ae3ab91e-092d-4222-9241-91898eb10907.png)

* Navigate to "Access SDK" section and select the "Get SDK for Android" option.  
![image](readme-images/2.png)

* In the "SDK documentation for Android" page , click on the "Download SDK" button.  
![image](readme-images/4.png)
**=> Your browser downloads the archive "VodafoneV2X_Android_SDK.rar"**  
* Open the archive, extract the V2X-SDK library file called "Android V2X SDK Vabc-release.aar" and save it on your laptop. 

### Download the Android V2X-SDK documentation

* In the "SDK documentation for Android" page , click on the "Download documentation" button.  
![image](readme-images/4.png)
**=> Your browser downloads the archive "Android_V2X_SDK_Documentation_Vabc.zip"**    
* Open the archive and save on your laptop the Javadoc and the user Guide

### Retrieve the Application_ID & Application_Token, you will need these 2 parameters later to configure the HelloV2XWorld app.

* Go the [Test application](https://de-he.step.vodafone.com/applications) Test Application Web page.
* Click on the Test Application button
  
![TestApplicationButton](https://github.com/Vodafone/HelloV2XWorld-Android/assets/122537808/f0ca89ed-241c-472c-a4f4-3b5ab5d1b856)

* Click on the Next Button two times
* Select the Application ID option and Copy the ApplicationID and save it as ApplicationID
  
![TestAppID](https://github.com/Vodafone/HelloV2XWorld-Android/assets/122537808/2945bf3d-e6fe-4b33-8a58-845a612256f8)

* Select the SDK Key option , copy the SDK Key and save it as ApplicationToken
  
![TestAppToken](https://github.com/Vodafone/HelloV2XWorld-Android/assets/122537808/c4ac1a74-ca59-4faa-82c5-2eae6cd4f6f1)
  
  
### Clone the HelloV2XWorld Application

* Open Android Studio.
* Select "File" then "New" then "Project from Version Control".  
![image](https://user-images.githubusercontent.com/122537808/217485720-2b32bc59-68a3-4c27-892f-9c72a7f12dc2.png)  
**=> A window called "Get from Version Control" appears.**

* In the field version Control select the option "Git".
* In the field URL set "https://github.com/Vodafone/HelloV2XWorld-Android.git".
* Click on the "Clone" button.  
![image](https://user-images.githubusercontent.com/122537808/217486544-d0d76732-31df-41be-ba32-61d5987ac47e.png)

* Wait until the completion of this operation.

### Import the V2X-SDK library in your project

* Copy the "v2xsdk-release.aar" file to the app/libs folder of your project.  
![image](https://user-images.githubusercontent.com/122537808/217487130-914d2ef5-ea17-4cbd-8fbe-d4795c9401f1.png)

### Set the Application_ID & Application_Token in your project

![AStudio](https://github.com/Vodafone/HelloV2XWorld-Android/assets/122537808/bd9c3a7d-6f55-4ca8-89c1-0af995a31318)


### Build the HelloV2XWorld application

* Click on "Build" then "Make Project" (or use the shortcut "Ctrl+F9").  
![image](https://user-images.githubusercontent.com/122537808/217487296-9948d446-380f-41c3-9549-1029936050a8.png)


### Run the HelloV2XWorld application on the virtual Android device

**Please, be aware that the HelloV2XWorld application needs to access to your location , it's why it requests the Location Permission.**  

* Select your virtual Device in Android Studio.  
![image](https://user-images.githubusercontent.com/122537808/217487784-c74199e4-65a5-4a39-ab67-2b28fcfa8062.png)  
* Press the RUN button.  
![image](https://user-images.githubusercontent.com/122537808/217487940-1506341e-c14e-4896-8646-40fcd24cd8ca.png)

* Wait until the "Emulator - HelloV2XWorld"  windows appears.  
![image](https://user-images.githubusercontent.com/122537808/217492436-9fa36727-21c0-4394-b65a-fe974c3517f3.png)

* Read the Term and Conditions, Scroll down and click on the "Accept" button.  
![image](https://user-images.githubusercontent.com/122537808/217490204-3ef41aac-bb5d-44b3-8949-1dc00d234979.png)  
* Provide the Location Permission to the HelloV2XWorld application  
![image](https://user-images.githubusercontent.com/122537808/217511629-7be9c480-d669-4695-bc4e-d69b83c7d563.png)

* A map is displayed centered on your current location  
![image](https://user-images.githubusercontent.com/122537808/217490887-29a8f43e-abe4-4a7f-a179-c152ee8cdc9c.png)

* The blue icon represents yourself
* The red icons represent the other road users close to you   
![image](https://user-images.githubusercontent.com/122537808/217504782-04ae9d13-63b0-448b-8b66-72ececb24650.png)

* When clicking on the blue icon, app displays you ITS information: StationID, StationType, Speed, Heading  
![image](https://user-images.githubusercontent.com/122537808/217570016-ea6b3c81-3e69-498d-bb5b-e69bcdf4f783.png)

* When clicking on any red icon, app displays you the CAM information: StationID, StationType, Speed, Heading   
![image](https://user-images.githubusercontent.com/122537808/217569716-3a8175b0-5e30-4a42-9ade-4800a537dd68.png)

* You can change your station type by accessing the setting page
![App Parameters2](readme-images/5.png)
   


## Version History

* 1.0.4.0
    * Initial Release

* 1.0.7.4
    * New V2X SDK V2 ETSI R2

## License

This project is licensed under the MIT License - see the LICENSE.md file for details

## Dependencies

* org.osmdroid:osmdroid-android:6.1.14 / Apache Software License Version 2.0
* com.github.barteksc:android-pdf-viewer:3.2.0-beta.1 / Apache 2.0
* com.jakewharton:process-phoenix:2.1.2	/ Apache 2.0
* pub.devrel:easypermissions:3.0.0 / Apache 2.0
* androidx.appcompat:appcompat:1.6.1 / Apache 2.0
* com.google.android.material:material:1.8.0 / Apache 2.0
* androidx.constraintlayout:constraintlayout:2.1.4	/ Apache 2.0
* com.jakewharton.timber:timber:5.0.1	/ Apache 2.0
* junit:junit:4.13.2	/ Eclipse Public License 1.0
* androidx.test.ext:junit:1.1.5	/ Apache 2.0
* androidx.test.espresso:espresso-core:3.5.1 / Apache 2.0

