package valentyn.androidtasks.utils

import android.content.Context

import android.net.Uri
import android.os.Environment
import java.io.File
import android.support.v4.content.FileProvider.getUriForFile
import java.text.SimpleDateFormat
import java.util.*

object FileUtils {

    val dateFormat = "yyyyMMdd_HHmmss"

    fun getURI(context: Context): Uri? {
        val newFile = File(
            context.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
            "image" + SimpleDateFormat(dateFormat).format(getCurrentTime()) + ".jpg"
        )
        return getUriForFile(context, "valentyn.androidtasks.fileprovider", newFile)
    }

    fun getTempFile(context: Context) = File(
        context.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
        "image" + SimpleDateFormat(dateFormat).format(getCurrentTime()) + ".jpg"
    )

    fun getUri(context: Context, file: File): Uri? = getUriForFile(context, "valentyn.androidtasks.fileprovider", file)

    fun getCurrentTime(): Date = Calendar.getInstance().time
}