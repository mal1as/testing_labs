package com.itmo.tpo.task3.model.impl;

import com.itmo.tpo.task3.exceptions.PersonNotInTheSameGroupException;
import com.itmo.tpo.task3.exceptions.PersonNotInTheSameLocationException;
import com.itmo.tpo.task3.model.Describable;
import lombok.Data;
import lombok.NonNull;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class Group implements Describable {

    private Set<Person> members = new HashSet<>();

    public String massNotice(@NonNull Describable something) {
        return "Все вместе наблюдают за: " + Optional.ofNullable(something.description()).orElse("Nothing");
    }

    public String addMember(@NonNull Person person) {
        if (members.isEmpty() || Objects.equals(person.getLocation(), members.iterator().next().getLocation())) {
            person.setGroup(this);
            members.add(person);
            return person.description() + " присоединился к группе.";
        }
        throw new PersonNotInTheSameLocationException(person, this);
    }

    public String removeMember(@NonNull Person person) {
        if (members.contains(person)) {
            person.setGroup(null);
            members.remove(person);
            return person.description() + " покинул группу.";
        }
        throw new PersonNotInTheSameGroupException();
    }

    @Override
    public String description() {
        return "Группа людей (" + this.members.stream().map(Person::description).collect(Collectors.joining(", ")) + ")";
    }
}
