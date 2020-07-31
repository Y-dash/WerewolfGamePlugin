package net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin;

import net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.game_status.Daytime;
import net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.game_status.GameStatus;
import net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.game_status.Night;
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
import java.util.stream.Collectors;

/**
 * ゲーム管理クラス
 */
public class Game implements Listener {
    /** 役職リスト */
    public static final List<Role> DEFAULT_ROLE_LIST = new ArrayList<>(Arrays.asList(
            new Villager(), new Villager(), new Villager(), new FortuneTeller(), new Psychic(), new Hunter(), new Madman(), new Werewolf(), new Werewolf()));
//    public static final List<Role> DEFAULT_ROLE_LIST = new ArrayList<>(Arrays.asList(new Werewolf()));

    /** ゲームステータス */
    public GameStatus gameStatus = new None(0);
    /** 参加登録者リスト */
    public List<Player> registrantList = new ArrayList<>(Arrays.asList());
    /** 参加者マップ */
    public HashMap<Player, Role> participantMap = new HashMap<Player, Role>();
    /** 生存者マップ */
    public HashMap<Player, Role> survivorMap = new HashMap<Player, Role>();

    /**
     * ゲーム未開始状態か
     *
     * @return ゲーム未開始ならtrue
     */
    public boolean isGameStatusNone() {
        return (gameStatus instanceof None);
    }

    /**
     * 昼か
     *
     * @return 昼ならtrue
     */
    public boolean isGameStatusDaytime() {
        return (gameStatus instanceof Daytime);
    }

    /**
     * 夜か
     *
     * @return 夜ならtrue
     */
    public boolean isGameStatusNight() {
        return (gameStatus instanceof Night);
    }

    /**
     * 役職を返す
     *
     * @param player 対象プレイヤー
     * @return 役職
     */
    public Role getRole(Player player) {
        return participantMap.get(player);
    }

    /**
     * プレイヤーと役職内訳を返す
     *
     * @return 内訳
     */
    public String getPlayerAndRoles() {
        return "[内訳] " + participantMap.entrySet().stream().map(map -> map.getKey().getName() + "=" + map.getValue().getRoleNameJa())
                .collect(Collectors.joining(", "));
    }

    /**
     * サーバーコマンドを実行
     *
     * @param command コマンド文字列
     */
    public void executeServerCommand(String command) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
    }

    /**
     * タイトル描画
     *
     * @param title タイトル文字列
     * @param color カラー文字列
     */
    public void sendTitle(String title, String color) {
        executeServerCommand("title @a title {\"text\":\"" + title + "\", \"color\":\"" + color + "\"}");
    }

    /**
     * タイトル描画
     *
     * @param title タイトル文字列
     */
    public void sendTitle(String title) {
        sendTitle(title, "white");
    }

    /**
     * tellコマンドを送信
     *
     * @param targetPlayerName 対象プレイヤー名
     * @param message メッセージ文字列
     */
    public void tell(String targetPlayerName, String message) {
        executeServerCommand("tell " + targetPlayerName + " " + message);
    }

    /**
     * tellrawコマンドを送信
     *
     * @param targetPlayerName 対象プレイヤー名
     * @param message メッセージ文字列
     * @param color カラー文字列
     */
    public void tellraw(String targetPlayerName, String message, String color) {
        executeServerCommand("tellraw "+ targetPlayerName + " {\"text\":\"" + message + "\",\"color\":\"" + color + "\",\"italic\":true}");
    }

    /**
     * sayコマンドを送信
     *
     * @param message メッセージ文字列
     */
    public void say(String message) {
        executeServerCommand("say " + message);
    }

    /**
     * 人狼チャットを送信
     *
     * @param message メッセージ文字列
     */
    public void tellWerewolf(String message) {
        survivorMap.entrySet().stream().filter(map -> map.getValue() instanceof Werewolf).map(map -> map.getKey().getName())
                .forEach(name -> tellraw(name, message, "dark_red"));
    }
}
