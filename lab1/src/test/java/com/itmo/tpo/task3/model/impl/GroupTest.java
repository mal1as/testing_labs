package com.itmo.tpo.task3.model.impl;

import com.itmo.tpo.task3.exceptions.PersonNotInTheSameGroupException;
import com.itmo.tpo.task3.exceptions.PersonNotInTheSameLocationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class GroupTest {

    private Environment firstLocation;
    private Environment secondLocation;
    private Person firstPerson;
    private Person secondPerson;
    private Person thirdPerson;
    private Group group;

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
        firstPerson = Person.builder()
                .surname("Richardo")
                .location(firstLocation)
                .build();
        secondPerson = Person.builder()
                .surname("Billy")
                .location(firstLocation)
                .build();
        thirdPerson = Person.builder()
                .surname("Sasha")
                .location(secondLocation)
                .build();
        group = new Group();
        group.addMember(firstPerson);
    }

    @Test
    @DisplayName("Check massNotice method")
    public void massNoticeTest() {
        String expected = "Все вместе наблюдают за: Event";
        assertEquals(expected, group.massNotice(firstLocation));

        expected = "Все вместе наблюдают за: Nothing";
        firstLocation.setEvent(null);
        assertEquals(expected, group.massNotice(firstLocation));
    }

    @Test
    @DisplayName("Check addMember method")
    public void addMemberTest() {
        String expected = "Billy присоединился к группе.";
        assertEquals(expected, group.addMember(secondPerson));
        assertEquals(Set.of(firstPerson, secondPerson), group.getMembers());
        assertEquals(group, secondPerson.getGroup());

        assertThrows(PersonNotInTheSameLocationException.class, () -> group.addMember(thirdPerson));
        assertEquals(Set.of(firstPerson, secondPerson), group.getMembers());
        assertNull(thirdPerson.getGroup());
    }

    @Test
    @DisplayName("Check removeMember method")
    public void removeMemberTest() {
        String expected = "Richardo покинул группу.";
        assertEquals(expected, group.removeMember(firstPerson));

        assertThrows(PersonNotInTheSameGroupException.class, () -> group.removeMember(firstPerson));
    }
}
