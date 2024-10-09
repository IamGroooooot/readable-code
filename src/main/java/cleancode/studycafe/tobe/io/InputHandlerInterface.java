package cleancode.studycafe.tobe.io;

import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;

import java.util.List;

public interface InputHandlerInterface {
    StudyCafePassType getPassTypeSelectingUserAction();
    StudyCafePass getSelectPass(List<StudyCafePass> passes);
    boolean getLockerSelection();
}
