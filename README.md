# IndigoCardGame

<p>The purpose of this implementation, of a standard card game, is to demonstrate some cool skills with kotlin. My solution is part of the Jetbrains 
Academy's Track to Kotlin Developer. It was my first, but not my last project on 
mastering kotlin.</p>

## Gameplay

The game rules are easy:

### Step 1

Four cards are placed on the table.

### Step 2

Six cards are dealt to each player.

### Step 3

The players take turns in playing cards. If the card has the same suit or rank as the topmost card, then the player wins all the cards on the table.
The suits `A, 10, J, Q, K` get 1 point each.

### Step 4

When both players have no cards in hand, go to step 2 unless there are no more remaining cards in the card deck.

### Step 5

The remaining cards on the table go to the player who won the cards last. In the rare case where none of the players win any cards, then all
cards go to the player who played first.

### Step 6

The player with the most cards, gets 3 points extra.

## Strategy

The computers' strategy is easy:

<p>1) If there is only one card in hand, put it on the table (Example 2);</p>

<p>2) If there is only one candidate card, put it on the table (Example 3);</p>

<p>3) If there are no cards on the table:<p>
If there are cards in hand with the same suit, throw one of them at random (Example 4). For example, if the cards in hand are 7♥ 9♥ 8♣ A♠ 3♦
7♦ Q♥ (multiple ♥, and ♦ suits), the computer will play one card at random.
If there are no cards in hand with the same suit, but there are cards with the same rank (this situation occurs only when there are 4 or fewer
cards in hand), then throw one of them at random (Example 5). For example, if the cards in hand are 7♦ 7♥ 4♠ K♣, throw one of 7♦ 7♥ at random.
If there are no cards in hand with the same suit or rank, throw any card at random. For example, if the cards in hand are 9♥ 8♣ A♠ 3♦, throw
any of them at random.</p>

<p>4) If there are cards on the table but no candidate cards, use the same tactics as in step 3. That is:<p>
If there are cards in hand with the same suit, throw one of them at random (Example 6). For example, if the top card on the table is A♦, and
the cards in hand are 6♣ Q♥ 8♣ J♠ 7♣ (multiple ♣ suit), the computer will place any of 6♣ 8♣ 7♣ at random.
If there are no cards in hand with the same suit, but there are cards with the same rank (this may occur when there are 3 or fewer cards in
hand), throw one of them at random (Example 7). For example, if the top card on the table is A♦ and the cards in hand are J♠ Q♥ J♣, put one of
J♠ J♣ at random.
If there are no cards in hand with the same suit or rank, then put any card at random. For example, if the top card on the table is A♦, and
the cards in hand are J♠ Q♥ K♣, throw any of them at random.</p>

<p>5) If there are two or more candidate cards:<p>
If there are 2 or more candidate cards with the same suit as the top card on the table, throw one of them at random (Example 8). For example,
if the top card on the table is 5♥, and the cards in hand are 6♥ 8♣ 5♠ 7♦ 7♥, then the candidate cards are 6♥ 7♥ 5♠. There are 2 candidate cards with
the same suit as the top card on the table, 6♥ 7♥. Place any at random.
If the above isn't applicable, but there are 2 or more candidate cards with the same rank as the top card on the table, throw one of them at
random (example 9). For example, if the top card on the table is J♥, and the cards in hand are 3♥ J♣ J♠ 6♦, then the candidate cards are 3♥ J♣ J♠. In
this case, there are no 2 or more candidate cards with the same suit, but there are 2 candidate cards with the same rank as the top card on the table
that are J♣ J♠. Put any at random. If nothing of the above is applicable, then throw any of the candidate cards at random.</p>