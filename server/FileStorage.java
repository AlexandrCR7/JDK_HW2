package Messenger.server;

import java.io.FileReader;
import java.io.FileWriter;

public class FileStorage implements Repository{

    public static final String LOG_PATH = "C:\\'Коммерческий Директор это я'\\'Рабочая тетрадь'\\java\\JDK_Sem2_HW2_V1\\src\\main\\java\\Log.txt";
    @Override
    public void saveText(String text) {
        try (FileWriter writer = new FileWriter(LOG_PATH, true)){
            writer.write(text);
            writer.write("\n");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public String readText() {
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader reader = new FileReader(LOG_PATH)){
            int c;
            while ((c = reader.read()) != -1){
                stringBuilder.append((char) c);
            }
            stringBuilder.delete(stringBuilder.length()-1, stringBuilder.length());
            return stringBuilder.toString();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
