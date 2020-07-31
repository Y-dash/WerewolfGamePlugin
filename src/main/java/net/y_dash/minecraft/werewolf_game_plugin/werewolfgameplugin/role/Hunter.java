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
    public void announceAtNight() {
        Game.survivorMap.entrySet().stream().filter(map -> map.getValue() instanceof Hunter)
                .forEach(map -> Game.tellraw(map.getKey().getName(),
                        "「/vw プレイヤー名」で守る相手を選んでください。全員が行動を完了すると夜が終わります",
                        "yellow"));
    }

    @Override
    public boolean voteAtNight(Player votingPlayer, Player targetPlayer) {
        if(votingPlayer == targetPlayer) {
            votingPlayer.sendMessage("自分自身は守れません");
            return false;
        }

        // 守り先の選択
        Game.gameStatus.protectedPlayer = targetPlayer;
        votingPlayer.sendMessage(targetPlayer.getName() + "を守り先に選択しました");

        Game.gameStatus.actedPlayerList.add(votingPlayer);
        return true;
    }
}
