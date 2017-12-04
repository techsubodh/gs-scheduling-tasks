package hello;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    //@Scheduled(fixedRate = 5000)
    @Scheduled(fixedDelayString = "${fixedDelay.in.milliseconds}")
    public void reportCurrentTime() {
        logger.info("The time is now {}", dateFormat.format(new Date()));
        logger.info("ReportCurrentTime Current Thread : {}", Thread.currentThread().getName());
    }

    @Scheduled(fixedRateString = "${fixedRate.in.milliseconds}")
    public void scheduleTaskWithFixedRate() {
        logger.info("Fixed Rate Task :: Execution Time - {}", dateFormat.format(new Date()) );
        logger.info("Fixed Rate Task Current Thread : {}", Thread.currentThread().getName());
    }

    @Scheduled(fixedDelayString = "${fixedDelay.in.milliseconds}")
    public void scheduleTaskWithFixedDelay() {
        logger.info("Fixed Delay Task :: Execution Time - {}", dateFormat.format(new Date()) );
        logger.info("Fixed Delay Task Current Thread : {}", Thread.currentThread().getName());
    }


    @Scheduled(fixedDelayString = "${fixedRate.in.milliseconds}",initialDelayString = "${intialDelay.in.milliseconds}")
    public void scheduleTaskWithInitialDelay() {
        logger.info("Intial Delay Task :: Execution Time - {}", dateFormat.format(new Date()) );
        logger.info("Intial Delay Task Current Thread : {}", Thread.currentThread().getName());

    }

    @Scheduled(cron = "${cron.expression}")
    public void scheduleTaskWithCronExpression() {
        logger.info("Cron Task :: Execution Time - {}", dateFormat.format(new Date()) );
        logger.info("Cron Task Current Thread : {}", Thread.currentThread().getName());
    }
}
