package net.n4th4.bukkit.nuxhomes.commands;

import java.util.List;
import net.n4th4.bukkit.nuxhomes.HomesList;
import net.n4th4.bukkit.nuxhomes.NuxHomes;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.config.Configuration;

public class HomeSetCommand
  implements CommandExecutor
{
  private NuxHomes plugin;

  public HomeSetCommand(NuxHomes plugin)
  {
    this.plugin = plugin;
  }

  public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args)
  {
    if ((sender instanceof Player)) {
      Player player = (Player)sender;
      String playerN = ""; String homeN = ""; String worldN = "";
      if (args.length > 2)
        return false;
      if ((args.length == 0) && (player.hasPermission("nuxhomes.basic.set"))) {
        playerN = player.getName();
        homeN = "default";
        worldN = player.getWorld().getName();
      } else if ((args.length == 1) && (player.hasPermission("nuxhomes.multi.set"))) {
        playerN = player.getName();
        homeN = args[0];
        worldN = player.getWorld().getName();
      } else if ((args.length == 2) && (player.hasPermission("nuxhomes.others.set"))) {
        playerN = args[0];
        homeN = args[1];
        worldN = player.getWorld().getName();
      } else {
        player.sendMessage(ChatColor.RED + "[NuxHomes] You can't do that");
        return true;
      }

      if (this.plugin.homesList.exists(worldN, playerN, homeN)) {
        this.plugin.homesList.updateHome(worldN, playerN, homeN, player.getLocation());
      } else {
        List list = this.plugin.homesList.listHomes(worldN, playerN);
        if ((list != null) && 
          (list.size() == this.plugin.config.getInt("maxhomes", 5))) {
          player.sendMessage(ChatColor.RED + "[NuxHomes] You already have the maximum authorized homes");
          return true;
        }
      }

      this.plugin.homesList.createHome(worldN, playerN, homeN, player.getLocation());

      player.sendMessage(ChatColor.GREEN + "[NuxHomes] New home set !");
      return true;
    }
    sender.sendMessage(ChatColor.RED + "[NuxHomes] Only in-game commands are supported.");
    return true;
  }
}

/* Location:           /home/munrek/Téléchargements/nuxos/backup nuxos/bukkit/plugins/NuxHomes.jar
 * Qualified Name:     net.n4th4.bukkit.nuxhomes.commands.HomeSetCommand
 * JD-Core Version:    0.6.0
 */