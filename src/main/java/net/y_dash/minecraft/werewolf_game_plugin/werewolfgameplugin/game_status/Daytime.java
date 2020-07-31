package net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.game_status;

import org.bukkit.Bukkit;

/**
 * 昼
 */
public class Daytime extends GameStatus {
    /**
     * コンストラクタ
     *
     * @param days 何日目か
     */
    public Daytime(int days) {
        super(days);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "time set day");
    }
}
