package assignment;

import java.util.ArrayList;

public class RightCommand extends NoArgumentCommand {

    public static final boolean endTurn = true;

    public boolean isTurnEnded() {
        return endTurn;
    }

    public int executeCommand(Critter critter, int currentLine) {

        critter.right();

        return currentLine + 1;

    }

}
