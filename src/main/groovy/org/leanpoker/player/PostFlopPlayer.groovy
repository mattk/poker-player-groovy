package org.leanpoker.player

import groovy.json.JsonSlurper

/**
 * @author xza
 */
class PostFlopPlayer extends Player
{
    static int betAmount(final game) {
        return 0;
    }

    static Object allCards(game)
    {
        def Object me = game.players[game.in_action]
        def communityCards = game.community_cards
        def holeCards = me.hole_cards
        return communityCards + holeCards
    }
}
