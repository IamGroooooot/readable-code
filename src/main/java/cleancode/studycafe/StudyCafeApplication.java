package cleancode.studycafe;

import cleancode.studycafe.io.provider.LockerPassFileReader;
import cleancode.studycafe.io.provider.SeatPassFileReader;
import cleancode.studycafe.provider.LockerPassProvider;
import cleancode.studycafe.provider.SeatPassProvider;

public class StudyCafeApplication {

    public static void main(String[] args) {
        SeatPassProvider seatPassProvider = new SeatPassFileReader();
        LockerPassProvider lockerPassProvider = new LockerPassFileReader();

        StudyCafePassMachine studyCafePassMachine = new StudyCafePassMachine(
            seatPassProvider, lockerPassProvider
        );
        studyCafePassMachine.run();
    }

}
