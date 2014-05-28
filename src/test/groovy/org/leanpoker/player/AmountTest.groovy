package org.leanpoker.player

import spock.lang.Specification

class AmountTest extends Specification
{
    def "call"()
    {
        given:
        def foo = [
                "current_buy_in": 320,
                "in_action": "1",
                "players": [
                        "1": ["bet": 80]
                ]
        ]
        expect:
        Amount.call(foo) == 240
    }

}
