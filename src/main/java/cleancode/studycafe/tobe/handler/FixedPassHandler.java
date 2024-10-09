package cleancode.studycafe.tobe.handler;

import cleancode.studycafe.tobe.io.InputHandlerInterface;
import cleancode.studycafe.tobe.io.OutputHandlerInterface;
import cleancode.studycafe.tobe.io.StudyCafeFileHandler;
import cleancode.studycafe.tobe.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.StudyCafePass;

import java.util.List;

public class FixedPassHandler {

    private final OutputHandlerInterface outputHandler;
    private final InputHandlerInterface inputHandler;
    private final StudyCafeFileHandler studyCafeFileHandler = new StudyCafeFileHandler();

    public FixedPassHandler(OutputHandlerInterface outputHandler, InputHandlerInterface inputHandler) {
        this.outputHandler = outputHandler;
        this.inputHandler = inputHandler;
    }

    public void handleFixedPass(StudyCafePass selectedPass) {
        List<StudyCafeLockerPass> lockerPasses = studyCafeFileHandler.readLockerPasses();
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