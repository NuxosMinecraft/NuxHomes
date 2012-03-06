package net.n4th4.bukkit.nuxhomes.commands;

import net.n4th4.bukkit.nuxhomes.Home;
import net.n4th4.bukkit.nuxhomes.HomesList;
import net.n4th4.bukkit.nuxhomes.NuxHomes;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HomeCommand
  implements CommandExecutor
{
  private NuxHomes plugin;

  public HomeCommand(NuxHomes plugin)
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
      if ((args.length == 0) && (player.hasPermission("nuxhomes.basic.go"))) {
        playerN = player.getName();
        homeN = "default";
        worldN = player.getWorld().getName();
      } else if ((args.length == 1) && (player.hasPermission("nuxhomes.multi.go"))) {
        playerN = player.getName();
        homeN = args[0];
        worldN = player.getWorld().getName();
      } else if ((args.length == 2) && (player.hasPermission("nuxhomes.others.go"))) {
        playerN = args[0];
        homeN = args[1];
        worldN = player.getWorld().getName();
      } else {
        player.sendMessage(ChatColor.RED + "[NuxHomes] You can't do that");
        return true;
      }

      playerN = this.plugin.homesList.getPlayerMatch(worldN, playerN);
      homeN = this.plugin.homesList.getHomeMatch(worldN, playerN, homeN);

      Home home = this.plugin.homesList.getHome(worldN, playerN, homeN);
      if (home == null) {
        player.sendMessage(ChatColor.RED + "[NuxHomes] This home does not exist");
      } else {
        player.teleport(new Location(this.plugin.getServer().getWorld(worldN), home.getCoordX(), home.getCoordY(), home.getCoordZ()));
        player.sendMessage(ChatColor.GREEN + "[NuxHomes] Welcome to " + home.getHomeName());
      }

      return true;
    }
    sender.sendMessage(ChatColor.RED + "[NuxHomes] Only in-game commands are supported.");
    return true;
  }
}

/* Location:           /home/munrek/Téléchargements/nuxos/backup nuxos/bukkit/plugins/NuxHomes.jar
 * Qualified Name:     net.n4th4.bukkit.nuxhomes.commands.HomeCommand
 * JD-Core Version:    0.6.0
 */