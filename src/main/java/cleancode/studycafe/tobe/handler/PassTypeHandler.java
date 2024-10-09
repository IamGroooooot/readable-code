package cleancode.studycafe.tobe.handler;

import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassCollection;
import cleancode.studycafe.tobe.model.StudyCafePassType;

import java.util.List;

public class PassTypeHandler {

    private final FileHandler fileHandler;

    public PassTypeHandler(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    public List<StudyCafePass> getPassesByType(StudyCafePassType passType) {
        StudyCafePassCollection passCollection = fileHandler.readStudyCafePasses();
        return passCollection.getPassesByType(passType);
    }

}
