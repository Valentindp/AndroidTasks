package valentyn.androidtasks.utils

object StringUtils {

    const val stringLengthMax: Int = 20
    const val forbiddenCharacterString: String = "+_#@"

    fun stringLengthCheck(string: String): Boolean = string.length > stringLengthMax

    fun forbiddenCharacterString(string: String): Boolean =
        Regex(pattern = "[^\\$forbiddenCharacterString]").containsMatchIn(input = string)

}