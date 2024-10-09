package cleancode.studycafe.tobe.handler;

import cleancode.studycafe.tobe.io.InputHandlerInterface;
import cleancode.studycafe.tobe.io.OutputHandlerInterface;
import cleancode.studycafe.tobe.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.StudyCafePass;

import java.util.List;

public class FixedPassHandler {

    private final OutputHandlerInterface outputHandler;
    private final InputHandlerInterface inputHandler;
    private final FileHandler fileHandler;

    public FixedPassHandler(OutputHandlerInterface outputHandler, InputHandlerInterface inputHandler, FileHandler fileHandler) {
        this.outputHandler = outputHandler;
        this.inputHandler = inputHandler;
        this.fileHandler = fileHandler;
    }

    public void handleFixedPass(StudyCafePass selectedPass) {
        List<StudyCafeLockerPass> lockerPasses = fileHandler.readLockerPasses();
        StudyCafeLockerPass lockerPass = lockerPasses.stream()
                .filter(option -> option.getPassType() == selectedPass.getPassType() &&
                        option.getDuration() == selectedPass.getDuration())
                .findFirst()
                .orElse(null);

        boolean lockerSelection = false;
        if (lockerPass != null) {
            outputHandler.askLockerPass(lockerPass);
            lockerSelection = inputHandler.getLockerSelection();
        }

        if (lockerSelection) {
            outputHandler.showPassOrderSummary(selectedPass, lockerPass);
        } else {
            outputHandler.showPassOrderSummary(selectedPass, null);
        }
    }
}