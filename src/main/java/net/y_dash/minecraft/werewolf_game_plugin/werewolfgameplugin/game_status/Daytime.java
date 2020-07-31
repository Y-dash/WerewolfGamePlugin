package net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.game_status;

import net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.Game;
import org.bukkit.entity.Player;

import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.reverseOrder;

/**
 * 昼
 */
public class Daytime extends GameStatus {

    /**
     * コンストラクタ
     *
     * @param days 日数
     */
    public Daytime(int days) {
        super(days);
        Game.executeServerCommand("time set day");
        Game.sendTitle("[" + days + "日目] 昼");
        Game.tellraw("@a", "「/vw プレイヤー名」で投票してください。全員が投票を完了すると昼が終わります", "yellow");
    }

    @Override
    public boolean vote(Player votingPlayer, Player targetPlayer) {
        if(!isVotable(votingPlayer, targetPlayer)) {
            return false;
        }

        // 投票する
        actedPlayerList.add(votingPlayer);
        votedPlayerList.add(targetPlayer);
        votingPlayer.sendMessage(targetPlayer.getName() + "へ投票しました");

        // 投票終了判定
        if(votedPlayerList.size() < Game.survivorMap.size()) {
            return true;
        }

        // 処刑処理
        Stream<Map.Entry<Player, Long>> aggregatevotedPlayerStream = votedPlayerList.stream()
                .collect(Collectors.groupingBy(player -> player, Collectors.counting())).entrySet().stream()
                .sorted(Comparator.comparing(map -> map.getValue(), reverseOrder()));

        Game.tellraw(
                "@a",
                "[投票終了]\n" + aggregatevotedPlayerStream.map(map -> map.getValue().toString() + "票 : " + map.getKey().getName())
                        .collect(Collectors.joining("\n")),
                "yellow");

        Player executionPlayer = aggregatevotedPlayerStream.findFirst().get().getKey();
        killPlayer(executionPlayer, "処刑されました");

        // 勝利判定
        if(judge()) {
            return true;
        }

        Game.gameStatus = new Night(days, executionPlayer);
        return true;
    }
}
