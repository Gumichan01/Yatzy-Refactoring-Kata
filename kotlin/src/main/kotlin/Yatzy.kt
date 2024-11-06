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
        dice.forEach { v -> counts[v - 1]++ }
        var n = 0
        var score = 0
        var i = 0
        while (i < 6) {
            if (counts[6 - i - 1] >= 2) {
                n++
                score += 6 - i
            }
            i += 1
        }
        return when {
            (n == 2) -> score * 2
            else -> 0
        }
    }


    fun four_of_a_kind(): Int {
        val counts = IntArray(6)
        dice.forEach { v -> counts[v - 1]++ }
        for (i in 0..5) {
            if (counts[i] >= 4) {
                return (i + 1) * 4
            }
        }
        return 0
    }

    fun three_of_a_kind(): Int {
        val counts: IntArray = IntArray(6)
        dice.forEach { v -> counts[v - 1]++ }
        for (i in 0..5)
            if (counts[i] >= 3)
                return (i + 1) * 3
        return 0
    }

    fun smallStraight(): Int {
        val tallies = IntArray(6)
        dice.forEach { v -> tallies[v - 1]++ }
        val equalOneList = tallies.dropLast(1).map { v -> v == 1 }
        return if (equalOneList.contains(false)) 0 else 15
    }

    fun largeStraight(): Int {
        val tallies = IntArray(6)
        dice.forEach { v -> tallies[v - 1]++ }
        val equalOneList = tallies.drop(1).map { v -> v == 1 }
        return if (equalOneList.contains(false)) 0 else 20
    }


    fun fullHouse(): Int {
        val pairScore: Int = score_pair()
        val tripletScore: Int = three_of_a_kind()
        return when {
            pairScore > 0 && tripletScore > 0 -> pairScore + tripletScore
            else -> 0
        }
    }
}


