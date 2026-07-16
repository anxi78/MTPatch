package top.nkbe.mtpatch.share;

public class Constants {

    final static public String CONFIG_ASSET_PATH = "assets/mtpatch/config.json";
    final static public String LOADER_DEX_ASSET_PATH = "assets/mtpatch/loader.bin";
    final static public String META_LOADER_DEX_ASSET_PATH = "assets/mtpatch/metaloader.dex";
    final static public String PROVIDER_DEX_ASSET_PATH = "assets/mtpatch/mtprovider.dex";
    final static public String ORIGINAL_APK_ASSET_PATH = "assets/mtpatch/origin.apk";
    final static public String EMBEDDED_MODULES_ASSET_PATH = "assets/mtpatch/modules/";

    final static public String PATCH_FILE_SUFFIX = "-mtpatched.apk";
    final static public String PROXY_APP_COMPONENT_FACTORY = "top.nkbe.mtpatch.metaloader.LSPAppComponentFactoryStub";
    final static public String MANAGER_PACKAGE_NAME = "top.nkbe.mtpatch";
    final static public String REAL_GMS_PACKAGE_NAME = "com.google.android.gms";
    final static public int MIN_ROLLING_VERSION_CODE = 633;

    public static final int SIGBYPASS_NONE = 0;
    public static final int SIGBYPASS_BASIC = 1;
    public static final int SIGBYPASS_HIGH = 2;
    public static final int SIGBYPASS_EXTREME = 3;
    public static final int SIGBYPASS_SECCOMP = 4;
}
