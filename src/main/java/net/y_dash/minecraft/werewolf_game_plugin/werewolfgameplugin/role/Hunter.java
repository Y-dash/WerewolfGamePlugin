package net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.role;

/**
 * 狩人
 */
public class Hunter extends VillagerTeamRole {
    @Override
    public String GetRoleNameJa() {
        return "狩人";
    }

    @Override
    public void ActAtNight() {
        // 守り先決める
        return;
    }
}
