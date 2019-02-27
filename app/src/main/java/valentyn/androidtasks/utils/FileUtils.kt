package valentyn.androidtasks.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.widget.Toast
import java.io.File
import java.io.IOException
import java.text.DateFormat

object FileUtils {

    private fun createImageFile(context: Context): File = File.createTempFile(
        "JPEG_" + DateFormat.getDateTimeInstance() + "_",
        ".jpg",
        context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    )

    fun getPhotoURI(context: Context): Uri? {
        var photoFile: File? = null
        var photoURI: Uri? = null
        try {
            photoFile = FileUtils.createImageFile(context)
        } catch (ex: IOException) {
            Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show()
        }
        if (photoFile != null) {
            photoURI = FileProvider.getUriForFile(
                context,
                "valentyn.androidtasks.android.provider",
                photoFile
            )
        }
        return photoURI
    }
}