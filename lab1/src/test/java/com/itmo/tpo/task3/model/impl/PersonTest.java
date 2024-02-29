package com.itmo.tpo.task3.model.impl;

import com.itmo.tpo.task3.exceptions.NoAccessToPassageException;
import com.itmo.tpo.task3.exceptions.PersonNotInTheSameGroupException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {

    private Environment firstLocation;
    private Environment secondLocation;
    private Environment thirdLocation;
    private Person firstPerson;
    private Person secondPerson;
    private Group group;
    private Passage passage;

    @BeforeEach
    public void init() {
        firstLocation = Environment.builder()
                .name("Desert")
                .sound("Sound")
                .smell("Smell")
                .event("Event")
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
        firstPerson = Person.builder()
                .surname("Richardo")
                .location(firstLocation)
                .build();
        secondPerson = Person.builder()
                .surname("Billy")
                .location(firstLocation)
                .group(group)
                .build();
        group = new Group();
        group.addMember(firstPerson);
        group.addMember(secondPerson);
        passage = Passage.builder()
                .name("Secret door")
                .thisSide(firstLocation)
                .otherSide(secondLocation)
                .locked(false)
                .secretSwitch("mama")
                .build();
    }

    @Test
    @DisplayName("Check usePassage method")
    public void usePassageTest() {
        String expected = "Richardo прошёл через Secret door";
        assertEquals(expected, firstPerson.usePassage(passage));
        assertEquals(secondLocation, firstPerson.getLocation());
        assertNull(firstPerson.getGroup());

        expected = "Richardo пытался пройти через Secret door, но проход оказался заперт";
        passage.setLocked(true);
        assertEquals(expected, firstPerson.usePassage(passage));
        assertEquals(secondLocation, firstPerson.getLocation());

        passage.setLocked(false);
        firstPerson.setLocation(thirdLocation);
        assertThrows(NoAccessToPassageException.class, () -> firstPerson.usePassage(passage));
    }

    @Test
    @DisplayName("Check hear method")
    public void hearTest() {
        String expected = "Richardo слышит Sound";
        assertEquals(expected, firstPerson.hear());

        expected = "Richardo слышит Nothing";
        firstLocation.setSound(null);
        assertEquals(expected, firstPerson.hear());
    }

    @Test
    @DisplayName("Check lookAround method")
    public void lookAroundTest() {
        String expected = "Richardo не видит ничего интересного вокруг себя.";
        assertEquals(expected, firstPerson.lookAround());

        expected = "Richardo видит cactus, scarab.";
        firstLocation.setThings(Set.of(new EnvironmentThing("scarab"), new EnvironmentThing("cactus")));
        assertEquals(expected, firstPerson.lookAround());
    }

    @Test
    @DisplayName("Check think method")
    public void thinkTest() {
        String expected = "Richardo думает о girl: nice dress.";
        assertEquals(expected, firstPerson.think(() -> "girl", "nice dress"));

        expected = "Richardo думает о Nothing: lol.";
        assertEquals(expected, firstPerson.think(() -> null, "lol"));
    }

    @Test
    @DisplayName("Check generateSound method")
    public void generateSoundTest() {
        String expected = "Richardo сказал: \"Hola!\".";
        assertEquals(expected, firstPerson.generateSound("Hola!"));
        assertEquals(expected, firstPerson.getLocation().getSound());
    }

    @Test
    @DisplayName("Check smell method")
    public void smellTest() {
        String expected = "Richardo почуял Smell";
        assertEquals(expected, firstPerson.smell());
    }

    @Test
    @DisplayName("Check speakTo method")
    public void speakToTest() {
        String expected = "Richardo обратился к Billy и сказал: \"Hola!\".";
        assertEquals(expected, firstPerson.speakTo(secondPerson, "Hola!"));

        firstPerson.setGroup(null);
        assertThrows(PersonNotInTheSameGroupException.class, () -> firstPerson.speakTo(secondPerson, "Hola!"));
    }

    @Test
    @DisplayName("Check follow method")
    public void followTest() {
        String expected = "Richardo следует за Billy";
        assertEquals(expected, firstPerson.follow(secondPerson));
    }

    @Test
    @DisplayName("Check tryUnlockPassage method")
    public void tryUnlockPassageTest() {
        String expected = "Richardo пытается отпереть Secret door...\nНо проход оказался не заперт.";
        assertEquals(expected, firstPerson.tryUnlockPassage(passage, "mama"));

        expected = "Richardo пытается отпереть Secret door...\nОн пробует нажать на mama...\nИ проход открылся!";
        passage.setLocked(true);
        assertEquals(expected, firstPerson.tryUnlockPassage(passage, "mama"));

        expected = "Richardo пытается отпереть Secret door...\nОн пробует нажать на papa...\nНо ничего не произошло.";
        passage.setLocked(true);
        assertEquals(expected, firstPerson.tryUnlockPassage(passage, "papa"));

        firstPerson.setLocation(thirdLocation);
        assertThrows(NoAccessToPassageException.class, () -> firstPerson.tryUnlockPassage(passage, "mama"));
    }
}
