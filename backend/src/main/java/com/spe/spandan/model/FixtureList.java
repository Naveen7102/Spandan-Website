package com.spe.spandan.model;

import java.util.ArrayList;
import java.util.List;

public class FixtureList {
    public List<FixtureReturn> fixtureList;

    public FixtureList(List<FixtureReturn> fixtureList) {
        this.fixtureList = fixtureList;
    }

    public FixtureList() {
        this.fixtureList = new ArrayList<>();
    }

    public List<FixtureReturn> getFixtureList() {
        return fixtureList;
    }

    public void setFixtureList(List<FixtureReturn> fixtureList) {
        this.fixtureList = fixtureList;
    }
}
