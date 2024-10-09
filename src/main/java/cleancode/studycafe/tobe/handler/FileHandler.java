package cleancode.studycafe.tobe.handler;

import cleancode.studycafe.tobe.model.StudyCafeLockerPassCollection;
import cleancode.studycafe.tobe.model.StudyCafePassCollection;

public interface FileHandler {

    StudyCafePassCollection readStudyCafePasses();
    StudyCafeLockerPassCollection readLockerPasses();

}
