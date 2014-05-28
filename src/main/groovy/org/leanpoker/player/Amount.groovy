package org.leanpoker.player

/**
 * @author xza
 */
class Amount
{
    static int call(game)
    {
        def Object me = game.players[game.in_action]
        return game.current_buy_in - me.bet
    }

}
