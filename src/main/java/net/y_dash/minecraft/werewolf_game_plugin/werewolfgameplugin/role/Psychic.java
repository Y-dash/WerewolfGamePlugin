package net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.role;

/**
 * 霊能者
 */
public class Psychic extends VillagerTeamRole {
    @Override
    public String GetRoleNameJa() {
        return "霊能者";
    }

    @Override
    public void ActAtNight() {
        // 霊能結果見る
        return;
    }
}
