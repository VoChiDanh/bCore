package net.danh.Events;

import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import io.lumine.mythic.lib.api.item.NBTItem;
import net.danh.Manager.Credit;
import net.danh.Manager.Data;
import net.danh.Manager.Status;
import net.danh.bCore;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;

import static net.danh.dcore.Utils.Player.sendPlayerMessage;

public class Bow implements Listener {

    @EventHandler
    public void onBow(ProjectileHitEvent e) {
        if (e.getEntity().getType() != EntityType.ARROW || e.getHitEntity() != null) {
            return;
        }
        Arrow arrow = (Arrow) e.getEntity();
        if (arrow.getShooter() instanceof Player damager) {
            if (e.getHitEntity() instanceof Player attacked) {
                if (!damager.hasMetadata("NPC") && !attacked.hasMetadata("NPC")) {
                    //player who hit
                    boolean damagerState = Status.getPvPStatus(damager);
                    //player who was hit
                    boolean attackedState = Status.getPvPStatus(attacked);
                    if (bCore.getInstance().getServer().getPluginManager().getPlugin("WorldGuard") != null) {
                        LocalPlayer localPlayer = WorldGuardPlugin.inst().wrapPlayer(damager);
                        LocalPlayer localPlayer1 = WorldGuardPlugin.inst().wrapPlayer(attacked);
                        Location loc = new Location(localPlayer.getWorld(), localPlayer.getLocation().getBlockX(), localPlayer.getLocation().getBlockY(), localPlayer.getLocation().getBlockZ());
                        Location loc1 = new Location(localPlayer1.getWorld(), localPlayer1.getLocation().getBlockX(), localPlayer1.getLocation().getBlockY(), localPlayer1.getLocation().getBlockZ());
                        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
                        RegionQuery query = container.createQuery();
                        if (!query.testState(loc, localPlayer, Flags.PVP) || !query.testState(loc1, localPlayer1, Flags.PVP)) {
                            e.setCancelled(true);
                        }
                    }
                    if (Credit.getCredit(damager) <= 50) {
                        sendPlayerMessage(damager, Data.getlanguagefile().getString("LOW_CREDIT_WORKING"));
                        e.setCancelled(true);
                        return;
                    }
                    if (!damagerState || !attackedState) {
                        e.setCancelled(true);
                    }
                    ItemStack d_i = damager.getInventory().getItemInMainHand();
                    ItemStack a_i = attacked.getInventory().getItemInMainHand();
                    if (d_i.getType() != Material.AIR) {
                        NBTItem item = NBTItem.get(d_i);
                        double stat = item.getDouble("MMOITEMS_ATTACK_DAMAGE"); // format is MMOITEMS_STAT_ID
                        NBTItem item1 = NBTItem.get(a_i);
                        double stat1 = item1.getDouble("MMOITEMS_ATTACK_DAMAGE"); // format is MMOITEMS_STAT_ID
                        if (a_i.getType() == Material.AIR) {
                            if (stat >= Data.getconfigfile().getInt("damage")) {
                                e.setCancelled(true);
                            }
                            if (d_i.getType() == Material.AIR) {
                                if (stat1 >= Data.getconfigfile().getInt("damage")) {
                                    e.setCancelled(true);
                                }
                            }
                        } else {
                            NBTItem item2 = NBTItem.get(a_i);
                            double stat2 = item2.getDouble("MMOITEMS_ATTACK_DAMAGE"); // format is MMOITEMS_STAT_ID
                            double damage = stat - stat2;
                            if (Math.abs(damage) > 0) {
                                if (Math.abs(damage) >= Data.getconfigfile().getInt("bt_damage")) {
                                    e.setCancelled(true);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

