import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BaseConverterTest {
    @Test
    public void testConvertToKelvin() {
        BaseConverter converter = new BaseConverter(25);
        double kelvin = converter.convertToKelvin();
        assertEquals(298.15, kelvin, 0.01);
    }

    @Test
    public void testConvertToFahrenheit() {
        BaseConverter converter = new BaseConverter(25);
        double fahrenheit = converter.convertToFahrenheit();
        assertEquals(77, fahrenheit, 0.01);
    }

    @Test
    public void testCreateWithLocale_Celsius() {
        BaseConverter converter = BaseConverter.createWithLocale(25);
        assertEquals(BaseConverter.class, converter.getClass());
    }

    @Test
    public void testCreateWithLocale_Fahrenheit() {
        BaseConverter converter = BaseConverter.createWithLocale(25);
        converter.setCelsius(77);
        assertEquals(BaseConverter.class, converter.getClass());
    }
}
