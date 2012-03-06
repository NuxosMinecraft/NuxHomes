package net.n4th4.bukkit.nuxhomes.gui;

import net.n4th4.bukkit.nuxhomes.HomesList;
import net.n4th4.bukkit.nuxhomes.NuxHomes;
import org.bukkit.World;
import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.gui.GenericLabel;
import org.getspout.spoutapi.player.SpoutPlayer;

public class DeleteButton extends GenericButton
{
  private Main GUI;

  public DeleteButton(Main instance)
  {
    this.GUI = instance;

    setText("Delete");
    setWidth(100);
    setHeight(this.GUI.height);
  }

  public void onButtonClick(ButtonClickEvent event) {
    SpoutPlayer sender = event.getPlayer();

    this.GUI.plugin.homesList.deleteHome(sender.getWorld().getName(), this.GUI.searchedPlayer, ((GenericLabel)event.getButton().getContainer().getChildren()[0]).getText());
    this.GUI.setCentralContainer();
  }
}

/* Location:           /home/munrek/Téléchargements/nuxos/backup nuxos/bukkit/plugins/NuxHomes.jar
 * Qualified Name:     net.n4th4.bukkit.nuxhomes.gui.DeleteButton
 * JD-Core Version:    0.6.0
 */