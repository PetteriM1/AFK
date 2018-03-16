package idk.plugin.afk;

import cn.nukkit.Player;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerQuitEvent;
import java.util.*;

public class Main extends PluginBase implements Listener {

    List<String> afkers = new ArrayList<>();

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
            afkers.remove(e.getPlayer().getName());
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player)sender;
            String name = p.getName();
            if (cmd.getName().equalsIgnoreCase("afk")) {
                if (afkers.contains(name)) {
                    afkers.remove(name);
                    p.setDisplayName(name);
                    p.sendMessage("\u00A76You are no longer AFK");
                    this.getServer().broadcastMessage("\u00A72" + name + " is no longer AFK");
                    return true;
                } else {
                    afkers.add(name);
                    p.setDisplayName("\u00A7c[AFK] " + name);
                    p.sendMessage("\u00A76You are now AFK");
                    this.getServer().broadcastMessage("\u00A7c" + name + " is now AFK");
                    return true;
                }
            }
        }
        return true;
    }
}
