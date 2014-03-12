package com.ouchadam.fyp.presentation;

public enum RuleName {
    RANGE {
        @Override
        public String toName() {
            return "Range";
        }
    }, KEY {
        @Override
        public String toName() {
            return "Key";
        }
    }, DIVERSITY {
        @Override
        public String toName() {
            return "Diversity";
        }
    }, INTERVAL {
        @Override
        public String toName() {
            return "Interval Jumps";
        }
    }, EVEN_RHYTHM {
        @Override
        public String toName() {
            return "Even Rhythm";
        }
    }, MIN_NOTE {
        @Override
        public String toName() {
            return "Minimum Notes";
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

}
