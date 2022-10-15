package indigo

object Out {
    /**
     *
     */
    fun printInitialTableDeck(tableDeck: List<String>) {
        var out = "Initial cards on the table:"
        tableDeck.forEach {
            out += " $it"
        }
        println(out)
    }

    /**
     *
     */
    fun printTableDeck(tableDeck: List<String>) {
        if (tableDeck.isEmpty()) {
            println("No cards on the table")
        } else {
            println("${tableDeck.size} cards on the table, and the top card is ${tableDeck.last()}")
        }
    }

    /**
     *
     */
    fun printPlayerDeck(entity: List<String>) {
        var out = "Cards in hand:"
        for (el in entity) {
            out += " ${entity.indexOf(el) + 1})$el"
        }
        println(out)
    }

    /**
     *
     */
    fun printComputerPlays(card: String, deck : List<String>) {
        var out = ""
        deck.forEach {
            out += "$it "
        }
        out = out.trim()
        out += "\nComputer plays $card"
        println(out)
    }

    /**
     *
     */
    fun printIfWon(
        who: String, playerScore: Int, playerCards: Int, computerScore: Int,
        computerCards: Int,
    ) {
        println(
            "$who wins cards\nScore: Player $playerScore - Computer $computerScore\n" +
                    "Cards: Player $playerCards - Computer $computerCards"
        )
    }

    /**
     *
     */
    fun printFinalMessage(
        tableDeck: List<String>, playerScore: Int, playerCards: Int, computerScore: Int,
        computerCards: Int,
    ) {
        if (playerScore > 0 && computerScore > 1) {
            printTableDeck(tableDeck)
            println(
                "Score: Player $playerScore - Computer $computerScore\n" +
                        "Cards: Player $playerCards - Computer $computerCards"
            )
        }
        println("Game Over")
    }
}

object In {

    private fun String.isBoolean() : Boolean {
        return this == "yes" || this == "no"
    }

    /**
     *
     */
    private fun String.toBoolean() : Boolean {
        return this == "yes";
    }

    fun getCardSelection(deck: List<String>) : Pair<Boolean, Int> {
        var rtn : Pair<Boolean, Int>?
        do {
            println("Choose a card to play (1-${deck.size}):")
            rtn = nextNumber()
            if (rtn != null && !rtn.first) {
                return rtn
            }
        } while (rtn == null || (rtn.second-1) !in (0 until deck.size))
        return rtn
    }

    /**
     * Gets the next number from console.
     */
    private fun nextNumber(): Pair<Boolean, Int>? {
        try {
            val inp = readLine()
            return if (inp != null) {
                if (inp == "exit") {
                    Pair(false, 0)
                } else {
                    Pair(true, inp.toInt())
                }
            } else {
                null
            }
        } catch (ex: NumberFormatException) {
            return null
        }
    }

    /**
     * Gets the next boolean from console.
     */
    fun nextBoolean(): Boolean {
        var rtn: String
        do {
            println("Play first?")
            rtn = readln()
        } while (!rtn.isBoolean())
        return rtn.toBoolean()
    }
}