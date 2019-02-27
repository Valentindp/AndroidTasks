package valentyn.androidtasks.utils

import android.content.Context

import android.net.Uri
import android.os.Environment
import java.io.File
import java.text.DateFormat
import android.support.v4.content.FileProvider.getUriForFile


object FileUtils {

    fun getPhotoURI(context: Context): Uri? {
        val newFile = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "image" + DateFormat.getDateTimeInstance() + ".jpg")
        return getUriForFile(context, "valentyn.androidtasks.fileprovider", newFile)
    }
}