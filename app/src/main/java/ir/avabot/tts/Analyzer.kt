package ir.avabot.tts

class Analyzer(that: AvaBotService, s: String?) {
    var words: ArrayList<Word>? = ArrayList()

    init {
        if (s == null) words = null
        else separate(s)
    }

    private fun separate(s: String) {
        val raw = s.trim().replace("\n", " ").split(" ")
        val complex = ArrayList<Word>()
        for (w in raw) complex.add(
            when {
                w.all { Chars.FA_IR.contains(it) } -> PersianWord(w)
                w.all {
                    Chars.FA_IR.contains(it) || Chars.EN_SYM.contains(it) || Chars.FA_SYM.contains(it)
                } -> PersianComplex(w)
                w.all { Chars.EN_LOWER.contains(it.lowercaseChar()) } -> EnglishWord(w)
                w.all {
                    Chars.EN_LOWER.contains(it.lowercaseChar()) ||
                            Chars.EN_SYM.contains(it) || Chars.FA_SYM.contains(it)
                } -> EnglishComplex(w)
                w.all { Chars.EN_SYM.contains(it) || Chars.FA_SYM.contains(it) } -> Symbol(w)
                else -> UnknownWord(w)
            }
        )
        process(complex)
    }

    private fun process(complex: ArrayList<Word>) {
        if (complex.size == 1) {
            words!!.add(complex[0]); return; }
        for (w in complex) {
            ///
            words!!.add(w)
        }
    }


    open class Word(val s: String) {
        fun join(another: Word): Word {
            ///////
            return another
        }
    }
    class PersianWord(s: String) : Word(s)
    class EnglishWord(s: String) : Word(s)
    class Symbol(s: String) : Word(s)
    class UnknownWord(s: String) : Word(s)

    open class Complex(s: String) : Word(s)
    class PersianComplex(s: String) : Complex(s)
    class EnglishComplex(s: String) : Complex(s)
}
