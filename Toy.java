public class Toy {
    private int id;
    private String name;
    private int count;
    private double weight;

    public Toy(int id, String name, int count, double weight) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void decreaseCount() {
        count--;
    }
}
