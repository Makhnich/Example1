/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mirea.kt.example1;

/**
 *
 * @author home
 */
import java.io.*;

import java.util.ArrayList;

// Класс, описывающий продукт

// Основной класс программы
public class Example1 {
    public static void main(String[] args) {
        // Запрос пути к файлу у пользователя
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Махнич ПР12 В5 Введите путь к файлу для десериализации: ");
        String filePath = null;
        try {
            filePath = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        // Создание и запуск отдельного потока для десериализации
        Thread deserializationThread = new Thread(() -> {
            try  {
                // Десериализация объекта
                ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath));
                Product product = (Product) inputStream.readObject();
                // Вывод содержимого полей объекта в консоль
                System.out.println("Код продукта: " + product.getCode());
                System.out.println("Название продукта: " + product.getName());
                System.out.println("Тип продукта: " + product.getType());
                System.out.println("Скидка на продукт: " + product.isDiscount());
                System.out.println("Ингредиенты: " + product.getIngredients());
                System.out.println("Цена продукта: " + product.getPrice());
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        deserializationThread.start();
    }
}