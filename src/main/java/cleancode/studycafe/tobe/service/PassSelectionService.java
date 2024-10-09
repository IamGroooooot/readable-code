package cleancode.studycafe.tobe.service;

import cleancode.studycafe.tobe.handler.PassTypeHandler;
import cleancode.studycafe.tobe.io.InputHandlerInterface;
import cleancode.studycafe.tobe.io.OutputHandlerInterface;
import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;

import java.util.List;

public class PassSelectionService {

    private final InputHandlerInterface inputHandler;
    private final OutputHandlerInterface outputHandler;
    private final PassTypeHandler passTypeHandler;

    public PassSelectionService(InputHandlerInterface inputHandler, OutputHandlerInterface outputHandler, PassTypeHandler passTypeHandler) {
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
        this.passTypeHandler = passTypeHandler;
    }

    public StudyCafePass selectPassByType(StudyCafePassType passType) {
        List<StudyCafePass> passes = passTypeHandler.getPassesByType(passType);
        outputHandler.showPassListForSelection(passes);
        return inputHandler.getSelectPass(passes);
    }

}
