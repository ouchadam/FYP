package com.ouchadam.fyp.analysis;

import javax.sound.midi.Sequence;

enum Division {
    PPQ, SMPTE_24, SMPTE_25, SMPTE_30, SMPTE_30DROP;

    static Division from(Sequence sequence) {
        float divisionType = sequence.getDivisionType();
        if (divisionType == Sequence.PPQ) {
            return PPQ;
        } else if (divisionType == Sequence.SMPTE_24) {
            return SMPTE_24;
        } else if (divisionType == Sequence.SMPTE_25) {
            return SMPTE_25;
        } else if (divisionType == Sequence.SMPTE_30) {
            return SMPTE_30;
        } else if (divisionType == Sequence.SMPTE_30DROP) {
            return SMPTE_30DROP;
        }
        throw new RuntimeException("Unhandled division type : " + divisionType);
    }
}
