package net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.command;

import net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.Game;
import net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.game_status.Daytime;
import net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.role.Role;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;
import java.util.Random;

public class StartCommand extends WerewolfGameCommand {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!Game.isGameStatusNone()) {
            sender.sendMessage("すでにゲームが開始されています。実行中のゲームを終了するには/wend");
            return false;
        }

        if(Game.registrantList.size() < Game.DEFAULT_ROLE_LIST.size()) {
            Game.tellraw("@a",
                    "このゲームは" + Game.DEFAULT_ROLE_LIST.size() + "人推奨ですが、" + Game.registrantList.size()
                            + "人しか参加登録されていません。ゲームをやりなおす場合はOPから/wendコマンドで一度終了してください。現在の参加登録は維持されます",
                    "yellow");
        }

        // 役職割り当て
        Game.participantMap.clear();
        Game.survivorMap.clear();

        List<Role> unallocatedRoleList = Game.DEFAULT_ROLE_LIST;

        Game.registrantList.forEach(player -> {
            int randomNumber = new Random().nextInt(unallocatedRoleList.size());

            Game.participantMap.put(player, unallocatedRoleList.get(randomNumber));
            unallocatedRoleList.remove(randomNumber);
        });

        Game.survivorMap = Game.participantMap;

        // 役職お知らせ
        Game.sendTitle("役職開示");
        Game.survivorMap.forEach((player, role) -> {
            Game.tellraw(player.getName(), "あなたの役職は「" + role.getRoleNameJa() + "」です", "yellow");
        });
        Game.tellWerewolf("人狼は「/wred 発言チャット」で人狼チャットへ発言できます");

        // 昼を始める
        Game.gameStatus = new Daytime(1);

        return true;
    }
}
