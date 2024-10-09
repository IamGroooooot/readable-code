package cleancode.studycafe.tobe.model;

import java.util.List;
import java.util.stream.Collectors;

public class StudyCafePassCollection {

    private final List<StudyCafePass> passes;

    public StudyCafePassCollection(List<StudyCafePass> passes) {
        this.passes = passes;
    }

    public List<StudyCafePass> getPassesByType(StudyCafePassType passType) {
        return passes.stream()
                .filter(pass -> pass.getPassType() == passType)
                .collect(Collectors.toList());
    }
}
