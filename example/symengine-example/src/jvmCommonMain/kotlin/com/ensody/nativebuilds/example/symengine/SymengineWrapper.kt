package com.ensody.nativebuilds.example.symengine

import com.ensody.nativebuilds.loader.NativeBuildsJvmLib
import com.ensody.nativebuilds.loader.NativeBuildsJvmLoader
import com.ensody.nativebuilds.symengine.NativeBuildsJvmLibSymengine

internal object SymengineWrapper : NativeBuildsJvmLib {
    override val packageName: String = "symengine"
    override val libName: String = "symengine-jni"
    override val platformFileName: Map<String, String> = mapOf(
        "linuxArm64" to "libsymengine-jni.so",
        "linuxX64" to "libsymengine-jni.so",
        "macosArm64" to "libsymengine-jni.dylib",
        "macosX64" to "libsymengine-jni.dylib",
        "mingwX64" to "libsymengine-jni.dll",
    )

    init {
        NativeBuildsJvmLoader.load(NativeBuildsJvmLibSymengine)
        NativeBuildsJvmLoader.load(this)
    }

    external fun getSymengineVersion(): String
}
