package net.danh.PClass;

import net.danh.PlayerData.PData;
import net.danh.dcore.Utils.Chat;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Objects;

import static net.danh.dcore.Utils.Player.sendPlayerMessage;

public class PClass {

    static final HashMap<Player, CName> pc = new HashMap<>();

    public static CName getPClass(Player p) {
        return pc.getOrDefault(p, CName.NONE);
    }

    public static void setPClass(Player p, CName name) {
        pc.put(p, Objects.requireNonNullElse(name, CName.NONE));
        sendPlayerMessage(p, "&bBạn đã chuyển thành class " + pc.get(p).getName());
    }

    public static String getPCData(Player p) {
        return new PData(p.getName()).getConfig().getString("class");
    }

    public static String getPCName(CName name) {
        return Chat.colorize(name.getName());
    }
    public static String getPCFName(CName name) {
        return name.getFormatName();
    }

}