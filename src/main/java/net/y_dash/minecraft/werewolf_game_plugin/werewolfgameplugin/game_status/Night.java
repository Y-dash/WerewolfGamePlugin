package net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.game_status;

import org.bukkit.Bukkit;

/**
 * 夜
 */
public class Night extends GameStatus {
    /**
     * コンストラクタ
     *
     * @param days 何日目か
     */
    public Night(int days) {
        super(days);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "time set night ");
    }
}
