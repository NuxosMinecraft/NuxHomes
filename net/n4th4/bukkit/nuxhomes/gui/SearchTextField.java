package net.n4th4.bukkit.nuxhomes.gui;

import org.getspout.spoutapi.event.screen.TextFieldChangeEvent;
import org.getspout.spoutapi.gui.GenericTextField;

public class SearchTextField extends GenericTextField
{
  private Main GUI;

  public SearchTextField(Main instance)
  {
    this.GUI = instance;

    setWidth(205);
    setHeight(20);
  }

  public void onTextFieldChange(TextFieldChangeEvent event) {
    this.GUI.fillList(event.getNewText());
    this.GUI.setSearchContainer(this.GUI.searchedPlayer);
    this.GUI.searchField.setFocus(true);
  }
}

/* Location:           /home/munrek/Téléchargements/nuxos/backup nuxos/bukkit/plugins/NuxHomes.jar
 * Qualified Name:     net.n4th4.bukkit.nuxhomes.gui.SearchTextField
 * JD-Core Version:    0.6.0
 */