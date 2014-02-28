package com.ouchadam.fyp.analysis;

public enum Key {
    A {
        @Override public int value() {
            return 9;
        }
    }, Bb {
        @Override public int value() {
            return 10;
        }
    }, B {
        @Override public int value() {
            return 11;
        }
    }, C {
        @Override public int value() {
            return 0;
        }
    }, Db {
        @Override public int value() {
            return 1;
        }
    }, D {
        @Override public int value() {
            return 2;
        }
    }, Eb {
        @Override public int value() {
            return 3;
        }
    }, E {
        @Override public int value() {
            return 4;
        }
    }, F {
        @Override public int value() {
            return 5;
        }
    }, Gb {
        @Override public int value() {
            return 6;
        }
    }, G {
        @Override public int value() {
            return 7;
        }
    }, Ab {
        @Override public int value() {
            return 8;
        }
    };

    public abstract int value();

    public static Key from(int note) {
        for (Key key : values()) {
            if (note == key.value()) {
                return key;
            }
        }
        throw new IllegalArgumentException("Tried to parse note : " + note + " but is not a valid note");
    }
}
