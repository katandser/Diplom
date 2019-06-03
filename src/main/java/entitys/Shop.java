package entitys;

public class Shop {
    private String id;
    private String name;
    private String adress;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAdress() {
        return adress;
    }

    public Shop(String id, String name, String adress) {
        this.id = id;
        this.name = name;
        this.adress = adress;
    }

}
