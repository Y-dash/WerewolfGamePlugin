package net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.command;

import net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.Game;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VoteCommand extends WerewolfGameCommand {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(Game.isGameStatusNone()) {
            sender.sendMessage("ゲームが開始されていません");
            return false;
        }

        Player targetPlayer = Game.getPlayer(args[0]);

        if(targetPlayer == null) {
            sender.sendMessage(args[0] + "が存在しません");
            return false;
        }

        return Game.gameStatus.vote((Player) sender, targetPlayer);
    }
}
