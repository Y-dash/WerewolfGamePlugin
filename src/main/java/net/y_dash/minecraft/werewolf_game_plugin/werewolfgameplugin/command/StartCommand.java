package net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.command;

import net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.Game;
import net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.game_status.Daytime;
import net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.game_status.None;
import net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.role.Hunter;
import net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.role.Werewolf;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Random;

public class StartCommand extends WerewolfGameCommand {
    /**
     * コンストラクタ
     *
     * @param game コマンド対象となるゲーム
     */
    public StartCommand(Game game) {
        super(game);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!game.isGameStatusNone()) {
            sender.sendMessage("すでにゲームが開始されています。実行中のゲームを終了するには/wend");
            return false;
        }

        if(game.registrantList.size() < game.DEFAULT_ROLE_LIST.size()) {
            sender.sendMessage("参加登録人数が足りません。あと" + (game.DEFAULT_ROLE_LIST.size() -  game.registrantList.size()) + "名必要です");
            return false;
        }

        // 役職割り当て
        List<Player> unemployedList = game.registrantList;
        for(int i = 0; i < game.DEFAULT_ROLE_LIST.size(); i++) {
            int randomNumber = new Random().nextInt(unemployedList.size());

            game.participantMap.put(unemployedList.get(randomNumber), game.DEFAULT_ROLE_LIST.get(i));
            unemployedList.remove(randomNumber);
        }
        game.survivorMap = game.participantMap;

        // 役職お知らせ
        game.sendTitle("役職開示");
        game.survivorMap.forEach((player, role) -> {
            game.tellraw(player.getName(), "あなたの役職は「" + role.getRoleNameJa() + "」です", "yellow");
        });
        game.tellWerewolf("人狼は「/wred 発言チャット」で人狼チャットへ発言できます");

        // 昼を始める
        game.gameStatus = new Daytime(1);

        return true;
    }
}
