package org.leanpoker.player

class Player {

    static final String VERSION = 'V 2';

    static int betRequest(def gameState) {
        return isPreflop(gameState) ? PreFlopPlayer.betAmount(gameState) : PostFlopPlayer.betAmount(gameState);
    }

    static boolean isPreflop(game)
    {
        return game.community_cards.size == 0
    }

    static void showdown(def gameState) {
    }
}
