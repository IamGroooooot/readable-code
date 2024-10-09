package cleancode.studycafe.tobe.service;

import cleancode.studycafe.tobe.model.PassTypeHandler;
import cleancode.studycafe.tobe.io.InputHandler;
import cleancode.studycafe.tobe.io.OutputHandler;
import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;

import java.util.List;

public class PassSelectionService {

    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;
    private final PassTypeHandler passTypeHandler;

    public PassSelectionService(InputHandler inputHandler, OutputHandler outputHandler, PassTypeHandler passTypeHandler) {
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
