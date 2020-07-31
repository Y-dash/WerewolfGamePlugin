package net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.command;

import net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.Game;
import net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.role.Werewolf;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 人狼チャットへメッセージを送る
 */
public class RedCommand extends WerewolfGameCommand {
    /**
     * コンストラクタ
     *
     * @param game コマンド対象となるゲーム
     */
    public RedCommand(Game game) {
        super(game);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(game.isGameStatusNone()) {
            sender.sendMessage("ゲームが開始されていません");
            return false;
        }

        if(sender instanceof Player && !(game.getRole((Player) sender) instanceof Werewolf)) {
            sender.sendMessage("人狼チャットは生存中の人狼のみ使えます");
            return false;
        }

        game.tellWerewolf("<" + sender.getName() + "> " + Arrays.stream(args).collect(Collectors.joining(" ")));

        return true;
    }
}
