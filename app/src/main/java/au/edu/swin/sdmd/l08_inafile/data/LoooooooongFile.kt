package au.edu.swin.sdmd.l08_inafile.data

import android.content.Context

object LoooooooongFile {
    private const val filename = "loooooong_file"

    fun appendInput(context: Context, s: String) {
        context.openFileOutput(filename, Context.MODE_APPEND).use {
            it.write(s.toByteArray())
            it.write("\n".toByteArray())
        }
    }

    fun deleteFile(context: Context) {
        context.deleteFile(filename)
    }
}