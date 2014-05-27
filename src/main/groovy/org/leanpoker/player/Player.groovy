package org.leanpoker.player

class Player {

    static final String VERSION = 'V 2';

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



    static void showdown(def gameState) {
    }
}
