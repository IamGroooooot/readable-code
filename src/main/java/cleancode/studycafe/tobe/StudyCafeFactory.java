package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.config.Configuration;
import cleancode.studycafe.tobe.handler.FileHandler;
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

    public StudyCafeFileHandler createFileHandler(Configuration configuration) {
        return new StudyCafeFileHandler(configuration);
    }

    public PassTypeHandler createPassTypeHandler(FileHandler fileHandler) {
        return new PassTypeHandler(fileHandler);
    }

    public FixedPassHandler createFixedPassHandler(OutputHandlerInterface outputHandler, InputHandlerInterface inputHandler, FileHandler studyCafeFileHandler) {
        return new FixedPassHandler(outputHandler, inputHandler, studyCafeFileHandler);
    }

    public StudyCafeService createStudyCafeService(InputHandlerInterface inputHandler, OutputHandlerInterface outputHandler, PassTypeHandler passTypeHandler, FixedPassHandler fixedPassHandler) {
        return new StudyCafeService(inputHandler, outputHandler, passTypeHandler, fixedPassHandler);
    }

}
