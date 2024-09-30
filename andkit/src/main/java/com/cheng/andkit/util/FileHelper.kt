package com.cheng.andkit.util

import com.cheng.andkit.log.Lumberjack
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

object FileHelper {

    fun atomicWriteToFile(targetFile: File, content: String): Boolean {
        val tempFile = File(targetFile.parent, "${targetFile.name}.tmp")
        var fos: FileOutputStream? = null
        return try {
            val data = content.toByteArray(Charsets.UTF_8)
            fos = FileOutputStream(tempFile)
            fos.write(data)
            fos.flush()
            // Ensure the data is written into the disk
            fos.fd.sync()

            tempFile.renameTo(targetFile).also {
                if (!it) {
                    Lumberjack.w("Failed to rename ${tempFile.absolutePath} to ${targetFile.absolutePath}")
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()

            false
        } finally {
            fos?.close()
            if (tempFile.exists()) {
                tempFile.delete()
            }
        }
    }

    fun readFileToString(file: File): String {
        return try {
            file.readText(Charsets.UTF_8)
        } catch (e: IOException) {
            e.printStackTrace()
            ""
        }
    }

}
