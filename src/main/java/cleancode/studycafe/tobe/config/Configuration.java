package cleancode.studycafe.tobe.config;

public class Configuration {

    private static final String STUDY_CAFE_PASS_LIST_PATH = "src/main/resources/cleancode/studycafe/pass-list.csv";
    private static final String STUDY_CAFE_LOCKER_PATH = "src/main/resources/cleancode/studycafe/locker.csv";

    public String getStudyCafePassListPath() {
        return STUDY_CAFE_PASS_LIST_PATH;
    }

    public String getStudyCafeLockerPath() {
        return STUDY_CAFE_LOCKER_PATH;
    }
}
