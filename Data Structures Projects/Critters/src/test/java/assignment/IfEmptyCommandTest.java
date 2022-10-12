package assignment;

import junit.framework.TestCase;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class IfEmptyCommandTest extends TestCase {

    public void testExecuteCommand() {

        testCommand(Critter.WALL, "123", 6);
        testCommand(Critter.WALL, "r10", 6);
        testCommand(Critter.WALL, "+10", 6);
        testCommand(Critter.WALL, "-10", 6);

        testCommand(Critter.ALLY, "123", 6);
        testCommand(Critter.ALLY, "r10", 6);
        testCommand(Critter.ALLY, "+10", 6);
        testCommand(Critter.ALLY, "-15", 6);

        testCommand(Critter.ENEMY, "123", 6);
        testCommand(Critter.ENEMY, "r10", 6);
        testCommand(Critter.ENEMY, "+10", 6);
        testCommand(Critter.ENEMY, "-15", 6);

        testCommand(Critter.EMPTY, "123", 123);
        testCommand(Critter.EMPTY, "r10", 10);
        testCommand(Critter.EMPTY, "+10", 15);
        testCommand(Critter.EMPTY, "-10", -5);
    }

    //a is the value returned by the critter method
    //n is the line change
    //nextLine is what the next line should be
    public void testCommand(int a, String n, int nextLine) {
        Command command = new IfEmptyCommand();

        ArrayList<String> args = new ArrayList<>();

        args.add("0");
        args.add(n);

        command.processTokens(args);

        Critter c = mock(Critter.class);

        when(c.getCellContent(0)).thenReturn(a);
        when(c.getReg(10)).thenReturn(10);

        int line = command.executeCommand(c, 5);

        assertEquals("jumped to wrong line", nextLine, line);

        verify(c).getCellContent(0);

    }

}