package net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.game_status;

import org.bukkit.entity.Player;

/**
 * 未ゲーム状態
 */
public class None extends GameStatus {

    /**
     * コンストラクタ
     *
     * @param days 日数
     */
    public None(int days) {
        super(days);
    }

    @Override
    public boolean vote(Player votingPlayer, Player targetPlayer) {
        return false;
    }
}
