package academy.nouri.mvp.utils

import android.content.Context
import android.os.Environment
import androidx.core.content.ContextCompat
import java.util.*

object Utility {
    fun getRootDirPath(context: Context): String {
        return if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()) {
            val file = ContextCompat.getExternalFilesDirs(context.applicationContext, null)[0]
            file.absolutePath
        } else {
            context.applicationContext.filesDir.absolutePath
        }
    }

    private fun getByteToMb(byte: Long): String {
        return String.format(Locale.ENGLISH, "%.2fMB", byte / (1024.00 * 1024.00))
    }

    fun getProgressDisplay(current: Long, total: Long): String {
        return getByteToMb(current) + " / " + getByteToMb(total)
    }
}