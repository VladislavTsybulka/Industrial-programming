import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FahrenheitConverterTest {
    @Test
    public void testConvertToKelvin() {
        FahrenheitConverter converter = new FahrenheitConverter(77);
        double kelvin = converter.convertToKelvin();
        assertEquals(350.15, kelvin, 0.01);
    }

    @Test
    public void testConvertToFahrenheit() {
        FahrenheitConverter converter = new FahrenheitConverter(77);
        double fahrenheit = converter.convertToFahrenheit();
        assertEquals(77, fahrenheit, 0.01);
    }
}