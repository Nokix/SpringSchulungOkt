package gmbh.conteco.SpringSchulungOkt.aop;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ShoppingCartTest {
    @Autowired
    ShoppingCart shoppingCart;

    Item cola = Item.builder().name("Cola").price(100L).build();
    Item sprite = Item.builder().name("Sprite").price(200L).build();
    Item apfel = Item.builder().name("Apfel").price(50L).build();
    Item orange = Item.builder().name("Orange").price(50L).build();

    @Test
    @DisplayName("add multiple items and remove")
    void test1() {
        shoppingCart.addItem(cola);
        shoppingCart.addItem(apfel);
        shoppingCart.addItem(cola);
        shoppingCart.addItem(cola);
        shoppingCart.removeItem(cola);

        assertEquals(250L, shoppingCart.getSumOfPrices());
        assertEquals(3L, shoppingCart.getCountOfItems());
    }

    @Test
    @DisplayName("remove item from empty cart")
    void test2() {
        shoppingCart.removeItem(apfel);
        assertEquals(0L, shoppingCart.getSumOfPrices());
        assertEquals(0L, shoppingCart.getCountOfItems());
    }

    @Test
    @DisplayName("We want Orange")
//    @Disabled
    void test3() {
        shoppingCart.addItem(orange);
        List<Item> inhalt = shoppingCart.getCart();
        assertTrue(inhalt.contains(orange));
    }


    @Test
    @DisplayName("checkout empty cart")
    void testEmptyCartCheckout() {
        assertDoesNotThrow(() -> shoppingCart.checkout());
    }

    @Test
    void addItemTest() {
        shoppingCart.addItem(cola);
        shoppingCart.addItem(apfel);
        shoppingCart.addItem(cola);
        shoppingCart.addItem(cola);

        assertEquals(4L, shoppingCart.getCountOfItems());
    }

    @Test
    void removeItem() {
        shoppingCart.addItem(cola);
        shoppingCart.addItem(apfel);
        shoppingCart.addItem(cola);
        shoppingCart.removeItem(cola);
        shoppingCart.removeItem(apfel);

        assertEquals(1L, shoppingCart.getCountOfItems());
    }

    @Test
    void updateSumOfPrices() {
        shoppingCart.addItem(cola);
        shoppingCart.addItem(apfel);
        assertEquals(150L, shoppingCart.getSumOfPrices());

        shoppingCart.addItem(cola);
        assertEquals(250L, shoppingCart.getSumOfPrices());
    }

    @Test
    void updateCountOfItems() {
        shoppingCart.addItem(cola);
        shoppingCart.addItem(apfel);
        assertEquals(2L, shoppingCart.getCountOfItems());

        shoppingCart.addItem(cola);
        assertEquals(3L, shoppingCart.getCountOfItems());
    }

    @Test
    void checkSpriteIsForbidden() {
        shoppingCart.addItem(sprite);


        assertEquals(0L, shoppingCart.getCountOfItems());
    }
}