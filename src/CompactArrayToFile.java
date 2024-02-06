import java.io.FileOutputStream;
import java.io.IOException;

public class CompactArrayToFile {

    public static void main(String[] args) {
        // Пример массива состояний ячеек
        int[] gameBoard = {1, 0, 2, 2, 1, 3, 0, 3, 3};

        // Упаковываем значения в одно целое число
        int compactedValue = compactArray(gameBoard);

        // Записываем в файл
        try (FileOutputStream fos = new FileOutputStream("compact_array.bin")) {
            fos.write(compactIntToBytes(compactedValue));
            System.out.println("Данные успешно записаны в файл.");
        } catch (IOException e) {
            System.err.println("Ошибка при записи данных в файл.");
            e.printStackTrace();
        }
    }

    private static int compactArray(int[] array) {
        int result = 0;
        for (int i = 0; i < array.length; i++) {
            // Смещаем текущее значение на 2 бита влево (для упаковки)
            result |= (array[i] & 0b11) << (2 * i);
        }
        return result;
    }

    private static byte[] compactIntToBytes(int value) {
        // Записываем компактированное значение в 3 байта
        return new byte[]{(byte) (value & 0xFF), (byte) ((value >> 8) & 0xFF), (byte) ((value >> 16) & 0xFF)};
    }
}
