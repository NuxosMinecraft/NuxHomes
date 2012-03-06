package net.n4th4.bukkit.nuxhomes.gui;

import java.util.List;
import net.n4th4.bukkit.nuxhomes.HomesList;
import net.n4th4.bukkit.nuxhomes.NuxHomes;
import org.bukkit.World;
import org.bukkit.util.config.Configuration;
import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.gui.GenericTextField;
import org.getspout.spoutapi.player.SpoutPlayer;

public class SetButton extends GenericButton
{
  private Main GUI;

  public SetButton(Main instance)
  {
    this.GUI = instance;

    setText("Set !");
    setWidth(100);
    setHeight(this.GUI.height);
  }

  public void onButtonClick(ButtonClickEvent event) {
    SpoutPlayer sender = event.getPlayer();

    if (this.GUI.plugin.homesList.exists(sender.getWorld().getName(), this.GUI.searchedPlayer, this.GUI.homeField.getText())) {
      this.GUI.plugin.homesList.updateHome(sender.getWorld().getName(), this.GUI.searchedPlayer, this.GUI.homeField.getText(), sender.getLocation());
    } else {
      List list = this.GUI.plugin.homesList.listHomes(sender.getWorld().getName(), this.GUI.searchedPlayer);
      if ((list != null) && 
        (list.size() == this.GUI.plugin.config.getInt("maxhomes", 5))) {
        return;
      }
    }

    if (this.GUI.homeField.getText().equals("")) {
      return;
    }
    this.GUI.plugin.homesList.createHome(sender.getWorld().getName(), this.GUI.searchedPlayer, this.GUI.homeField.getText(), sender.getLocation());

    this.GUI.setCentralContainer();
  }
}

/* Location:           /home/munrek/Téléchargements/nuxos/backup nuxos/bukkit/plugins/NuxHomes.jar
 * Qualified Name:     net.n4th4.bukkit.nuxhomes.gui.SetButton
 * JD-Core Version:    0.6.0
 */