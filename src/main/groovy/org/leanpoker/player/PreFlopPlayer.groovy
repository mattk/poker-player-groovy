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

        if (coolCards == 1)
            return callAmount(game);

        return 0;

    }
}
