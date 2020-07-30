package net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.command;

import net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.Game;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 参加登録リストを全削除する
 */
public class ClearCommand extends WerewolfGameCommand {
    /**
     * コンストラクタ
     *
     * @param game コマンド対象となるゲーム
     */
    public ClearCommand(Game game) {
        super(game);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        game.registrantList.clear();
        sender.sendMessage("参加登録リストを全削除しました");
        return false;
    }
}
