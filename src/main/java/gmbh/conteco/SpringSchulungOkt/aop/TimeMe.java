package gmbh.conteco.SpringSchulungOkt.aop;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.TimeUnit;

@Retention(RetentionPolicy.RUNTIME)
public @interface TimeMe {
    TimeUnit timeUnit() default TimeUnit.NANOSECONDS;
}
