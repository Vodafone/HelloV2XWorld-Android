# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
-keepattributes Exceptions
-keepattributes MethodParameters
-keepattributes Signature,InnerClasses,SourceFile,LineNumberTable

-keepclasseswithmembers class io.netty.** {
    *;
}

-keepnames class io.netty.** {
    *;
}

-keepclasseswithmembers class org.jctools.queues.** {
    *;
}

-keepnames class org.jctools.queues.** {
    *;
}

-keepclassmembers class * extends java.lang.Enum {
    <fields>;
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class org.eclipse.paho.** { *; }

-keep class sun.misc.Unsafe { *; }

#SDK side
-keep public class com.unigone.** {
  public protected *;
}

-keep public class com.vodafone.v2xsdk4javav2.facade.** {
  public protected *;
}

-keep public class com.vodafone.v2xsdk4javav2.core.messages.** {
  public protected *;
}
#For PDF view
-keep class com.shockwave.**