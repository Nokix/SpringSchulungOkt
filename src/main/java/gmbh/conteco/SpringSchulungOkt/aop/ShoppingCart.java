package gmbh.conteco.SpringSchulungOkt.aop;

import lombok.Getter;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@Getter
public class ShoppingCart {
    Long sumOfPrices = 0L;
    Long countOfItems = 0L;

    List<Item> cart = new ArrayList<>();

    @TimeMe(timeUnit = TimeUnit.MICROSECONDS)
    public void addItem(Item item) {
        cart.add(item);
//        return 1;
    }

    @TimeMe
    public void removeItem(Item item) {
        cart.remove(item);
    }

    void updateSumOfPrices() {
        sumOfPrices = cart.stream()
                .mapToLong(Item::getPrice)
                .sum();
    }

    void updateCountOfItems() {
        this.countOfItems = (long) cart.size();
    }

    public String checkout() {
        return "Du hast " + countOfItems
                + " viele Items, die zusammen " + sumOfPrices
                + " kosten.";
    }
}
