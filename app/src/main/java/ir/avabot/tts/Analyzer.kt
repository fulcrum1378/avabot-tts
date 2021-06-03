package ir.avabot.tts

class Analyzer(seq: CharSequence?, that: AvaBotService) : ArrayList<Analyzer.Word>() {
    private val chars = Chars()
    private var curWord = ""
    private var curType: String? = null
    private var curIndex = 0

    init {
        if (seq != null) {
            separate(seq.toString().trim().replace("\n", " "))
            audios(that)
        }
    }

    private fun separate(s: String) {
        for (ch in s) when (val wh = whiChar(ch)) {
            Chars.SPACE -> endWord(true)
            Chars.ARABIC, Chars.LATIN, Chars.CYRILLIC -> {
                if (wh != curType) endWord()
                curType = wh
                appendChar(ch)
            }
            Chars.ARABI_NUMBER, Chars.LATIN_NUMBER -> {
                if (curType != Chars.NUMERIC) endWord()
                curType = Chars.NUMERIC
                appendChar(ch)
            }
            else -> {
                if (curType != Chars.ELSE) endWord()
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

    private fun endWord(bySpace: Boolean = false) {
        // 2+ spaces won't be counted
        if (curWord == "" || curType == null) return
        if (bySpace) curIndex++
        this.add(Word(curWord, curIndex, curType!!))
        curWord = ""
        curType = null
    }

    private fun appendChar(ch: Char) {
        curWord += ch
    }

    private fun audios(that: AvaBotService) {
        for (w in this) {
        }
    }


    class Word(
        val s: String,
        val index: Int, // according to white spaces: ' '
        val tag: String,
        var audio: Int? = null
    )
}
