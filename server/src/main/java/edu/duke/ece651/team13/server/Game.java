package edu.duke.ece651.team13.server;

import edu.duke.ece651.team13.shared.Player;
import edu.duke.ece651.team13.shared.map.MapRO;
import edu.duke.ece651.team13.shared.order.Order;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * The interface of Game
 */
public interface Game {


    void initPlayer(String name, Socket clientSocket);

    Iterator<Player> getPlayersIterator();

    /**
     * Get the board of one game
     *
     * @return Board game board
     */
    MapRO getMap();

    void playOneTurn();

    /**
     * Validate a batch of orders made by one player
     *
     * @param orders is the list of orders made by a player
     * @return null if the batch of order is valid,
     * description of error if invalid
     */
    String validateOrders(ArrayList<Order> orders);

    /**
     * Get the player by the player's name
     *
     * @return the player with the corresponding name if found
     * null if such player is not found
     */
    Player getPlayerByName(String name);
}