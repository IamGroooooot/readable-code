package cleancode.studycafe.tobe.model;

import java.util.List;
import java.util.Optional;

public class StudyCafeLockerPassCollection {

    private final List<StudyCafeLockerPass> lockerPasses;

    public StudyCafeLockerPassCollection(List<StudyCafeLockerPass> lockerPasses) {
        this.lockerPasses = lockerPasses;
    }

    public Optional<StudyCafeLockerPass> getLockerPassForSelectedPass(StudyCafePass selectedPass) {
        return lockerPasses.stream()
                .filter(lockerPass -> lockerPass.getPassType() == selectedPass.getPassType() &&
                        lockerPass.getDuration() == selectedPass.getDuration())
                .findFirst();
    }

}
