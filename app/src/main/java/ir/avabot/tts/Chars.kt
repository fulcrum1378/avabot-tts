package ir.avabot.tts

class Chars : HashMap<String, CharArray>() {
    companion object {
        const val SPACE = "SPACE"
        const val ARABIC = "ARABIC" // script not alphabet
        const val LATIN = "LATIN"
        const val CYRILLIC = "CYRILLIC"
        const val NUMERIC = "NUMERIC"
        const val ELSE = "ELSE"

        const val SYM = "SYM"
        const val FA_SYM = "FA_SYM"
        const val LATIN_NUMBER = "LATIN_NUMBER"
        const val ARABI_NUMBER = "ARABI_NUMBER"
    }

    init {
        this[ARABIC] = charArrayOf(
            'ا', 'ب', 'پ', 'ت', 'ث', 'ج', 'چ', 'ح', 'خ', 'د', 'ذ', 'ر', 'ز', 'ژ', 'س', 'ش', 'ص',
            'ض', 'ط', 'ظ', 'ع', 'غ', 'ف', 'ق', 'ک', 'گ', 'ل', 'م', 'ن', 'و', 'ه', 'ی', 'آ', 'ئ',
            'ء'
        )
        this[LATIN] = charArrayOf(
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
        )
        this[CYRILLIC] = charArrayOf()
        this[LATIN_NUMBER] = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')
        this[ARABI_NUMBER] = charArrayOf('۰', '۱', '۲', '۳', '۴', '۵', '۶', '۷', '۸', '۹')
        this[SYM] = charArrayOf(
            '.', ',', '?', '!', '_', '-', '+', '=', '/', '\\', '\"', '\'', '[', ']',
            '{', '}', '@', '#', '$', '%', '^', '(', ')'
        )
        this[FA_SYM] = charArrayOf('،', '؟', '٪', '؛', '»', '«')
    }
}
