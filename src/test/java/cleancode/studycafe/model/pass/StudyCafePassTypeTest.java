package cleancode.studycafe.model.pass;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * StudyCafePassType 열거형에 대한 테스트 클래스
 */
public class StudyCafePassTypeTest {

    @Test
    @DisplayName("StudyCafePassType의 isLockerType 메서드는 FIXED 유형에서 true를 반환하고, 다른 유형에서 false를 반환한다.")
    public void testIsLockerType() {
        // given
        StudyCafePassType fixedType = StudyCafePassType.FIXED;
        StudyCafePassType hourlyType = StudyCafePassType.HOURLY;
        StudyCafePassType weeklyType = StudyCafePassType.WEEKLY;

        // when
        boolean isFixedLocker = fixedType.isLockerType();
        boolean isHourlyLocker = hourlyType.isLockerType();
        boolean isWeeklyLocker = weeklyType.isLockerType();

        // then
        assertThat(isFixedLocker).isTrue();
        assertThat(isHourlyLocker).isFalse();
        assertThat(isWeeklyLocker).isFalse();
    }

    @Test
    @DisplayName("StudyCafePassType의 isNotLockerType 메서드는 FIXED 유형에서 false를 반환하고, 다른 유형에서 true를 반환한다.")
    public void testIsNotLockerType() {
        // given
        StudyCafePassType fixedType = StudyCafePassType.FIXED;
        StudyCafePassType hourlyType = StudyCafePassType.HOURLY;
        StudyCafePassType weeklyType = StudyCafePassType.WEEKLY;

        // when
        boolean isFixedNotLocker = fixedType.isNotLockerType();
        boolean isHourlyNotLocker = hourlyType.isNotLockerType();
        boolean isWeeklyNotLocker = weeklyType.isNotLockerType();

        // then
        assertThat(isFixedNotLocker).isFalse();
        assertThat(isHourlyNotLocker).isTrue();
        assertThat(isWeeklyNotLocker).isTrue();
    }
}