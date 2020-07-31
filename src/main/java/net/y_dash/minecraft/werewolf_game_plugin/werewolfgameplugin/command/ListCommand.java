package net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.command;

import net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.Game;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.stream.Collectors;

/**
 * 参加登録リストを表示する
 */
public class ListCommand extends WerewolfGameCommand {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage("[参加登録リスト] (" + Game.registrantList.size() + "名) : " + Game.registrantList.stream().map(
                player -> player.getName()).collect(Collectors.joining(", ")));
        return true;
    }
}