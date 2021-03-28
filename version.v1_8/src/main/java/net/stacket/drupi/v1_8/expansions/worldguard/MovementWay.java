package net.stacket.drupi.v1_8.expansions.worldguard;

/**
 * describes the way how a player left/entered a region
 * @author mewin
 */
public enum MovementWay {
    /**
     * this way is used if a player entered/left a region by walking
     */
    MOVE,
    /**
     * this way is used if a player teleported into a region / out of a region
     */
    TELEPORT,
    /**
     * this way is used if a player spawned in a region
     */
    SPAWN,
    /**
     * this way is used if a player left a region by disconnecting
     */
    DISCONNECT
}