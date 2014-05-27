package org.leanpoker.player

import groovy.json.JsonSlurper
import spock.lang.Specification

class PlayerFileTestSpock extends Specification {

    def json = new File("src/test/resources/game.json").getText()

	def "JsonSlurper from File"() {
		when:
		def gameState = new JsonSlurper().parseText(json)

		then:
		gameState.small_blind == 10
	}

    def "I am in action"() {
        given:
      	def gameState = new JsonSlurper().parseText(json)
        when:
        def Object me = gameState.players[gameState.in_action]
        then:
        me.name == 'Bob'
    }
}
