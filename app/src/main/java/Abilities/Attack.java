package Abilities;

import Monsters.*;

/**
 * Name: R. Sergio Garcia
 * Date: 7/30/2024
 * Explanation: Attack interface
 * defines attack interface
 * will be implemented by fireAttack, waterAttack, grassAttack
 */
public interface Attack extends Ability{
    default Integer attack(Monster target){
        return null;
    }

}
