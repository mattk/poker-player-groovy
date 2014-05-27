package org.leanpoker.player

import groovy.json.JsonSlurper
import spock.lang.Specification

class PreFlopPlayerTest extends Specification
{

    def json = new File("src/test/resources/preflop.json").getText()

    def "player bets"()
    {
        given:
        def gameState = new JsonSlurper().parseText(json)
        when:
        def result = Player.betRequest(gameState)

        then:
        result == 240
    }

    def "22 hole pair all-in"()
    {
        given:
        def gameState = new JsonSlurper().parseText(json)
        def Object me = gameState.players[gameState.in_action]
        when:
        def holeCards = me.hole_cards
        holeCards[0].rank = '2'
        holeCards[1].rank = '2'
        then:
        Player.betRequest(gameState) == Player.ALLIN
    }

    def "1 cool card"()
    {
        given:
        def gameState = new JsonSlurper().parseText(json)
        def Object me = gameState.players[gameState.in_action]
        when:
        def holeCards = me.hole_cards
        then:
        PreFlopPlayer.countCoolCards(holeCards) == 1
    }

    def "AQ is 2 cool cards"()
    {
        given:
        def gameState = new JsonSlurper().parseText(json)
        def Object me = gameState.players[gameState.in_action]
        when:
        def holeCards = me.hole_cards
        holeCards[0].rank = 'A'
        holeCards[1].rank = 'Q'
        then:
        PreFlopPlayer.countCoolCards(holeCards) == 2
    }

    def "23 is no cool card"()
    {
        given:
        def gameState = new JsonSlurper().parseText(json)
        def Object me = gameState.players[gameState.in_action]
        when:
        def holeCards = me.hole_cards
        holeCards[0].rank = '2'
        holeCards[1].rank = '3'
        then:
        PreFlopPlayer.countCoolCards(holeCards) == 0
    }
    def "10-2 is ONE cool card"()
    {
        given:
        def gameState = new JsonSlurper().parseText(json)
        def Object me = gameState.players[gameState.in_action]
        when:
        def holeCards = me.hole_cards
        holeCards[0].rank = '2'
        holeCards[1].rank = '10'
        then:
        PreFlopPlayer.countCoolCards(holeCards) == 1
    }
}
