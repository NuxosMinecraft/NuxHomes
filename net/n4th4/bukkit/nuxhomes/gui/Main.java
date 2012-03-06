package net.n4th4.bukkit.nuxhomes.gui;

import java.text.Collator;
import java.util.Collections;
import java.util.List;
import net.n4th4.bukkit.nuxhomes.HomesList;
import net.n4th4.bukkit.nuxhomes.NuxHomes;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.getspout.spoutapi.gui.GenericContainer;
import org.getspout.spoutapi.gui.GenericLabel;
import org.getspout.spoutapi.gui.GenericPopup;
import org.getspout.spoutapi.gui.GenericTextField;
import org.getspout.spoutapi.gui.Widget;
import org.getspout.spoutapi.player.SpoutPlayer;

public class Main extends GenericPopup
{
  private GenericContainer searchContainer = new GenericContainer();
  private GenericContainer centralContainer = new GenericContainer();
  private GenericContainer tabsContainer = new GenericContainer();
  public SearchTextField searchField = new SearchTextField(this);
  public GenericTextField homeField = new GenericTextField();
  public int height = 20;
  public String currentTab = new String("Homes");
  public int index = 0;
  public List<String> playersList;
  private SpoutPlayer spoutplayer;
  public String searchedPlayer;
  public NuxHomes plugin;

  public Main(NuxHomes plugin, SpoutPlayer spoutplayer)
  {
    this.plugin = plugin;
    this.spoutplayer = spoutplayer;
    this.screen = spoutplayer.getMainScreen();

    fillList("");

    setSearchContainer(spoutplayer.getName());
    setHomesContainer();
    setTabsContainer();
  }

  public void setSearchContainer(String newPlayer) {
    int baseY = 6;

    this.searchedPlayer = newPlayer;

    removeWidget(this.searchContainer);
    this.searchContainer = new GenericContainer();

    Collator collator = Collator.getInstance();
    collator.setStrength(1);
    Collections.sort(this.playersList, collator);

    for (int column = 0; (column < 4) && 
      (this.index * 4 + column != this.playersList.size()); column++)
    {
      String name = (String)this.playersList.get(this.index * 4 + column);
      PlayerButton playerButton = new PlayerButton(this, name);
      playerButton.setY(baseY).setX(6 + 105 * column);
      this.searchContainer.addChild(playerButton);
    }

    baseY += 25;

    GenericLabel currentLabel = new GenericLabel("Player : " + this.searchedPlayer);

    NavButton nextButton = new NavButton(this, ">>");
    NavButton prevButton = new NavButton(this, "<<");

    currentLabel.setY(baseY).setX(6);
    this.searchField.setY(baseY).setX(111);
    prevButton.setY(baseY).setX(321);
    nextButton.setY(baseY).setX(373);

    if (this.index == 0)
      prevButton.setEnabled(false);
    if (this.index * 4 + 4 >= this.playersList.size()) {
      nextButton.setEnabled(false);
    }
    this.searchContainer.addChildren(new Widget[] { currentLabel, this.searchField, prevButton, nextButton });

    attachWidget(this.plugin, this.searchContainer);
    setDirty(true);
  }

  private void setTabsContainer() {
    int baseY = 214;

    removeWidget(this.tabsContainer);

    TabButton homesTab = new TabButton(this, "Homes");
    TabButton adminTab = new TabButton(this, "Admin");

    homesTab.setY(baseY).setX(111);
    adminTab.setY(baseY).setX(216);

    this.tabsContainer.addChildren(new Widget[] { homesTab, adminTab });
    attachWidget(this.plugin, this.tabsContainer);
    setDirty(true);
  }

  public void fillList(String prefix) {
    this.index = 0;

    if (this.currentTab.equals("Homes")) {
      if (prefix.isEmpty()) {
        this.playersList = this.plugin.homesList.listPlayers(this.spoutplayer.getWorld().getName());
      } else {
        List tempList = this.plugin.homesList.listPlayers(this.spoutplayer.getWorld().getName());
        this.playersList.clear();
        for (int i = 0; i < tempList.size(); i++)
          if (((String)tempList.get(i)).toLowerCase().startsWith(prefix.toLowerCase()))
            this.playersList.add(tempList.get(i));
      }
    }
    else {
      Player[] tempList = this.plugin.getServer().getOnlinePlayers();
      if (prefix.isEmpty()) {
        this.playersList.clear();
        for (int i = 0; i < tempList.length; i++)
          this.playersList.add(tempList[i].getName());
      }
      else {
        this.playersList.clear();
        for (int i = 0; i < tempList.length; i++)
          if (tempList[i].getName().toLowerCase().startsWith(prefix.toLowerCase()))
            this.playersList.add(tempList[i].getName());
      }
    }
  }

  public void setCentralContainer()
  {
    if (this.currentTab.equals("Homes"))
      setHomesContainer();
    else if (this.currentTab.equals("Admin"))
      setAdminContainer();
  }

  private void setHomesContainer() {
    int baseY = 65;

    removeWidget(this.centralContainer);

    this.centralContainer = new GenericContainer();
    List list = this.plugin.homesList.listHomes(this.spoutplayer.getWorld().getName(), this.searchedPlayer);

    for (int i = 0; i < list.size(); i++)
    {
      GenericLabel label = new GenericLabel((String)list.get(i));
      GoButton goButton = new GoButton(this);
      DeleteButton deleteButton = new DeleteButton(this);
      InfoButton infoButton = new InfoButton(this);

      label.setY(baseY + 25 * i).setX(6);
      goButton.setY(baseY + 25 * i).setX(111);
      deleteButton.setY(baseY + 25 * i).setX(216);
      infoButton.setY(baseY + 25 * i).setX(321);

      if ((!this.spoutplayer.hasPermission("nuxhomes.others.delete")) && (!this.spoutplayer.getName().equals(this.searchedPlayer))) {
        deleteButton.setEnabled(false);
      }
      GenericContainer homeContainer = new GenericContainer();
      homeContainer.addChildren(new Widget[] { label, goButton, deleteButton });
      this.centralContainer.addChild(homeContainer);
    }

    baseY += 125;

    this.homeField = new GenericTextField();
    this.homeField.setHeight(this.height).setWidth(100);
    SetButton setButton = new SetButton(this);

    this.homeField.setY(baseY).setX(111);
    setButton.setY(baseY).setX(216);

    if (!this.spoutplayer.hasPermission("nuxhomes.multi.set"))
      setButton.setEnabled(false);
    if ((!this.spoutplayer.hasPermission("nuxhomes.others.set")) && (!this.spoutplayer.getName().equals(this.searchedPlayer))) {
      setButton.setEnabled(false);
    }
    this.centralContainer.addChildren(new Widget[] { this.homeField, setButton });
    attachWidget(this.plugin, this.centralContainer);
    setDirty(true);
  }

  private void setAdminContainer() {
    int baseY = 60;

    removeWidget(this.centralContainer);

    CommandButton sunnyButton = new CommandButton(this, "Sunny", "weather sunny");
    CommandButton stormyButton = new CommandButton(this, "Stormy", "weather stormy");
    CommandButton dayButton = new CommandButton(this, "Day", "time day");
    CommandButton nightButton = new CommandButton(this, "Night", "time night");

    sunnyButton.setY(baseY).setX(6);
    stormyButton.setY(baseY).setX(111);
    dayButton.setY(baseY).setX(216);
    nightButton.setY(baseY).setX(321);

    baseY = 85;

    CommandButton normalButton = new CommandButton(this, "Normal", "gamemode %player% 0");
    CommandButton creativeButton = new CommandButton(this, "Creative", "gamemode %player% 1");
    CommandButton godButton = new CommandButton(this, "God", "god %player%");
    CommandButton ungodButton = new CommandButton(this, "Ungod", "ungod %player%");

    normalButton.setY(baseY).setX(6);
    creativeButton.setY(baseY).setX(111);
    godButton.setY(baseY).setX(216);
    ungodButton.setY(baseY).setX(321);

    this.centralContainer = new GenericContainer();
    this.centralContainer.addChildren(new Widget[] { sunnyButton, stormyButton, dayButton, nightButton, normalButton, creativeButton, godButton, ungodButton });
    attachWidget(this.plugin, this.centralContainer);
    setDirty(true);
  }
}

/* Location:           /home/munrek/Téléchargements/nuxos/backup nuxos/bukkit/plugins/NuxHomes.jar
 * Qualified Name:     net.n4th4.bukkit.nuxhomes.gui.Main
 * JD-Core Version:    0.6.0
 */