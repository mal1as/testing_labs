package com.itmo.tpo.task3.model.impl;

import com.itmo.tpo.task3.model.Describable;
import lombok.Data;
import lombok.NonNull;

@Data
public class EnvironmentThing implements Describable {

    @NonNull
    private String name;

    public EnvironmentThing(String name) {
        this.name = name;
    }

    @Override
    public String description() {
        return getName();
    }
}
