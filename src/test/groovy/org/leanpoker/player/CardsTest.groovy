package org.leanpoker.player

import groovy.json.JsonSlurper
import spock.lang.Shared
import spock.lang.Specification

class CardsTest extends Specification
{
    @Shared
    def json
    def game
    def me

    def setupSpec()
    {
        json = new File("src/test/resources/game.json").getText()
    }

    def setup()
    {
        game = new JsonSlurper().parseText(json)
        me = game.players[game.in_action]
    }

    def "asList"()
    {
        when:
        def cards = Cards.asList(me.hole_cards)
        then:
        cards instanceof List
        cards.size == cards.size
    }

    def "mine"()
    {
        when:
        def cards = Cards.mine(game)
        then:
        cards.size == 2
    }

    def "community"()
    {
        when:
        def cards = Cards.community(game)
        then:
       cards.size == 3
    }

    def "all"()
    {
        when:
        def cards = Cards.ofGame(game)
        then:
        cards.size == 5
    }

}
