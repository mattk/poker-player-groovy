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

    def "1 cool card"()
    {
        given:
        def game = new JsonSlurper().parseText(json)
        def Object me = game.players[game.in_action]
        when:
        def holeCards = me.hole_cards
        then:
        Player.countCoolCards(holeCards) == 1
    }

    def "AQ is 2 cool cards"()
    {
        given:
        def game = new JsonSlurper().parseText(json)
        def Object me = game.players[game.in_action]
        when:
        def holeCards = me.hole_cards
        holeCards[0].rank = 'A'
        holeCards[1].rank = 'Q'
        then:
        Player.countCoolCards(holeCards) == 2
    }

    def "23 is no cool card"()
    {
        given:
        def game = new JsonSlurper().parseText(json)
        def Object me = game.players[game.in_action]
        when:
        def holeCards = me.hole_cards
        holeCards[0].rank = '2'
        holeCards[1].rank = '3'
        then:
        Player.countCoolCards(holeCards) == 0
    }

    def "Pre-Flop 22 hole pair all-in"()
    {
        given:
        def game = new JsonSlurper().parseText(json)
        def Object me = game.players[game.in_action]
        when:
        def holeCards = me.hole_cards
        holeCards[0].rank = '2'
        holeCards[1].rank = '2'
        then:
        PreFlopPlayer.betAmount(game) == Player.ALLIN
    }
    def "all cards"()
    {
        given:
        def game = new JsonSlurper().parseText(json)
        when:
        def cards = PostFlopPlayer.allCards(game)
        then:
        cards.size == 5
    }


}
