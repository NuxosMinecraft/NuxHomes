package net.n4th4.bukkit.nuxhomes;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Home
{
  private String playerN;
  private String homeN;
  private String worldN;
  private double coordx;
  private double coordy;
  private double coordz;

  public Home(String player, String name, String world, double coordX, double coordY, double coordZ)
  {
    this.playerN = player;
    this.homeN = name;
    this.worldN = world;
    this.coordx = coordX;
    this.coordy = coordY;
    this.coordz = coordZ;
  }

  public double getCoordX() {
    return this.coordx;
  }

  public double getCoordY() {
    return this.coordy;
  }

  public double getCoordZ() {
    return this.coordz;
  }

  public String getHomeName() {
    return this.homeN;
  }

  public void save() {
    Connection conn = ConnectionManager.getConnection();
    try {
      Statement state = conn.createStatement();
      state.executeUpdate("INSERT INTO homes VALUES ('" + this.playerN + "', '" + this.homeN + "', " + this.coordx + ", " + this.coordy + ", " + this.coordz + ", '" + this.worldN + "');");
      state.close();
      conn.commit();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void update(double coordX, double coordY, double coordZ) {
    this.coordx = coordX;
    this.coordy = coordY;
    this.coordz = coordZ;

    Connection conn = ConnectionManager.getConnection();
    try {
      Statement state = conn.createStatement();
      state.executeUpdate("UPDATE homes SET coordx=" + this.coordx + ", coordy=" + this.coordy + ", coordz=" + this.coordz + " WHERE player='" + this.playerN + "' AND name='" + this.homeN + "' AND world='" + this.worldN + "';");
      state.close();
      conn.commit();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void delete() {
    Connection conn = ConnectionManager.getConnection();
    try {
      Statement state = conn.createStatement();
      state.executeUpdate("DELETE FROM homes WHERE player='" + this.playerN + "' AND name='" + this.homeN + "' AND world='" + this.worldN + "';");
      state.close();
      conn.commit();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    this.playerN = null;
    this.homeN = null;
    this.worldN = null;
    this.coordx = 0.0D;
    this.coordy = 0.0D;
    this.coordz = 0.0D;
  }
}

/* Location:           /home/munrek/Téléchargements/nuxos/backup nuxos/bukkit/plugins/NuxHomes.jar
 * Qualified Name:     net.n4th4.bukkit.nuxhomes.Home
 * JD-Core Version:    0.6.0
 */