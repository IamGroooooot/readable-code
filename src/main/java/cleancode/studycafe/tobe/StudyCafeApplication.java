package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.config.Configuration;

public class StudyCafeApplication {

    public static void main(String[] args) {
        final Configuration configuration = new Configuration();
        final StudyCafeFactory factory = new StudyCafeFactory();

        StudyCafePassMachine studyCafePassMachine = new StudyCafePassMachine(factory, configuration);
        studyCafePassMachine.run();
    }

}
