package valentyn.androidtasks.utils

object StringUtils {

    const val forbiddenCharacterString: String = "+_#@"

    fun forbiddenCharacterString(string: String): Boolean =
        Regex(pattern = "[^\\$forbiddenCharacterString]").containsMatchIn(input = string)

    fun checkString(text: String): String {
        var errorText = ""
        if (forbiddenCharacterString(text)) errorText =
                "There are forbidden characters in the string [${forbiddenCharacterString}]"
        return errorText
    }

}