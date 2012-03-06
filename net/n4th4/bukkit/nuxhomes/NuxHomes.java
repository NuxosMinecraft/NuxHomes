package net.n4th4.bukkit.nuxhomes;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;
import net.n4th4.bukkit.nuxhomes.commands.HomeCommand;
import net.n4th4.bukkit.nuxhomes.commands.HomeDeleteCommand;
import net.n4th4.bukkit.nuxhomes.commands.HomeListCommand;
import net.n4th4.bukkit.nuxhomes.commands.HomeSetCommand;
import net.n4th4.bukkit.nuxhomes.gui.HomesBinding;
import org.bukkit.Server;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.keyboard.KeyBindingManager;
import org.getspout.spoutapi.keyboard.Keyboard;

public class NuxHomes extends JavaPlugin
{
  private HomeCommand homeCommand;
  private HomeDeleteCommand homeDeleteCommand;
  private HomeListCommand homeListCommand;
  private HomeSetCommand homeSetCommand;
  public Logger log;
  public Configuration config;
  public HomesList homesList;

  public void onEnable()
  {
    this.log = getServer().getLogger();
    this.config = getConfiguration();

    ConnectionManager.init(this);
    Connection conn = ConnectionManager.getConnection();
    try
    {
      Statement state = conn.createStatement();
      state.executeUpdate("CREATE TABLE IF NOT EXISTS homes ( player VARCHAR(256), name VARCHAR(256), coordx DOUBLE, coordy DOUBLE, coordz DOUBLE, world VARCHAR(256) );");
      conn.commit();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    this.homesList = new HomesList();

    this.homeCommand = new HomeCommand(this);
    this.homeDeleteCommand = new HomeDeleteCommand(this);
    this.homeListCommand = new HomeListCommand(this);
    this.homeSetCommand = new HomeSetCommand(this);
    getCommand("home").setExecutor(this.homeCommand);
    getCommand("homedelete").setExecutor(this.homeDeleteCommand);
    getCommand("homelist").setExecutor(this.homeListCommand);
    getCommand("homeset").setExecutor(this.homeSetCommand);

    SpoutManager.getKeyBindingManager().registerBinding("mainBinding", Keyboard.KEY_H, "Open the homes' screen", new HomesBinding(this), this);

    PluginDescriptionFile pdfFile = getDescription();
    this.log.info("[NuxHomes] " + pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!");
  }

  public void onDisable() {
    ConnectionManager.closeConnection();
  }
}

/* Location:           /home/munrek/Téléchargements/nuxos/backup nuxos/bukkit/plugins/NuxHomes.jar
 * Qualified Name:     net.n4th4.bukkit.nuxhomes.NuxHomes
 * JD-Core Version:    0.6.0
 */