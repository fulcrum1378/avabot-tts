package ir.avabot.tts

class Analyzer(s: String) : ArrayList<Analyzer.Sentence>() {
    private val chars = Chars()
    private var seudo: ArrayList<String> = ArrayList(s.split(" "))
    private var swPoint = 0

    init {
        readSentence()
    }

    private fun readSentence() { // Repeats for each sentence
        val sen = Sentence()
        sewLoop@ while (swPoint < seudo.size) { // Repeats for each seudo-word
            val s = seudo[swPoint]
            var senEnded = false
            var chPoint = 0
            val w = Word()
            charLoop@ while (chPoint < s.length) { // Repeats for each CHAR
                when (script(s[chPoint])) {
                    Script.MISC_MARK -> {
                        w.pause = (1).toByte()
                        chPoint++
                        continue@charLoop
                    }
                    Script.CLOSER -> {
                        w.pause = (2).toByte()
                        sen.tone = when (s[chPoint]) {
                            chars[Script.CLOSER]!![1] -> Tone.EXCLAMATORY
                            chars[Script.CLOSER]!![2], chars[Script.CLOSER]!![3] -> {
                                w.stress = true
                                Tone.INTERROGATIVE
                            }
                            else -> Tone.NORMAL
                        }
                        chPoint++
                        senEnded = true
                        break@charLoop
                    }
                }
                if (w.script == null) {
                    w.script = script(s[chPoint])
                    w.text += s[chPoint]
                    chPoint++
                } else if (script(s[chPoint]) == w.script && w.pause == (0).toByte()) {
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
            swPoint++
            if (senEnded) break
        }
        //sen.words.last().pause = (sen.words.last().pause + 1).toByte()
        add(sen)
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
        var pause: Byte = 0, // 0: NONE, 1: SHORT, 2: MEDIUM, 3: LONG
        var stress: Boolean = false
    )

    enum class Script {
        ARABIC, LATIN, CYRILLIC, UNKNOWN, NUMERIC, CLOSER, MISC_MARK, SPECIAL_MARK
    }

    enum class Tone { NORMAL, INTERROGATIVE, EXCLAMATORY }
}
