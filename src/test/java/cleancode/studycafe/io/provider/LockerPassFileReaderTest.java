package cleancode.studycafe.io.provider;

import cleancode.studycafe.model.pass.StudyCafePassType;
import cleancode.studycafe.model.pass.StudyCafeSeatPass;
import cleancode.studycafe.model.pass.locker.StudyCafeLockerPass;
import cleancode.studycafe.model.pass.locker.StudyCafeLockerPasses;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("LockerPassFileReader 클래스 테스트")
public class LockerPassFileReaderTest {

    private static final String LOCKER_LIST_CSV_PATH = "src/main/resources/cleancode/studycafe/locker.csv";
    private LockerPassFileReader lockerPassFileReader;

    @BeforeEach
    public void setUp() {
        lockerPassFileReader = new LockerPassFileReader();
    }

    @AfterEach
    public void tearDown() throws IOException {
        // 테스트 후 locker.csv 파일을 삭제하여 원상 복구
        Files.deleteIfExists(Paths.get(LOCKER_LIST_CSV_PATH));
    }

    @Test
    @DisplayName("LockerPassFileReader는 올바른 locker.csv 파일을 읽어 StudyCafeLockerPasses를 반환해야 한다.")
    public void testGetLockerPasses_Success() throws IOException {
        // given
        // 테스트용 locker.csv 파일 생성
        new File("src/main/resources/cleancode/studycafe").mkdirs();
        try (FileWriter writer = new FileWriter(LOCKER_LIST_CSV_PATH)) {
            writer.write("FIXED,3,3000\n");
            writer.write("FIXED,2,2000\n");
            writer.write("FIXED,1,1000\n");
        }

        // when
        StudyCafeLockerPasses lockerPasses = lockerPassFileReader.getLockerPasses();

        // then
        assertThat(lockerPasses).isNotNull();
        List<StudyCafeLockerPass> allLockerPasses = lockerPasses.findLockerPassBy(
                StudyCafeSeatPass.of(StudyCafePassType.FIXED, 3, 3000, 1.0)
        ).stream().toList();
        assertThat(allLockerPasses).hasSize(1);
        StudyCafeLockerPass pass1 = allLockerPasses.get(0);
        assertThat(pass1.getPassType()).isEqualTo(StudyCafePassType.FIXED);
        assertThat(pass1.getDuration()).isEqualTo(3);
        assertThat(pass1.getPrice()).isEqualTo(3000);
    }

    @Test
    @DisplayName("LockerPassFileReader는 locker.csv 파일이 존재하지 않을 때 RuntimeException을 던져야 한다.")
    public void testGetLockerPasses_FileNotFound() {
        // given
        // locker.csv 파일이 존재하지 않음

        // when & then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            lockerPassFileReader.getLockerPasses();
        });
        assertThat(exception.getMessage()).isEqualTo("파일을 읽는데 실패했습니다.");
    }
}