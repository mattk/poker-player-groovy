package org.leanpoker.player

/**
 * @author xza
 */
class PreFlopPlayer extends Player
{
    static int betAmount(final game) {
        def Object me = game.players[game.in_action]
        def holeCards = me.hole_cards
        def coolCards = countCoolCards(holeCards)
        def ranksEqual = areRanksEqual(holeCards)

        if (ranksEqual || coolCards == 2)
            return ALLIN;

        if (coolCards == 1 && safeCallAmount(game))
            return callAmount(game);

        return 0;

    }

    static Boolean safeCallAmount(def game) {

        def Object me = game.players[game.in_action]

        return Math.min(me.stack/10,30.0f) < callAmount(game)


    }
}
