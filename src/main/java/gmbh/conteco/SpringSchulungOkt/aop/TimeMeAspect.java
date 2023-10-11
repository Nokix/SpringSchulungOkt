package gmbh.conteco.SpringSchulungOkt.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.function.LongFunction;
import java.util.function.LongSupplier;

@Aspect
@Component
public class TimeMeAspect {
    @Around("@annotation(timeMe)")
    public Object timeMethod(ProceedingJoinPoint joinPoint,
                             TimeMe timeMe) throws Throwable {
        Timer timer = new Timer().start();
        Object result = joinPoint.proceed();
        timer.end();

        System.out.println(
                timer.prettyToString(timeMe.timeUnit()));

        return result;
    }

    class Timer {

        private Long start;
        private Long end;
        private LongSupplier clock = System::nanoTime;

        public Timer() {
        }

        public Timer(LongSupplier clock) {
            this.clock = clock;
        }

        public Timer start() {
            this.start = clock.getAsLong();
            return this;
        }

        public Timer end() {
            this.end = clock.getAsLong();
            return this;
        }

        public long getTime() throws Exception {
            if (this.start == null) {
                throw new Exception("Timer not started.");
            }
            if (this.end == null) {
                throw new Exception("Timer not stopped.");
            }

            return this.end - this.start;
        }

        public long getTime(TimeUnit timeUnit) throws Exception {
            long time = getTime();
            return switch (timeUnit) {
                case MICROSECONDS -> time / 1000;
                case MILLISECONDS -> time / 1000000;
                case SECONDS -> time / 1000000000;
                default -> time;
            };
        }

        public String prettyToString(TimeUnit timeUnit) throws Exception{
            long time = this.getTime(timeUnit);
            return time + " " + timeUnit;
        }
    }

}
