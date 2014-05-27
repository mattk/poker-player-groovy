package org.leanpoker.player

/**
 * @author xza
 */
class PreFlopPlayer extends Player
{
    static int betAmount(final game) {
        def Object me = game.players[game.in_action]
        def holeCards = me.hole_cards
        def coolCards = countCoolCards(holeCards)
        def ranksEqual = areRanksEqual(holeCards)

        if (ranksEqual || coolCards == 2)
            return ALLIN;

        if (coolCards == 1 && safeCallAmount(game))
            return callAmount(game);

        return 0;

    }

    static Boolean safeCallAmount(def game) {

        def Object me = game.players[game.in_action]

        return Math.max(me.stack/10,30.0f) > callAmount(game)




    }
    static int countCoolCards(def cards)
    {
        def cool = 0
        if (Ranks.bySymbol(cards[0].rank).ordinal() >= 8) cool++
        if (Ranks.bySymbol(cards[1].rank).ordinal() >= 8) cool++
        return cool
    }


    static enum Ranks {
        TWO('2'),
        THREE('3'),
        FOUR('4'),
        FIVES('5'),
        SIX('6'),
        SEVEN('7'),
        EIGHT('8'),
        NINE('9'),
        TEN('10'),
        JACK('J'),
        QUEEN('Q'),
        KING('K'),
        ACE('A');

        Ranks(String symbol) { this.symbol = symbol }

        private final String symbol

        String symbol() { return symbol }

        static Ranks bySymbol(String symbol) {
            for (Ranks r  : Ranks.values() ) {
                if (r.symbol== symbol)return r;
            }
            return null;
        }
    }

}
