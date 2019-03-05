package valentyn.androidtasks.utils


object StringUtils {

    const val FORBIDDEN_CHARACTER: String = "+_#@"

    fun haveForbiddenCharacter(c:Char):Boolean = FORBIDDEN_CHARACTER.indexOf(c) > 0

}