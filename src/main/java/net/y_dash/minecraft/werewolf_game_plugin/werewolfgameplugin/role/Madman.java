package net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.role;

import net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.Game;
import org.bukkit.entity.Player;

public class Madman extends WerewolfTeamRole {
    @Override
    public String getRoleNameJa() {
        return "狂人";
    }

    @Override
    public void actAtNight() {
        return;
    }
}
