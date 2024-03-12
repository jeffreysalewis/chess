package servicesTests;

import org.junit.jupiter.api.*;
import service.*;

public class ClearTest {
    @Test
    @DisplayName("clear() test >:D")
    public void clearTest() {
        try {
            var claro = new Clear();
            Assertions.assertEquals(0, claro.clear());
        } catch(Exception e) {
            Assertions.fail();
        }
    }

    @Test
    @DisplayName("bad clear() test >:D")
    public void badclearTest() {
        try{
            var claro = new Clear();
            Assertions.assertNotEquals(-1, claro.clear());
        } catch(Exception e) {
            Assertions.fail();
        }
    }
}
