package com.itmo.tpo.task3.model.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PassageTest {

    private Environment firstLocation;
    private Environment secondLocation;
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
        passage = Passage.builder()
                .name("Secret door")
                .thisSide(firstLocation)
                .otherSide(secondLocation)
                .build();
    }

    @Test
    @DisplayName("Check tryUnlock method")
    public void tryUnlockTest() {
        assertTrue(passage.tryUnlock("mama"));
        passage.setSecretSwitch("mama");
        assertTrue(passage.tryUnlock("mama"));
        assertFalse(passage.isLocked());
        assertFalse(passage.tryUnlock("papa"));
    }
}
