package game.enums;

/**
 * Enum class to give `buff` or `debuff`.
 * It is also useful to give a `state` to abilities or actions that can be attached-detached.
 *
 * @author: Vanessa Khoo Ming Yi
 * @version: 1.0.0
 * @see: edu.monash.fit2099.game.enums
 */
public enum Status {
    HOSTILE_TO_ENEMY, // use this status to be considered hostile towards enemy (e.g., to be attacked by enemy)
    TALL, // status for Buff after consuming SuperMushroom
    MUST_JUMP, // status for actors who must jump over higher ground
    INVINCIBLE, // status for Buff after consuming PowerStar
    RESET, // status for whether the item has to be RESET (all other resettable classes)
           // or has already been RESET (Player class only)
    DORMANT, // status for currently dormant actors
    HAS_WRENCH, // status for actors who have a wrench
    ENEMY, // status for whetehr the actor is an enemy to Player
    HAS_DORMANCY // status for the ability to be DORMANT
    // use this status to tell that current instance has "grown" and the MUST_JUMP to signify that the actor cannot simply walk over certain surfaces and must jump onto that surface .
}
