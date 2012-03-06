package net.n4th4.bukkit.nuxhomes.gui;

import net.n4th4.bukkit.nuxhomes.HomesList;
import net.n4th4.bukkit.nuxhomes.NuxHomes;
import org.bukkit.World;
import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.player.SpoutPlayer;

public class SearchButton extends GenericButton
{
  private Main GUI;

  public SearchButton(Main instance)
  {
    this.GUI = instance;

    setText("Search");
    setWidth(100);
    setHeight(this.GUI.height);
  }

  public void onButtonClick(ButtonClickEvent event) {
    SpoutPlayer sender = event.getPlayer();

    String player = this.GUI.plugin.homesList.getPlayerMatch(sender.getWorld().getName(), this.GUI.searchField.getText());
    if (this.GUI.plugin.homesList.playerExists(sender.getWorld().getName(), player))
      this.GUI.setSearchContainer(player);
  }
}

/* Location:           /home/munrek/Téléchargements/nuxos/backup nuxos/bukkit/plugins/NuxHomes.jar
 * Qualified Name:     net.n4th4.bukkit.nuxhomes.gui.SearchButton
 * JD-Core Version:    0.6.0
 */