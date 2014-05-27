package org.leanpoker.player

import groovy.json.JsonSlurper
import spock.lang.Specification

class PlayerTestSpock extends Specification {

	def "JsonSlurper from File"() {
        def json = new File("src/test/resources/game.json").getText()

		given:
		def gameState = new JsonSlurper().parseText(json)

		expect:
		gameState.small_blind == 10
	}
}
