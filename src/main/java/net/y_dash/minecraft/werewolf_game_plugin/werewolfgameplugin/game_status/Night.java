package net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.game_status;

import net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.Game;
import org.bukkit.entity.Player;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Comparator.reverseOrder;

/**
 * 夜
 */
public class Night extends GameStatus {
    /**
     * コンストラクタ
     *
     * @param days 日数
     */
    public Night(int days, Player executedPlayerAtDaytime) {
        super(days);
        Game.executeServerCommand("time set night");
        Game.sendTitle("[" + days + "日目] 夜");
        Game.survivorMap.entrySet().stream().forEach(map -> map.getValue().announceAtNight());
    }

    @Override
    public boolean vote(Player votingPlayer, Player targetPlayer) {
        if(!isVotable(votingPlayer, targetPlayer)) {
            return false;
        }

        // 役職ごとの行動
        if(Game.survivorMap.get(votingPlayer).voteAtNight(votingPlayer, targetPlayer)){
            return false;
        }

        // 行動終了判定
        if(votedPlayerList.size() < Game.survivorMap.size()) {
            return true;
        }

        // 狼かみ
        List<Map.Entry<Player, Long>> sortedvotedPlayerList = getSortedvotedPlayerList();
        Player killedPlayer = sortedvotedPlayerList.get(sortedvotedPlayerList.size() - 1).getKey();

        if(killedPlayer == Game.gameStatus.protectedPlayer) {
            Game.tellraw("@a", "犠牲者は出ませんでした", "yellow");
        } else {
            killPlayer(killedPlayer, "死亡しました");
        }

        // 終了判定
        if(judge()) {
            return true;
        }

        Game.gameStatus = new Daytime(days++);
        return true;
    }
}
