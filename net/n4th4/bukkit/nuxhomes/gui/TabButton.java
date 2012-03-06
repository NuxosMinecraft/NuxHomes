package net.n4th4.bukkit.nuxhomes.gui;

import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.gui.Button;
import org.getspout.spoutapi.gui.GenericButton;

public class TabButton extends GenericButton
{
  private Main GUI;

  public TabButton(Main instance, String text)
  {
    this.GUI = instance;

    setText(text);
    setWidth(100);
    setHeight(this.GUI.height);
  }

  public void onButtonClick(ButtonClickEvent event) {
    this.GUI.currentTab = event.getButton().getText();
    this.GUI.fillList(this.GUI.searchField.getText());
    this.GUI.setSearchContainer("");
    this.GUI.setCentralContainer();
  }
}

/* Location:           /home/munrek/Téléchargements/nuxos/backup nuxos/bukkit/plugins/NuxHomes.jar
 * Qualified Name:     net.n4th4.bukkit.nuxhomes.gui.TabButton
 * JD-Core Version:    0.6.0
 */