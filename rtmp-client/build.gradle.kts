import com.android.tools.r8.internal.fr

plugins {
    alias(libs.plugins.android.library)
    id("maven-publish")
}
android {
    namespace = "io.antmedia.rtmp_client"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
        externalNativeBuild {
            cmake {
                cppFlags("-frtti -fexceptions")
                arguments("-DANDROID_PLATFORM=android-15")
                abiFilters(
                    "x86",
                    "x86_64",
                    "armeabi-v7a",
                    "arm64-v8a"
                ) //, 'armeabi', 'mips', 'mips64'
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    externalNativeBuild {
        cmake {
            path("CMakeLists.txt")
            version = "3.30.5"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("maven") {
                groupId = "com.github.nzdeveloper009"
                artifactId = "rtmp-client"
                version = "1.0.1"
            }
        }
    }
}

/*afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("maven") {
                from (components["release"])
                groupId = "com.github.nzdeveloper009"
                artifactId = "rtmp-client"
                version = "1.0.0"
            }
        }

        repositories {
            mavenLocal()
        }
    }
}*/

dependencies {

}