package com.itmo.tpo.task3.model.impl;

import com.itmo.tpo.task3.exceptions.NoAccessToPassageException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MonsterTest {

    private Environment firstLocation;
    private Environment secondLocation;
    private Environment thirdLocation;
    private Monster monster;
    private Passage passage;

    @BeforeEach
    public void init() {
        firstLocation = Environment.builder()
                .name("Desert")
                .sound("Nothing")
                .smell("Nothing")
                .event("Nothing")
                .build();
        secondLocation = Environment.builder()
                .name("River")
                .sound("Nothing")
                .smell("Nothing")
                .event("Nothing")
                .build();
        thirdLocation = Environment.builder()
                .name("Space")
                .build();
        monster = Monster.builder()
                .name("Cheburashka")
                .location(firstLocation)
                .build();
        passage = Passage.builder()
                .name("Secret door")
                .thisSide(firstLocation)
                .otherSide(secondLocation)
                .locked(false)
                .build();
    }

    @Test
    @DisplayName("Check usePassage method")
    public void usePassageTest() {
        String expected = "Cheburashka " +
                "хлюпая слизью, продавило свою зелёную, желеобразную тушу через Secret door";
        assertEquals(expected, monster.usePassage(passage));
        assertEquals(secondLocation, monster.getLocation());

        expected = "Cheburashka хлюпая, " +
                "попыталось выдавить свою зелёную, желеобразную тушу через Secret door, но " +
                "проход оказался заперт";
        passage.setLocked(true);
        assertEquals(expected, monster.usePassage(passage));
        assertEquals(secondLocation, monster.getLocation());

        passage.setLocked(false);
        monster.setLocation(thirdLocation);
        assertThrows(NoAccessToPassageException.class, () -> monster.usePassage(passage));
    }

    @Test
    @DisplayName("Check generateSound method")
    public void generateSoundTest() {
        String expected = "Cheburashka издало: \"УБУБУБУБУ!!!\".";
        assertEquals(expected, monster.generateSound("УБУБУБУБУ!!!"));
        expected = "УБУБУБУБУ!!!";
        assertEquals(expected, monster.getLocation().getSound());
    }

    @Test
    @DisplayName("Check generateGoo method")
    public void generateGooTest(){
        String expected = "Cheburashka источает слизь.";
        assertEquals(expected, monster.generateGoo());
        HashSet<EnvironmentThing> expectedSet = new HashSet<>();
        expectedSet.add(new EnvironmentThing("Комок слизи"));
        assertEquals(expectedSet, monster.getLocation().getThings());
    }

    @Test
    @DisplayName("Check generateSmell method")
    void generateSmellTest() {
        String expected = "Cheburashka источает смрад.";
        assertEquals(expected, monster.generateSmell());

        expected = "смрад";
        assertEquals(expected, monster.getLocation().getSmell());
    }
}
