package net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.role;

import org.bukkit.entity.Player;

/**
 * 役職クラス
 */
public abstract class Role {
    /**
     * 役職の日本語表記を返す
     *
     * @return 役職の日本語表記
     */
    public abstract String getRoleNameJa();

    /**
     * 夜開始時のアナウンス
     */
    public abstract void announceAtNight();

    /**
     * 夜の行動
     *
     * @param votingPlayer 行動するプレイヤー
     * @param targetPlayer 行動対象のプレイヤー
     * @return 行動成否
     */
    public abstract boolean voteAtNight(Player votingPlayer, Player targetPlayer);
}
