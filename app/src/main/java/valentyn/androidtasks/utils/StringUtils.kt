package valentyn.androidtasks.utils

object StringUtils {

    const val forbiddenCharacterString: String = "+_#@"

    fun haveForbiddenCharacter(c:Char):Boolean = forbiddenCharacterString.indexOf(c) > 0

}