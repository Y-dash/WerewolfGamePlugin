package net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.command;

import net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.Game;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class EndCommand extends WerewolfGameCommand {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(Game.isGameStatusNone()) {
            sender.sendMessage("ゲームが開始されていません");
            return false;
        }

        Game.sendTitle("ゲーム中断");
        Game.endGame();

        return true;
    }
}
