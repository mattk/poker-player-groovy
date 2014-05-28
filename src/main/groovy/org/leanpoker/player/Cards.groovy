package org.leanpoker.player

/**
 * @author xza
 */
class Cards
{
    static List ofGame(game)
    {
        return mine(game) + community(game)
    }

    static List mine(final game)
    {
        def Object me = game.players[game.in_action]
        return asList(me.hole_cards)
    }

    static List community(final game)
    {
        return asList(game.community_cards)

    }

    static List asList(final cards)
    {
        def list = []
        cards.each { card ->
            list.add([rank: card.rank, suit: card.suit])
        }
        return list
    }

}
