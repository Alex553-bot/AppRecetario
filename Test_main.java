import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.*;

public class Test_main {
    public Test_main() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }
    
    @Test
    public void testNombreNulo() {
        try {
            Receta nombre = new Receta("");
            fail("error, objeto creado");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "nombre invalido");
        }
    }
}