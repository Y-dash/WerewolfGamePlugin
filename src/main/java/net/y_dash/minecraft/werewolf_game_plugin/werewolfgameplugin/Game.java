package net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin;

import net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.game_status.GameStatus;
import net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.game_status.None;
import net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.role.*;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * ゲーム管理クラス
 */
public class Game implements Listener {
    /** 役職リスト */
    public static final List<Class> DEFAULT_ROLE_LIST = new ArrayList<>(Arrays.asList(
            Villager.class, Villager.class, Villager.class, FortuneTeller.class, Psychic.class, Hunter.class, Madman.class, Werewolf.class, Werewolf.class));

    /** ゲームステータス */
    public GameStatus gameStatus = new None(0);
    /** 参加登録者リスト */
    public List<Player> registrantList = new ArrayList<>(Arrays.asList());
    /** 参加者マップ */
    public HashMap<Player, Role> participantMap = new HashMap<Player, Role>();
    /** 生存者マップ */
    public HashMap<Player, Role> survivorMap = new HashMap<Player, Role>();

}
