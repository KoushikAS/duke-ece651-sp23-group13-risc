package edu.duke.ece651.team13.shared.territory;

import edu.duke.ece651.team13.shared.Player;
import edu.duke.ece651.team13.shared.PlayerRO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * This class handles the owner, neighbors, unit numbers
 * and current attackers of the territory
 */
public class GameTerritory implements Territory, Serializable {
  private final String name;
  private PlayerRO owner;
  private int unitNum;
  private ArrayList<TerritoryRO> neighbours;
  private HashMap<PlayerRO, Integer> attackers;

  public GameTerritory(String name) {
    this.name = name;
    this.owner = null;
    this.unitNum = 0;
    this.attackers = new HashMap<>();
    this.neighbours = new ArrayList<>();
  }

  private GameTerritory(GameTerritory toCopy){
    this(toCopy.name, toCopy.owner, toCopy.unitNum);
  }

  private GameTerritory(String name,
                        PlayerRO owner,
                        int unitNum){
    this.name = name;
    this.owner = owner;
    this.unitNum = unitNum;
    this.attackers = new HashMap<>();
    this.neighbours = new ArrayList<>();
  }

  /**
   * Get the name of the territory
   * 
   * @return the String name
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * Get the owner
   * 
   * @return the Player that is currently the owner of the territory
   */
  @Override
  public PlayerRO getOwner() {
    return this.owner;
  }

  /**
   * Get the number of units that are currently in the territory
   * 
   * @return the integer unitNum
   */
  @Override
  public int getUnitNum() {
    return this.unitNum;
  }


  /**
   * Set the owner
   * 
   * @param newOwner is the new owner
   */
  @Override
  public void setOwner(PlayerRO newOwner) {
    this.owner = newOwner;
  }

  /**
   * Set the unit number
   * precondition: newUnitNum must be >= 0, if not, throws
   * IllegalArgumentException
   * 
   * @param newUnitNum is the new unitNum
   */
  @Override
  public void setUnitNum(int newUnitNum) {
    if (newUnitNum < 0)
      throw new IllegalArgumentException("The unit number in a territory should be >= 0.");
    this.unitNum = newUnitNum;
  }

  /**
   * Add an attacker to the attacker list of this turn
   * 
   * @param attacker      is the attacking player
   * @param attackUnitNum is the number of attacking units
   */
  @Override
  public void addAttacker(PlayerRO attacker, int attackUnitNum) {
    if(attackers.containsKey(attacker)){
      attackers.put(attacker, attackers.get(attacker) + attackUnitNum);
    }
    else{
      attackers.put(attacker, attackUnitNum);
    }
  }

  /**
   * Remove all the attackers
   */
  @Override
  public void clearAttackers() {
    attackers.clear();
  }

  /**
   * Get the attackers
   * 
   * @return the map of attacking players to the attacking unit numbers
   */
  @Override
  public HashMap<PlayerRO, Integer> getAttackers() {
    return attackers;
  }

  /**
   * Get the iterator of neighbours
   *
   */
  @Override
  public Iterator<TerritoryRO> getNeighbourIterartor() {
    return neighbours.iterator();
  }

  /**
   * Add Neighbouring terrtiory to the current Territory if it is not added
   * already or if the neighbour is not the current territory
   *
   */
  @Override
  public void addNeighbours(TerritoryRO neighbour) {
    if (!this.equals(neighbour) && !neighbours.contains(neighbour)) {
      neighbours.add(neighbour);
    }
  }

  @Override
  public Territory replicate() {
    return new GameTerritory(this);
  }
}
