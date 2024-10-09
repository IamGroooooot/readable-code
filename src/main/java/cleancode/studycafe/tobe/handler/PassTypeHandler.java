package cleancode.studycafe.tobe.handler;

import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;

import java.util.List;
import java.util.stream.Collectors;

public class PassTypeHandler {

    private final FileHandler studyCafeFileHandler;

    public PassTypeHandler(FileHandler fileHandler) {
        this.studyCafeFileHandler = fileHandler;
    }

    public List<StudyCafePass> getPassesByType(StudyCafePassType passType) {
        List<StudyCafePass> studyCafePasses = studyCafeFileHandler.readStudyCafePasses();
        return studyCafePasses.stream()
                .filter(pass -> pass.getPassType() == passType)
                .collect(Collectors.toList());
    }

}
