
plugins {
    id(Plugins.androidApp)
    id(Plugins.kotlinAndroid)
    id(Plugins.daggerPlug)
    id(Plugins.kapt)
    id(Plugins.navigationPlug)
    id(Plugins.serializerPlug)
}

android {
    namespace = "com.example.taskapplicationnew"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.taskapplicationnew"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompiler
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(Libs.constraintLayout)
    implementation(Libs.coreCtx)
    implementation(Libs.composeActivity)
    implementation(Libs.composeUi)
    implementation(Libs.composeUiToolingPreview)
    implementation(Libs.lifecycleRuntime)
    implementation(Libs.material3)
    implementation(Libs.navigationFragmentKtx)
    implementation(Libs.navigationUiKtx)
    implementation(Libs.coil)
    implementation(Libs.serializerJson)
    implementation(Libs.retrofitBase)
    implementation(Libs.retrofitConvert)
    implementation(Libs.navCompose)
    implementation(Libs.daggerNav)
    implementation(Libs.daggerBase)
    implementation(Libs.roomBase)
    implementation(Libs.roomKtx)
    kapt(Libs.daggerKapt)
    kapt(Libs.roomKapt)
    implementation(Libs.jakeWharton)


    androidTestImplementation(Libs.Test.junit4)
    testImplementation(Libs.Test.junit5)
    testImplementation(Libs.Test.mockk)
    testImplementation(Libs.Test.mockkAgent)
    testImplementation(Libs.Test.coroutineTest)
    testRuntimeOnly(Libs.Test.junit5Engine)
}

//tasks.withType<Test> {
//    useJUnitPlatform()
//    testLogging {
//        events(mutableSetOf("passed", "skipped", "failed"))
//    }
//}


