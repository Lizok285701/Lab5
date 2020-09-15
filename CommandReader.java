import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Класс, отвечающий за ввод команд пользователем и диалог м/д программой и пользователем
 */

public class CommandReader {

     TreeSet<Organization> collection;
     LocalDateTime creation_date;
     File file;

    /**
     * Конструктор CommandReader
     */

    CommandReader(TreeSet<Organization> collection, LocalDateTime creation_date, File file){

        this.collection = collection;
        this.creation_date = creation_date;
        this.file = file;
    }

    /**
     * Метод, взаимодействующий с консолью
     */

    public void start_reading(HashSet<String> is_console, String Path) throws InterruptedException {
        Boolean is_ok = true;

        CommandHub hub = new CommandHub();
        Scanner sc = new Scanner(System.in);

        if (is_console.contains(Path)) {
            is_ok = false;
            System.out.println("Вы попытались вызвать из скрипта тот же самый скрипт");
        }

        is_console.add(Path);

        if (is_console.size()==1 && is_ok) sc = new Scanner(System.in);
        else
            try {
                sc = new Scanner(new File(Path));

            } catch (Exception e) {
                System.out.println("Проблема с файлом");
                is_ok = false;
            }

        if (is_ok) {
            String command = sc.nextLine().toLowerCase().trim();
            String[] commandParts;
            while (!command.equals("exit")) {
                commandParts = command.split(" ", 3);

                switch (commandParts[0]) {
                    case "":
                        break;
                    case "help":
                        hub.help(commandParts);
                        break;
                    case "info":
                        hub.info(collection, creation_date);
                        break;
                    case "show":
                        hub.show(collection);
                        break;
                    case "add":
                        hub.add(collection);
                        break;
                    case "update_by_id":
                        try{
                            hub.update_by_id(Long.parseLong(commandParts[1]), collection);
                        } catch (Exception e) {
                            System.out.println("Неверный формат ввода. Для корректной работы команды update_by_id необходимо ввести id элемента, который хочется обновить");
                        }
                        break;
                    case "remove_by_id":
                        try {
                            hub.remove_by_id(Long.parseLong(commandParts[1]), collection);
                        } catch (NumberFormatException e) {
                            System.out.println("Неверный формат ввода id. Попробуйте снова");
                        }
                        break;
                    case "clear":
                        hub.clear(collection);
                        break;
                    case "save":
                        try {
                            hub.save(collection, file);
                        } catch (FileNotFoundException e) {
                            System.out.println("Файл отсутствует или к нему нет доступа");
                        }
                        break;
                    case "execute_script":
                        try {
                            this.start_reading(is_console, commandParts[1]);
                        }
                        catch (Exception e)
                        {
                            System.out.println("Не удалось выполнить скрипт");
                        }
                        finally {
                            try {
                                is_console.remove(commandParts[1]);
                            }catch (Exception e){
                                System.out.println("Возможно скрипт не указан");
                            }}
                        break;
                    case "add_if_min":
                        try{
                            hub.add_if_min(collection);
                        } catch (Exception e){
                            System.out.println("Попробуйте еще раз");
                        }
                        break;
                    case "remove_greater":
                        try {
                            hub.remove_greater(commandParts[1], collection);}
                        catch (Exception e)
                        {
                            System.out.println("Попробуйте еще раз"); }
                        break;
                    case "remove_lower":
                        try {
                        hub.remove_lower(commandParts[1], collection);}
                        catch (Exception e)
                            {
                                System.out.println("Попробуйте еще раз"); }
                        break;
                    case "count_greater_than_type":
                        try {
                        hub.count_greater_than_type(commandParts[1], collection);}
                        catch (Exception e)
                        {
                            System.out.println("Попробуйте еще раз"); }
                        break;
                    case "print_descending":
                        hub.print_descending(collection);
                        break;
                    case "print_field_ascending_official_address":
                        hub.print_field_ascending_official_address(collection);
                        break;
                    default:
                        System.out.println('"' + command + "\" не является командой. Используйте help, чтобы узнать список доступных команд.");
                        break;
                }
                System.out.print('>');
                command = sc.nextLine().toLowerCase().trim();
            }
        }
    }
}
