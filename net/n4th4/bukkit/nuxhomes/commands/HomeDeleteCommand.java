package net.n4th4.bukkit.nuxhomes.commands;

import net.n4th4.bukkit.nuxhomes.HomesList;
import net.n4th4.bukkit.nuxhomes.NuxHomes;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HomeDeleteCommand
  implements CommandExecutor
{
  private NuxHomes plugin;

  public HomeDeleteCommand(NuxHomes plugin)
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
      if ((args.length == 0) && (player.hasPermission("nuxhomes.basic.delete"))) {
        playerN = player.getName();
        homeN = "default";
        worldN = player.getWorld().getName();
      } else if ((args.length == 1) && (player.hasPermission("nuxhomes.multi.delete"))) {
        playerN = player.getName();
        homeN = args[0];
        worldN = player.getWorld().getName();
      } else if ((args.length == 2) && (player.hasPermission("nuxhomes.others.delete"))) {
        playerN = args[0];
        homeN = args[1];
        worldN = player.getWorld().getName();
      } else {
        player.sendMessage(ChatColor.RED + "[NuxHomes] You can't do that");
        return true;
      }

      boolean result = this.plugin.homesList.deleteHome(worldN, playerN, homeN);
      if (result)
        player.sendMessage(ChatColor.GREEN + "[NuxHomes] Home deleted");
      else {
        player.sendMessage(ChatColor.RED + "[NuxHomes] This home does not exist");
      }
      return true;
    }
    sender.sendMessage(ChatColor.RED + "[NuxHomes] Only in-game commands are supported.");
    return true;
  }
}

/* Location:           /home/munrek/Téléchargements/nuxos/backup nuxos/bukkit/plugins/NuxHomes.jar
 * Qualified Name:     net.n4th4.bukkit.nuxhomes.commands.HomeDeleteCommand
 * JD-Core Version:    0.6.0
 */