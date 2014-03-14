package com.ouchadam.fyp.presentation.tab.rule;

public enum RuleName {
    RANGE {
        @Override
        public String toName() {
            return "Range";
        }

        @Override
        public String toolTip() {
            return "The maximum allowed range either side of the calculated base. eg. 10 12 14 has a range of 2 : +2 -2.";
        }

    }, KEY {
        @Override
        public String toName() {
            return "Key";
        }

        @Override
        public String toolTip() {
            return "The Key interval to enforce. Could be Major, Minor Harmonic, Minor Melodic or Minor Natural.";
        }
    }, DIVERSITY {
        @Override
        public String toName() {
            return "Diversity";
        }

        @Override
        public String toolTip() {
            return "The minimum amount of different notes.";
        }
    }, INTERVAL {
        @Override
        public String toName() {
            return "Interval Jumps";
        }

        @Override
        public String toolTip() {
            return "Force an initial jump of the selected size and then have the following notes jump with 2-3 interval steps up or down from the preceding note";
        }

    }, EVEN_RHYTHM {
        @Override
        public String toName() {
            return "Even Rhythm";
        }

        @Override
        public String toolTip() {
            return "Force all the notes to be on even 16th";
        }
    }, MIN_NOTE {
        @Override
        public String toName() {
            return "Minimum Notes";
        }

        @Override
        public String toolTip() {
            return "Force a minimum amount of notes to be played";
        }
    };

    public abstract String toName();

    public static RuleName from(String name) {
        for (RuleName ruleName : values()) {
            if (name.equalsIgnoreCase(ruleName.toName())) {
                return ruleName;
            }
        }
        throw new RuntimeException("unhandled rule name : " + name);
    }

    public abstract String toolTip();
}
