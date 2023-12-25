import java.util.Locale;

public class BaseConverter {
    private double celsius;

    public BaseConverter(double celsius) {
        this.celsius = celsius;
    }

    public double getCelsius() {
        return celsius;
    }

    public void setCelsius(double celsius) {
        this.celsius = celsius;
    }

    public double convertToKelvin() {
        return celsius + 273.15;
    }

    public double convertToFahrenheit() {
        return celsius * 9 / 5 + 32;
    }

    public double convertToCustomUnit(double conversionFactor) {
        return celsius * conversionFactor;
    }

    public static BaseConverter createWithLocale(double celsius) {
        Locale locale = Locale.getDefault();
        String countryCode = locale.getCountry();

        String[] fahrenheitCountries = {"BS", "US", "BZ", "KY", "PW"};

        if (isCountryInArray(countryCode, fahrenheitCountries)) {
            return new FahrenheitConverter(celsius);
        } else {
            return new BaseConverter(celsius);
        }
    }

    private static boolean isCountryInArray(String countryCode, String[] countries) {
        for (String country : countries) {
            if (countryCode.equals(country)) {
                return true;
            }
        }
        return false;
    }
}
