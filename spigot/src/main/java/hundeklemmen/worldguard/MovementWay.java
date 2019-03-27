package hundeklemmen.worldguard;

/**
 * describes the way how a player left/entered a Region
 * @author mewin
 */
public enum MovementWay {
   /**
    * this way is used if a player entered/left a Region by walking
    */
   MOVE, 
   /**
    * this way is used if a player teleported into a Region / out of a Region
    */
   TELEPORT, 
   /**
    * this way is used if a player spawned in a Region
    */
   SPAWN, 
   /**
    * this way is used if a player left a Region by disconnecting
    */
   DISCONNECT
}
