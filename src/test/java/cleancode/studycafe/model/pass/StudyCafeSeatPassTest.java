package cleancode.studycafe.model.pass;

import cleancode.studycafe.model.pass.locker.StudyCafeLockerPass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class StudyCafeSeatPassTest {

    @Test
    @DisplayName("StudyCafeSeatPass의 cannotUseLocker 메서드는 패스 타입이 잠금장치 유형이 아닐 때 true를 반환한다.")
    public void testCannotUseLocker_WhenNotLockerType() {
        // given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(
                StudyCafePassType.HOURLY, 1, 1000, 0.9
        );

        // when
        boolean cannotUseLocker = seatPass.cannotUseLocker();

        // then
        assertThat(cannotUseLocker).isTrue();
    }

    @Test
    @DisplayName("StudyCafeSeatPass의 cannotUseLocker 메서드는 패스 타입이 잠금장치 유형일 때 false를 반환한다.")
    public void testCannotUseLocker_WhenLockerType() {
        // given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(
                StudyCafePassType.FIXED, 1, 1000, 0.9
        );

        // when
        boolean cannotUseLocker = seatPass.cannotUseLocker();

        // then
        assertThat(cannotUseLocker).isFalse();
    }

    @Test
    @DisplayName("StudyCafeSeatPass의 isSameDurationType 메서드는 잠금장치 패스와 동일한 패스 타입 및 기간을 가질 때 true를 반환한다.")
    public void testIsSameDurationType_WhenSame() {
        // given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(
                StudyCafePassType.FIXED, 2, 2000, 0.85
        );
        StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(
                StudyCafePassType.FIXED, 2, 2000
        );

        // when
        boolean isSame = seatPass.isSameDurationType(lockerPass);

        // then
        assertThat(isSame).isTrue();
    }

    @Test
    @DisplayName("StudyCafeSeatPass의 isSameDurationType 메서드는 잠금장치 패스와 다른 패스 타입 또는 기간을 가질 때 false를 반환한다.")
    public void testIsSameDurationType_WhenDifferent() {
        // given
        StudyCafeSeatPass seatPassDifferentType = StudyCafeSeatPass.of(
                StudyCafePassType.HOURLY, 2, 2000, 0.85
        );
        StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(
                StudyCafePassType.FIXED, 2, 2000
        );

        StudyCafeSeatPass seatPassDifferentDuration = StudyCafeSeatPass.of(
                StudyCafePassType.FIXED, 3, 2000, 0.85
        );

        // when
        boolean isSameType = seatPassDifferentType.isSameDurationType(lockerPass);
        boolean isSameDuration = seatPassDifferentDuration.isSameDurationType(lockerPass);

        // then
        assertThat(isSameType).isFalse();
        assertThat(isSameDuration).isFalse();
    }

    @Test
    @DisplayName("StudyCafeSeatPass의 isSamePassType 메서드는 동일한 패스 타입일 때 true를 반환한다.")
    public void testIsSamePassType_WhenSame() {
        // given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(
                StudyCafePassType.WEEKLY, 7, 7000, 0.8
        );

        // when
        boolean isSame = seatPass.isSamePassType(StudyCafePassType.WEEKLY);

        // then
        assertThat(isSame).isTrue();
    }

    @Test
    @DisplayName("StudyCafeSeatPass의 isSamePassType 메서드는 다른 패스 타입일 때 false를 반환한다.")
    public void testIsSamePassType_WhenDifferent() {
        // given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(
                StudyCafePassType.WEEKLY, 7, 7000, 0.8
        );

        // when
        boolean isSame = seatPass.isSamePassType(StudyCafePassType.FIXED);

        // then
        assertThat(isSame).isFalse();
    }

    @Test
    @DisplayName("StudyCafeSeatPass의 getDiscountPrice 메서드는 올바른 할인 가격을 반환한다.")
    public void testGetDiscountPrice() {
        // given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(
                StudyCafePassType.HOURLY, 1, 1000, 0.9
        );

        // when
        int discountPrice = seatPass.getDiscountPrice();

        // then
        assertThat(discountPrice).isEqualTo(900);
    }

    @Test
    @DisplayName("StudyCafeSeatPass의 getter 메서드들은 올바른 값을 반환한다.")
    public void testGetters() {
        // given
        StudyCafePassType passType = StudyCafePassType.FIXED;
        int duration = 3;
        int price = 3000;
        double discountRate = 0.85;

        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(
                passType, duration, price, discountRate
        );

        // when & then
        assertThat(seatPass.getPassType()).isEqualTo(passType);
        assertThat(seatPass.getDuration()).isEqualTo(duration);
        assertThat(seatPass.getPrice()).isEqualTo(price);
        assertThat(seatPass.getDiscountPrice()).isEqualTo((int) (price * discountRate));
    }
}