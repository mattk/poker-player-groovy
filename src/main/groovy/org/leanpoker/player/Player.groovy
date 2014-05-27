package org.leanpoker.player

class Player {

    static final String VERSION = 'V 1';

    static final RANKS = ['2', '3', '4', '5', '6', '7', '8', '9', '10', 'J', 'Q', 'K', 'A']
    public static final int ALLIN = 3000

    static int betRequest(def gameState) {
        return isPreflop(gameState) ? PreFlopPlayer.betAmount(gameState) : PostFlopPlayer.betAmount(gameState);
    }

    static boolean isPreflop(game)
    {
        return game.community_cards.size == 0
    }

    static int callAmount(game)
    {
        def Object me = game.players[game.in_action]
        return game.current_buy_in - me.bet
    }


    static boolean areRanksEqual(def cards)
    {
        return cards[0].rank == cards[1].rank
    }

    static void showdown(def gameState) {
    }
}
