package org.leanpoker.player

class PostFlopPlayer extends Player
{
    static int betAmount(final game)
    {
        if (Ranking.ofGame(game) > 1)
        {
            return Amount.allIn(game)
        };
        if (Ranking.ofCards(game) == 1)
        {
            return Amount.call(game)
        };
        return 0;
    }
}
