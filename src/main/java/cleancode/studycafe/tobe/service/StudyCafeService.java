package cleancode.studycafe.tobe.service;

import cleancode.studycafe.tobe.handler.FixedPassHandler;
import cleancode.studycafe.tobe.handler.PassTypeHandler;
import cleancode.studycafe.tobe.io.InputHandlerInterface;
import cleancode.studycafe.tobe.io.OutputHandlerInterface;
import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;

public class StudyCafeService {

    private final InputHandlerInterface inputHandler;
    private final OutputHandlerInterface outputHandler;
    private final FixedPassHandler fixedPassHandler;
    private final PassSelectionService passSelectionService;

    public StudyCafeService(InputHandlerInterface inputHandler, OutputHandlerInterface outputHandler, PassTypeHandler passTypeHandler, FixedPassHandler fixedPassHandler) {
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
        this.fixedPassHandler = fixedPassHandler;
        this.passSelectionService = new PassSelectionService(inputHandler, outputHandler, passTypeHandler);
    }

    public void startSession() {
        try {
            showWelcomeAndAnnouncements();
            StudyCafePassType selectedPassType = getPassTypeFromUser();
            StudyCafePass selectedPass = passSelectionService.selectPassByType(selectedPassType);
            handleSelectedPass(selectedPassType, selectedPass);
        } catch (Exception e) {
            handleError(e);
        }
    }

    private void showWelcomeAndAnnouncements() {
        outputHandler.showWelcomeMessage();
        outputHandler.showAnnouncement();
    }

    private StudyCafePassType getPassTypeFromUser() {
        outputHandler.askPassTypeSelection();
        return inputHandler.getPassTypeSelectingUserAction();
    }

    private void handleSelectedPass(StudyCafePassType passType, StudyCafePass selectedPass) {
        if (passType == StudyCafePassType.FIXED) {
            fixedPassHandler.handleFixedPass(selectedPass);
        } else {
            outputHandler.showPassOrderSummary(selectedPass, null);
        }
    }

    private void handleError(Exception e) {
        if (e instanceof RuntimeException) {
            outputHandler.showSimpleMessage(e.getMessage());
        } else {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

}
