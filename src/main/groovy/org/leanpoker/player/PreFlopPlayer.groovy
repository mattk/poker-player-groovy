package org.leanpoker.player

/**
 * @author xza
 */
class PreFlopPlayer extends Player {
    static int betAmount(final game) {
        def Object me = game.players[game.in_action]
        def holeCards = me.hole_cards
        def score = scoreHand(holeCards)
        
        if (score < 3) {
            if (Math.random() > 0.5) {
                return ALLIN;
            }
            if(callAmount(game) < game.small_blind *4) {
                return game.small_blind *4
            }
            return callAmount(game)
        }


        if (Math.random() < 0.8 && callAmount(game) < 40)
            return callAmount(game)

        if(Math.random()< 0.25 && callAmount(game) < (7*game.small_blind)) {
            return Math.min(callAmount(game), Integer.valueOf(Math.random() * 0.7 * game.small_blind));

        }
        if(score <=6 && Math.random() > 0.65) {
            return callAmount(game)
        }

        if (safeCallAmount(me.stack,callAmount(game), score))
            return callAmount(game);

        return 0;

    }

    static Boolean safeCallAmount(def stack,def callAmount,def score) {
        if(score >= 8) return false

        return Math.max(stack/(2+score*score),30.0f-(2*score)) > callAmount
    }

    static int countCoolCards(def cards) {
        def cool = 0
        if (Ranks.bySymbol(cards[0].rank).ordinal() >= 8) cool++
        if (Ranks.bySymbol(cards[1].rank).ordinal() >= 8) cool++
        return cool
    }


    static enum Ranks {
        TWO('2'),
        THREE('3'),
        FOUR('4'),
        FIVE('5'),
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
            for (Ranks r : Ranks.values()) {
                if (r.symbol == symbol) return r;
            }
            return null;
        }

    }
    static int scoreHand(cards) {
        def card1 = cards[0]
        def card2 = cards[1]

        if (Ranks.ACE.symbol() == card1.rank && Ranks.ACE.symbol() == card2.rank) return 1; if (Ranks.ACE.symbol() == card1.rank && Ranks.KING.symbol() == card2.rank) return 1; if (Ranks.ACE.symbol() == card1.rank && Ranks.QUEEN.symbol() == card2.rank) return 2; if (Ranks.ACE.symbol() == card1.rank && Ranks.JACK.symbol() == card2.rank) return 2; if (Ranks.ACE.symbol() == card1.rank && Ranks.TEN.symbol() == card2.rank) return 3; if (Ranks.ACE.symbol() == card1.rank && Ranks.NINE.symbol() == card2.rank) return 5; if (Ranks.ACE.symbol() == card1.rank && Ranks.EIGHT.symbol() == card2.rank) return 5; if (Ranks.ACE.symbol() == card1.rank && Ranks.SEVEN.symbol() == card2.rank) return 5; if (Ranks.ACE.symbol() == card1.rank && Ranks.SIX.symbol() == card2.rank) return 5; if (Ranks.ACE.symbol() == card1.rank && Ranks.FIVE.symbol() == card2.rank) return 5; if (Ranks.ACE.symbol() == card1.rank && Ranks.FOUR.symbol() == card2.rank) return 5; if (Ranks.ACE.symbol() == card1.rank && Ranks.THREE.symbol() == card2.rank) return 5; if (Ranks.ACE.symbol() == card1.rank && Ranks.TWO.symbol() == card2.rank) return 5;
        if (Ranks.KING.symbol() == card1.rank && Ranks.ACE.symbol() == card2.rank) return 2; if (Ranks.KING.symbol() == card1.rank && Ranks.KING.symbol() == card2.rank) return 1; if (Ranks.KING.symbol() == card1.rank && Ranks.QUEEN.symbol() == card2.rank) return 2; if (Ranks.KING.symbol() == card1.rank && Ranks.JACK.symbol() == card2.rank) return 3; if (Ranks.KING.symbol() == card1.rank && Ranks.TEN.symbol() == card2.rank) return 4; if (Ranks.KING.symbol() == card1.rank && Ranks.NINE.symbol() == card2.rank) return 6; if (Ranks.KING.symbol() == card1.rank && Ranks.EIGHT.symbol() == card2.rank) return 7; if (Ranks.KING.symbol() == card1.rank && Ranks.SEVEN.symbol() == card2.rank) return 7; if (Ranks.KING.symbol() == card1.rank && Ranks.SIX.symbol() == card2.rank) return 7; if (Ranks.KING.symbol() == card1.rank && Ranks.FIVE.symbol() == card2.rank) return 7; if (Ranks.KING.symbol() == card1.rank && Ranks.FOUR.symbol() == card2.rank) return 7; if (Ranks.KING.symbol() == card1.rank && Ranks.THREE.symbol() == card2.rank) return 7; if (Ranks.KING.symbol() == card1.rank && Ranks.TWO.symbol() == card2.rank) return 7;
        if (Ranks.QUEEN.symbol() == card1.rank && Ranks.ACE.symbol() == card2.rank) return 3; if (Ranks.QUEEN.symbol() == card1.rank && Ranks.KING.symbol() == card2.rank) return 4; if (Ranks.QUEEN.symbol() == card1.rank && Ranks.QUEEN.symbol() == card2.rank) return 1; if (Ranks.QUEEN.symbol() == card1.rank && Ranks.JACK.symbol() == card2.rank) return 3; if (Ranks.QUEEN.symbol() == card1.rank && Ranks.TEN.symbol() == card2.rank) return 4; if (Ranks.QUEEN.symbol() == card1.rank && Ranks.NINE.symbol() == card2.rank) return 5; if (Ranks.QUEEN.symbol() == card1.rank && Ranks.EIGHT.symbol() == card2.rank) return 7;
        if (Ranks.JACK.symbol() == card1.rank && Ranks.ACE.symbol() == card2.rank) return 4; if (Ranks.JACK.symbol() == card1.rank && Ranks.KING.symbol() == card2.rank) return 5; if (Ranks.JACK.symbol() == card1.rank && Ranks.QUEEN.symbol() == card2.rank) return 5; if (Ranks.JACK.symbol() == card1.rank && Ranks.JACK.symbol() == card2.rank) return 1; if (Ranks.JACK.symbol() == card1.rank && Ranks.TEN.symbol() == card2.rank) return 3; if (Ranks.JACK.symbol() == card1.rank && Ranks.NINE.symbol() == card2.rank) return 4; if (Ranks.JACK.symbol() == card1.rank && Ranks.EIGHT.symbol() == card2.rank) return 6; if (Ranks.JACK.symbol() == card1.rank && Ranks.SEVEN.symbol() == card2.rank) return 8;
        if (Ranks.TEN.symbol() == card1.rank && Ranks.ACE.symbol() == card2.rank) return 6; if (Ranks.TEN.symbol() == card1.rank && Ranks.KING.symbol() == card2.rank) return 6; if (Ranks.TEN.symbol() == card1.rank && Ranks.QUEEN.symbol() == card2.rank) return 6; if (Ranks.TEN.symbol() == card1.rank && Ranks.JACK.symbol() == card2.rank) return 5; if (Ranks.TEN.symbol() == card1.rank && Ranks.TEN.symbol() == card2.rank) return 2; if (Ranks.TEN.symbol() == card1.rank && Ranks.NINE.symbol() == card2.rank) return 4; if (Ranks.TEN.symbol() == card1.rank && Ranks.EIGHT.symbol() == card2.rank) return 5; if (Ranks.TEN.symbol() == card1.rank && Ranks.SEVEN.symbol() == card2.rank) return 7;
        if (Ranks.NINE.symbol() == card1.rank && Ranks.ACE.symbol() == card2.rank) return 8; if (Ranks.NINE.symbol() == card1.rank && Ranks.KING.symbol() == card2.rank) return 8; if (Ranks.NINE.symbol() == card1.rank && Ranks.QUEEN.symbol() == card2.rank) return 8; if (Ranks.NINE.symbol() == card1.rank && Ranks.JACK.symbol() == card2.rank) return 7; if (Ranks.NINE.symbol() == card1.rank && Ranks.TEN.symbol() == card2.rank) return 7; if (Ranks.NINE.symbol() == card1.rank && Ranks.NINE.symbol() == card2.rank) return 3; if (Ranks.NINE.symbol() == card1.rank && Ranks.EIGHT.symbol() == card2.rank) return 4; if (Ranks.NINE.symbol() == card1.rank && Ranks.SEVEN.symbol() == card2.rank) return 5; if (Ranks.NINE.symbol() == card1.rank && Ranks.SIX.symbol() == card2.rank) return 8;
        if (Ranks.EIGHT.symbol() == card1.rank && Ranks.JACK.symbol() == card2.rank) return 8; if (Ranks.EIGHT.symbol() == card1.rank && Ranks.TEN.symbol() == card2.rank) return 8; if (Ranks.EIGHT.symbol() == card1.rank && Ranks.NINE.symbol() == card2.rank) return 7; if (Ranks.EIGHT.symbol() == card1.rank && Ranks.EIGHT.symbol() == card2.rank) return 4; if (Ranks.EIGHT.symbol() == card1.rank && Ranks.SEVEN.symbol() == card2.rank) return 5; if (Ranks.EIGHT.symbol() == card1.rank && Ranks.SIX.symbol() == card2.rank) return 6; if (Ranks.EIGHT.symbol() == card1.rank && Ranks.FIVE.symbol() == card2.rank) return 8;
        if (Ranks.SEVEN.symbol() == card1.rank && Ranks.EIGHT.symbol() == card2.rank) return 8; if (Ranks.SEVEN.symbol() == card1.rank && Ranks.SEVEN.symbol() == card2.rank) return 5; if (Ranks.SEVEN.symbol() == card1.rank && Ranks.SIX.symbol() == card2.rank) return 5; if (Ranks.SEVEN.symbol() == card1.rank && Ranks.FIVE.symbol() == card2.rank) return 7; if (Ranks.SEVEN.symbol() == card1.rank && Ranks.FOUR.symbol() == card2.rank) return 8;
        if (Ranks.SIX.symbol() == card1.rank && Ranks.SEVEN.symbol() == card2.rank) return 8; if (Ranks.SIX.symbol() == card1.rank && Ranks.SIX.symbol() == card2.rank) return 6; if (Ranks.SIX.symbol() == card1.rank && Ranks.FIVE.symbol() == card2.rank) return 5; if (Ranks.SIX.symbol() == card1.rank && Ranks.FOUR.symbol() == card2.rank) return 7;
        if (Ranks.FIVE.symbol() == card1.rank && Ranks.SIX.symbol() == card2.rank) return 8; if (Ranks.FIVE.symbol() == card1.rank && Ranks.FIVE.symbol() == card2.rank) return 6; if (Ranks.FIVE.symbol() == card1.rank && Ranks.FOUR.symbol() == card2.rank) return 6; if (Ranks.FIVE.symbol() == card1.rank && Ranks.THREE.symbol() == card2.rank) return 8;
        if (Ranks.FOUR.symbol() == card1.rank && Ranks.FIVE.symbol() == card2.rank) return 8; if (Ranks.FOUR.symbol() == card1.rank && Ranks.FOUR.symbol() == card2.rank) return 7; if (Ranks.FOUR.symbol() == card1.rank && Ranks.THREE.symbol() == card2.rank) return 7; if (Ranks.FOUR.symbol() == card1.rank && Ranks.TWO.symbol() == card2.rank) return 8;
        if (Ranks.THREE.symbol() == card1.rank && Ranks.THREE.symbol() == card2.rank) return 7; if (Ranks.THREE.symbol() == card1.rank && Ranks.TWO.symbol() == card2.rank) return 8;
        if (Ranks.TWO.symbol() == card1.rank && Ranks.TWO.symbol() == card2.rank) return 7;

        return 10;
    }
}
