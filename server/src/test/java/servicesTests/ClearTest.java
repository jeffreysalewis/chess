package servicesTests;

import org.junit.jupiter.api.*;
import service.*;

public class ClearTest {
    @Test
    @DisplayName("clear() test >:D")
    public void clearTest() {
        var claro = new Clear();
        Assertions.assertEquals(0, claro.clear());
    }

    @Test
    @DisplayName("bad clear() test >:D")
    public void badclearTest() {
        var claro = new Clear();
        Assertions.assertNotEquals(-1, claro.clear());
    }
}
