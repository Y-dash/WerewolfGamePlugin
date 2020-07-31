package net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.command;

import net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.Game;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * 参加登録コマンド
 */
public class JoinCommand extends WerewolfGameCommand {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!Game.isGameStatusNone()) {
            sender.sendMessage("ゲーム開始後には追加できません");
            return false;
        }

        if(Game.registrantList.size() >= Game.DEFAULT_ROLE_LIST.size()) {
            sender.sendMessage("参加登録者が満員です。/wleaveでmcid指定削除、もしくは/wclearで全削除してから追加してください");
            return false;
        }

        Player targetPlayer = Game.getPlayer(args[0]);

        if(targetPlayer == null) {
            sender.sendMessage(args[0] + "が存在しません");
            return false;
        }

        if(Game.registrantList.contains(targetPlayer)) {
            sender.sendMessage(args[0] + "はすでに登録されています");
            return false;
        }

        Game.registrantList.add(targetPlayer);
        sender.sendMessage(args[0] + "を参加登録しました");

        return true;
    }
}
