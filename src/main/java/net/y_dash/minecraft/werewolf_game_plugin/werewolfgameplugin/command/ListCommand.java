package net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.command;

import net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.Game;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 参加登録リストを表示する
 */
public class ListCommand extends WerewolfGameCommand {
    /**
     * コンストラクタ
     *
     * @param game コマンド対象となるゲーム
     */
    public ListCommand(Game game) {
        super(game);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        List<Player> registrantList = game.registrantList;
        sender.sendMessage("参加登録中 [" + registrantList.size() + "名] : " + registrantList.stream().map(
                player -> player.getName()).collect(Collectors.joining(", ")));
        return true;
    }
}