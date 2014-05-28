package org.leanpoker.player

/**
 * @author xza
 */
class Amount
{
    public static final int INITIAL_STACK = 1000

    static int call(game)
    {
        def Object me = game.players[game.in_action]
        game.current_buy_in - me.bet
    }

    static int allIn(game)
    {
        game.players.size() * INITIAL_STACK
    }

}
