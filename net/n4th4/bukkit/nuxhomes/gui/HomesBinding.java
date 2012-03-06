package net.n4th4.bukkit.nuxhomes.gui;

import net.n4th4.bukkit.nuxhomes.NuxHomes;
import org.getspout.spoutapi.event.input.KeyBindingEvent;
import org.getspout.spoutapi.gui.InGameHUD;
import org.getspout.spoutapi.gui.Screen;
import org.getspout.spoutapi.gui.ScreenType;
import org.getspout.spoutapi.keyboard.BindingExecutionDelegate;
import org.getspout.spoutapi.player.SpoutPlayer;

public class HomesBinding
  implements BindingExecutionDelegate
{
  private NuxHomes plugin;

  public HomesBinding(NuxHomes instance)
  {
    this.plugin = instance;
  }

  public void keyPressed(KeyBindingEvent event)
  {
  }

  public void keyReleased(KeyBindingEvent event)
  {
    if (event.getPlayer().getCurrentScreen() == null) {
      return;
    }
    if (event.getPlayer().getCurrentScreen().getScreenType() == ScreenType.GAME_SCREEN) {
      Main popup = new Main(this.plugin, event.getPlayer());
      event.getPlayer().getMainScreen().attachPopupScreen(popup);
    }
  }
}

/* Location:           /home/munrek/Téléchargements/nuxos/backup nuxos/bukkit/plugins/NuxHomes.jar
 * Qualified Name:     net.n4th4.bukkit.nuxhomes.gui.HomesBinding
 * JD-Core Version:    0.6.0
 */