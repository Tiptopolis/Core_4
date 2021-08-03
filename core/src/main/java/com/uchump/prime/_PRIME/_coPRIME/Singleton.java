package com.uchump.prime._PRIME._coPRIME;

public enum Singleton {
    INSTANCE,
    GROUP,
    CLASS,
    TYPE;
    private Singleton() {
        //This is called the first time this enum is initialised
        System.out.println("I am initialised");
    }
}