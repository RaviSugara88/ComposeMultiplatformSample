plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("org.jetbrains.compose")
    kotlin("plugin.serialization") version "1.8.21"
}

kotlin {
    android()

    iosX64()
    iosArm64()
    iosSimulatorArm64()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = true
        }
        extraSpecAttributes["resources"] = "['src/commonMain/resources/**', 'src/iosMain/resources/**']"

    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
                implementation("media.kamel:kamel-image:0.6.0")
                // MVVM Staff
                api("dev.icerock.moko:mvvm-core:0.16.1")
                api("dev.icerock.moko:mvvm-compose:0.16.1")
                // Ktor Staff
                implementation("io.ktor:ktor-client-core:2.3.1")
                implementation("io.ktor:ktor-client-content-negotiation:2.3.1")
                implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.1")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
                implementation("io.ktor:ktor-client-logging:2.2.3")


            }
        }

        val androidMain by getting {
            dependencies {
                api("androidx.activity:activity-compose:1.6.1")
                api("androidx.appcompat:appcompat:1.6.1")
                api("androidx.core:core-ktx:1.9.0")
                implementation("io.ktor:ktor-client-android:2.3.1")
            }
        }

        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)

            dependencies {
                implementation("io.ktor:ktor-client-darwin:2.3.1")
            }
        }

    }
}

android {
    namespace = "com.example.composemultiplateformex"
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    compileSdk = 33
    defaultConfig {
        minSdk = 26
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlin {
        jvmToolchain(11)
    }

}