package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.handler.FixedPassHandler;
import cleancode.studycafe.tobe.handler.PassTypeHandler;
import cleancode.studycafe.tobe.io.InputHandlerInterface;
import cleancode.studycafe.tobe.io.OutputHandlerInterface;
import cleancode.studycafe.tobe.service.StudyCafeService;

public class StudyCafePassMachine {

    private final StudyCafeService studyCafeService;

    public StudyCafePassMachine() {
        StudyCafeFactory factory = new StudyCafeFactory();

        InputHandlerInterface inputHandler = factory.createInputHandler();
        OutputHandlerInterface outputHandler = factory.createOutputHandler();
        factory.createFileHandler();
        PassTypeHandler passTypeHandler = factory.createPassTypeHandler();
        FixedPassHandler fixedPassHandler = factory.createFixedPassHandler(outputHandler, inputHandler);

        this.studyCafeService = factory.createStudyCafeService(inputHandler, outputHandler, passTypeHandler, fixedPassHandler);
    }

    public void run() {
        studyCafeService.startSession();
    }
}
