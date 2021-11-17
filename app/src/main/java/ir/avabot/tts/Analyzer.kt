package ir.avabot.tts

class Analyzer(s: String) {
    val result = ArrayList<Sentence>()

    init {
        readSentence()
    }

    private val chars = Chars()
    private var seudo: ArrayList<String> = ArrayList(s.split(" "))
    private var swPoint = 0
    private fun readSentence() { // Repeats for each sentence
        val sen = Sentence()
        while (swPoint < seudo.size) { // Repeats for each seudo-word
            val s = seudo[swPoint]
            var chPoint = 0
            val w = Word()
            while (chPoint < s.length) { // Repeats for each CHAR
                when (script(s[chPoint])) {
                    Script.MISC_MARK -> {
                        w.pause = Pause.SHORT
                        chPoint++
                        continue
                    }
                    Script.CLOSER -> {
                        w.pause = Pause.MEDIUM
                        sen.tone = when (s[chPoint]) {
                            chars[Script.CLOSER]!![1] -> Tone.EXCLAMATORY
                            chars[Script.CLOSER]!![2], chars[Script.CLOSER]!![3] -> Tone.INTERROGATIVE
                            else -> Tone.NORMAL
                        }
                        chPoint++
                        break
                    }
                }
                if (w.script == null) {
                    w.script = script(s[chPoint])
                    w.text += s[chPoint]
                    chPoint++
                } else if (script(s[chPoint]) == w.script && w.pause == Pause.NONE) {
                    w.text += s[chPoint]
                    chPoint++
                } else break
            }
            sen.words.add(w)
            if (chPoint < s.length) { // Repair the seudo-word
                seudo.removeAt(swPoint)
                seudo.add(swPoint, s.substring(chPoint)) // first put the remaining
                seudo.add(swPoint, s.substring(0, chPoint)) // then those which you just read
            }
            val last = sen.words.last()
            if (last.script == Script.CLOSER) break
            swPoint++
        }
        result.add(sen)
        if (seudo.size != swPoint) readSentence()
    }

    private fun script(c: Char): Script = when (c) {
        in chars[Script.ARABIC]!! -> Script.ARABIC
        in chars[Script.LATIN]!! -> Script.LATIN
        //in chars[Script.CYRILLIC]!! -> Script.CYRILLIC
        in chars[Script.NUMERIC]!! -> Script.NUMERIC
        in chars[Script.CLOSER]!! -> Script.CLOSER
        in chars[Script.MISC_MARK]!! -> Script.MISC_MARK
        in chars[Script.SPECIAL_MARK]!! -> Script.SPECIAL_MARK
        else -> Script.UNKNOWN
    }


    data class Sentence(
        var words: ArrayList<Word> = ArrayList(),
        var tone: Tone? = null
    )

    data class Word(
        var text: String = "",
        var script: Script? = null,
        var pause: Pause = Pause.NONE,
        var stressed: Boolean = false
    )

    enum class Script {
        ARABIC, LATIN, CYRILLIC, UNKNOWN, NUMERIC, CLOSER, MISC_MARK, SPECIAL_MARK
    }

    enum class Pause(val ms: Long) {
        NONE(10), SHORT(250), MEDIUM(500), LONG(1000)
    }

    enum class Tone { NORMAL, INTERROGATIVE, EXCLAMATORY }
}
