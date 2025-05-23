package defaultmethod.ex2;

import java.time.LocalDateTime;

public class SMSNotifier implements Notifier {

    @Override
    public void notify(String message) {
        System.out.println("[SMS] " + message);
    }
}
