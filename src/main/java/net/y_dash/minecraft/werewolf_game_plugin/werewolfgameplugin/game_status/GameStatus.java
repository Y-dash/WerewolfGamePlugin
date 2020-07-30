package net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.game_status;

/**
 * ゲームステータス
 */
public abstract class GameStatus {
    /** 行動済みプレイヤー数 */
    private int actedPlayerCount = 0;
    /** 日数 */
    private int days;

    /**
     * コンストラクタ
     * @param days 何日目か
     */
    public GameStatus(int days) {
        this.days = days;
    }

    /**
     * 行動済みプレイヤー数の取得
     * @return 行動済みプレイヤー数
     */
    public int getActedPlayerCount() {
        return actedPlayerCount;
    }

    /**
     * 行動済みプレイヤー数をインクリメント
     */
    public void incrementActedPlayerCount() {
        actedPlayerCount++;
    }

    /**
     * 何日目か取得
     * @return 日数
     */
    public int getDays() {
        return days;
    }
}
