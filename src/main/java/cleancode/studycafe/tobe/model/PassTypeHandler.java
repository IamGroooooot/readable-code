package cleancode.studycafe.tobe.model;

import cleancode.studycafe.tobe.io.StudyCafeFileHandler;

import java.util.List;
import java.util.stream.Collectors;

public class PassTypeHandler {

    private final StudyCafeFileHandler studyCafeFileHandler = new StudyCafeFileHandler();

    public List<StudyCafePass> getPassesByType(StudyCafePassType passType) {
        List<StudyCafePass> studyCafePasses = studyCafeFileHandler.readStudyCafePasses();
        return studyCafePasses.stream()
                .filter(pass -> pass.getPassType() == passType)
                .collect(Collectors.toList());
    }

}
