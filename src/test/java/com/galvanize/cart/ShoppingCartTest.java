package com.galvanize.cart;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class ShoppingCartTest {

    // Class Under Test
    private ShoppingCart shoppingCart;

    @Before
    public void setUp() {
        shoppingCart = new ShoppingCart();
    }

    /* Given that I visit the site, when I begin shopping, then I expect my
       cart to be empty.*/
    @Test
    public void isEmpty_newCart_returnTrue() {
        assertTrue(shoppingCart.isEmpty());
    }

    /*Given I have an empty cart, when I add an Item, then I expect to see
      totalPrice reflect the sum of all the Items in my cart, times the
      quantities of each item.*/
    @Test
    public void getTotalPrice_emptyCart_addOneItem_returnPrice() {
        // Setup
        int quantity = 1;
        String name = "widget";
        BigDecimal price = new BigDecimal("1.11");

        // Exercise
        shoppingCart.addItem(quantity, name, price);

        // Assert
        assertEquals(price, shoppingCart.totalPrice());

        // Teardown
    }

    /*Given I have an empty cart, when I add more than one of an item, then
      I expect itemQuantities() to show the number of items I have added.*/
    @Test
    public void itemQuantities_emptyCart_returnQuantity() {
        // Setup
        int quantity = 2;
        String name = "widget";
        BigDecimal price = new BigDecimal("1.11");

        // Exercise
        shoppingCart.addItem(quantity, name, price);

        // Assert
        assertEquals(quantity, shoppingCart.getQuantity());
    }

    /*Given I have an empty cart, when I add items, then I expect itemizedList()
      reflect the items I have added along with their price and quantity.*/
    @Test
    public void addItems_emptyCart_returnListOfItems() {
        // Setup
        int quantity = 2;
        String name = "widget";
        BigDecimal price = new BigDecimal("1.11");

        // Exercise
        shoppingCart.addItem(quantity, name, price);
        shoppingCart.addItem(quantity, name, price);

        // Assert - list of 2 items
        List<ShoppingCart.LineItem> cartList = shoppingCart.itemizedList();
        assertNotNull(cartList);
        assertFalse(cartList.isEmpty());
        assertEquals(2, cartList.size());
        for(ShoppingCart.LineItem item : cartList){
            assertEquals(name, item.name);
            assertEquals(quantity, item.quantity);
            assertEquals(price, item.price);
        }

    }

    /*Given I have an empty cart, when I add more than one of an item, then
      I expect totalPrice to reflect both the item price and quantity.*/
    @Test
    public void totalPrice_emptyCart_twoItems_returnExtTotal() {
        // Setup
        int quantity = 2;
        String name = "widget";
        BigDecimal price = new BigDecimal("1.11");

        // Exercise
        shoppingCart.addItem(quantity, name, price);
        shoppingCart.addItem(quantity, name, price);

        // Assert - list of 2 items
        assertEquals(new BigDecimal("4.44"), shoppingCart.totalPrice());
    }

    /*Given I have a cart with items that are not on sale, when I add items
      that are on sale, I expect onSaleItems() to include only the items on sale.*/
    @Test
    public void onSaleItems_returnOnlyOnSaleItems() {
        // Setup
        int quantity = 2;
        String name = "widget";
        BigDecimal price = new BigDecimal("1.11");

        // Exercise
        shoppingCart.addItem(quantity, name, price);
        shoppingCart.addItem(quantity, name, price);
        shoppingCart.addItem(quantity, name, price, true);
        shoppingCart.addItem(quantity, name, price, true);

        // Total items in cart is 4
        assertEquals(4, shoppingCart.itemizedList().size());
        // Two items are on sale
        List<ShoppingCart.LineItem> cartList = shoppingCart.onSaleItems();
        assertNotNull(cartList);
        assertFalse(cartList.isEmpty());
        assertEquals(2, cartList.size());
        for(ShoppingCart.LineItem item : cartList){
            assertEquals(name, item.name);
            assertEquals(quantity, item.quantity);
            assertEquals(price, item.price);
            assertTrue(item.onSale);
        }
    }

    /* given an empty cart, add item 2323 from Widget Catalog */

    /* given an empty cart, add all items of category primary widgets from Widget Catelog */
}