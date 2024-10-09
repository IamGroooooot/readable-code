package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.handler.FileHandler;
import cleancode.studycafe.tobe.handler.FixedPassHandler;
import cleancode.studycafe.tobe.handler.PassTypeHandler;
import cleancode.studycafe.tobe.io.InputHandlerInterface;
import cleancode.studycafe.tobe.io.OutputHandlerInterface;
import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;
import cleancode.studycafe.tobe.service.PassSelectionService;

public class StudyCafePassMachine {

    private final InputHandlerInterface inputHandler;
    private final OutputHandlerInterface outputHandler;
    private final PassSelectionService passSelectionService;
    private final FixedPassHandler fixedPassHandler;

    public StudyCafePassMachine(InputHandlerInterface inputHandler, OutputHandlerInterface outputHandler, FileHandler fileHandler) {
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
        PassTypeHandler passTypeHandler = new PassTypeHandler();
        this.passSelectionService = new PassSelectionService(inputHandler, outputHandler, passTypeHandler);
        this.fixedPassHandler = new FixedPassHandler(outputHandler, inputHandler);
    }

    public void run() {
        try {
            outputHandler.showWelcomeMessage();
            outputHandler.showAnnouncement();

            outputHandler.askPassTypeSelection();
            StudyCafePassType studyCafePassType = inputHandler.getPassTypeSelectingUserAction();

            StudyCafePass selectedPass = passSelectionService.selectPassByType(studyCafePassType);

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
