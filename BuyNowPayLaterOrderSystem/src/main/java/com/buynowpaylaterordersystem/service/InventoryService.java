package com.buynowpaylaterordersystem.service;

import com.buynowpaylaterordersystem.model.Item;
import com.buynowpaylaterordersystem.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InventoryService {
    List<Product> inventory;

    public InventoryService() {
        this.inventory = new ArrayList<>();
    }

    public void seedInventory(List<Product> products) {
        inventory.addAll(products);
    }

    public void viewInventory() {
        for (Product pro: inventory) {
            System.out.println("Product: " + pro.getName() + ", Price: " + pro.getPrice() + ", Quantity: " + pro.getQuantity());
        }
    }

    public void checkAvailability(List<Item> items) throws Exception {
        for (Item item: items) {
            if (!inventory.contains(item.getProduct())) {
                throw new Exception("Inventory doesn't contain product: " + item.getProduct());
            }
            Integer availableProdQuantity = inventory.stream()
                    .filter(product -> product.getName().equals(item.getProduct().getName()))
                    .map(Product::getQuantity)
                    .findFirst()
                    .orElse(0);
            if (item.getQuantity() > availableProdQuantity) {
                throw new Exception("Inventory doesn't have enough items for product: " + item.getProduct());
            }
        }
    }

    public void updateInventory(List<Item> items) throws Exception {
        for (Item item: items) {
            Product product = this.getProduct(item.getProduct().getName());
            product.setQuantity(product.getQuantity()- item.getQuantity());
        }
    }

    public Product getProduct(String product) {
        return inventory.stream().filter(pro -> pro.getName().equals(product)).findFirst().orElse(null);
    }

    public List<Item> createItemList(Map<String, Integer> itemProducts) throws Exception {
        List<Item> itemList = new ArrayList<>();
        for (Map.Entry<String, Integer> itemProduct: itemProducts.entrySet()) {
            if (this.getProduct(itemProduct.getKey()) == null) {
                throw new Exception("Inventory doesn't contain product: " + itemProduct.getValue());
            }
            Item item = new Item(this.getProduct(itemProduct.getKey()), itemProduct.getValue());
            itemList.add(item);
        }
        return itemList;
    }
}
