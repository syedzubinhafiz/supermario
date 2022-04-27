package game.enums;

/**
 * Use this enum class to give `buff` or `debuff`.
 * It is also useful to give a `state` to abilities or actions that can be attached-detached.
 */
public enum Status {
    HOSTILE_TO_ENEMY, // use this status to be considered hostile towards enemy (e.g., to be attacked by enemy)
    TALL, // status for Player Buff after consuming SuperMushroom
    MUST_JUMP,
    INVINCIBLE,
    RESET,
    DORMANT // status for dormant Koopas
    // use this status to tell that current instance has "grown" and the MUST_JUMP to signify that the actor cannot simply walk over certain surfaces and must jump onto that surface .
}
