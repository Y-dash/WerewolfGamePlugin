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
    public void announceAtNight() {
        Game.survivorMap.entrySet().stream().filter(map -> map.getValue() instanceof FortuneTeller)
                .forEach(map -> Game.tellraw(map.getKey().getName(),
                        "「/vw プレイヤー名」で占う相手を選んでください。全員が行動を完了すると夜が終わります",
                        "yellow"));
    }

    @Override
    public boolean voteAtNight(Player votingPlayer, Player targetPlayer) {
        if(votingPlayer == targetPlayer) {
            votingPlayer.sendMessage("自分自身は占えません");
            return false;
        }

        Game.tellraw(votingPlayer.getName(),
                "[占い結果] 「" + targetPlayer.getName() + "は"
                        + ((Game.getRole(targetPlayer) instanceof Werewolf) ? "人狼である" : "人狼ではない") + "」",
                "yellow");

        Game.gameStatus.actedPlayerList.add(votingPlayer);
        return true;
    }
}
