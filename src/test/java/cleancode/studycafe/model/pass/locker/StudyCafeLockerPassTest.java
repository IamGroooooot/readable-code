package cleancode.studycafe.model.pass.locker;

import cleancode.studycafe.model.pass.StudyCafePassType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("StudyCafeLockerPass 클래스 테스트")
public class StudyCafeLockerPassTest {

    @Test
    @DisplayName("of 메서드는 올바른 StudyCafeLockerPass 인스턴스를 생성해야 한다.")
    public void testOfMethod() {
        // given
        StudyCafePassType passType = StudyCafePassType.FIXED;
        int duration = 3;
        int price = 3000;

        // when
        StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(passType, duration, price);

        // then
        assertThat(lockerPass).isNotNull();
        assertThat(lockerPass.getPassType()).isEqualTo(passType);
        assertThat(lockerPass.getDuration()).isEqualTo(duration);
        assertThat(lockerPass.getPrice()).isEqualTo(price);
    }

    @Test
    @DisplayName("isSamePassType 메서드는 동일한 패스 타입일 때 true를 반환해야 한다.")
    public void testIsSamePassType_SameType() {
        // given
        StudyCafePassType passType = StudyCafePassType.WEEKLY;
        StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(passType, 2, 2000);

        // when
        boolean result = lockerPass.isSamePassType(StudyCafePassType.WEEKLY);

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("isSamePassType 메서드는 다른 패스 타입일 때 false를 반환해야 한다.")
    public void testIsSamePassType_DifferentType() {
        // given
        StudyCafePassType passType = StudyCafePassType.HOURLY;
        StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(passType, 1, 1000);

        // when
        boolean result = lockerPass.isSamePassType(StudyCafePassType.FIXED);

        // then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("isSameDuration 메서드는 동일한 기간일 때 true를 반환해야 한다.")
    public void testIsSameDuration_SameDuration() {
        // given
        StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(StudyCafePassType.FIXED, 3, 3000);

        // when
        boolean result = lockerPass.isSameDuration(3);

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("isSameDuration 메서드는 다른 기간일 때 false를 반환해야 한다.")
    public void testIsSameDuration_DifferentDuration() {
        // given
        StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(StudyCafePassType.FIXED, 2, 2000);

        // when
        boolean result = lockerPass.isSameDuration(3);

        // then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("getPassType 메서드는 올바른 패스 타입을 반환해야 한다.")
    public void testGetPassType() {
        // given
        StudyCafePassType passType = StudyCafePassType.WEEKLY;
        StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(passType, 2, 2000);

        // when
        StudyCafePassType retrievedPassType = lockerPass.getPassType();

        // then
        assertThat(retrievedPassType).isEqualTo(passType);
    }

    @Test
    @DisplayName("getDuration 메서드는 올바른 기간을 반환해야 한다.")
    public void testGetDuration() {
        // given
        int duration = 3;
        StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(StudyCafePassType.FIXED, duration, 3000);

        // when
        int retrievedDuration = lockerPass.getDuration();

        // then
        assertThat(retrievedDuration).isEqualTo(duration);
    }

    @Test
    @DisplayName("getPrice 메서드는 올바른 가격을 반환해야 한다.")
    public void testGetPrice() {
        // given
        int price = 2500;
        StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(StudyCafePassType.HOURLY, 1, price);

        // when
        int retrievedPrice = lockerPass.getPrice();

        // then
        assertThat(retrievedPrice).isEqualTo(price);
    }
}