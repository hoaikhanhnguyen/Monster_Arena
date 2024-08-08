package com.example.monster_arena.database.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import java.time.Instant;
import java.time.LocalDateTime;
import com.example.monster_arena.database.MonsterArenaDatabase;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;

@Entity(tableName = MonsterArenaDatabase.MONSTERS_TABLE)
public class Monsters {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String description;
    private Integer user_id;
    private Integer level;
    private double next_level;
    private String type;
    private Integer attack;
    private Integer defense;
    private Integer agility;
    private double experience;
    private int hp;
    //private LocalDateTime created_at;

    public Monsters(String name, String description, Integer user_id, Integer level, double next_level, String type, int hp, Integer attack, Integer defense, Integer agility, double experience) {
        this.name = name;
        this.description = description;
        this.user_id = user_id;
        this.level = level;
        this.next_level = next_level;
        this.hp = hp;
        this.type = type;
        this.attack = attack;
        this.defense = defense;
        this.agility = agility;
        this.experience = experience;
        //created_at = LocalDateTime.now();
    }

    public Object getMonsterName() {
        return name;
    }

    public class LocalDateTypeConverter {
        @TypeConverter
        public long convertDateToLong(LocalDateTime created_at) {
            ZonedDateTime zdt = ZonedDateTime.of(created_at, ZoneId.systemDefault());
            return zdt.toInstant().toEpochMilli();
        }

        @TypeConverter
        public LocalDateTime convertLongToDate(Long epochMilli) {
            Instant instant = Instant.ofEpochMilli(epochMilli);
            return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Monsters monsters = (Monsters) o;
        return id == monsters.id && Double.compare(next_level, monsters.next_level) == 0 && Double.compare(experience, monsters.experience) == 0 && hp == monsters.hp && Objects.equals(name, monsters.name) && Objects.equals(description, monsters.description) && Objects.equals(user_id, monsters.user_id) && Objects.equals(level, monsters.level) && Objects.equals(type, monsters.type) && Objects.equals(attack, monsters.attack) && Objects.equals(defense, monsters.defense) && Objects.equals(agility, monsters.agility);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, user_id, level, next_level, type, attack, defense, agility, experience, hp);
    }

    @NonNull
    @Override
    public String toString() {
        return description + '\n' +
                "level: " + level + "\n" +
                "experience: " + experience + "\n" +
                "attack: " + attack + "\n" +
                "defense: " + defense;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public double getNext_level() {
        return next_level;
    }

    public void setNext_level(double next_level) {
        this.next_level = next_level;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getAttack() {
        return attack;
    }

    public void setAttack(Integer attack) {
        this.attack = attack;
    }

    public Integer getDefense() {
        return defense;
    }

    public void setDefense(Integer defense) {
        this.defense = defense;
    }

    public Integer getAgility() {
        return agility;
    }

    public void setAgility(Integer agility) {
        this.agility = agility;
    }

    public double getExperience() {
        return experience;
    }

    public void setExperience(double experience) {
        this.experience = experience;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void attack(Monsters enemy) {
        int newHp = enemy.getHp() - this.attack;
        enemy.setHp(newHp);
    }
}
