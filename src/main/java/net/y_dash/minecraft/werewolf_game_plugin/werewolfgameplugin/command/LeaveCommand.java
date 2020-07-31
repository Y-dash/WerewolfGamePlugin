package net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.command;

import net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.Game;
import net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.game_status.None;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * 参加登録から削除コマンド
 */
public class LeaveCommand extends WerewolfGameCommand {
    /**
     * コンストラクタ
     *
     * @param game コマンド対象となるゲーム
     */
    public LeaveCommand(Game game) {
        super(game);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!game.isGameStatusNone()) {
            sender.sendMessage("ゲーム開始後には削除できません");
            return false;
        }

        Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);

        if(!game.registrantList.contains(targetPlayer)) {
            sender.sendMessage(args[0] + "は登録されていません");
            return false;
        }

        game.registrantList.remove(targetPlayer);
        sender.sendMessage(args[0] + "を参加登録から削除しました");

        return true;
    }
}
