package indigo

/**
 *
 */
object ComputerStrategy {

    /**
     *
     */
    private fun List<String>.rankDuplicates(): List<String> {
        val duplicates = mutableListOf<String>()
        ranks().forEach {
            val rank = it
            val isA = if (filter { it.contains(rank) }.size > 1) true else false
            if (isA) duplicates.add(it)
        }
        return duplicates
    }

    /**
     *
     */
    private fun List<String>.suitDuplicates(): List<String> {
        val duplicates = mutableListOf<String>()
        suits().forEach {
            val suit = it
            val isA =
                if (filter { it.contains(suit) }.size > 1) true else false
            if (isA) duplicates.add(it)
        }
        return duplicates
    }

    /**
     *
     */
    private fun List<String>.independentPreferred(duplicates: List<String>): Int {
        val mostRepresented = duplicates.reduce()
        return indexOf(filter { it.contains(mostRepresented) }.random())
    }

    /**
     * Returns the string that is most represented.
     */
    private fun List<String>.reduce(): String {
        if (this.isEmpty()) {
            return ""
        }
        val mapped: MutableList<Pair<String, Int>> = mutableListOf()
        forEach {
            val el = it
            mapped.add(Pair(it, filter { it == el }.size))
        }
        return mapped.largest().first
    }

    private fun List<Pair<String, Int>>.largest(): Pair<String, Int> {
        var rtnEl = this[0]
        for (it in this) {
            if (it.second > rtnEl.second) rtnEl = it
        }
        return rtnEl
    }

    /**
     * Returns a double array that groups the ranks together in an inner dimension.
     */
    private fun List<String>.ranks(): List<String> {
        return this.map {
            if (it.length == 3) it.substring(0, 2) else it.substring(0, 1)
        }.toMutableList()
    }

    /**
     * Returns a double array that groups the suits together in an inner dimension.
     */
    private fun List<String>.suits(): List<String> {
        return this.map {
            if (it.length == 3) it.get(2).toString() else it.get(1).toString()
        }.toMutableList()
    }

    /**
     *
     */
    fun Participant.strategicSelect(tableDeck: MutableList<String>): Int {
        if (this.deck.isEmpty()) {
            throw java.lang.Exception("Can not strategic select an empty deck.")
        }
        return when (this.deck.size) {
            1 -> {
                0
            }
            else -> {
                val rankDuplicates = this.deck.rankDuplicates()
                val suitDuplicates = this.deck.suitDuplicates()
                val noDuplicates = rankDuplicates.isEmpty() && suitDuplicates.isEmpty()
                when (tableDeck.isEmpty()) {
                    true -> {
                        return if (noDuplicates) (0..this.deck
                            .lastIndex)
                            .random()
                        else this.deck.independentPreferred(
                            if (suitDuplicates.isNotEmpty())
                                suitDuplicates else rankDuplicates
                        )
                    }
                    false -> {
                        val winningSuit = this.deck.suits().filter {
                            tableDeck.last().contains(it)
                        }.firstOrNull() ?: ""
                        val winningRank = this.deck.ranks().filter {
                            tableDeck.last().contains(it)
                        }.firstOrNull() ?: ""
                        when (winningSuit != "" || winningRank != "") {
                            true -> {
                                return this.deck.indexOf(
                                    this.deck.filter { it.contains(winningSuit) && it.contains(winningRank) }.firstOrNull()
                                        ?: this.deck.filter { it.contains(winningSuit) }.firstOrNull()
                                        ?: this.deck.filter { it.contains(winningRank)}.first()
                                )
                            }
                            false -> {
                                return if (noDuplicates) {
                                    (0..this.deck.lastIndex).random()
                                } else {
                                    return this.deck.independentPreferred(
                                        if (suitDuplicates.isNotEmpty())
                                            suitDuplicates else rankDuplicates
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}