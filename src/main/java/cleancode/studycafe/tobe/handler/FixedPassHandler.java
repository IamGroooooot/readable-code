package cleancode.studycafe.tobe.handler;

import cleancode.studycafe.tobe.io.InputHandlerInterface;
import cleancode.studycafe.tobe.io.OutputHandlerInterface;
import cleancode.studycafe.tobe.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.StudyCafeLockerPassCollection;
import cleancode.studycafe.tobe.model.StudyCafePass;

import java.util.Optional;

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
        StudyCafeLockerPassCollection lockerPasses = fileHandler.readLockerPasses();
        Optional<StudyCafeLockerPass> lockerPass = lockerPasses.getLockerPassForSelectedPass(selectedPass);

        boolean lockerSelection = false;
        if (lockerPass.isPresent()) {
            outputHandler.askLockerPass(lockerPass.orElse(null));
            lockerSelection = inputHandler.getLockerSelection();
        }

        if (lockerSelection) {
            outputHandler.showPassOrderSummary(selectedPass, lockerPass.orElse(null));
        } else {
            outputHandler.showPassOrderSummary(selectedPass, null);
        }
    }

}