package net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.role;

import net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.Game;
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
     * 夜の行動
     */
    public abstract void actAtNight();
}
