package com.itmo.tpo.task3.model.impl;

import com.itmo.tpo.task3.exceptions.NoAccessToPassageException;
import com.itmo.tpo.task3.exceptions.PersonNotInTheSameGroupException;
import com.itmo.tpo.task3.model.Alive;
import com.itmo.tpo.task3.model.Describable;
import com.itmo.tpo.task3.model.Movable;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@Builder
public class Person implements Alive, Movable, Describable {

    @NonNull
    private String surname;
    private Group group;
    @NonNull
    private Environment location;

    public String usePassage(@NonNull Passage passage) {
        if (!location.equals(passage.getThisSide()) && !location.equals(passage.getOtherSide()))
            throw new NoAccessToPassageException(this, passage);
        String message;
        if (passage.isLocked()) message = description() + " пытался пройти через " + passage.description() + ", но " +
                "проход оказался заперт";
        else {
            message = description() + " прошёл через " + passage.description();
            if (Objects.nonNull(group)) {
                group.removeMember(this);
                setGroup(null);
            }
            if (location.equals(passage.getThisSide())) setLocation(passage.getOtherSide());
            else setLocation(passage.getThisSide());
        }

        return message;
    }

    public String hear() {
        return description() + " слышит " + Optional.of(location).map(Environment::getSound).orElse("Nothing");
    }

    public String lookAround() {
        if (Objects.isNull(location.getThings()) || location.getThings().isEmpty())
            return description() + " не видит ничего интересного вокруг себя.";
        else {
            return description() + " видит " +
                    location.getThings().stream().map(EnvironmentThing::getName).sorted().collect(Collectors.joining(", ")) + ".";
        }
    }

    public String think(@NonNull Describable describable, @NonNull String thought) {
        return description() + " думает о " + Optional.of(describable).map(Describable::description).orElse("Nothing")
                + ": " + thought + ".";
    }

    public String generateSound(@NonNull String speech) {
        String message = description() + " сказал: \"" + speech + "\".";
        location.setSound(message);
        return message;
    }

    public String smell() {
        return description() + " почуял " + Optional.of(location).map(Environment::getSmell).orElse("Nothing");
    }

    public String speakTo(@NonNull Person person, @NonNull String speech) {
        if (Objects.isNull(group) || Objects.isNull(group.getMembers()) || !group.getMembers().contains(person))
            throw new PersonNotInTheSameGroupException(this, person);
        return description() + " обратился к " + person.description() + " и сказал: \"" + speech + "\".";
    }

    public String follow(@NonNull Person person) {
        if (Objects.isNull(group) || Objects.isNull(group.getMembers()) || !group.getMembers().contains(person))
            throw new PersonNotInTheSameGroupException(this, person);
        return description() + " следует за " + person.description();
    }

    public String tryUnlockPassage(@NonNull Passage passage, @NonNull String probablySecretSwitch) {
        if (!location.equals(passage.getThisSide()) && !location.equals(passage.getOtherSide()))
            throw new NoAccessToPassageException(this, passage);
        StringBuilder message = new StringBuilder(description() + " пытается отпереть " + passage.description() + "...\n");
        if (!passage.isLocked()) message.append("Но проход оказался не заперт.");
        else {
            message.append("Он пробует нажать на ").append(probablySecretSwitch).append("...\n");
            if (passage.tryUnlock(probablySecretSwitch)) message.append("И проход открылся!");
            else message.append("Но ничего не произошло.");
        }

        return message.toString();
    }

    @Override
    public String description() {
        return getSurname();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return surname.equals(person.surname) && Objects.equals(group, person.group) && Objects.equals(location, person.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(surname);
    }
}
