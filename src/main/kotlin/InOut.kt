package indigo

/**
 * Specifies methods for sending feedback to the user via the console.
 */
object Out {
    /**
     * Prints the initial table deck, a follows:
     * "Initial cards on the table: <List of cards>
     */
    fun printInitialTableDeck(tableDeck: List<String>) {
        var out = "Initial cards on the table:"
        tableDeck.forEach {
            out += " $it"
        }
        println(out)
    }

    /**
     * Prints the cards on the table.
     */
    fun printTableDeck(tableDeck: List<String>) {
        if (tableDeck.isEmpty()) {
            println("No cards on the table")
        } else {
            println("${tableDeck.size} cards on the table, and the top card is ${tableDeck.last()}")
        }
    }

    /**
     * Prints the deck of a player.
     *
     * @param cardList The list of cards of the player, or the computer.
     */
    fun printPlayerDeck(cardList: List<String>) {
        var out = "Cards in hand:"
        for (el in cardList) {
            out += " ${cardList.indexOf(el) + 1})$el"
        }
        println(out)
    }

    /**
     * Prints the card played by the computer.
     *
     * @param deck The deck of the computer.
     * @param card The card played by the computer.
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
     * Prints the win message.
     *
     * @param who Who has won?
     * @param playerScore The score of the player.
     * @param playerCards The amount of cards of the player.
     * @param computerScore The score of the computer.
     * @param computerCards The cards of the computer.
     *
     */
    fun printWinMessage(
        who: String, playerScore: Int, playerCards: Int, computerScore: Int,
        computerCards: Int,
    ) {
        println(
            "$who wins cards\nScore: Player $playerScore - Computer $computerScore\n" +
                    "Cards: Player $playerCards - Computer $computerCards"
        )
    }

    /**
     * Prints the message at the end of a game.
     * Prints the tables deck, as well as the scores, plus "Game Over".
     *
     * @param tableDeck The cards on the table.
     * @param playerScore The score of the player.
     * @param playerCards The amount of cards of the player.
     * @param computerScore The score of the computer.
     * @param computerCards The cards of the computer.
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

/**
 * Defines rules to send requests to the users console.
 */
object In {

    /**
     * Returns whether this string is a boolean.
     */
    private fun String.isBoolean() : Boolean {
        return this == "yes" || this == "no"
    }

    /**
     * Returns this string as boolean. Before invoke, it must be checked, whether it is a boolean, or not.
     */
    private fun String.toBoolean() : Boolean {
        return this == "yes"
    }

    /**
     * Requests an integer from the user.
     *
     * As long there is no valid input from the user, the request is returned.
     *
     * @return If the user inputs "exit", an empty pair, with a false flag is returned. In any other case a pair is with the int value and a true
     * flag ist returned.
     */
    fun requestInteger(deck: List<String>) : Pair<Boolean, Int> {
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
     * Request a number, or "exit" input from the console.
     *
     * @return If "exit" is given to the console, pair with 0 and false is returned. In any other case, a pair with the number value and true is
     * returned.
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
     * Requests an boolean input from the user. Valid inputs could be "yes" or "no".
     * As long there is no valid input, the request is returned.
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