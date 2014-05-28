package org.leanpoker.player

class PreFlopPlayer extends Player
{
    static int betAmount(final game)
    {
        def Object me = game.players[game.in_action]
        def holeCards = me.hole_cards
        def score = Score.of(holeCards)

        if (Math.random() < 0.05)
        {
            return Amount.allIn(game)(game)
        };

        if (score < 3)
        {
            return Amount.allIn(game);
            if (Amount.call(game) < game.small_blind * 4)
            {
                return game.small_blind * 4
            }
            return Amount.call(game)
        }


        if (Math.random() < 0.8 && Amount.call(game) < 40)
        {
            return Amount.call(game)
        }

        if (Math.random() < 0.25 && Amount.call(game) < (7 * game.small_blind))
        {
            return Math.min(Amount.call(game), (int) (Math.random() * 0.7 * game.small_blind));

        }
        if (score <= 6 && Math.random() > 0.65)
        {
            return Amount.call(game)
        }

        if (safeCallAmount(me.stack, Amount.call(game), score))
        {
            return Amount.call(game)
        };

        return 0;

    }

    static Boolean safeCallAmount(def stack, def callAmount, def score)
    {
        if (score >= 8)
        {
            return false
        }

        return Math.max(stack / (2 + score * score), 30.0f - (2 * score)) > callAmount
    }

}
