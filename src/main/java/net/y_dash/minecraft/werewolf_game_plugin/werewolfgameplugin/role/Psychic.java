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
    public void announceAtNight() {
        Game.survivorMap.entrySet().stream().filter(map -> map.getValue() instanceof Psychic)
                .forEach(map -> {
                    Game.tellraw(map.getKey().getName(),
                            "[霊能結果] 「" + Game.gameStatus.executedPlayerAtDaytime.getName() + "は"
                                    + ((Game.getRole(Game.gameStatus.executedPlayerAtDaytime) instanceof Werewolf) ? "人狼である" : "人狼ではない") + "」",
                            "yellow");
                    Game.gameStatus.actedPlayerList.add(map.getKey());
                });
    }

    @Override
    public boolean voteAtNight(Player votingPlayer, Player targetPlayer) {
        return false;
    }
}
