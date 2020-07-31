package net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin;

import net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.game_status.GameStatus;
import net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.game_status.None;
import net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.role.*;
import org.bukkit.Bukkit;
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

    /** ゲームステータス */
    public static GameStatus gameStatus = new None(0);
    /** 参加登録者リスト */
    public static List<Player> registrantList = new ArrayList<>(Arrays.asList());
    /** 参加者マップ */
    public static HashMap<Player, Role> participantMap = new HashMap<Player, Role>();
    /** 生存者マップ */
    public static HashMap<Player, Role> survivorMap = new HashMap<Player, Role>();

    /**
     * プレイヤーと役職内訳を出力
     */
    public static void tellrawPlayerAndRoles() {
        tellraw("@a",
                "[内訳] " + participantMap.entrySet().stream().map(map -> map.getKey().getName() + "=" + map.getValue().getRoleNameJa())
                .collect(Collectors.joining(", ")),
                "yellow");
    }

    /**
     * サーバーコマンドを実行
     *
     * @param command コマンド文字列
     */
    public static void executeServerCommand(String command) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
    }

    /**
     * プレイヤーを取得
     *
     * @param playerName プレイヤー名
     * @return プレイヤー
     */
    public static Player getPlayer(String playerName) {
        return Bukkit.getServer().getPlayer(playerName);
    }

    /**
     * 役職を取得
     *
     * @param player プレイヤー
     * @return 役職
     */
    public static Role getRole(Player player) {
        return survivorMap.get(player);
    }

    /**
     * 未ゲーム状態かを返す
     *
     * @return 未ゲーム状態ならtrue
     */
    public static boolean isGameStatusNone() {
        return (Game.gameStatus instanceof None);
    }

    /**
     * 生存している人狼の数を返す
     *
     * @return 生存人狼数
     */
    public static long getWerewolfCount() {
        return survivorMap.entrySet().stream().filter(map -> map.getValue() instanceof Werewolf).count();
    }

    /**
     * タイトル描画
     *
     * @param title タイトル文字列
     * @param color カラー文字列
     */
    public static void sendTitle(String title, String color) {
        executeServerCommand("title @a title {\"text\":\"" + title + "\", \"color\":\"" + color + "\"}");
    }

    /**
     * タイトル描画
     *
     * @param title タイトル文字列
     */
    public static void sendTitle(String title) {
        sendTitle(title, "white");
    }

    /**
     * tellコマンドを送信
     *
     * @param targetPlayerName 対象プレイヤー名
     * @param message メッセージ文字列
     */
    public static void tell(String targetPlayerName, String message) {
        executeServerCommand("tell " + targetPlayerName + " " + message);
    }

    /**
     * tellrawコマンドを送信
     *
     * @param targetPlayerName 対象プレイヤー名
     * @param message メッセージ文字列
     * @param color カラー文字列
     */
    public static void tellraw(String targetPlayerName, String message, String color) {
        executeServerCommand("tellraw "+ targetPlayerName + " {\"text\":\"" + message + "\",\"color\":\"" + color + "\",\"italic\":true}");
    }

    /**
     * sayコマンドを送信
     *
     * @param message メッセージ文字列
     */
    public static void say(String message) {
        executeServerCommand("say " + message);
    }

    /**
     * 人狼チャットを送信
     *
     * @param message メッセージ文字列
     */
    public static void tellWerewolf(String message) {
        survivorMap.entrySet().stream().filter(map -> map.getValue() instanceof Werewolf).map(map -> map.getKey().getName())
                .forEach(name -> tellraw(name, message, "dark_red"));
    }

    /**
     * ゲーム終了処理
     */
    public static void endGame() {
        tellrawPlayerAndRoles();
        gameStatus = new None(0);
        participantMap.clear();
        survivorMap.clear();
    }
}
