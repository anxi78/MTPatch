import java.util.Locale

val defaultManagerPackageName: String by rootProject.extra
val apiCode: Int by rootProject.extra
val verCode: Int by rootProject.extra
val verName: String by rootProject.extra
val coreVerCode: Int by rootProject.extra
val coreVerName: String by rootProject.extra
val miuixVersion = mtpatch.versions.miuix.get()

plugins {
    alias(libs.plugins.agp.app)
    alias(mtpatch.plugins.compose.compiler)
    alias(mtpatch.plugins.google.devtools.ksp)
    alias(mtpatch.plugins.rikka.tools.refine)
    alias(mtpatch.plugins.kotlin.android)
    id("kotlin-parcelize")
}

android {
    defaultConfig {
        applicationId = defaultManagerPackageName
    }

    packaging {
        jniLibs {
            excludes += "lib/*/libandroidx.graphics.path.so"
            excludes += "lib/*/libdatastore_shared_counter.so"
        }
        resources {
            excludes += "kotlin/**"
            excludes += "META-INF/androidx*"
            excludes += "META-INF/androidx/**"
            excludes += "DebugProbesKt.bin"
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true      // 启用 R8/ProGuard 进行代码压缩、优化和混淆。
            isShrinkResources = true    // 启用资源缩减，移除未被引用的资源文件。
            isDebuggable = false        // 发布版本禁止调试。
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        all {
            sourceSets[name].assets.srcDirs(rootProject.projectDir.resolve("out/assets/$name"))
        }
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15"
    }

    namespace = "top.nkbe.mtpatch"

    applicationVariants.all {
        kotlin.sourceSets {
            getByName(name) {
                kotlin.srcDir("build/generated/ksp/$name/kotlin")
            }
        }
    }
}

afterEvaluate {
    android.applicationVariants.forEach { variant ->
        val variantLowered = variant.name.lowercase()
        val variantCapped = variant.name.replaceFirstChar { it.uppercase() }

        val copyAssetsTaskProvider = tasks.register<Copy>("copy${variantCapped}Assets") {
            dependsOn(":meta-loader:copy$variantCapped")
            dependsOn(":patch-loader:copy$variantCapped")

            val targetDir = layout.buildDirectory.dir("intermediates/assets/$variantLowered/merge${variantCapped}Assets")
            doFirst {
                delete(targetDir.map { it.file("mtpatch/loader.dex") })
            }
            into(targetDir)

            from("${rootProject.projectDir}/out/assets/${variant.name}")
        }

        tasks.named("merge${variantCapped}Assets").configure {
            dependsOn(copyAssetsTaskProvider)
        }

        tasks.register<Copy>("build$variantCapped") {
            dependsOn("assemble$variantCapped")
            from(variant.outputs.map { it.outputFile })
            into("${rootProject.projectDir}/out/$variantLowered")
            rename(".*.apk", "MTPatch-v$verName-$verCode-$variantLowered.apk")
        }
    }
}

dependencies {
    implementation(projects.patch)
    implementation(projects.share.android)
    implementation(projects.share.java)
    implementation("vector:daemon-service")

    implementation(platform(mtpatch.androidx.compose.bom))
    implementation(mtpatch.androidx.activity.compose)
    implementation(mtpatch.androidx.compose.material.icons.extended)
    implementation(mtpatch.androidx.compose.material3)
    implementation(mtpatch.androidx.compose.ui)
    implementation(mtpatch.androidx.compose.ui.tooling.preview)
    implementation(mtpatch.androidx.core.ktx)
    implementation(libs.material)
    implementation(mtpatch.androidx.datastore.preferences)
    implementation(mtpatch.coil.compose)
    implementation(libs.gson)
    implementation(mtpatch.androidx.lifecycle.viewmodel.compose)
    implementation(mtpatch.androidx.navigation3.runtime)
    implementation(mtpatch.androidx.navigation3.ui)
    implementation(libs.androidx.preference)
    implementation(mtpatch.androidx.room.ktx)
    implementation(mtpatch.androidx.room.runtime)
    implementation("com.squareup.okhttp3:okhttp:5.3.2")

    implementation(libs.material)
    implementation(libs.gson)
    implementation(mtpatch.rikka.shizuku.api)
    implementation(mtpatch.rikka.shizuku.provider)
    implementation(mtpatch.rikka.refine)
    //implementation(mtpatch.raamcosta.compose.destinations)
    implementation(libs.appiconloader)
    implementation(libs.hiddenapibypass)

    // MiuiX & Haze
    implementation(mtpatch.haze)
    implementation(mtpatch.hazeBlur)
    implementation(mtpatch.backdrop)
    implementation("top.yukonga.miuix.kmp:miuix-ui:$miuixVersion")
    implementation("top.yukonga.miuix.kmp:miuix-preference:$miuixVersion")
    implementation("top.yukonga.miuix.kmp:miuix-icons:$miuixVersion")
    implementation(mtpatch.androidx.webkit)


    annotationProcessor(mtpatch.androidx.room.compiler)
    compileOnly(mtpatch.rikka.hidden.stub)
    ksp(mtpatch.androidx.room.compiler)
    //ksp(mtpatch.raamcosta.compose.destinations.ksp)

    debugImplementation(mtpatch.androidx.compose.ui.tooling)
    debugImplementation(mtpatch.androidx.customview)
    debugImplementation(mtpatch.androidx.customview.poolingcontainer)
}
