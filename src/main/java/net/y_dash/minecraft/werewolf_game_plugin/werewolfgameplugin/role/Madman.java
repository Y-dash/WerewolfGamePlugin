package net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.role;

import net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.Game;
import org.bukkit.entity.Player;

public class Madman extends WerewolfTeamRole {
    @Override
    public String getRoleNameJa() {
        return "狂人";
    }

    @Override
    public void announceAtNight() {
        Game.survivorMap.entrySet().stream().filter(map -> map.getValue() instanceof Madman)
                .forEach(map -> {
                    Game.tellraw(map.getKey().getName(),
                            "狂人が夜に使える能力はありません。能力を持つ全員が行動を完了すると夜が終わります",
                            "yellow");
                    Game.gameStatus.actedPlayerList.add(map.getKey());
                });

    }

    @Override
    public boolean voteAtNight(Player votingPlayer, Player targetPlayer) {
        return false;
    }
}
