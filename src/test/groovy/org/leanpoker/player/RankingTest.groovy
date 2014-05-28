package org.leanpoker.player

import groovy.json.JsonSlurper
import spock.lang.Specification
import spock.lang.Unroll

class RankingTest extends Specification
{
    def "toJSON"()
    {
        given:
        def cards = [
                ["rank": "2", "suit": "hearts"],
                ["rank": "3", "suit": "spades"]
        ]
        when:
        def jsonCards = Ranking.toJSON(cards)
        then:
        jsonCards == '[{"rank":"2","suit":"hearts"},{"rank":"3","suit":"spades"}]'
    }

    def "ranking with less than 5 cards fails"()
    {
        given:
        def cards = [
                ["rank": "2", "suit": "hearts"],
                ["rank": "3", "suit": "spades"],
                ["rank": "4", "suit": "diamonds"],
                ["rank": "5", "suit": "clubs"]
        ]
        when:
        def ranking = Ranking.ofCards(cards)
        then:
        ranking.rank == null
        ranking.error == "Not enough cards"
    }

    @Unroll
    def "#name has rank #rank"()
    {
        expect:
        rank == Ranking.ofCards(cards).rank

        where:
        name              | rank || cards
        "High card"       | 0    || [
                ["rank": "2", "suit": "hearts"],
                ["rank": "3", "suit": "spades"],
                ["rank": "4", "suit": "diamonds"],
                ["rank": "5", "suit": "clubs"],
                ["rank": "7", "suit": "clubs"]
        ]
        "Pair"            | 1    || [
                ["rank": "2", "suit": "hearts"],
                ["rank": "2", "suit": "spades"],
                ["rank": "4", "suit": "diamonds"],
                ["rank": "5", "suit": "clubs"],
                ["rank": "7", "suit": "clubs"]
        ]
        "Two Pair"        | 2    || [
                ["rank": "2", "suit": "hearts"],
                ["rank": "2", "suit": "spades"],
                ["rank": "4", "suit": "diamonds"],
                ["rank": "4", "suit": "clubs"],
                ["rank": "7", "suit": "clubs"]
        ]
        "Three of a kind" | 3    || [
                ["rank": "2", "suit": "hearts"],
                ["rank": "2", "suit": "spades"],
                ["rank": "2", "suit": "diamonds"],
                ["rank": "5", "suit": "clubs"],
                ["rank": "7", "suit": "clubs"]
        ]
        "Straight"        | 4    || [
                ["rank": "2", "suit": "hearts"],
                ["rank": "3", "suit": "spades"],
                ["rank": "4", "suit": "diamonds"],
                ["rank": "5", "suit": "clubs"],
                ["rank": "6", "suit": "clubs"]
        ]
        "Flush"           | 5    || [
                ["rank": "2", "suit": "hearts"],
                ["rank": "3", "suit": "hearts"],
                ["rank": "4", "suit": "hearts"],
                ["rank": "5", "suit": "hearts"],
                ["rank": "7", "suit": "hearts"]
        ]
        "Full house"      | 6    || [
                ["rank": "2", "suit": "hearts"],
                ["rank": "2", "suit": "spades"],
                ["rank": "2", "suit": "diamonds"],
                ["rank": "7", "suit": "clubs"],
                ["rank": "7", "suit": "clubs"]
        ]
        "Four of a kind"  | 7    || [
                ["rank": "2", "suit": "hearts"],
                ["rank": "2", "suit": "spades"],
                ["rank": "2", "suit": "diamonds"],
                ["rank": "2", "suit": "clubs"],
                ["rank": "6", "suit": "clubs"]
        ]
        "Straight flush"  | 8    || [
                ["rank": "2", "suit": "hearts"],
                ["rank": "3", "suit": "hearts"],
                ["rank": "4", "suit": "hearts"],
                ["rank": "5", "suit": "hearts"],
                ["rank": "6", "suit": "hearts"]
        ]
    }

    def "ofGame"()
    {
        given:
        def  json = new File("src/test/resources/game.json").getText()
        def game = new JsonSlurper().parseText(json)
        def cards = Cards.ofGame(game)
        expect:
        Ranking.ofCards(cards) == Ranking.ofGame(game)
    }

}
