package cleancode.studycafe.tobe.io;

import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;

import java.util.List;
import java.util.Scanner;

public class InputHandler implements InputHandlerInterface {

    private static final Scanner SCANNER = new Scanner(System.in);

    public StudyCafePassType getPassTypeSelectingUserAction() {
        String userInput = getUserInput();
        return parsePassType(userInput);
    }

    public StudyCafePass getSelectPass(List<StudyCafePass> passes) {
        String userInput = getUserInput();
        int selectedIndex = parseSelectedIndex(userInput, passes.size());
        return passes.get(selectedIndex);
    }

    public boolean getLockerSelection() {
        String userInput = getUserInput();
        return parseLockerSelection(userInput);
    }

    private String getUserInput() {
        return SCANNER.nextLine();
    }

    private StudyCafePassType parsePassType(String userInput) {
        switch (userInput) {
            case "1":
                return StudyCafePassType.HOURLY;
            case "2":
                return StudyCafePassType.WEEKLY;
            case "3":
                return StudyCafePassType.FIXED;
            default:
                throw new AppException("잘못된 입력입니다.");
        }
    }

    private int parseSelectedIndex(String userInput, int listSize) {
        try {
            int selectedIndex = Integer.parseInt(userInput) - 1;
            if (selectedIndex < 0 || selectedIndex >= listSize) {
                throw new AppException("잘못된 선택입니다.");
            }
            return selectedIndex;
        } catch (NumberFormatException e) {
            throw new AppException("숫자를 입력해 주세요.");
        }
    }

    private boolean parseLockerSelection(String userInput) {
        return switch (userInput) {
            case "1" -> true;
            case "2" -> false;
            default -> throw new AppException("잘못된 입력입니다.");
        };
    }
}
