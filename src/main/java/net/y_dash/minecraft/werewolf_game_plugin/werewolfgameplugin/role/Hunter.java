package net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.role;

import net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.Game;
import org.bukkit.entity.Player;

/**
 * 狩人
 */
public class Hunter extends VillagerTeamRole {
    @Override
    public String getRoleNameJa() {
        return "狩人";
    }

    @Override
    public void actAtNight() {
        // 守り先決める
        return;
    }
}
