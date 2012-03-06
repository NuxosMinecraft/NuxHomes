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

public class HomeListCommand
  implements CommandExecutor
{
  private NuxHomes plugin;

  public HomeListCommand(NuxHomes plugin)
  {
    this.plugin = plugin;
  }

  public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args)
  {
    if ((sender instanceof Player)) {
      Player player = (Player)sender;
      String playerN = ""; String worldN = "";
      if (args.length > 1)
        return false;
      if ((args.length == 0) && (player.hasPermission("nuxhomes.multi.list"))) {
        playerN = player.getName();
        worldN = player.getWorld().getName();
      } else if ((args.length == 1) && (player.hasPermission("nuxhomes.others.list"))) {
        playerN = args[0];
        worldN = player.getWorld().getName();
      } else {
        player.sendMessage(ChatColor.RED + "[NuxHomes] You can't do that");
        return true;
      }

      playerN = this.plugin.homesList.getPlayerMatch(worldN, playerN);
      List list = this.plugin.homesList.listHomes(worldN, playerN);
      if (list == null) {
        player.sendMessage(ChatColor.RED + "[NuxHomes] This player doesn't exist.");
        return true;
      }

      String message = ChatColor.GREEN + "[NuxHomes] " + this.plugin.homesList.getPlayerMatch(worldN, playerN) + "'s home(s) : ";
      for (int i = 0; i < list.size(); i++) {
        message = message + (String)list.get(i) + ", ";
      }
      player.sendMessage(message.substring(0, message.length() - 2));

      return true;
    }
    sender.sendMessage(ChatColor.RED + "[NuxHomes] Only in-game commands are supported.");
    return true;
  }
}

/* Location:           /home/munrek/Téléchargements/nuxos/backup nuxos/bukkit/plugins/NuxHomes.jar
 * Qualified Name:     net.n4th4.bukkit.nuxhomes.commands.HomeListCommand
 * JD-Core Version:    0.6.0
 */