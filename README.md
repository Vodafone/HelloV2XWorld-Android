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
![STEPWP_Signup_Annoted](https://user-images.githubusercontent.com/122537808/217470142-8de75aad-462e-42f2-b70e-2535766ce3fc.png)

* Go to the [STEP Web portal](https://step.vodafone.com/) webpage and login.  
**=>Enter your credentials**   
![STEPWP_Login1_Annoted](https://user-images.githubusercontent.com/122537808/217473639-455f00ff-6ed8-44bb-a2b9-a67430e4e8c0.png)  
**=>Enter your pin Code**   
![STEPWP_Login2_annoted](https://user-images.githubusercontent.com/122537808/217473122-e0c54fc5-20b3-4576-9010-de3916b09d8e.png)  

* Navigate to the page "Documentation" and select the "SDK documentation for Android" option.  
![image](https://user-images.githubusercontent.com/122537808/217475735-292c0902-a0f5-41d3-9987-875069a92279.png)

* In the "SDK documentation for Android" page , click on the "Download SDK" button.  
![image](https://user-images.githubusercontent.com/122537808/217475876-45c8af5d-c11e-42f9-93cb-eb76ecfa7e44.png)  
**=> Your browser downloads the archive "VodafoneV2X_Android_SDK.rar"**  
* Open the archive, extract the V2X-SDK library file called "v2xsdk-release.aar" and save it on your laptop. 

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


### Build the HelloV2XWorld application

* Click on "Build" then "Make Project" (or use the shortcut "Ctrl+F9").  
![image](https://user-images.githubusercontent.com/122537808/217487296-9948d446-380f-41c3-9549-1029936050a8.png)


### Run the HelloV2XWorld application on the virtual Android device

* Select your virtual Device in Android Studio.  
![image](https://user-images.githubusercontent.com/122537808/217487784-c74199e4-65a5-4a39-ab67-2b28fcfa8062.png)  
* Press the RUN button.  
![image](https://user-images.githubusercontent.com/122537808/217487940-1506341e-c14e-4896-8646-40fcd24cd8ca.png)

* Wait until the "Emulator - HelloV2XWorld"  windows appears.  
![image](https://user-images.githubusercontent.com/122537808/217492436-9fa36727-21c0-4394-b65a-fe974c3517f3.png)

* Read the Term and Conditions, Scroll down and click on the "Accept" button.  
![image](https://user-images.githubusercontent.com/122537808/217490204-3ef41aac-bb5d-44b3-8949-1dc00d234979.png)  
* Provide the Location Permission to the HelloV2XWorld application  
![image](https://user-images.githubusercontent.com/122537808/217490588-ad1ed671-03dc-4e13-ab3d-e99f80b73af3.png)

* A map is displayed centered on your current location  
![image](https://user-images.githubusercontent.com/122537808/217490887-29a8f43e-abe4-4a7f-a179-c152ee8cdc9c.png)

* The blue icon represents yourself
* The red icons represent the other road users close to you   
![image](https://user-images.githubusercontent.com/122537808/217504782-04ae9d13-63b0-448b-8b66-72ececb24650.png)

* When clicking on the blue icon, app displays you ITS information: StationID, StationType, Speed, Heading    
![image](https://user-images.githubusercontent.com/122537808/217491129-8c3f59a4-5f91-465a-a6a0-2f2b3e1c7b79.png)

* You can change your station type by accessing the setting page  
![image](https://user-images.githubusercontent.com/122537808/217491318-d9fbc51d-af98-4844-a8ae-f74883cc86f7.png)  


## Version History

* 1.02
    * Initial Release

## License

This project is licensed under the MIT License - see the LICENSE.md file for details

