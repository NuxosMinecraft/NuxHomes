package net.n4th4.bukkit.nuxhomes.gui;

import net.n4th4.bukkit.nuxhomes.NuxHomes;
import org.bukkit.Server;
import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.player.SpoutPlayer;

public class CommandButton extends GenericButton
{
  private Main GUI;
  private String command;

  public CommandButton(Main instance, String text, String command)
  {
    this.GUI = instance;
    this.command = command;

    setText(text);
    setTooltip("Launch : /" + command);
    setWidth(100);
    setHeight(this.GUI.height);
  }

  public void onButtonClick(ButtonClickEvent event) {
    SpoutPlayer sender = event.getPlayer();

    String realCommand = this.command.replace("%player%", this.GUI.searchedPlayer);

    this.GUI.plugin.getServer().dispatchCommand(sender, realCommand);
  }
}

/* Location:           /home/munrek/Téléchargements/nuxos/backup nuxos/bukkit/plugins/NuxHomes.jar
 * Qualified Name:     net.n4th4.bukkit.nuxhomes.gui.CommandButton
 * JD-Core Version:    0.6.0
 */