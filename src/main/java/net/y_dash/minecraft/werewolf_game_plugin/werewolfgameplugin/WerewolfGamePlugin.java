package net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin;

import net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.command.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class WerewolfGamePlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        Game game = new Game();

        getServer().getPluginManager().registerEvents(game, this);
        this.getCommand("wjoin").setExecutor(new JoinCommand(game));
        this.getCommand("wleave").setExecutor(new LeaveCommand(game));
        this.getCommand("wclear").setExecutor(new ClearCommand(game));
        this.getCommand("wlist").setExecutor(new ListCommand(game));
        this.getCommand("wstart").setExecutor(new StartCommand(game));
        this.getCommand("wend").setExecutor(new EndCommand(game));
        this.getCommand("wred").setExecutor(new RedCommand(game));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
