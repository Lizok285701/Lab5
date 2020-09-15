import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import org.json.JSONObject;
import org.json.JSONArray;


/**
 * Класс, содержащий методы, которые реализуют команды пользователя
 */
public class CommandHub {

    private HashMap<String, String> CommandHelpList = new HashMap<String, String>();

    /**
     * Construction method to
     */
    CommandHub() {

        CommandHelpList.put("help", "Команда help выведет справку по доступным командам.");
        CommandHelpList.put("info", "Команда info выведет информацию о коллекции.");
        CommandHelpList.put("show", "Команда show выведет все элементы коллекции.");
        CommandHelpList.put("add", "Команда add добавит новый элемент, созданный по указанным параметрам, в коллекцию.");
        CommandHelpList.put("update_by_id", "Команда update id обновит значение элемента коллекции, id которого равен заданному.");
        CommandHelpList.put("remove_by_id", "Команда remove_by_id удалит из коллекции элемент с указанным id.");
        CommandHelpList.put("clear", "Команда clear очистит коллекцию.");
        CommandHelpList.put("save", "Команда save сохранит коллекцию в файл.");
        CommandHelpList.put("execute_script", "Команда execute_script cчитает и исполнит скрипт из указанного файла.");
        CommandHelpList.put("exit","Команда exit завершит работу программы без сохранения файла.");
        CommandHelpList.put("add_if_min", "Команда add_if_min добавит новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента коллекции.");
        CommandHelpList.put("remove_greater", "Команда remove_greater удалит из коллекции все элементы, превышающие заданный.");
        CommandHelpList.put("remove_lower","Команда remove_lower удалит из коллекции все элементы, превышающие заданный.");
        CommandHelpList.put("count_greater_than_type", "Команда count_greater_than_type выведет количество элементов, значение поля type которых больше заданного.");
        CommandHelpList.put("print_descending", "Команда print_descending выведет элементы коллекции в порядке убывания.");
        CommandHelpList.put("print_field_ascending_official_address", "Команда print_field_ascending_official_address выведет значения поля officialAddress в порядке возрастания.");

    }
    /**
     * Метод, предназначенный для вывода справки по доступным командам
     */

    void help(String [] commandParts) {
        if (commandParts.length==1)
        {
            System.out.println("Список доступных команд:\n"  + CommandHelpList.keySet());

        }
        else
        {
                if (CommandHelpList.containsKey(commandParts[1])) {
                    System.out.println(CommandHelpList.get(commandParts[1]));
                }
              else
                    System.out.println("Такая команда не найдена");

        }
    }

    /**
     * Метод, предназначенный для вывода информации о коллекции
     */

    void info(TreeSet<Organization> organizations, LocalDateTime creation_date) {
        System.out.println("Тип коллекции: TreeSet");
        System.out.println("Дата создания: " + creation_date);
        System.out.println("Количество элементов: " + organizations.size());

    }

    /**
     * Метод, предназначенный для вывода всех элементов коллекции
     */

    void show(TreeSet<Organization> collection) {

        Iterator<Organization> itr = collection.iterator();
        while (itr.hasNext()){
            Organization temp = itr.next();


            System.out.println("Название: "+temp.getName()+"\n"+
                    "Id: " + temp.getId()+"\n"+
                    "Координаты: " + temp.getCoordinates().toString()+"\n"+
                    "Дата создания: " + temp.getCreationDate().toString()+"\n"+
                    "Годовой оборот: " + temp.getAnnualTurnover() +"\n"+
                    "Количество сотрудников: " + temp.getEmployeesCount()+"\n"+
                    "Тип: " + temp.getType()+"\n"+
                    "Официальный адрес: " + temp.getOfficialAddress()+'\n'+
                    "\n"+"____________________________________"+"\n");

        }
    }

    /**
     * Метод, предназначенный для создания и добавления в коллекцию нового элемента
     */

    void add(TreeSet<Organization> collection) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите название организации: ");
        String name = in.nextLine().trim();
        while (true)
        {
            System.out.println(name);
            if (!name.isEmpty()) break;
            System.out.println("Имя не может быть пустой строкой. Введите имя ещё раз");
            name = in.nextLine().trim();
        }


        System.out.println("Введите координаты организации в целых числах через пробел без иных символов \n" +
                "*************************************************** \n" +
                "* x лежит в промежутке [-903, 9000000000000000000]*\n" +
                "* y лежит в промежутке [-2147483647, 551]         * \n" +
                "***************************************************");
        long x;
        int y;
        String [] coords;
        String coordinates;
        while (true)
        {
            try {
                coordinates = in.nextLine().trim();
                coords = coordinates.split(" ", 2);
                x = Long.parseLong(coords[0].trim());
                y = Integer.parseInt(coords[1].trim());
                if (x<=-904 || y>551 || x>9.01E18 || y <-2147483647) x = 1/0;
                break;
            }
            catch (Exception e)
            {
                System.out.println("Что-то пошло не так. Возможно, ваши числа слишком большие или маленькие");
            }
        }


        Coordinates newcoords = new Coordinates(x, y);


        LocalDateTime date = LocalDateTime.now();


        System.out.println("Введите годовой оборот (положительное число, меньшее 3.3E38): ");
        float annualTurnover;
        while (true)
        {
            try{
                annualTurnover = Float.parseFloat(in.nextLine().trim());
                if (annualTurnover<=0 || annualTurnover>= 3.301E38) annualTurnover = 1/0;
                break;
            }
            catch (Exception e)
            {
                System.out.println("Вероятно, вы ошиблись с числом");
            }
        }


        System.out.println("Введите количество сотрудников (целое положительное число, меньше 2147483647): ");
        Integer employeesCount;

        while (true)
        {
            try{
                employeesCount = Integer.parseInt(in.nextLine().trim());
                if (employeesCount<=0 || employeesCount >= 2147483647) employeesCount=1/0;
                break;
            }
            catch (Exception e){
                System.out.println("Что-то не так. Вводите число согласно указаниям");
            }
        }

        System.out.println("Выберите тип организации среди предложенных(не обязательно): \n•COMMERCIAL\n" +
                "•PUBLIC\n" +
                "•TRUST ");


        OrganizationType type = OrganizationType.COMMERCIAL;
        while (true)
        {
            try{
                String buffer_string = in.nextLine().toUpperCase().trim();
                if (buffer_string.isEmpty()) break;
                type = OrganizationType.valueOf(buffer_string);

                break;
            }
            catch (Exception e){
                System.out.println("Нет такого типа. Попробуйте снова");
            }
        }
        System.out.println("Введите адрес организации (поле можно оставить пустым): ");

        Address officialAddress = null;
        while(true) {

            try {
                String zipCode = in.nextLine().trim();
                if (zipCode.length() > 15) break;
                officialAddress = new Address(zipCode);
                break;
            } catch (Exception e) {
                System.out.println("Длина адреса не может быть больше 15. Повторите ввод");
            }
        }
        Organization org = new Organization(name, newcoords, date, annualTurnover, employeesCount, type, officialAddress);
        collection.add(org);
        System.out.println("Oрганизация "+ org.getName() + " успешно создана");
    }

    /**
     * Метод, предназначенный для обновления значения элемента коллекции, id которого равен заданному
     */

    int update_by_id(long id, TreeSet<Organization> collection) {
        Iterator<Organization> itr = collection.iterator();
        while (itr.hasNext()){
            Organization t = itr.next();
            if(t.getId() == id){

                TreeSet<Organization> temp = new TreeSet<Organization>();
                add(temp);
                t.replace(temp.first().getName(), temp.first().getCoordinates(), temp.first().getAnnualTurnover(), temp.first().getEmployeesCount(), OrganizationType.valueOf(temp.first().getType()));
                System.out.println("Элемент по id "+ t.getId()+ " успешно обновлен");
                return 0;
            }
        }

        System.out.println("Элемент с таким id не найден");
        return -1;
    }

    /**
     * Метод, удаляющий элемент из коллекции по его id
     */

    void remove_by_id(long id, TreeSet<Organization> collection) {
        Iterator<Organization> itr = collection.iterator();
        boolean check=false;
        while (itr.hasNext()){
            if(itr.next().getId() == id){
                itr.remove();
                check=true;
                System.out.println("Элемент с заданным id успешно удален");
                break;
            }
        }
        if(!check){
            System.out.println("Элемент с заданным id не найден");
        }
    }

    /**
     * Метод, очищающий коллекцию
     */

    void clear(TreeSet<Organization> collection) {
        collection.clear();
        System.out.println("Коллекция усепшно очищена");
    }

    /**
     * Метод, который сохраняет коллекцию в файл формата .json
     */

    void save(TreeSet<Organization> collection, File file) throws FileNotFoundException {

        JSONArray organizations = new JSONArray();

        for (Organization a: collection) {
            JSONObject one = new JSONObject();

            one.put("id", a.getId());
            one.put("name", a.getName());
            JSONObject coords = new JSONObject();
            coords.put("x",a.getCoordinates().getX());
            coords.put("y",a.getCoordinates().getY());
            one.put("coordinates", coords);
            one.put("creationDate", a.getCreationDate().toString());
            one.put("annualTurnover", a.getAnnualTurnover());
            one.put("employeesCount", a.getEmployeesCount());
            if (a.getType()==null)
                one.put("type", JSONObject.NULL);
            else
                one.put("type", a.getType());

            try{
                one.put("officialAddress", a.getOfficialAddress().toString());
            }
            catch (Exception e)
            {
                one.put("officialAddress", JSONObject.NULL);
            }


            organizations.put(one);
        }

        PrintWriter writer = new PrintWriter(file);
        writer.write(organizations.toString());
        writer.close();
        System.out.println("Коллекция успешно сохранена в файл " + file);
    }

    /**
     * Метод, добавляющий новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции
     */

    void add_if_min(TreeSet<Organization> collection) {
        if (!collection.isEmpty()) {
            TreeSet<Organization> temp = new TreeSet<Organization>();
            add(temp);
            Organization org = temp.last();

            if (org.compareTo(collection.first()) < 0) {
                collection.add(org);
                System.out.println("Элемент минимален - успешно добавлен в коллекцию");
            } else {
                System.out.println("Элемент не является минимальным - данный метод не может добавить его в коллекцию");
            }
        }
        else {
            System.out.println("Коллекция пустая");
        }
    }

    /**
     * Метод, удаляющий из коллекции все элементы, превыщающие заданный
     */

    void remove_greater(String name, TreeSet<Organization> collection) {// tailSet() more effective or not, because need to create new collection
        Iterator<Organization> itr = collection.iterator();
        boolean check = false;
        while(itr.hasNext()){
            if(!check){
                if(itr.next().getName().equalsIgnoreCase(name)){check = true;}
            }else{
                itr.next();
                itr.remove();
            }
        }
    }

    /**
     * Метод, удаляющий из коллекции все элементы, значение которых меньше заданного
     */

    void remove_lower(String name, TreeSet<Organization> collection) {//    headSet() more effective or not, because need to create new collection
        Iterator<Organization> itr = collection.iterator();
        while(itr.hasNext()&&!(itr.next().getName().equalsIgnoreCase(name))){
            itr.remove();
        }
    }

    /**
     * Метод, который выводит количество элементов, значение поля type которых больше заданного
     */

    void count_greater_than_type(String s, TreeSet<Organization> collection) {

        try {

            OrganizationType type = OrganizationType.valueOf(s.toUpperCase());


            int count = 0;

            Iterator<Organization> itr = collection.iterator();
            while (itr.hasNext()) {
                if (OrganizationType.valueOf(itr.next().getType()).ordinal() > type.ordinal()) {
                    count++;
                }
            }
            System.out.println("Количество организаций типа больше, чем " + type.toString() + " :" + count);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Название типа не найдено. Или произошла непредвиденная ошибка");
        }
    }

    /**
     * Метод, который выводит все элементы коллекции в порядке убывания
     */

    void print_descending(TreeSet<Organization> collection) {
        Iterator<Organization> itr = collection.iterator();
        itr.next();
        out(itr);
    }

    /**
     * Метод, выводящий значения поля officialAddress всех элементов в порядке возрастания
     */

    void print_field_ascending_official_address(TreeSet<Organization> collection) {
        Iterator<Organization> itr = collection.iterator();
        ArrayList<String> list = new ArrayList<String>();
        while(itr.hasNext()){
            list.add(itr.next().getOfficialAddress());
        }


        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        for (String address: list){
            System.out.println(address);
        }
    }

    /**
     * Метод, осуществляющий чтение данных из файла
     */

    public static TreeSet<Organization> readFromFile(File file)
    {
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            String text = new String();

            while((line=reader.readLine())!=null)  text+=line+'\n';

            TreeSet<Organization> organizations = new TreeSet<Organization>();

            JSONArray orgs = new JSONArray(text);


            for (int i=0; i<orgs.length(); i++)
            {
                JSONObject a = orgs.getJSONObject(i);

                String name = a.getString("name");

                long id = a.getLong("id");

                JSONObject c = a.getJSONObject("coordinates");
                Coordinates coords = new Coordinates(c.getLong("x"), c.getInt("y"));

                LocalDateTime creationDate = LocalDateTime.parse(a.getString("creationDate"));

                float annualTurnover = a.getFloat("annualTurnover");

                Integer employeesCount = a.getInt("employeesCount");

                OrganizationType type;
                if (a.isNull("type"))  type = null;
                else type = OrganizationType.valueOf(a.getString("type"));

                Address officialAddress;
                if (a.isNull("officialAddress")) officialAddress = null;
                else officialAddress = new Address(a.getString("officialAddress"));

                Organization organization = new Organization(name, coords, creationDate, annualTurnover, employeesCount, type, officialAddress);

                organizations.add(organization);
            }

            return organizations;
        }
        catch (Exception e)
        {
            System.out.println("Беда с файлом. Создана пустая коллекция");
            System.out.println(e.getMessage());
            return new TreeSet<Organization>();
        }
    }

    /**
     * Метод, который выводит все элементы коллекции
     */

    public void out(Iterator<Organization> itr) {
        while (itr.hasNext()) {
            Organization a = itr.next();
            out(itr);
            System.out.println(a.toString());
        }
    }
}
