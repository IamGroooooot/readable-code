package cleancode.studycafe.model.order;

import cleancode.studycafe.model.pass.StudyCafePassType;
import cleancode.studycafe.model.pass.StudyCafeSeatPass;
import cleancode.studycafe.model.pass.locker.StudyCafeLockerPass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DisplayName("StudyCafePassOrder 클래스 테스트")
public class StudyCafePassOrderTest {

    @Test
    @DisplayName("StudyCafePassOrder의 getDiscountPrice 메서드는 seatPass의 할인 가격을 반환해야 한다.")
    public void testGetDiscountPrice() {
        // given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(
                StudyCafePassType.HOURLY, 1, 1000, 0.9
        );
        StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(
                StudyCafePassType.FIXED, 3, 3000
        );
        StudyCafePassOrder passOrder = StudyCafePassOrder.of(seatPass, lockerPass);

        // when
        int discountPrice = passOrder.getDiscountPrice();

        // then
        assertThat(discountPrice).isEqualTo(900); // 1000 * 0.9 = 900
    }

    @Test
    @DisplayName("StudyCafePassOrder의 getTotalPrice 메서드는 사물함이 있을 때 올바른 총 결제 금액을 반환해야 한다.")
    public void testGetTotalPrice_WithLockerPass() {
        // given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(
                StudyCafePassType.WEEKLY, 2, 2000, 0.85
        );
        StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(
                StudyCafePassType.FIXED, 2, 500
        );
        StudyCafePassOrder passOrder = StudyCafePassOrder.of(seatPass, lockerPass);

        // when
        int totalPrice = passOrder.getTotalPrice();

        // then
        // 총 결제 금액 = seatPass 가격 + lockerPass 가격 - 할인 금액
        // = 2000 + 500 - (2000 * 0.85) = 2500 - 1700 = 800
        assertThat(totalPrice).isEqualTo(800);
    }

    @Test
    @DisplayName("StudyCafePassOrder의 getTotalPrice 메서드는 사물함이 없을 때 올바른 총 결제 금액을 반환해야 한다.")
    public void testGetTotalPrice_WithoutLockerPass() {
        // given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(
                StudyCafePassType.FIXED, 3, 3000, 0.8
        );
        StudyCafePassOrder passOrder = StudyCafePassOrder.of(seatPass, null);

        // when
        int totalPrice = passOrder.getTotalPrice();

        // then
        // 총 결제 금액 = seatPass 가격 + 0 - 할인 금액
        // = 3000 + 0 - (3000 * 0.8) = 3000 - 2400 = 600
        assertThat(totalPrice).isEqualTo(600);
    }

    @Test
    @DisplayName("StudyCafePassOrder의 getSeatPass 메서드는 올바른 StudyCafeSeatPass를 반환해야 한다.")
    public void testGetSeatPass() {
        // given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(
                StudyCafePassType.HOURLY, 1, 1000, 0.95
        );
        StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(
                StudyCafePassType.FIXED, 1, 500
        );
        StudyCafePassOrder passOrder = StudyCafePassOrder.of(seatPass, lockerPass);

        // when
        StudyCafeSeatPass retrievedSeatPass = passOrder.getSeatPass();

        // then
        assertThat(retrievedSeatPass).isEqualTo(seatPass);
    }

    @Test
    @DisplayName("StudyCafePassOrder의 getLockerPass 메서드는 사물함 패스가 있을 때 Optional에 패스를 포함하여 반환해야 한다.")
    public void testGetLockerPass_WithLockerPass() {
        // given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(
                StudyCafePassType.WEEKLY, 2, 2000, 0.85
        );
        StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(
                StudyCafePassType.FIXED, 2, 500
        );
        StudyCafePassOrder passOrder = StudyCafePassOrder.of(seatPass, lockerPass);

        // when
        Optional<StudyCafeLockerPass> optionalLockerPass = passOrder.getLockerPass();

        // then
        assertThat(optionalLockerPass).isPresent();
        assertThat(optionalLockerPass.get()).isEqualTo(lockerPass);
    }

    @Test
    @DisplayName("StudyCafePassOrder의 getLockerPass 메서드는 사물함 패스가 없을 때 Optional.empty를 반환해야 한다.")
    public void testGetLockerPass_WithoutLockerPass() {
        // given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(
                StudyCafePassType.FIXED, 3, 3000, 0.8
        );
        StudyCafePassOrder passOrder = StudyCafePassOrder.of(seatPass, null);

        // when
        Optional<StudyCafeLockerPass> optionalLockerPass = passOrder.getLockerPass();

        // then
        assertThat(optionalLockerPass).isEmpty();
    }
}