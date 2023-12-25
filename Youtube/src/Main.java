import java.io.*;
import java.util.HashSet;
import java.util.regex.*;

public class Main {
    public static void main(String[] args) {
        // Читаю ссылки из файлов data1 и data2.
        HashSet<String> data1Links = readLinksFromFile("data1.txt");
        HashSet<String> data2Links = readLinksFromFile("data2.txt");

        // Проверяю правильность написания ссылок из data1.
        HashSet<String> correctLinks = new HashSet<>();
        HashSet<String> incorrectLinks = new HashSet<>();
        for (String link : data1Links) {
            if (isValidLink(link)) {
                correctLinks.add(link);
            } else {
                incorrectLinks.add(link);
            }
        }

        // Ищу ссылки из data2 в data1.
        HashSet<String> intersection = new HashSet<>(data1Links);
        intersection.retainAll(data2Links);

        // Проверка ссылок из data2.txt в data1.txt
        HashSet<String> data2InData1 = new HashSet<>(data2Links);
        data2InData1.retainAll(data1Links);

        // Запись результатов в файл Output.txt
        writeResultsToFile("Output.txt", correctLinks, incorrectLinks, intersection, data2InData1);
    }

    // Метод для чтения ссылок из файла
    private static HashSet<String> readLinksFromFile(String fileName) {
        HashSet<String> links = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                links.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return links;
    }

    // Метод для проверки правильности ссылки с использованием регулярного выражения
    private static boolean isValidLink(String link) {
        String regex = "^https?://(www\\.)?youtube\\.com/watch\\?v=[\\w-]{11}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(link);
        return matcher.matches();
    }

    // Метод для записи результатов в файл
    private static void writeResultsToFile(String fileName, HashSet<String> correctLinks, HashSet<String> incorrectLinks,
                                           HashSet<String> intersection, HashSet<String> data2InData1) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println("1. Correct links:");
            for (String link : correctLinks) {
                writer.println(link);
            }
            writer.println();

            writer.println("2. Incorrect links:");
            for (String link : incorrectLinks) {
                writer.println(link);
            }
            writer.println();

            writer.println("3. Common links for data1.txt and data2.txt:");
            for (String link : intersection) {
                writer.println(link);
            }
            writer.println();

            writer.println("4. Links from data2.txt in data1.txt:");
            for (String link : data2InData1) {
                writer.println(link);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}