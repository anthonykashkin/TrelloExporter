package ru.allwrite.TrelloExporter.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.allwrite.TrelloExporter.Executor;

@Component
public class ScheduledTask {
    private final Executor executor;

    @Autowired
    public ScheduledTask(Executor executor) {
        this.executor = executor;
    }

    @Scheduled(fixedRateString = "${scheduler.fixedRate}")
    public void scheduleTaskWithFixedDelay() {
        executor.execute();
    }
}
