package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.model.FixedPassHandler;
import cleancode.studycafe.tobe.model.PassTypeHandler;
import cleancode.studycafe.tobe.io.InputHandler;
import cleancode.studycafe.tobe.io.OutputHandler;
import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;

import java.util.List;

public class StudyCafePassMachine {

    private final InputHandler inputHandler = new InputHandler();
    private final OutputHandler outputHandler = new OutputHandler();
    private final PassTypeHandler passTypeHandler = new PassTypeHandler();
    private final FixedPassHandler fixedPassHandler = new FixedPassHandler(outputHandler, inputHandler);

    public void run() {
        try {
            outputHandler.showWelcomeMessage();
            outputHandler.showAnnouncement();

            outputHandler.askPassTypeSelection();
            StudyCafePassType studyCafePassType = inputHandler.getPassTypeSelectingUserAction();

            List<StudyCafePass> passes = passTypeHandler.getPassesByType(studyCafePassType);
            outputHandler.showPassListForSelection(passes);
            StudyCafePass selectedPass = inputHandler.getSelectPass(passes);

            if (studyCafePassType == StudyCafePassType.FIXED) {
                fixedPassHandler.handleFixedPass(selectedPass);
            } else {
                outputHandler.showPassOrderSummary(selectedPass, null);
            }
        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }
}
