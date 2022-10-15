package indigo

import indigo.ComputerStrategy.strategicSelect

/**
 * Start Deck.
 */
val deck = any().toMutableList()

/**
 * Deck on table.
 */
val tableDeck = mutableListOf<String>()

/**
 * The player.
 */
val player = Participant()

/**
 * The computer.
 */
val computer = Participant()

/**
 *
 */
var wonLast = ""

/**
 *
 */
fun handOut(size: Int): List<String> {
    val rtn = mutableListOf<String>()
    for (i in 1..size) {
        rtn.add(deck.pull((0..deck.lastIndex).random()))
    }
    return rtn
}

/**
 *
 */
fun player(): Boolean {
    if (player.deck.size < 1) {
        player.deck.addAll(handOut(6))
    }
    print("\n")
    Out.printTableDeck(tableDeck)
    Out.printPlayerDeck(player.deck)
    val selection = In.getCardSelection(player.deck)
    if (!selection.first) {
        return false
    }
    when (Ruleset.isWon(player.deck.get(selection.second - 1), tableDeck)) {
        true -> {
            wonLast = "player"
            tableDeck.add(player.deck.pull(selection.second - 1))
            player.points += Ruleset.calcPoints(tableDeck)
            player.cards += tableDeck.size
            tableDeck.clear()
            Out.printIfWon("Player", player.points, player.cards, computer.points, computer.cards)
        }
        false -> {
            tableDeck.add(player.deck.pull(selection.second - 1))
        }
    }
    return true
}

fun bot() {
    if (computer.deck.size < 1)
        computer.deck.addAll(handOut(6))
    print("\n")
    Out.printTableDeck(tableDeck)
    val pullIndex = computer.strategicSelect(tableDeck)
    val pulled = computer.deck[pullIndex]
    Out.printComputerPlays(pulled, computer.deck)
    computer.deck.removeAt(pullIndex)
    when (Ruleset.isWon(pulled, tableDeck)) {
        true -> {
            wonLast = "bot"
            tableDeck.add(pulled)
            computer.points += Ruleset.calcPoints(tableDeck)
            computer.cards += tableDeck.size
            tableDeck.clear()
            Out.printIfWon("Computer", player.points, player.cards, computer.points, computer.cards)
        }
        false -> {
            tableDeck.add(pulled)
        }
    }
}

/**
 *
 */
fun play(initial: Boolean) {
    tableDeck.addAll(handOut(4))
    Out.printInitialTableDeck(tableDeck)
    var playersTurn = initial
    do {
        if (playersTurn) {
            if (!player()) {
                break;
            }
        } else {
            bot()
        }
        playersTurn = !playersTurn
    } while (deck.size > 5 || (playersTurn && player.deck.size > 0) || (!playersTurn && computer
            .deck.size > 0)
    )
    when (wonLast) {
        "player" -> {
            player.cards += tableDeck.size
            player.points += Ruleset.calcPoints(tableDeck)
        }
        "bot" -> {
            computer.cards += tableDeck.size
            computer.points += Ruleset.calcPoints(tableDeck)
        }
        "" -> {
            if (initial) {
                player.cards += tableDeck.size
                player.points += Ruleset.calcPoints(tableDeck)
            } else {
                computer.cards += tableDeck.size
                computer.points += Ruleset.calcPoints(tableDeck)
            }
        }
    }
    print("\n")
    Ruleset.pointsToMostCards(player, computer)
    Out.printFinalMessage(tableDeck, player.points, player.cards, computer.points, computer.cards)
}

/**
 *
 */
fun main() {
    println("Indigo Card Game")
    play(In.nextBoolean())
}