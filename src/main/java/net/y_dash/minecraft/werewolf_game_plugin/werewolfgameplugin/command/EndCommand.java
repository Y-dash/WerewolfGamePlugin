package net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.command;

import net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.Game;
import net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.game_status.None;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class EndCommand extends WerewolfGameCommand {
    /**
     * コンストラクタ
     *
     * @param game コマンド対象となるゲーム
     */
    public EndCommand(Game game) {
        super(game);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(game.isGameStatusNone()) {
            sender.sendMessage("ゲームが開始されていません");
            return false;
        }

        game.sendTitle("ゲーム中断");
        game.say(game.getPlayerAndRoles());

        game.gameStatus = new None(0);
        game.participantMap.clear();
        game.survivorMap.clear();

        return true;
    }
}
