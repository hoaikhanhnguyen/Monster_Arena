package Monsters;

import androidx.annotation.NonNull;

import java.util.Random;

import Abilities.*;

public abstract class Monster {

    String player = "Player";
    String name;
    Integer monsterLevel;
    String monsterType;
    Integer strength;
    Integer agility;
    Integer xp;
    Integer nextLevel;
    Integer defense;
    Attack attack;          // need to implement diff attacks (water, fire, grass)
                            // similar to Monster.java assignment

    // public Monster(String name, Integer hp, Integer xp) {
    //    this.name = name;
    //    this.hp = hp;
    //    this.xp = xp;
    //}


    public Monster(String name, Integer monsterLevel, Integer strength, Integer agility, Integer xp, Integer nextLevel, Integer defense, Attack attack) {

        this.name = name;
        this.monsterLevel = monsterLevel;
        //this.monsterType = monsterType;
        this.strength = strength;
        this.agility = agility;
        this.xp = xp;
        this.nextLevel = nextLevel;
        this.defense = defense;
        this.attack = attack;
    }

    /**
     * Simple toString()
     * This method will be overwritten in the specific monster class
     * @return Name (String) , hp (Integer) and xp (Integer)
     */
    @NonNull
    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", monsterLevel=" + monsterLevel +
                ", monsterType='" + monsterType + '\'' +
                ", strength=" + strength +
                ", xp=" + xp +
                '}';
    }

    /**
     * This methods takes in a min and max to return a random Integer
     * The Integer can be used to set stats to random numbers when
     * a monster is captured.
     * @param min an int
     * @param max an int
     * @return random number between min and max
     */
    Integer getAttribute(Integer min, Integer max) {
        Random rand = new Random();
        if (min > max) {
            Integer temp = min;
            min = max;
            max = temp;
        }
        // returns a random number between min and max inclusive
        return rand.nextInt(max - min) + min;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMonsterLevel() {
        return monsterLevel;
    }

    public void setMonsterLevel(Integer monsterLevel) {
        this.monsterLevel = monsterLevel;
    }

    public String getMonsterType() {
        return monsterType;
    }

    public void setMonsterType(String monsterType) {
        this.monsterType = monsterType;
    }

    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public Integer getAgility() {
        return agility;
    }

    public void setAgility(Integer agility) {
        this.agility = agility;
    }

    public Integer getXp() {
        return xp;
    }

    public void setXp(Integer xp) {
        this.xp = xp;
    }

    public Integer getNextLevel() {
        return nextLevel;
    }

    public void setNextLevel(Integer nextLevel) {
        this.nextLevel = nextLevel;
    }

    public Integer getDefense() {
        return defense;
    }

    public void setDefense(Integer defense) {
        this.defense = defense;
    }

    public Attack getAttack() {
        return attack;
    }

    public void setAttack(Attack attack) {
        this.attack = attack;
    }
}


