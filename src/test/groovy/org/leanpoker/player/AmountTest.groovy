package org.leanpoker.player

import spock.lang.Specification

class AmountTest extends Specification
{
    def "call"()
    {
        given:
        def game = [
                "current_buy_in": 320,
                "in_action": "1",
                "players": [
                        "1": ["bet": 80]
                ]
        ]
        expect:
        Amount.call(game) == 240
    }

    def "all in"()
    {
        given:
        def game = [
                "players": [
                        "0": [],
                        "1": [],
                        "2": []
                ]
        ]
        expect:
        Amount.allIn(game) == 3000
    }

}
