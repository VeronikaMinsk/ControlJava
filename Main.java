import java.io.*;
import java.util.*;

public class Main {
    private static final String FILENAME = "toys.txt";

    public static void main(String[] args) {
        ToyShop toyShop = new ToyShop();
        loadToysFromFile(toyShop);

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("1. Добавить игрушку");
                System.out.println("2. Изменить вес игрушки");
                System.out.println("3. Розыгрыш игрушки");
                System.out.println("4. Выход");
                System.out.print("Выберите действие: ");

                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        System.out.print("Введите id игрушки: ");
                        int id = scanner.nextInt();
                        System.out.print("Введите название игрушки: ");
                        String name = scanner.next();
                        System.out.print("Введите количество игрушек: ");
                        int count = scanner.nextInt();
                        System.out.print("Введите вес игрушки (в % от 100): ");
                        double weight = scanner.nextDouble();
                        toyShop.addToy(id, name, count, weight);
                        saveToysToFile(toyShop);
                        break;
                    case 2:
                        System.out.print("Введите id игрушки: ");
                        int idToUpdate = scanner.nextInt();
                        System.out.print("Введите новый вес игрушки (в % от 100): ");
                        double newWeight = scanner.nextDouble();
                        toyShop.updateToyWeight(idToUpdate, newWeight);
                        saveToysToFile(toyShop);
                        break;
                    case 3:
                        Toy toy = toyShop.drawToy();
                        if (toy == null) {
                            System.out.println("Нет игрушек для розыгрыша");
                        } else {
                            System.out.printf("Призовая игрушка: %d %s\n", toy.getId(), toy.getName());
                            saveToyToFile(toy);
                            saveToysToFile(toyShop);
                        }
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("Неверный выбор");
                        break;
                }
            }
        }
    }
    
    private static void loadToysFromFile(ToyShop toyShop) {
        try (Scanner scanner = new Scanner(new File(FILENAME))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                int count = Integer.parseInt(parts[2]);
                double weight = Double.parseDouble(parts[3]);
                toyShop.addToy(id, name, count, weight);
            }
        } catch (FileNotFoundException e) {
            // do nothing, file will be created when saving toys
        }
    }
    
    private static void saveToysToFile(ToyShop toyShop) {
        try (PrintWriter writer = new PrintWriter(new File(FILENAME))) {
            for (Toy toy : toyShop.getToys()) {
                writer.printf("%d,%s,%d,%.2f\n", toy.getId(), toy.getName(), toy.getCount(), toy.getWeight());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    private static void saveToyToFile(Toy toy) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(new File("prize.txt"), true))) {
            writer.printf("%d,%s\n", toy.getId(), toy.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
    
