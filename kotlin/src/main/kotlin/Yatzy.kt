class Yatzy(private val dice: IntArray) {

    fun fours(): Int {
        return dice.filter { v -> v == 4 }.sum()
    }

    fun fives(): Int {
        return dice.filter { v -> v == 5 }.sum()
    }

    fun sixes(): Int {
        return dice.filter { v -> v == 6 }.sum()
    }

    fun chance(): Int {
        return dice.sum()
    }

    fun yatzy(): Int {
        return if (dice.distinct().size == 1) 50 else 0
    }

    fun ones(): Int {
        return dice.filter { v -> v == 1 }.sum()
    }

    fun twos(): Int {
        return dice.filter { v -> v == 2 }.sum()
    }

    fun threes(): Int {
        return dice.filter { v -> v == 3 }.sum()
    }

    fun score_pair(): Int {
        val counts = IntArray(6)
        dice.forEach { v -> counts[v - 1]++ }
        val indexedValue: IndexedValue<Int>? = counts.withIndex().lastOrNull { iv -> iv.value >= 2 }
        return when {
            indexedValue != null && indexedValue.value >= 2 -> (indexedValue.index + 1) * 2
            else -> 0
        }
    }

    fun two_pair(): Int {
        val counts = IntArray(6)
        val indexedValues: List<IndexedValue<Int>> = counts.withIndex().filter { iv -> iv.value >= 2 }.takeLast(2)
        return when (indexedValues.size) {
            2 -> indexedValues.sumOf { it.index + 1 } * 2
            else -> 0
        }
    }

    companion object {
        fun four_of_a_kind(_1: Int, _2: Int, d3: Int, d4: Int, d5: Int): Int {
            val tallies: IntArray = IntArray(6)
            tallies[_1 - 1]++
            tallies[_2 - 1]++
            tallies[d3 - 1]++
            tallies[d4 - 1]++
            tallies[d5 - 1]++
            for (i in 0..5)
                if (tallies[i] >= 4)
                    return (i + 1) * 4
            return 0
        }

        fun three_of_a_kind(d1: Int, d2: Int, d3: Int, d4: Int, d5: Int): Int {
            val t: IntArray = IntArray(6)
            t[d1 - 1]++
            t[d2 - 1]++
            t[d3 - 1]++
            t[d4 - 1]++
            t[d5 - 1]++
            for (i in 0..5)
                if (t[i] >= 3)
                    return (i + 1) * 3
            return 0
        }

        fun smallStraight(d1: Int, d2: Int, d3: Int, d4: Int, d5: Int): Int {
            val tallies: IntArray = IntArray(6)
            tallies[d1 - 1] += 1
            tallies[d2 - 1] += 1
            tallies[d3 - 1] += 1
            tallies[d4 - 1] += 1
            tallies[d5 - 1] += 1
            return if (tallies[0] == 1 &&
                tallies[1] == 1 &&
                tallies[2] == 1 &&
                tallies[3] == 1 &&
                tallies[4] == 1
            ) 15 else 0
        }

        fun largeStraight(d1: Int, d2: Int, d3: Int, d4: Int, d5: Int): Int {
            val tallies: IntArray = IntArray(6)
            tallies[d1 - 1] += 1
            tallies[d2 - 1] += 1
            tallies[d3 - 1] += 1
            tallies[d4 - 1] += 1
            tallies[d5 - 1] += 1
            return if (tallies[1] == 1 &&
                tallies[2] == 1 &&
                tallies[3] == 1 &&
                tallies[4] == 1
                && tallies[5] == 1
            ) 20 else 0
        }

        fun fullHouse(d1: Int, d2: Int, d3: Int, d4: Int, d5: Int): Int {
            val tallies: IntArray
            var _2 = false
            var i: Int
            var _2_at = 0
            var _3 = false
            var _3_at = 0

            tallies = IntArray(6)
            tallies[d1 - 1] += 1
            tallies[d2 - 1] += 1
            tallies[d3 - 1] += 1
            tallies[d4 - 1] += 1
            tallies[d5 - 1] += 1

            i = 0
            while (i != 6) {
                if (tallies[i] == 2) {
                    _2 = true
                    _2_at = i + 1
                }
                i += 1
            }

            i = 0
            while (i != 6) {
                if (tallies[i] == 3) {
                    _3 = true
                    _3_at = i + 1
                }
                i += 1
            }

            return if (_2 && _3)
                _2_at * 2 + _3_at * 3
            else
                0
        }
    }
}


