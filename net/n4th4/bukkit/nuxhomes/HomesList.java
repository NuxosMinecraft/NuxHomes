package net.n4th4.bukkit.nuxhomes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import org.bukkit.Location;

public class HomesList
{
  private Hashtable<String, Hashtable<String, Hashtable<String, Home>>> homesList = new Hashtable();

  public HomesList()
  {
    Connection conn = ConnectionManager.getConnection();
    try {
      Statement state = conn.createStatement();
      ResultSet result = state.executeQuery("SELECT * FROM homes");
      while (result.next()) {
        Home home = new Home(result.getString("player"), result.getString("name"), result.getString("world"), result.getDouble("coordx"), result.getDouble("coordy"), result.getDouble("coordz"));
        put(result.getString("world"), result.getString("player"), result.getString("name"), home);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private void put(String world, String player, String name, Home home) {
    if (!this.homesList.containsKey(world)) {
      this.homesList.put(world, new Hashtable());
    }
    if (!((Hashtable)this.homesList.get(world)).containsKey(player)) {
      ((Hashtable)this.homesList.get(world)).put(player, new Hashtable());
    }
    ((Hashtable)((Hashtable)this.homesList.get(world)).get(player)).put(name, home);
  }

  public String getPlayerMatch(String world, String player) {
    ArrayList exactMatches = new ArrayList();
    ArrayList matches = new ArrayList();

    List names = new ArrayList(((Hashtable)this.homesList.get(world)).keySet());

    for (int i = 0; i < names.size(); i++) {
      String currName = (String)names.get(i);

      if (currName.equalsIgnoreCase(player))
        exactMatches.add(currName);
      else if (currName.toLowerCase().startsWith(player.toLowerCase())) {
        matches.add(currName);
      }
    }

    if (exactMatches.size() == 1) {
      return (String)exactMatches.get(0);
    }
    if (matches.size() == 1) {
      return (String)matches.get(0);
    }
    return player;
  }

  public String getHomeMatch(String world, String player, String name) {
    if (!((Hashtable)this.homesList.get(world)).containsKey(player)) {
      return name;
    }

    ArrayList exactMatches = new ArrayList();
    ArrayList matches = new ArrayList();

    List names = new ArrayList(((Hashtable)((Hashtable)this.homesList.get(world)).get(player)).keySet());

    for (int i = 0; i < names.size(); i++) {
      String currName = (String)names.get(i);
      Home warp = (Home)((Hashtable)((Hashtable)this.homesList.get(world)).get(player)).get(currName);

      if (currName.equalsIgnoreCase(name))
        exactMatches.add(warp);
      else if (currName.toLowerCase().startsWith(name.toLowerCase())) {
        matches.add(warp);
      }
    }

    if (exactMatches.size() == 1) {
      return ((Home)exactMatches.get(0)).getHomeName();
    }
    if (matches.size() == 1) {
      return ((Home)matches.get(0)).getHomeName();
    }
    return name;
  }

  public boolean exists(String world, String player, String name) {
    if (!this.homesList.containsKey(world))
      return false;
    if (!((Hashtable)this.homesList.get(world)).containsKey(player)) {
      return false;
    }
    return ((Hashtable)((Hashtable)this.homesList.get(world)).get(player)).containsKey(name);
  }

  public boolean playerExists(String world, String player)
  {
    if (!this.homesList.containsKey(world)) {
      return false;
    }
    return ((Hashtable)this.homesList.get(world)).containsKey(player);
  }

  public Home getHome(String world, String player, String name)
  {
    player = getPlayerMatch(world, player);
    name = getHomeMatch(world, player, name);
    if (!exists(world, player, name))
      return null;
    return (Home)((Hashtable)((Hashtable)this.homesList.get(world)).get(player)).get(name);
  }

  public List<String> listHomes(String world, String player) {
    if (!this.homesList.containsKey(world))
      return null;
    if (!((Hashtable)this.homesList.get(world)).containsKey(player)) {
      return null;
    }
    List list = new ArrayList(((Hashtable)((Hashtable)this.homesList.get(world)).get(player)).keySet());
    return list;
  }

  public List<String> listPlayers(String world) {
    if (!this.homesList.containsKey(world)) {
      return null;
    }
    List list = new ArrayList(((Hashtable)this.homesList.get(world)).keySet());
    return list;
  }

  public void createHome(String world, String player, String name, Location loc) {
    Home home = new Home(player, name, world, loc.getX(), loc.getY(), loc.getZ());
    home.save();
    put(world, player, name, home);
  }

  public boolean deleteHome(String world, String player, String name) {
    if (!exists(world, player, name))
      return false;
    ((Home)((Hashtable)((Hashtable)this.homesList.get(world)).get(player)).get(name)).delete();
    ((Hashtable)((Hashtable)this.homesList.get(world)).get(player)).remove(name);
    return true;
  }

  public boolean updateHome(String world, String player, String name, Location loc) {
    if (!exists(world, player, name))
      return false;
    ((Home)((Hashtable)((Hashtable)this.homesList.get(world)).get(player)).get(name)).update(loc.getX(), loc.getY(), loc.getZ());
    return true;
  }
}

/* Location:           /home/munrek/Téléchargements/nuxos/backup nuxos/bukkit/plugins/NuxHomes.jar
 * Qualified Name:     net.n4th4.bukkit.nuxhomes.HomesList
 * JD-Core Version:    0.6.0
 */