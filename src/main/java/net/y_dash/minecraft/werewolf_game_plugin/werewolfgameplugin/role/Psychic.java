package net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.role;

import net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.Game;
import org.bukkit.entity.Player;

/**
 * 霊能者
 */
public class Psychic extends VillagerTeamRole {
    @Override
    public String getRoleNameJa() {
        return "霊能者";
    }

    @Override
    public void actAtNight() {
        // 霊能結果見る
        return;
    }
}
