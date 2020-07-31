package net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.command;

import net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.Game;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 * 参加登録リストを全削除する
 */
public class ClearCommand extends WerewolfGameCommand {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!Game.isGameStatusNone()) {
            sender.sendMessage("ゲーム開始後には削除できません");
            return false;
        }

        Game.registrantList.clear();
        sender.sendMessage("参加登録リストを全削除しました");
        return true;
    }
}
