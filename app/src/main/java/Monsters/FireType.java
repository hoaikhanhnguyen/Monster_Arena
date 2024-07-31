package Monsters;

import androidx.annotation.NonNull;
import java.util.Random;
import Abilities.*;

public class FireType extends Monster{


    public FireType(String name, Integer monsterLevel, Integer strength, Integer agility, Integer xp, Integer nextLevel, Integer defense, Attack attack) {
        super(name, monsterLevel, strength, agility, xp, nextLevel, defense, attack);

        int min = 0;
        int max = 10;

        Random rand = new Random();
        this.player = getPlayer();
        this.name = "FireType";
        this.monsterLevel = monsterLevel;
        this.strength = super.getAttribute(min,rand.nextInt(max));
        this.agility = super.getAttribute(min,rand.nextInt(max));
        this.xp = super.getAttribute(min,rand.nextInt(max));
        this.defense = super.getAttribute(min,rand.nextInt(max));
        this.attack = new FireAttack(this);
    }

    /**
     * Simple toString() method
     * @return super info
     */
    @NonNull
    @Override
    public String toString() {
        return "FireType " + super.toString();
    }
}
