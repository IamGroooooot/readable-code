package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.handler.FileHandler;
import cleancode.studycafe.tobe.io.*;

public class StudyCafeApplication {

    public static void main(String[] args) {
        final InputHandlerInterface inputHandler = new InputHandler();
        final OutputHandlerInterface outputHandler = new OutputHandler();
        final FileHandler fileHandler = new StudyCafeFileHandler();
        StudyCafePassMachine studyCafePassMachine = new StudyCafePassMachine(inputHandler, outputHandler, fileHandler);
        studyCafePassMachine.run();
    }

}
