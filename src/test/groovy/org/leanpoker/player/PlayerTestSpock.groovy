package org.leanpoker.player

import groovy.json.JsonSlurper
import spock.lang.Specification

class PlayerTestSpock extends Specification {

	def "The default folding player should fold"() {
		given:
		def gameState = new JsonSlurper().parseText('{"key1": "value1", "key2": "value2"}')

		expect:
		Player.betRequest(gameState) == 1000
	}

	def "JsonSlurper from File"() {
        def json = new File("src/test/resources/game.json").getText()

		given:
		def gameState = new JsonSlurper().parseText(json)

		expect:
		gameState.small_blind == 10
	}
}
