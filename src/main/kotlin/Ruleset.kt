package indigo

/**
 * Rulesets for the game.
 */
object Ruleset {
    /**
     * Check if win conditions are true for a card.
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
        return !deck.isEmpty() && (deck.last().contains(value) || deck.last().contains(color))
    }



    /**
     * Calculates the points of a won deck.
     */
    fun calcPoints(deck: List<String>): Int {
        return deck.filter {
            it.get(0).toString() in listOf("A", "1", "J", "Q", "K")
        }.count()
    }

    /**
     *
     */
    fun pointsToMostCards(p: Participant, c: Participant) {
        if (p.cards > c.cards) {
            p.points += 3
        } else if (p.cards < c.cards) {
            c.points += 3
        }
    }
}