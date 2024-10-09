package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.config.Configuration;
import cleancode.studycafe.tobe.handler.FileHandler;
import cleancode.studycafe.tobe.handler.FixedPassHandler;
import cleancode.studycafe.tobe.handler.PassTypeHandler;
import cleancode.studycafe.tobe.io.InputHandlerInterface;
import cleancode.studycafe.tobe.io.OutputHandlerInterface;
import cleancode.studycafe.tobe.service.StudyCafeService;

public class StudyCafePassMachine {

    private final StudyCafeService studyCafeService;

    public StudyCafePassMachine(StudyCafeFactory factory, Configuration configuration) {
        InputHandlerInterface inputHandler = factory.createInputHandler();
        OutputHandlerInterface outputHandler = factory.createOutputHandler();
        FileHandler fileHandler = factory.createFileHandler(configuration);
        PassTypeHandler passTypeHandler = factory.createPassTypeHandler(fileHandler);
        FixedPassHandler fixedPassHandler = factory.createFixedPassHandler(outputHandler, inputHandler, fileHandler);

        this.studyCafeService = factory.createStudyCafeService(inputHandler, outputHandler, passTypeHandler, fixedPassHandler);
    }

    public void run() {
        studyCafeService.startSession();
    }
}
