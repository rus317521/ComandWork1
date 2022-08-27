import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int sumProducts = 0;
        String[] products = {
                "Молоко 1л", "Хлеб", "Сыр 200 гр", "Яйца 10 шт", "Гречка 1 кг", "Мука 1 кг", "Макароны 800 гр", "Курица 1кг", "Картошка 1 кг", "Яблоки 1 кг"
        };
        int[] prices = {60, 50, 150, 80, 90, 120, 100, 200, 45, 130};
        String[] saleProducts = {
                "Мороженое Филёвское", "Чипсы Lays", "Шоколад KitKat"
        };
        int[] salePrices = {60, 180, 150};
        int[] productsCount = new int[prices.length + salePrices.length];

        System.out.println("Список возможных товаров для покупки");
        for (int i = 0; i < products.length; i++) {
            System.out.println(i + 1 + " " + products[i] + " " + prices[i] + " руб/шт");
        }
        System.out.println("Список товаров по акции 3 = 2");
        for (int k = 0; k < saleProducts.length; k++) {
            System.out.println((k + products.length + 1) + " " + saleProducts[k] + " " + salePrices[k] + " руб/шт");
        }

        while (true) {
            int number = 0;
            int count = 0;
                System.out.println("Выберите товар и количество или введите `end`");
                try {
                String input = scanner.nextLine();
                if ("end".equals(input)) {
                    break;
                }

                String[] parts = input.split(" ");
                if (parts.length == 2) {
                    number = Integer.parseInt(parts[0]) - 1;
                    count = Integer.parseInt(parts[1]);
                } else {
                    System.out.println("Данные введены некорректно. Необходимо ввести два целых положительных числа!");
                    continue;
                }

                if (Integer.parseInt(parts[0]) > productsCount.length || Integer.parseInt(parts[0]) < 1 || Integer.parseInt(parts[1]) < 1) {
                    System.out.println("Данные введены некорректно. Первое число(номер товара) должен быть целым числом в диапазоне от 1 до 13. А второе число (количество товара) должно быть целым положительным числом");
                } else {
                    productsCount[number] += count;
                }
            } catch (NumberFormatException e) {
                System.out.println("Данные введены некорректно. Необходимо ввести два целых положительных числа!");
                continue;
            }
        }

        System.out.println("Ваша корзина:");
        int sumProduct = 0; //сюда товар не по акции
        int saleSumProduct = 0; //сюда продукт по акции
        int saleSumProductAll = 0; // сюда всё по акции

        System.out.println("Товары без акции:");
        for (int i = 0; i < productsCount.length; i++) {
            if (productsCount[i] > 0) {
                if (productsCount[i] / 3 == 0 && i > prices.length - 1) {
                    sumProduct = productsCount[i] * salePrices[i - products.length];
                    System.out.println(saleProducts[i - products.length] + " " + productsCount[i] + " шт " + salePrices[i - products.length] + " руб/шт " + sumProduct + " руб в сумме");
                    sumProducts += sumProduct;
                } else if (i < 10) {
                    sumProduct = productsCount[i] * prices[i];
                    System.out.println(products[i] + " " + productsCount[i] + " шт " + prices[i] + " руб/шт " + sumProduct + " руб в сумме");
                    sumProducts += sumProduct;
                }
            }
        }
        System.out.println("Итого: " + sumProducts + " руб");

        System.out.println("Товары по акции 3 = 2:");
        for (int i = 0; i < productsCount.length; i++) {
            if (productsCount[i] != 0) {
                if (productsCount[i] / 3 > 0 && i > prices.length - 1) {
                    int numberSaleProducts = i - products.length;
                    int countSaleProducts = (productsCount[i] * 2 / 3 + productsCount[i] % 3);
                    saleSumProduct = countSaleProducts * salePrices[numberSaleProducts];
                    System.out.println(saleProducts[numberSaleProducts] + " " + countSaleProducts + " шт вместо " + productsCount[i] + " шт " + salePrices[numberSaleProducts] + " руб/шт " + " в сумме " + saleSumProduct + " руб. ");
                    saleSumProductAll += saleSumProduct;
                }
            }
        }
        System.out.println("Итого по акции: " + saleSumProductAll + " руб");
        System.out.println("Итого по всем: " + (sumProducts + saleSumProduct) + " руб");
    }
}