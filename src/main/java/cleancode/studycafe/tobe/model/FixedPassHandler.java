package cleancode.studycafe.tobe.model;

import cleancode.studycafe.tobe.io.InputHandler;
import cleancode.studycafe.tobe.io.OutputHandler;
import cleancode.studycafe.tobe.io.StudyCafeFileHandler;

import java.util.List;

public class FixedPassHandler {

    private final OutputHandler outputHandler;
    private final InputHandler inputHandler;
    private final StudyCafeFileHandler studyCafeFileHandler = new StudyCafeFileHandler();

    public FixedPassHandler(OutputHandler outputHandler, InputHandler inputHandler) {
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