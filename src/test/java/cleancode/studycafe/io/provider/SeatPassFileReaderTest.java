package cleancode.studycafe.io.provider;

import cleancode.studycafe.model.pass.StudyCafePassType;
import cleancode.studycafe.model.pass.StudyCafeSeatPass;
import cleancode.studycafe.model.pass.StudyCafeSeatPasses;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("SeatPassFileReader 클래스 테스트")
public class SeatPassFileReaderTest {

    private static final String PASS_LIST_CSV_PATH = "src/main/resources/cleancode/studycafe/pass-list.csv";
    private SeatPassFileReader seatPassFileReader;

    @BeforeEach
    public void setUp() {
        seatPassFileReader = new SeatPassFileReader();
    }

    @AfterEach
    public void tearDown() throws IOException {
        // 테스트 후 pass-list.csv 파일을 삭제하여 원상 복구
        Files.deleteIfExists(Paths.get(PASS_LIST_CSV_PATH));
    }

    @Test
    @DisplayName("SeatPassFileReader는 올바른 pass-list.csv 파일을 읽어 StudyCafeSeatPasses를 반환해야 한다.")
    public void testGetSeatPasses_Success() throws IOException {
        // given
        // 테스트용 pass-list.csv 파일 생성
        new File("src/main/resources/cleancode/studycafe").mkdirs();
        try (FileWriter writer = new FileWriter(PASS_LIST_CSV_PATH)) {
            writer.write("HOURLY,1,1000,0.9\n");
            writer.write("WEEKLY,2,2000,0.85\n");
            writer.write("FIXED,3,3000,0.8\n");
        }

        // when
        StudyCafeSeatPasses seatPasses = seatPassFileReader.getSeatPasses();

        // then
        assertThat(seatPasses).isNotNull();
        List<StudyCafeSeatPass> passList = seatPasses.findPassBy(StudyCafePassType.HOURLY)
                .stream().toList();
        assertThat(passList).hasSize(1);
        StudyCafeSeatPass pass1 = passList.get(0);
        assertThat(pass1.getPassType()).isEqualTo(StudyCafePassType.HOURLY);
        assertThat(pass1.getDuration()).isEqualTo(1);
        assertThat(pass1.getPrice()).isEqualTo(1000);
        assertThat(pass1.getDiscountPrice()).isEqualTo(900);

        // 추가적인 패스 검증
        passList = seatPasses.findPassBy(StudyCafePassType.WEEKLY)
                .stream().toList();
        assertThat(passList).hasSize(1);
        StudyCafeSeatPass pass2 = passList.get(0);
        assertThat(pass2.getPassType()).isEqualTo(StudyCafePassType.WEEKLY);
        assertThat(pass2.getDuration()).isEqualTo(2);
        assertThat(pass2.getPrice()).isEqualTo(2000);
        assertThat(pass2.getDiscountPrice()).isEqualTo(1700);

        passList = seatPasses.findPassBy(StudyCafePassType.FIXED)
                .stream().toList();
        assertThat(passList).hasSize(1);
        StudyCafeSeatPass pass3 = passList.get(0);
        assertThat(pass3.getPassType()).isEqualTo(StudyCafePassType.FIXED);
        assertThat(pass3.getDuration()).isEqualTo(3);
        assertThat(pass3.getPrice()).isEqualTo(3000);
        assertThat(pass3.getDiscountPrice()).isEqualTo(2400);
    }

    @Test
    @DisplayName("SeatPassFileReader는 pass-list.csv 파일이 존재하지 않을 때 RuntimeException을 던져야 한다.")
    public void testGetSeatPasses_FileNotFound() {
        // given
        // pass-list.csv 파일이 존재하지 않음

        // when & then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            seatPassFileReader.getSeatPasses();
        });
        assertThat(exception.getMessage()).isEqualTo("파일을 읽는데 실패했습니다.");
    }
}