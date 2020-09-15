
import java.time.LocalDateTime;
/**
 * Класс организаций, содержащий конструкторы, по кототорым создаются объекты класса, хранящиеся в коллекции
 */

public class Organization implements Comparable <Organization>{
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates Coordinates; //Поле не может быть null
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private float annualTurnover; //Значение поля должно быть больше 0
    private Integer employeesCount; //Поле не может быть null, Значение поля должно быть больше 0
    private OrganizationType type = null; //Поле может быть null
    private Address officialAddress = null; //Поле может быть null

    /**
     * Конструктор организации
     */

    public Organization(String name, Coordinates coordinates, LocalDateTime creationDate,
                        float annualTurnover, Integer employeesCount, OrganizationType type, Address officialAddress){
        this.name = name;
        this.Coordinates = coordinates;
        this.creationDate = creationDate;
        this.annualTurnover = annualTurnover;
        this.employeesCount = employeesCount;
        this.type = type;
        this.officialAddress = officialAddress;
        this.id = this.hashCode();
    }

    /**
     * Метод, предназначенный для замены организации с заданным id. Реализуется через update_id
     */

    public void replace(String name, Coordinates coordinates,
                        float annualTurnover, Integer employeesCount, OrganizationType type){
        this.name = name;
        this.Coordinates = coordinates;
        this.annualTurnover = annualTurnover;
        this.employeesCount = employeesCount;
        this.type = type;
    }

    @Override
    public int compareTo(Organization o) {
        int result = this.name.compareTo(o.name);
        return result;
    }


    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return Coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public float getAnnualTurnover() {
        return annualTurnover;
    }

    public Integer getEmployeesCount() {
        return employeesCount;
    }

    public String getType() {
        if(type != null){ return type.toString();}else{return "Не указан";}
    }

    public String getOfficialAddress() {
        if(officialAddress != null){ return officialAddress.toString();}else{return "Не указан";}

    }

    @Override
    public String toString()
    {
        return name + " c Id: " + id;
    }

}


