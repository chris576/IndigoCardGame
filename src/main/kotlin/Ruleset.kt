package indigo

/**
 * A utility class to define the rules of the card game.
 */
object Ruleset {
    /**
     * Checks whether the card's rank or suit is equal to the deck's top card's rank or suit.
     */
    fun isWon(card: String, deck: List<String>): Boolean {
        val value = if (card.length == 3) {
            card.substring(0, 2)
        } else {
            card.substring(0, 1)
        }
        val color = if (card.length == 3) {
            card[2].toString()
        } else {
            card[1].toString()
        }
        return deck.isNotEmpty() && (deck.last().contains(value) || deck.last().contains(color))
    }

    /**
     * Calculates the points of a deck.
     * A, 10, J, Q, K get each a single point.
     */
    fun calcPoints(deck: List<String>): Int {
        return deck.filter {
            it.get(0).toString() in listOf("A", "1", "J", "Q", "K")
        }.count()
    }

    /**
     * The participant with the most cards, get 3 points extra.
     */
    fun pointsToMostCards(p: Participant, c: Participant) {
        if (p.cards > c.cards) {
            p.points += 3
        } else if (p.cards < c.cards) {
            c.points += 3
        }
    }
}