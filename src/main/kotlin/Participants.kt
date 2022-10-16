package indigo

/**
 * A class to encapsulate the points and cards for a player and computer.
 */
class Participant(
    /**
     * The points of the participant.
     */
    var points: Int = 0,
    /**
     * The card count of the participant.
     */
    var cards: Int = 0,
    /**
     * The cards in hand of the participant.
     */
    val deck: MutableList<String> = mutableListOf(),
)