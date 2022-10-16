package indigo

/**
 * This utility class adds a strategy to the computers' selection for a card.
 * Thus, the card deck is handled as list of strings, a list of strings is always handled as list of cards, suits,
 * or ranks.
 *
 * There for methods where added to methods where added to the list class, to extend its functionality.
 * Those extensions are only valid within this utility class.
 *
 * @author Christopher Hübner
 */
object ComputerStrategy {

    /**
     * Extracts the ranks from this list and checks, whether they are represented at least twice.Those are
     * returned as new list.
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
     * Extracts the suits from this list and checks, whether they are represented at least twice.Those are
     * returned as new list.
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
     * Identifies a random index of an element, that contains the param.
     *
     * @param substring A suit or rank.
     * @return Returns a randomized index of elements within this list, that contains the most represented element of the {@link #substrings}.
     */
    private fun List<String>.randomIndexMostContainedSubstring(substring: String): Int {
        return indexOf(filter { it.contains(substring) }.random())
    }

    /**
     * Returns the String (Card, Suit or Rank), that is most contained within the list.
     */
    private fun List<String>.mostContained(): String {
        var str = ""
        var amount = 0
        val it = iterator()
        while (it.hasNext()) {
            val next = it.next()
            val cAmount = filter { it == next }.size
            if (cAmount > amount) {
                str = next
                amount = cAmount
            }
        }
        return str
    }

    /**
     * Extracts the ranks of the elements of this list and returns them as a new list in the same order.
     */
    private fun List<String>.ranks(): List<String> {
        return this.map {
            if (it.length == 3) it.substring(0, 2) else it.substring(0, 1)
        }.toMutableList()
    }

    /**
     * Extracts the suits of the elements of this list and returns them as a new list in the same order.
     */
    private fun List<String>.suits(): List<String> {
        return this.map {
            if (it.length == 3) it.get(2).toString() else it.get(1).toString()
        }.toMutableList()
    }

    /**
     * The computers' strategy is easy:
     * <p>
     * 1) If there is only one card in hand, put it on the table (Example 2);
     * <p>
     * 2) If there is only one candidate card, put it on the table (Example 3);
     * <p>
     * 3) If there are no cards on the table:
     * <p>
     * If there are cards in hand with the same suit, throw one of them at random (Example 4). For example, if the cards in hand are 7♥ 9♥ 8♣ A♠ 3♦
     * 7♦ Q♥ (multiple ♥, and ♦ suits), the computer will play one card at random.
     * If there are no cards in hand with the same suit, but there are cards with the same rank (this situation occurs only when there are 4 or fewer
     *  cards in hand), then throw one of them at random (Example 5). For example, if the cards in hand are 7♦ 7♥ 4♠ K♣, throw one of 7♦ 7♥ at random.
     * If there are no cards in hand with the same suit or rank, throw any card at random. For example, if the cards in hand are 9♥ 8♣ A♠ 3♦, throw
     * any of them at random.
     * <p>
     * 4) If there are cards on the table but no candidate cards, use the same tactics as in step 3. That is:
     *  If there are cards in hand with the same suit, throw one of them at random (Example 6). For example, if the top card on the table is A♦, and
     * the cards in hand are 6♣ Q♥ 8♣ J♠ 7♣ (multiple ♣ suit), the computer will place any of 6♣ 8♣ 7♣ at random.
     * If there are no cards in hand with the same suit, but there are cards with the same rank (this may occur when there are 3 or fewer cards in
     * hand), throw one of them at random (Example 7). For example, if the top card on the table is A♦ and the cards in hand are J♠ Q♥ J♣, put one of
     *  J♠ J♣ at random.
     * If there are no cards in hand with the same suit or rank, then put any card at random. For example, if the top card on the table is A♦, and
     * the cards in hand are J♠ Q♥ K♣, throw any of them at random.
     * <p>
     * 5) If there are two or more candidate cards:
     * <p>
     * If there are 2 or more candidate cards with the same suit as the top card on the table, throw one of them at random (Example 8). For example,
     * if the top card on the table is 5♥, and the cards in hand are 6♥ 8♣ 5♠ 7♦ 7♥, then the candidate cards are 6♥ 7♥ 5♠. There are 2 candidate cards with the same suit as the top card on the table, 6♥ 7♥. Place any at random.
     * If the above isn't applicable, but there are 2 or more candidate cards with the same rank as the top card on the table, throw one of them at
     * random (example 9). For example, if the top card on the table is J♥, and the cards in hand are 3♥ J♣ J♠ 6♦, then the candidate cards are 3♥ J♣ J♠. In this case, there are no 2 or more candidate cards with the same suit, but there are 2 candidate cards with the same rank as the top card on the table that are J♣ J♠. Put any at random.
     * If nothing of the above is applicable, then throw any of the candidate cards at random.
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
                        else this.deck.randomIndexMostContainedSubstring(
                            if (suitDuplicates.isNotEmpty())
                                suitDuplicates.mostContained() else rankDuplicates.mostContained()
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
                                        ?: this.deck.filter { it.contains(winningRank) }.first()
                                )
                            }
                            false -> {
                                return if (noDuplicates) {
                                    (0..this.deck.lastIndex).random()
                                } else {
                                    return this.deck.randomIndexMostContainedSubstring(
                                        if (suitDuplicates.isNotEmpty())
                                            suitDuplicates.mostContained() else rankDuplicates.mostContained()
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