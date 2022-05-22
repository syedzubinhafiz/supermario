package edu.monash.fit2099.game.enums;

/**
 * Enum class to give `buff` or `debuff`.
 * It is also useful to give a `state` to abilities or actions that can be attached-detached.
 *
 * @author Vanessa Khoo Ming Yi
 * @version 1.0.0
 * @see edu.monash.fit2099.game.enums
 */
public enum Status {
    /**
     * use this status to be considered hostile towards enemy (e.g., to be attacked by enemy)
     */
    HOSTILE_TO_ENEMY,
    /**
     * status for Buff after consuming SuperMushroom (can jump over higher ground)
     */
    TALL,
    /**
     * status for actors who must jump over higher ground
     */
    MUST_JUMP,
    /**
     * status for Buff after consuming PowerStar
     */
    INVINCIBLE,
    /**
     * status for whether the item has to be RESET (all other resettable classes)
     * or has already been RESET (Player class only)
     */
    RESET,
    /**
     * status for currently dormant actors
     */
    DORMANT,
    /**
     * status for actors who have a wrench
     */
    HAS_WRENCH,
    /**
     * status for the ability to be DORMANT
     */
    HAS_DORMANCY,
    /**
     * status for the ability to stand in lava
     */
    CAN_STAND_LAVA,
    /**
     * status for representing fire attack buff
     */
    FIRE_ATTACK_EFFECT,
    /**
     * status appended to key for checking purposes
     */
    END_GAME,
    /**
     * status for actor being able to enter Floor ground type
     */
    CAN_ENTER_FLOOR,
    /**
     * status for all Enemies that can follow the player
     */
    FOLLOW,
    HIGHER_GROUND,
    /**
     * status for actor having obtained a bottle
     */
    HAS_BOTTLE,
    /**
     * status for Fountain ground types
     */
    FOUNTAIN,
    /**
     * status for actors that can fly
     */
    FLYING
}
