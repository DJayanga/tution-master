package common;

import javafx.geometry.Pos;
import org.controlsfx.control.Notifications;

public class NotifyController {

    public static void displayNotification(String type, String message) {
        Notifications notify = Notifications.create()
                .title(type)
                .text(message)
                .position(Pos.BOTTOM_RIGHT);
        notify.show();
    }
}
