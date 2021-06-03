package ir.avabot.tts

class Analyzer(that: AvaBotService, seq: CharSequence?) : ArrayList<Analyzer.Word>() {
    private val chars = Chars()
    private var curWord = ""
    private var curType: String? = null

    init {
        if (seq != null) {
            separate(seq.toString().trim().replace("\n", " "))
            audios()
        }
    }

    private fun separate(s: String) {
        for (ch in s) when (val wh = whiChar(ch)) {
            Chars.SPACE -> endWord()
            Chars.ARABIC, Chars.LATIN, Chars.CYRILLIC -> {
                if (wh != curType) endWord()
                curType = wh
                appendChar(ch)
            }
            Chars.ARABI_NUMBER, Chars.LATIN_NUMBER -> {
                curType = Chars.NUMERIC
                appendChar(ch)
            }
            else -> {
                curType = Chars.ELSE
                appendChar(ch)
            }
        }
        endWord()
    }

    private fun whiChar(ch: Char): String? {
        if (ch == ' ') return Chars.SPACE
        for (k in chars.keys) if (ch in chars[k]!!) return k
        return null
    }

    private fun endWord() {
        if (curWord != "" && curType != null)
            this.add(Word(curWord, this.size, curType!!))
        curWord = ""
        curType = null
    }

    private fun appendChar(ch: Char) {
        curWord += ch
    }

    private fun audios() {
        for (w in this) {
        }
    }


    class Word(val s: String, val index: Int, val tag: String, var audio: Int? = null)
}
