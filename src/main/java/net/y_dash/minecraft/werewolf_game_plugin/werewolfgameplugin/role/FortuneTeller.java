package net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.role;

import net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.Game;
import org.bukkit.entity.Player;

/**
 * 占い師
 */
public class FortuneTeller extends VillagerTeamRole {
    @Override
    public String getRoleNameJa() {
        return "占い師";
    }

    @Override
    public void actAtNight() {
        // 占う
        return;
    }
}
