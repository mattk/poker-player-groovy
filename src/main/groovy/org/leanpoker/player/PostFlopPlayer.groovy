package org.leanpoker.player

import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.ContentType
import groovyx.net.http.RESTClient

import static groovyx.net.http.ContentType.JSON
import static groovyx.net.http.ContentType.URLENC
import static groovyx.net.http.Method.POST

/**
 * @author xza
 */
class PostFlopPlayer extends Player
{
    static int betAmount(final game)
    {
        if (ranking(game) > 3)
            return ALLIN;
        return 0;
    }

    static List allCards(game)
    {
        def Object me = game.players[game.in_action]
        def communityCards = game.community_cards
        def holeCards = me.hole_cards
        def allCards = communityCards + holeCards

        def ret = []
        allCards.each { card ->
            ret.add([rank: card.rank, suit: card.suit])
        }

        return ret
    }

    static Object ranking(game)
    {
        def cards = allCards(game)
        def cardString = new JsonBuilder(cards).toString()

        def api = new RESTClient( 'http://localhost:2048/' )

        def resp = api.post(
                body: [cards: cardString],
                requestContentType : URLENC
        )

        return new JsonSlurper().parseText(resp.data)
    }
}
