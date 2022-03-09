package net.danh.main.Manager;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TranslatableComponent;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Storage {

    public static @NotNull Integer getStorage(@NotNull Player p, String name) {
        return Data.getstoragefile().getInt("players." + p.getName() + ".Blocks." + name);
    }

    public static void setStorage(@NotNull Player p, String name, Integer number) {
        Data.getstoragefile().set("players." + p.getName() + ".Blocks." + name, number);
        Data.savestorage();
    }

    public static void addStorage(@NotNull Player p, String name, Integer number) {
        Data.getstoragefile().set("players." + p.getName() + ".Blocks." + name, getStorage(p, name) + number);
        p.spigot().sendMessage(ChatMessageType.valueOf(Data.getconfigfile().getString("Add_Type")),
                new TranslatableComponent(Data.convert(Objects.requireNonNull(Data.getlanguagefile().getString("ADD_STORAGE"))
                .replaceAll("%amount%", String.valueOf(number))
                .replaceAll("%block%", name.replaceAll("_", " "))
                .replaceAll("%weight%", String.valueOf(Weight.getWeight(p)))
                .replaceAll("%maxweight%", String.valueOf(Weight.getMaxWeight(p))))));
        Data.savestorage();
    }

    public static void removeStorage(@NotNull Player p, String name, Integer number) {
        Data.getstoragefile().set("players." + p.getName() + ".Blocks." + name, getStorage(p, name) - number);
        p.spigot().sendMessage(ChatMessageType.valueOf(Data.getconfigfile().getString("Add_Type")),
                new TranslatableComponent(Data.convert(Objects.requireNonNull(Data.getlanguagefile().getString("REMOVE_STORAGE"))
                .replaceAll("%amount%", String.valueOf(number))
                .replaceAll("%block%", name.replaceAll("_", " "))
                .replaceAll("%weight%", String.valueOf(Weight.getWeight(p) - number))
                .replaceAll("%maxweight%", String.valueOf(Weight.getMaxWeight(p))))));
        Data.savestorage();
    }
}
