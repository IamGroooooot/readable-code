package cleancode.studycafe.model.pass.locker;

import cleancode.studycafe.model.pass.StudyCafePassType;
import cleancode.studycafe.model.pass.StudyCafeSeatPass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DisplayName("StudyCafeLockerPasses 클래스 테스트")
public class StudyCafeLockerPassesTest {

    @Test
    @DisplayName("of 메서드는 올바른 StudyCafeLockerPasses 인스턴스를 생성해야 한다.")
    public void testOfMethod() {
        // given
        StudyCafeLockerPass lockerPass1 = StudyCafeLockerPass.of(StudyCafePassType.FIXED, 3, 3000);
        StudyCafeLockerPass lockerPass2 = StudyCafeLockerPass.of(StudyCafePassType.FIXED, 2, 2000);
        List<StudyCafeLockerPass> lockerPasses = List.of(lockerPass1, lockerPass2);

        // when
        StudyCafeLockerPasses lockerPassesInstance = StudyCafeLockerPasses.of(lockerPasses);

        // then
        assertThat(lockerPassesInstance).isNotNull();
        // 내부 리스트를 직접 검증할 수 없으므로 findLockerPassBy 메서드를 사용하여 간접 검증
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 3, 3000, 1.0);
        Optional<StudyCafeLockerPass> result = lockerPassesInstance.findLockerPassBy(seatPass);
        assertThat(result).isPresent().contains(lockerPass1);
    }

    @Test
    @DisplayName("findLockerPassBy 메서드는 일치하는 StudyCafeLockerPass를 반환해야 한다.")
    public void testFindLockerPassBy_MatchingPass() {
        // given
        StudyCafeLockerPass lockerPass1 = StudyCafeLockerPass.of(StudyCafePassType.FIXED, 3, 3000);
        StudyCafeLockerPass lockerPass2 = StudyCafeLockerPass.of(StudyCafePassType.FIXED, 2, 2000);
        StudyCafeLockerPasses lockerPasses = StudyCafeLockerPasses.of(List.of(lockerPass1, lockerPass2));

        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 3, 3000, 1.0);

        // when
        Optional<StudyCafeLockerPass> result = lockerPasses.findLockerPassBy(seatPass);

        // then
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(lockerPass1);
    }

    @Test
    @DisplayName("findLockerPassBy 메서드는 일치하는 StudyCafeLockerPass가 없을 때 Optional.empty를 반환해야 한다.")
    public void testFindLockerPassBy_NoMatchingPass() {
        // given
        StudyCafeLockerPass lockerPass1 = StudyCafeLockerPass.of(StudyCafePassType.FIXED, 2, 2000);
        StudyCafeLockerPass lockerPass2 = StudyCafeLockerPass.of(StudyCafePassType.FIXED, 1, 1000);
        StudyCafeLockerPasses lockerPasses = StudyCafeLockerPasses.of(List.of(lockerPass1, lockerPass2));

        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 3, 3000, 1.0); // duration 3, no match

        // when
        Optional<StudyCafeLockerPass> result = lockerPasses.findLockerPassBy(seatPass);

        // then
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("findLockerPassBy 메서드는 빈 lockerPasses 리스트에서 Optional.empty를 반환해야 한다.")
    public void testFindLockerPassBy_EmptyList() {
        // given
        StudyCafeLockerPasses lockerPasses = StudyCafeLockerPasses.of(List.of());

        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 3, 3000, 1.0);

        // when
        Optional<StudyCafeLockerPass> result = lockerPasses.findLockerPassBy(seatPass);

        // then
        assertThat(result).isEmpty();
    }
}