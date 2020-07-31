package net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.command;

import net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.Game;
import net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.game_status.None;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getLogger;

/**
 * 参加登録コマンド
 */
public class JoinCommand extends WerewolfGameCommand {
    /**
     * コンストラクタ
     *
     * @param game コマンド対象となるゲーム
     */
    public JoinCommand(Game game) {
        super(game);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!game.isGameStatusNone()) {
            sender.sendMessage("ゲーム開始後には追加できません");
            return false;
        }

        if(game.registrantList.size() >= game.DEFAULT_ROLE_LIST.size()) {
            sender.sendMessage("参加登録者が満員です。/wleaveでmcid指定削除、もしくは/wclearで全削除してから追加してください");
            return false;
        }

        Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);

        if(targetPlayer == null) {
            sender.sendMessage(args[0] + "が存在しません");
            return false;
        }

        if(game.registrantList.contains(targetPlayer)) {
            sender.sendMessage(args[0] + "はすでに登録されています");
            return false;
        }

        game.registrantList.add(targetPlayer);
        sender.sendMessage(args[0] + "を参加登録しました");

        return true;
    }
}
