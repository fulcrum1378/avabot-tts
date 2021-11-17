package ir.avabot.tts

import ir.avabot.tts.Analyzer.Script
import ir.avabot.tts.Analyzer.Script.*

class Chars : HashMap<Script, CharArray>() {
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
        this[NUMERIC] = charArrayOf(
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '۰', '۱', '۲', '۳', '۴', '۵', '۶', '۷', '۸', '۹'
        )
        this[CLOSER] = charArrayOf('.', '!', '?', '؟')
        this[MISC_MARK] = charArrayOf(
            ',', '_', '\\', '\"', '\'', '[', ']', '{', '}', '^', '(', ')', '،', '؛', '»', '«'
        )
        this[SPECIAL_MARK] = charArrayOf(
            '-', '+', '=', '/', '@', '#', '$', '%', '٪'
        )
    }
}
