package net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.role;

import net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.Game;
import org.bukkit.entity.Player;

/**
 * 村人
 */
public class Villager extends VillagerTeamRole {
    @Override
    public String getRoleNameJa() {
        return "村人";
    }

    @Override
    public void actAtNight() {
        return;
    }
}
