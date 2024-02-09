package Task3;

import java.time.LocalDateTime;
import java.time.Month;

public class Main {
    public static void main(String[] args) throws CantBookException {
        LocalDateTime from = LocalDateTime.of(1986, Month.MAY, 01, 17, 30, 0);
        LocalDateTime to = LocalDateTime.of(1986, Month.MAY, 02, 17, 30, 0);
        BookingService bookingService = new BookingService();
        bookingService.checkTimeInBD(from,to);
        bookingService.book("001",from,to);
    }
}
