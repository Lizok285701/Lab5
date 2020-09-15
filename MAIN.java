import java.io.File;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeSet;

/**
 *Main class
 */
public class MAIN {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("Добро пожаловать!");
        System.out.println("\nДля ознакомления с доступными командами введите help");
        String env_name = "LAB_PROG_ENV_NAME";
        if (System.getenv(env_name)==null)
            System.out.println("Переменная окружения не задана. Для считывания файла добавьте переменную среды "+ env_name);
        else{

        String file_name = System.getenv(env_name);

        System.out.println("\nСчитано: " + env_name + "   " + file_name);

        File file = new File (file_name);

        TreeSet<Organization> organizations = CommandHub.readFromFile(file);

        LocalDateTime collection_creation_date = LocalDateTime.now();


        CommandReader dialog = new CommandReader(organizations, collection_creation_date, file);
        dialog.start_reading(new HashSet<String>(), null);
        }
    }
}




