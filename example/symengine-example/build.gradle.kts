import com.ensody.nativebuilds.jniNativeBuild
import com.ensody.buildlogic.setupBuildLogic
import com.ensody.nativebuilds.cinterops
import com.ensody.nativebuilds.substituteAndroidNativeLibsInUnitTests

plugins {
    id("com.ensody.build-logic.example")
    id("com.ensody.build-logic.android")
    id("com.ensody.build-logic.kmp")
    alias(libs.plugins.nativebuilds)
}

setupBuildLogic {
    kotlin {
        sourceSets.commonMain.dependencies {
            api(libs.nativebuilds.symengine.libsymengine)
        }

        cinterops(libs.nativebuilds.symengine.headers) {
            definitionFile.set(file("src/nativeMain/cinterop/lib.def"))
        }
    }

    jniNativeBuild(
        name = "libsymengine-jni",
        nativeBuilds = listOf(
            libs.nativebuilds.symengine.headers,
            libs.nativebuilds.symengine.libsymengine,
        ),
    ) {
        inputFiles.from("src/jvmCommonMain/jni")
    }

    substituteAndroidNativeLibsInUnitTests()
}
