package marcel.demonworld.armygenerator.enums;

public enum GameEnums {
    NONE,
    NO_ALLY,
    // generic items are marked with a "*" in the DB.
    GENERIC {
        @Override
        public String toString() {
            return "*";
        }
    }
}
