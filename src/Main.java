import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            // Получаем ссылку на картинку от пользователя
            System.out.println("Введите ссылку на картинку: ");
            String url = scanner.nextLine();

            // Получаем название файла от пользователя
            System.out.println("Введите название для файла: ");
            String name = scanner.nextLine();

            // Вызываем метод для загрузки изображения
            try {
                downloadImage(url, name);
                System.out.println("Файл успешно загружен: " + name + ".jpg");
            } catch (IOException e) {
                System.err.println("Ошибка при загрузке изображения: " + e.getMessage());
            }
        }
    }

    private static void downloadImage(String url, String name) throws IOException {
        // Создаем URL объект для передачи ввода пользователя
        URL website = new URL(url);

        // Создаем канал для чтения байтов из URL
        try (ReadableByteChannel rbc = Channels.newChannel(website.openStream());
             // Создаем FileOutputStream для записи байтов в файл
             FileOutputStream fos = new FileOutputStream(name + ".jpg")) {

            // Переносим байты из канала в файл
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        }
    }
}
