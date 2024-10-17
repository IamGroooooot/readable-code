package cleancode.studycafe.io;

import cleancode.studycafe.model.pass.StudyCafePassType;
import cleancode.studycafe.model.pass.StudyCafeSeatPass;
import cleancode.studycafe.model.pass.locker.StudyCafeLockerPass;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("OutputHandler 클래스 테스트")
public class OutputHandlerTest {

    private final OutputHandler outputHandler = new OutputHandler();
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    public void setUp() {
        // 테스트 전 System.out을 ByteArrayOutputStream으로 교체
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void tearDown() {
        // 테스트 후 System.out을 원래대로 복원
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("showWelcomeMessage 메서드는 환영 메시지를 출력해야 한다.")
    public void testShowWelcomeMessage() {
        // given
        String expectedMessage = "*** 프리미엄 스터디카페 ***\n";

        // when
        outputHandler.showWelcomeMessage();

        // then
        assertThat(outContent.toString()).isEqualTo(expectedMessage);
    }

    @Test
    @DisplayName("showAnnouncement 메서드는 공지사항을 올바르게 출력해야 한다.")
    public void testShowAnnouncement() {
        // given
        String expectedMessage = "* 사물함은 고정석 선택 시 이용 가능합니다. (추가 결제)\n" +
                "* !오픈 이벤트! 2주권 이상 결제 시 10% 할인, 12주권 결제 시 15% 할인! (결제 시 적용)\n\n";

        // when
        outputHandler.showAnnouncement();

        // then
        assertThat(outContent.toString()).isEqualTo(expectedMessage);
    }

    @Test
    @DisplayName("askPassTypeSelection 메서드는 이용권 선택 메시지를 출력해야 한다.")
    public void testAskPassTypeSelection() {
        // given
        String expectedMessage = "사용하실 이용권을 선택해 주세요.\n" +
                "1. 시간 이용권(자유석) | 2. 주단위 이용권(자유석) | 3. 1인 고정석\n";

        // when
        outputHandler.askPassTypeSelection();

        // then
        assertThat(outContent.toString()).isEqualTo(expectedMessage);
    }

    @Test
    @DisplayName("showPassListForSelection 메서드는 이용권 목록을 올바르게 출력해야 한다.")
    public void testShowPassListForSelection() {
        // given
        StudyCafeSeatPass pass1 = StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 1, 1000, 0.9);
        StudyCafeSeatPass pass2 = StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 2, 2000, 0.85);
        List<StudyCafeSeatPass> passes = List.of(pass1, pass2);

        String expectedMessage = "\n" +
                "이용권 목록\n" +
                "1. 1시간권 - 1000원\n" +
                "2. 2주권 - 2000원\n";

        // when
        outputHandler.showPassListForSelection(passes);

        // then
        assertThat(outContent.toString()).isEqualTo(expectedMessage);
    }

    @Test
    @DisplayName("askLockerPass 메서드는 사물함 선택 메시지를 올바르게 출력해야 한다.")
    public void testAskLockerPass() {
        // given
        StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(StudyCafePassType.FIXED, 3, 3000);
        String expectedMessage = "\n" +
                "사물함을 이용하시겠습니까? (3주권 - 3000원)\n" +
                "1. 예 | 2. 아니오\n";

        // when
        outputHandler.askLockerPass(lockerPass);

        // then
        assertThat(outContent.toString()).isEqualTo(expectedMessage);
    }

    @Test
    @DisplayName("showSimpleMessage 메서드는 전달된 메시지를 올바르게 출력해야 한다.")
    public void testShowSimpleMessage() {
        // given
        String message = "테스트 메시지입니다.";

        // when
        outputHandler.showSimpleMessage(message);

        // then
        assertThat(outContent.toString()).isEqualTo(message);
    }
}