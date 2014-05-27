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
        result == 0
    }

    def "AA hole pair all-in"()
    {
        given:
        def gameState = new JsonSlurper().parseText(json)
        def Object me = gameState.players[gameState.in_action]
        when:
        def holeCards = me.hole_cards
        holeCards[0].rank = 'A'
        holeCards[1].rank = 'A'
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
    def "safe call amount"() {
        when:
        def result = PreFlopPlayer.safeCallAmount(1000, 300, 1)
        then:
        result == true
    }
    def "safe call amount, low cash"() {
        when:
        def result = PreFlopPlayer.safeCallAmount(500, 300, 4)
        then:
        result == false
    }
    def "safe call amount, crappy cards"() {
        when:
        def result = PreFlopPlayer.safeCallAmount(1000, 50, 10)
        then:
        result == false
    }

}
