package net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.command;

import net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.Game;
import org.bukkit.command.CommandExecutor;

/**
 * ゲーム上のコマンド
 */
public abstract class WerewolfGameCommand implements CommandExecutor {
    protected Game game;

    /**
     * コンストラクタ
     *
     * @param game コマンド対象となるゲーム
     */
    protected WerewolfGameCommand(Game game) {
        this.game = game;
    }
}
