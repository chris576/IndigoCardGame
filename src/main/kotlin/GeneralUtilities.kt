package indigo

/**
 * Adds a pull function to a mutable list. Element on index i is returned and removed from list.
 */
fun MutableList<String>.pull(i: Int): String {
    val rtn = get(i)
    removeAt(i)
    return rtn
}

/**
 * Any cards of the card game, as list of strings.
 */
fun any() = listOf(
    "A♦", "2♦", "3♦", "4♦", "5♦", "6♦", "7♦", "8♦", "9♦", "10♦", "J♦", "Q♦", "K♦",
    "A♥", "2♥", "3♥", "4♥", "5♥", "6♥", "7♥", "8♥", "9♥", "10♥", "J♥", "Q♥", "K♥",
    "A♠", "2♠", "3♠", "4♠", "5♠", "6♠", "7♠", "8♠", "9♠", "10♠", "J♠", "Q♠", "K♠",
    "A♣", "2♣", "3♣", "4♣", "5♣", "6♣", "7♣", "8♣", "9♣", "10♣", "J♣", "Q♣", "K♣"
)