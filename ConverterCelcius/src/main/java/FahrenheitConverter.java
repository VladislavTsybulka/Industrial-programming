public class FahrenheitConverter extends BaseConverter {
    public FahrenheitConverter(double celsius) {
        super(celsius);
    }

    @Override
    public double convertToFahrenheit() {
        return getCelsius();
    }

    @Override
    public double convertToCustomUnit(double conversionFactor) {
        return getCelsius() * conversionFactor;
    }
}
