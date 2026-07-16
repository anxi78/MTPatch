-assumenosideeffects class kotlin.jvm.internal.Intrinsics {
 public static void check*(...);
 public static void throw*(...);
}
-assumenosideeffects class java.util.Objects {
    public static ** requireNonNull(...);
}
-assumenosideeffects public class kotlin.coroutines.jvm.internal.DebugMetadataKt {
   private static ** getDebugMetadataAnnotation(...) return null;
}
-keepattributes *Annotation*,Signature,InnerClasses,EnclosingMethod
-keep class com.beust.jcommander.** { *; }
-keep interface com.beust.jcommander.** { *; }
-keep class top.nkbe.mtpatch.patch.MTPatch { *; }
-keepclassmembers class top.nkbe.mtpatch.patch.MTPatch {
    @com.beust.jcommander.Parameter <fields>;
}

-keepclassmembers class top.nkbe.mtpatch.database.dao.** { *; }
-keep class top.nkbe.mtpatch.database.entity.** { *; }
-keep class top.nkbe.mtpatch.manager.ConfigProvider { *; }
-keep class top.nkbe.mtpatch.Patcher$Options { *; }
-keep class top.nkbe.mtpatch.share.LSPConfig { *; }
-keep class top.nkbe.mtpatch.share.PatchConfig { *; }
-keep class org.lsposed.lspd.nativebridge.** { *; }
-keep class top.nkbe.mtpatch.loader.SigBypass { *; }
-dontwarn com.google.auto.value.AutoValue$Builder
-dontwarn com.google.auto.value.AutoValue
-dontwarn com.squareup.moshi.**
-dontwarn retrofit2.**
-dontwarn okio.**