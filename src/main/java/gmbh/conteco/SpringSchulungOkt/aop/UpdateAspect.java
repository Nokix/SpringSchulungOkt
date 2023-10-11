package gmbh.conteco.SpringSchulungOkt.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Aspect
@Component
public class UpdateAspect {

    @Pointcut("args(item)")
    public void parameterIsItem(Item item) {
    }

    @Pointcut("within(ShoppingCart)")
    public void withinShoppingCart() {
    }

//    @After("@annotation(TimeMe))")
//    @After("execution(* ShoppingCart.*Item(..))")
    @After("withinShoppingCart() && parameterIsItem(item)")
    public void updateCart(JoinPoint joinPoint, Item item) {
        ShoppingCart cart =
                (ShoppingCart) joinPoint.getTarget();
        System.out.println(joinPoint.getKind());

        System.out.println(item);

        cart.updateSumOfPrices();
        cart.updateCountOfItems();
    }

    @Around("execution(* ShoppingCart.add*(Item)) " +
            "&& parameterIsItem(item)")
    public Object checkForbiddenItem(
            ProceedingJoinPoint joinPoint,
            Item item) throws Throwable {
        if (item.getName().equals("Sprite")) {
            return null;
        }
        Object result = joinPoint.proceed();
        return result;
    }
}
