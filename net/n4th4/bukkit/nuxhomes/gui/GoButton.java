package net.n4th4.bukkit.nuxhomes.gui;

import net.n4th4.bukkit.nuxhomes.Home;
import net.n4th4.bukkit.nuxhomes.HomesList;
import net.n4th4.bukkit.nuxhomes.NuxHomes;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.gui.GenericLabel;
import org.getspout.spoutapi.player.SpoutPlayer;

public class GoButton extends GenericButton
{
  private Main GUI;

  public GoButton(Main instance)
  {
    this.GUI = instance;

    setText("Go !");
    setWidth(100);
    setHeight(this.GUI.height);
  }

  public void onButtonClick(ButtonClickEvent event) {
    SpoutPlayer sender = event.getPlayer();

    Home home = this.GUI.plugin.homesList.getHome(sender.getWorld().getName(), this.GUI.searchedPlayer, ((GenericLabel)event.getButton().getContainer().getChildren()[0]).getText());
    sender.closeActiveWindow();
    if (home == null) {
      sender.sendMessage(ChatColor.RED + "[NuxHomes] This home does not exist");
    } else {
      sender.teleport(new Location(sender.getWorld(), home.getCoordX(), home.getCoordY(), home.getCoordZ()));
      sender.sendMessage(ChatColor.GREEN + "[NuxHomes] Welcome to " + home.getHomeName());
    }
  }
}

/* Location:           /home/munrek/Téléchargements/nuxos/backup nuxos/bukkit/plugins/NuxHomes.jar
 * Qualified Name:     net.n4th4.bukkit.nuxhomes.gui.GoButton
 * JD-Core Version:    0.6.0
 */