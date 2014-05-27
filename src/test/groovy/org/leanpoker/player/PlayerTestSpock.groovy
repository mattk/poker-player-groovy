package org.leanpoker.player

import groovy.json.JsonSlurper
import spock.lang.Specification

/**
 * Java/Groovy unit test using the Spock framework.
 * https://code.google.com/p/spock/
 */
class PlayerTestSpock extends Specification {
def game_state ="{\n" +
    "    \"small_blind\": 10,"  +
    "" +
    "\n" +
    "    \"current_buy_in\": 320," +
    "\n" +
    "    \"pot\": 400," +
    "\n" +
    "    \"minimum_raise\": 240," +
    "" +
    "\n" +
    "    \"dealer\": 1," +
    "" +
    "\n" +
    "    \"orbits\": 7," +
    "" +
    "\n" +
    "    \"in_action\": 1," +
    "\n" +
    "    \"players\": [" +
    "        {" +
    "\n" +
    "            \"id\": 0," +
    "\n" +
    "            \"name\": \"Albert\"," +
    "\n" +
    "            \"status\": \"active\"," +
    "" +
    "" +
    "" +
    "" +
    "\n" +
    "            \"version\": \"Default random player\"," +
    "\n" +
    "            \"stack\": 1010," +
    "" +
    "\n" +
    "            \"bet\": 320" +
    "        },\n" +
    "        {\n" +
    "            \"id\": 1," +
    "            \"name\": \"Bob\",\n" +
    "            \"status\": \"active\",\n" +
    "            \"version\": \"Default random player\",\n" +
    "            \"stack\": 1590,\n" +
    "            \"bet\": 80,\n" +
    "            \"hole_cards\": [" +
    "" +
    "                {\n" +
    "                    \"rank\": \"6\"," +
    "                    \"suit\": \"hearts\"" +
    "                },\n" +
    "                {\n" +
    "                    \"rank\": \"K\",\n" +
    "                    \"suit\": \"spades\"\n" +
    "                }\n" +
    "            ]\n" +
    "        },\n" +
    "        {\n" +
    "            \"id\": 2,\n" +
    "            \"name\": \"Chuck\",\n" +
    "            \"status\": \"out\",\n" +
    "            \"version\": \"Default random player\",\n" +
    "            \"stack\": 0,\n" +
    "            \"bet\": 0\n" +
    "        }\n" +
    "    ],\n" +
    "    \"community_cards\": [" +
    "        {\n" +
    "            \"rank\": \"4\",\n" +
    "            \"suit\": \"spades\"\n" +
    "        },\n" +
    "        {\n" +
    "            \"rank\": \"A\",\n" +
    "            \"suit\": \"hearts\"\n" +
    "        },\n" +
    "        {\n" +
    "            \"rank\": \"6\",\n" +
    "            \"suit\": \"clubs\"\n" +
    "        }\n" +
    "    ]\n" +
    "}";

	def "The default folding player should fold"() {
		given:
		def gameState = new JsonSlurper().parseText('{"key1": "value1", "key2": "value2"}')

		expect:
		Player.betRequest(gameState) == 0
	}
	def "JsonSlurper"() {
		given:
		def gameState = new JsonSlurper().parseText(game_state)

		expect:
		gameState.small_blind == 10
	}
}
