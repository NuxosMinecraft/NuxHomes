package net.n4th4.bukkit.nuxhomes.gui;

import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.gui.GenericButton;

public class InfoButton extends GenericButton
{
  private Main GUI;

  public InfoButton(Main instance)
  {
    this.GUI = instance;

    setText("Info");
    setWidth(100);
    setHeight(this.GUI.height);
  }

  public void onButtonClick(ButtonClickEvent event)
  {
  }
}

/* Location:           /home/munrek/Téléchargements/nuxos/backup nuxos/bukkit/plugins/NuxHomes.jar
 * Qualified Name:     net.n4th4.bukkit.nuxhomes.gui.InfoButton
 * JD-Core Version:    0.6.0
 */