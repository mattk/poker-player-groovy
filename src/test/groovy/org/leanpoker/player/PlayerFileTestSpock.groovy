package org.leanpoker.player

import groovy.json.JsonSlurper
import spock.lang.Specification

class PlayerFileTestSpock extends Specification
{

    def json = new File("src/test/resources/game.json").getText()

    def "JsonSlurper from File"()
    {
        when:
        def game = new JsonSlurper().parseText(json)

        then:
        game.small_blind == 10
    }

    def "I am in action"()
    {
        given:
        def game = new JsonSlurper().parseText(json)
        when:
        def Object me = game.players[game.in_action]
        then:
        me.name == 'Bob'
    }

    def "All cards"()
    {
        given:
        def game = new JsonSlurper().parseText(json)
        def Object me = game.players[game.in_action]
        when:
        def communityCards = game.community_cards
        def holeCards = me.hole_cards
        then:
        communityCards.size == 3
        holeCards.size == 2
    }


}
