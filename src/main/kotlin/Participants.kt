package indigo

class Participant(
    var points: Int = 0,
    var cards: Int = 0,
    val deck: MutableList<String> = mutableListOf(),
)