public class Airplane {
    private int id;
	private int EconomyCapacity;
	private int BusinessCapacity;
	private String model;


    public Airplane(int id, int economyCapacity, int businessCapacity, String model) {
        this.id = id;
        this.EconomyCapacity = economyCapacity;
        this.BusinessCapacity = businessCapacity;
        this.model = model;
    }
    public int getId() {
        return id;
    }
    public int getEconomyCapacity() {
        return EconomyCapacity;
    }
    public int getBusinessCapacity() {
        return BusinessCapacity;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public void setEconomyCapacity(int EconomyCapacity) {
        this.EconomyCapacity = EconomyCapacity;
    }
    public void setBusinessCapacity(int businessCapacity) {
        this.BusinessCapacity = businessCapacity;
    }

    public void setId(int id) {
        this.id = id;
    }

}
