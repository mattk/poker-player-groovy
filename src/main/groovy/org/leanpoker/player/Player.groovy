package org.leanpoker.player

class Player {

    static final String VERSION = 'Default Groovy folding player';

    static final RANKS = ['2', '3', '4', '5', '6', '7', '8', '9', '10', 'J', 'Q', 'K', 'A']
    public static final int ALLIN = 3000

    static int betRequest(def gameState) {
        def Object me = gameState.players[gameState.in_action]
        def holeCards = me.hole_cards
        def coolCards = countCoolCards(holeCards)

        if (coolCards == 0)
            return 0;

        return ALLIN;
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


    static void showdown(def gameState) {
    }
}
