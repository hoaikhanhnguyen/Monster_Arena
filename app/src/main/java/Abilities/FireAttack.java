package Abilities;

import Monsters.*;

public class FireAttack implements Attack {

    Monster attacker;

    public FireAttack(Monster attacker) {
        this.attacker = attacker;
    }

    public Integer attack(Monster target){
        String message = attacker + " uses a Fire Attack on " + target;
        System.out.println(message);
        return attacker.getStrength() - target.getDefense();
    }
}
