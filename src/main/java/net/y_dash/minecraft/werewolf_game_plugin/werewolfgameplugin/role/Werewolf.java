package net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.role;

import net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.Game;
import org.bukkit.entity.Player;

public class Werewolf extends WerewolfTeamRole {
    @Override
    public String getRoleNameJa() {
        return "人狼";
    }

    @Override
    public void announceAtNight() {
        Game.survivorMap.entrySet().stream().filter(map -> map.getValue() instanceof Werewolf)
                .forEach(map -> Game.tellraw(map.getKey().getName(),
                        "「/vw プレイヤー名」で噛む相手へ投票してください。全員が行動を完了すると夜が終わります",
                        "yellow"));
    }

    @Override
    public boolean voteAtNight(Player votingPlayer, Player targetPlayer) {
        if(Game.getRole(targetPlayer) instanceof Werewolf) {
            votingPlayer.sendMessage("人狼には噛みつけません");
            return false;
        }

        Game.gameStatus.votedPlayerList.add(targetPlayer);
        votingPlayer.sendMessage(targetPlayer.getName() + "へ投票しました");

        Game.gameStatus.actedPlayerList.add(votingPlayer);
        return true;
    }
}
