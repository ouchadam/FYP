package com.ouchadam.fyp.analysis.midi;

import javax.sound.midi.ShortMessage;

public enum Type {
    ON {
        @Override
        public int asStatus() {
            return ShortMessage.NOTE_ON;
        }
    },
    OFF {
        @Override
        public int asStatus() {
            return ShortMessage.NOTE_OFF;
        }
    };

    public abstract int asStatus();

    static Type from(ShortMessage message) {
        switch (message.getCommand()) {

            case ShortMessage.NOTE_ON:
                return ON;

            case ShortMessage.NOTE_OFF:
                return OFF;

            default:
                throw new RuntimeException("Tried to create a midi note with a unhandled command : " + message.getCommand());
        }
    }

}
