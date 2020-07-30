package net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.role;

/**
 * 占い師
 */
public class FortuneTeller extends VillagerTeamRole {
    @Override
    public String GetRoleNameJa() {
        return "占い師";
    }

    @Override
    public void ActAtNight() {
        // 占う
        return;
    }
}
