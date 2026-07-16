-dontobfuscate
-keep class com.beust.jcommander.** { *; }
-keep class top.nkbe.mtpatch.Patcher$Options { *; }
-keep class top.nkbe.mtpatch.share.LSPConfig { *; }
-keep class top.nkbe.mtpatch.share.PatchConfig { *; }
-keep class org.lsposed.lspd.nativebridge.** { *; }
-keep class top.nkbe.mtpatch.loader.SigBypass { *; }
-keepclassmembers class org.lsposed.patch.MTPatch {
    private <fields>;
}
-dontwarn com.google.auto.value.AutoValue$Builder
-dontwarn com.google.auto.value.AutoValue
