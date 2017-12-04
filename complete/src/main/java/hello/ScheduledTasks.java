package hello;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private AtomicInteger counter = new AtomicInteger(0);

    //@Scheduled(fixedRate = 5000)
    @Scheduled(fixedDelayString = "${fixedDelay.in.milliseconds}")
    public void reportCurrentTime() {

        int jobId = counter.incrementAndGet();

        logger.info("The time is now {}", dateFormat.format(new Date())+ ", jobId: " + jobId);
        logger.info("ReportCurrentTime Current Thread : {}", Thread.currentThread().getName());
    }

    @Scheduled(fixedRateString = "${fixedRate.in.milliseconds}")
    public void scheduleTaskWithFixedRate() {

        int jobId = counter.incrementAndGet();

        logger.info("Fixed Rate Task :: Execution Time - {}", dateFormat.format(new Date())+ ", jobId: " + jobId);
        logger.info("Fixed Rate Task Current Thread : {}", Thread.currentThread().getName());
    }

    @Scheduled(fixedDelayString = "${fixedDelay.in.milliseconds}")
    public void scheduleTaskWithFixedDelay() {

        int jobId = counter.incrementAndGet();

        logger.info("Fixed Delay Task :: Execution Time - {}", dateFormat.format(new Date()) + ", jobId: " + jobId);
        logger.info("Fixed Delay Task Current Thread : {}", Thread.currentThread().getName());
    }


    @Scheduled(fixedDelayString = "${fixedRate.in.milliseconds}",initialDelayString = "${intialDelay.in.milliseconds}")
    public void scheduleTaskWithInitialDelay() {

        int jobId = counter.incrementAndGet();

        logger.info("Intial Delay Task :: Execution Time - {}", dateFormat.format(new Date()) + ", jobId: " + jobId);
        logger.info("Intial Delay Task Current Thread : {}", Thread.currentThread().getName());

    }

    @Scheduled(cron = "${cron.expression}")
    public void scheduleTaskWithCronExpression() {

        int jobId = counter.incrementAndGet();

        logger.info("Cron Task :: Execution Time - {}", dateFormat.format(new Date())+ ", jobId: " + jobId);
        logger.info("Cron Task Current Thread : {}", Thread.currentThread().getName());
    }
}
