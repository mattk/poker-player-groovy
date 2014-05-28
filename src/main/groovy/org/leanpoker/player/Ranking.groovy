package org.leanpoker.player

import groovy.json.JsonBuilder
import groovyx.net.http.RESTClient

import static groovyx.net.http.ContentType.JSON
import static groovyx.net.http.ContentType.URLENC

class Ranking
{
    static Object ofGame(final game)
    {
        def cards = Cards.ofGame(game)
        return ofCards(cards)

    }

    static Object ofCards(final cards)
    {
        def api = new RESTClient('http://localhost:2048/')

        def resp = api.post(
                body: [cards: toJSON(cards)],
                requestContentType: URLENC,
                contentType: JSON
        )

        return resp.data
    }

    static String toJSON(cards)
    {
        return new JsonBuilder(cards).toString()
    }

}
