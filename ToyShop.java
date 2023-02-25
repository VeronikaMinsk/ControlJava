import java.util.*;

public class ToyShop {
    private List<Toy> toys;
    private Map<Integer, Toy> toyMap;

    public ToyShop() {
        toys = new ArrayList<>();
        toyMap = new HashMap<>();
    }

    public void addToy(int id, String name, int count, double weight) {
        Toy toy = new Toy(id, name, count, weight);
        toys.add(toy);
        toyMap.put(id, toy);
    }

    public void updateToyWeight(int id, double weight) {
        Toy toy = toyMap.get(id);
        if (toy != null) {
            toy.setWeight(weight);
        }
    }

    public Toy drawToy() {
        double totalWeight = 0;
        for (Toy toy : toys) {
            totalWeight += toy.getWeight();
        }

        double random = Math.random() * totalWeight;
        double sumWeight = 0;
        for (Toy toy : toys) {
            sumWeight += toy.getWeight();
            if (random < sumWeight) {
                toy.decreaseCount();
                return toy;
            }
        }

        return null;
    }

    public List<Toy> getToys() {
        return toys;
    }
}
