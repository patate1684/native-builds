@file:Suppress("UnstableApiUsage")

package com.ensody.nativebuilds

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.jvm.tasks.Jar
import org.gradle.kotlin.dsl.support.unzipTo
import org.gradle.kotlin.dsl.support.useToRun
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.CInteropProcess
import java.io.File
import java.util.zip.ZipFile

public class NativeBuildsPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            val nativeBuild = configurations.create("nativeBuild")
            tasks.register("unzipNativeBuilds") {
                outputs.dir(layout.buildDirectory.dir("nativebuilds"))
                // Use lenientConfiguration so missing platform artifacts (e.g. android when
                // only mingwX64 has been built locally) don't fail the build.
                inputs.files(
                    provider {
                        nativeBuild.resolvedConfiguration.lenientConfiguration.artifacts.map { it.file }
                    }
                )

                doLast {
                    outputs.files.singleFile.deleteRecursively()
                    nativeBuild.resolvedConfiguration.lenientConfiguration.artifacts.forEach { artifact ->
                        val name = artifact.moduleVersion.id.name
                        val outputDir = File(outputs.files.singleFile, name)
                        unzipTo(outputDir, artifact.file)
                        val classesJar = File(outputDir, "classes.jar")
                        if (artifact.file.extension == "aar" && classesJar.exists()) {
                            val metadataPath = "META-INF/nativebuild.json"
                            ZipFile(classesJar).useToRun {
                                getEntry(metadataPath)?.let { getInputStream(it).readBytes() }
                            }?.also {
                                File(outputDir, metadataPath).writeBytes(it)
                            }
                        }
                    }
                }
            }
            tasks.withType<CInteropProcess> {
                dependsOn("unzipNativeBuilds")
            }
            tasks.withType<Jar> {
                dependsOn("unzipNativeBuilds")
            }
            // Android
            tasks.findByName("preBuild")?.apply {
                dependsOn("unzipNativeBuilds")
            }
        }
    }
}
