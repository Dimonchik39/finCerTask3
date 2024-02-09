package Task3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;
import java.time.Month;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookingServiceTest {
    private static final Logger logger
            = LoggerFactory.getLogger(BookingServiceTest.class);

    @Mock
    private BookingService bookingService;

    @Test
    void bookValidTimeTrue() throws CantBookException {

        LocalDateTime from = LocalDateTime.of(2019, Month.MAY, 01, 17, 30, 0);
        LocalDateTime to = LocalDateTime.of(2019, Month.MAY, 02, 17, 30, 0);
        logger.info("Тест проверки валидного времени запущен {} {} для записи", from, to);
        logger.debug("Мокирование для метода book()");
        when(bookingService.book("001", from, to)).thenReturn(true);
        boolean result = bookingService.book("001", from, to);
        logger.info("Тест успешно пройден");
        assertTrue(result);
        logger.info("Вызов метода с параметрами");
        verify(bookingService).book("001", from, to);
    }
    @Test
    void bookInvalidTimeCantBookExceptionThrown() throws CantBookException {
        LocalDateTime from = LocalDateTime.of(2019, Month.MAY, 01, 17, 30, 0);
        LocalDateTime to = LocalDateTime.of(2019, Month.MAY, 01, 19, 00, 0);
        logger.info("Запущен тест проверки невалидного времени {} {} для записи", from, to);
        logger.debug("Мокирование поведения для метода book()");
        Mockito.doThrow(CantBookException.class).when(bookingService).book("001", from, to);
        logger.info("Тест успешно пройден");
        assertThrows(CantBookException.class, () -> {
            bookingService.book("001", from, to);
        }, "Время занято");
    }
}
