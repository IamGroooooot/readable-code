package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.handler.FixedPassHandler;
import cleancode.studycafe.tobe.handler.PassTypeHandler;
import cleancode.studycafe.tobe.io.InputHandler;
import cleancode.studycafe.tobe.io.InputHandlerInterface;
import cleancode.studycafe.tobe.io.OutputHandler;
import cleancode.studycafe.tobe.io.OutputHandlerInterface;
import cleancode.studycafe.tobe.io.StudyCafeFileHandler;
import cleancode.studycafe.tobe.service.StudyCafeService;

public class StudyCafeFactory {

    public InputHandlerInterface createInputHandler() {
        return new InputHandler();
    }

    public OutputHandlerInterface createOutputHandler() {
        return new OutputHandler();
    }

    public StudyCafeFileHandler createFileHandler() {
        return new StudyCafeFileHandler();
    }

    public PassTypeHandler createPassTypeHandler() {
        return new PassTypeHandler();
    }

    public FixedPassHandler createFixedPassHandler(OutputHandlerInterface outputHandler, InputHandlerInterface inputHandler) {
        return new FixedPassHandler(outputHandler, inputHandler);
    }

    public StudyCafeService createStudyCafeService(InputHandlerInterface inputHandler, OutputHandlerInterface outputHandler, PassTypeHandler passTypeHandler, FixedPassHandler fixedPassHandler) {
        return new StudyCafeService(inputHandler, outputHandler, passTypeHandler, fixedPassHandler);
    }

}
