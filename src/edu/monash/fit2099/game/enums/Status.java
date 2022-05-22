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
    CAN_STAND_LAVA,
    FIRE_ATTACK_EFFECT,
    END_GAME,
    CAN_ENTER_FLOOR,
    FOLLOW,
    HIGHER_GROUND,
    HAS_BOTTLE,
    FOUNTAIN,
    CAN_DRINK,
    FLYING
}
