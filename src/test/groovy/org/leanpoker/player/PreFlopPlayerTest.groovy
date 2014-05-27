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


}
