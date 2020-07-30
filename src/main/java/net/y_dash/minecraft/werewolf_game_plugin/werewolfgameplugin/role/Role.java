package net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.role;

/**
 * 役職クラス
 */
public abstract class Role {
    /**
     * 役職の日本語表記を返す
     *
     * @return 役職の日本語表記
     */
    public abstract String GetRoleNameJa();

    /**
     * 夜の行動
     */
    public abstract void ActAtNight();
}
