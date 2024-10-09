package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.handler.FileHandler;
import cleancode.studycafe.tobe.handler.FixedPassHandler;
import cleancode.studycafe.tobe.handler.PassTypeHandler;
import cleancode.studycafe.tobe.io.InputHandlerInterface;
import cleancode.studycafe.tobe.io.OutputHandlerInterface;
import cleancode.studycafe.tobe.service.StudyCafeService;

public class StudyCafePassMachine {

    private final StudyCafeService studyCafeService;

    public StudyCafePassMachine(InputHandlerInterface inputHandler, OutputHandlerInterface outputHandler, FileHandler fileHandler) {
        PassTypeHandler passTypeHandler = new PassTypeHandler();
        FixedPassHandler fixedPassHandler = new FixedPassHandler(outputHandler, inputHandler);
        this.studyCafeService = new StudyCafeService(inputHandler, outputHandler, passTypeHandler, fixedPassHandler);
    }

    public void run() {
        studyCafeService.startSession();
    }

}
