package net.y_dash.minecraft.werewolf_game_plugin.werewolfgameplugin.role;

/**
 * 村人
 */
public class Villager extends VillagerTeamRole {
    @Override
    public String GetRoleNameJa() {
        return "村人";
    }

    @Override
    public void ActAtNight() {
        return;
    }
}
