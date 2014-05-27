package org.leanpoker.player

class Player {

    static final String VERSION = 'Default Groovy folding player';

    static final RANKS = ['2', '3', '4', '5', '6', '7', '8', '9', '10', 'J', 'Q', 'K', 'A']
    public static final int ALLIN = 3000

    static int betRequest(def gameState) {
        def Object me = gameState.players[gameState.in_action]
        def holeCards = me.hole_cards
        def coolCards = countCoolCards(holeCards)
        def ranksEqual = areRanksEqual(holeCards)

        if (ranksEqual || coolCards == 2)
            return ALLIN;

        if (coolCards == 1)
            return ALLIN;

        return 0;
    }

    static int callAmount(game)
    {
        def Object me = game.players[game.in_action]
        return game.current_buy_in - me.bet
    }

    static int countCoolCards(def cards)
    {
        def cool = 0
        if (RANKS.indexOf(cards[0].rank) >= 8) cool++
        if (RANKS.indexOf(cards[1].rank) >= 8) cool++
        return cool
    }

    static boolean areRanksEqual(def cards)
    {
        return cards[0].rank == cards[1].rank
    }


    static void showdown(def gameState) {
    }
}
