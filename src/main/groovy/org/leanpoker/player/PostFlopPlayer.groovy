package org.leanpoker.player

import groovy.json.JsonBuilder
import groovyx.net.http.HTTPBuilder

import static groovyx.net.http.ContentType.JSON
import static groovyx.net.http.Method.POST

/**
 * @author xza
 */
class PostFlopPlayer extends Player
{
    static int betAmount(final game)
    {
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

    static void ranking(game)
    {
        def cards = allCards(game)
        def cardString = new JsonBuilder(cards).toString()


        def http = new HTTPBuilder('http://localhost:2048/')

        http.request(POST, JSON) { req ->

            body = [
                    cards: cardString
            ]

            response.success = { resp, json ->
                println 'YAY!'
            }
        }

    }
}
