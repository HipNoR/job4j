package ru.job4j.vacparser;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import java.util.Properties;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Main class of the parser application.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 24.10.2018
 */
public class StartParser {



    public void task(String prop) throws SchedulerException {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        JobDetail jobDetail = newJob(ParserJob.class).withIdentity("job1").build();
        JobDataMap dm = jobDetail.getJobDataMap();
        Configurator cfg = new Configurator();
        Properties properties = cfg.resProperties(prop);
        dm.put("prop", properties);
        String cron = properties.getProperty("cron.time");
        Trigger trigger = newTrigger().withIdentity("trigger1").withSchedule(cronSchedule(cron))
                .forJob("job1").build();
        scheduler.scheduleJob(jobDetail, trigger);

        scheduler.start();
    }


    public static void main(String[] args) {
        StartParser parser = new StartParser();
        try {
            parser.task(args[0]);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
