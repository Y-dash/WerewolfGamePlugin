package net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin;

import net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.command.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class WerewolfGamePlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        Game game = new Game();

        getServer().getPluginManager().registerEvents(game, this);

        this.getCommand("wjoin").setExecutor(new JoinCommand());
        this.getCommand("wleave").setExecutor(new LeaveCommand());
        this.getCommand("wclear").setExecutor(new ClearCommand());
        this.getCommand("wlist").setExecutor(new ListCommand());
        this.getCommand("wstart").setExecutor(new StartCommand());
        this.getCommand("wend").setExecutor(new EndCommand());
        this.getCommand("wred").setExecutor(new RedCommand());
        this.getCommand("wv").setExecutor(new VoteCommand());
    }
}
