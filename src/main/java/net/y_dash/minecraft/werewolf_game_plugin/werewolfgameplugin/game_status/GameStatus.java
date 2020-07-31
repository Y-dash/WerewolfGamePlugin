package net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.game_status;

import net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.Game;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ゲームステータス
 */
public abstract class GameStatus {
    /** 日数 */
    protected int days;
    /** 行動済みプレイヤーリスト */
    public List<Player> actedPlayerList = new ArrayList<>(Arrays.asList());
    /** 被投票プレイヤーリスト */
    public List<Player> votedPlayerList = new ArrayList<>(Arrays.asList());
    /** 守り先プレイヤー */
    public Player protectedPlayer;
    /** 処刑されたプレイヤー */
    public Player executedPlayerAtDaytime;

    /**
     * コンストラクタ
     *
     * @param days 日数
     */
    public GameStatus(int days) {
        this.days = days;
    }

    /**
     * 選択行動コマンド
     *
     * @param votingPlayer 選択するプレイヤー
     * @param targetPlayer 被選択プレイヤー
     * @return
     */
    public abstract boolean vote(Player votingPlayer, Player targetPlayer);

    /**
     * 行動可否を返す
     *
     * @param votingPlayer 行動するプレイヤー
     * @param targetPlayer 行動対象のプレイヤー
     * @return
     */
    public boolean isVotable(Player votingPlayer, Player targetPlayer) {
        if(votingPlayer == targetPlayer) {
            votingPlayer.sendMessage("自分自身は対象にできません");
            return false;
        }

        if(actedPlayerList.contains(votingPlayer)) {
            votingPlayer.sendMessage("すでに行動しています");
            return false;
        }

        if(!Game.survivorMap.containsValue(votingPlayer)) {
            votingPlayer.sendMessage("行動できるのは生存者のみです");
            return false;
        }

        if(!Game.survivorMap.containsValue(targetPlayer)) {
            votingPlayer.sendMessage("対象にできるのは生存者のみです");
            return false;
        }

        return true;
    }

    /**
     * ゲーム結果を判定
     *
     * @return ゲーム終了したらtrue
     */
    protected boolean judge() {
        long werewolfCount = Game.getWerewolfCount();

        if(werewolfCount <= 0) {
            Game.sendTitle("村人の勝利");
            Game.endGame();
            return true;
        }

        if(werewolfCount >= Game.survivorMap.size() - werewolfCount) {
            Game.sendTitle("人狼の勝利");
            Game.endGame();
            return true;
        }

        return false;
    }

    /**
     * プレイヤーを処刑する
     *
     * @param player 対象のプレイヤー
     * @param cause 死因
     */
    protected void killPlayer(Player player, String cause) {
        Game.executeServerCommand("kill " + player.getName());
        Game.tellraw("@a", player.getName() + "が" + cause, "yellow");
        Game.survivorMap.remove(player);
    }
}
