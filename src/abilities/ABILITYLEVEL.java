package abilities;

public enum ABILITYLEVEL {

    ONE, TWO, THREE, FOUR, FIVE;

    static ABILITYLEVEL getNextLevel(ABILITYLEVEL level) {
        switch (level) {
            case ONE -> level = TWO;
            case TWO -> level = THREE;
            case THREE -> level = FOUR;
            default -> level = FIVE;
        }

        return level;
    }

}