package org.leanpoker.player

import groovy.json.JsonSlurper
import spock.lang.Specification

class PreFlopPlayerTest extends Specification
{

    def json = new File("src/test/resources/preflop.json").getText()

    def "player bets"()
    {
        given:
        def game = new JsonSlurper().parseText(json)
        when:
        def result = Player.betRequest(game)

        then:
        result == 0
    }

    def "AA hole pair all-in"()
    {
        given:
        def game = new JsonSlurper().parseText(json)
        def Object me = game.players[game.in_action]
        when:
        def holeCards = me.hole_cards
        holeCards[0].rank = 'A'
        holeCards[1].rank = 'A'
        then:
        Player.betRequest(game) == Amount.allIn(game)
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
    def "safe call amount, lower pair"() {
        when:
        def result = PreFlopPlayer.safeCallAmount(1000, 40, 6)
        then:
        result == false
    }

}
