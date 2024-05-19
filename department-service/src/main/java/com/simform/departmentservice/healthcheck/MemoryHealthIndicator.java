package com.simform.departmentservice.healthcheck;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;

/**
 * A custom health check indicator that checks the amount of free memory in the JVM
*/

public class MemoryHealthIndicator implements HealthIndicator
{
    @Override
    public Health health() {
        long freeMemory = Runtime.getRuntime().freeMemory();
        long totalMemory = Runtime.getRuntime().totalMemory();
        double freeMemoryPercent = ((double) freeMemory / (double) totalMemory) * 100;
        if(freeMemoryPercent > 25) {
            return Health.up()
                    .withDetail("freeMemory", freeMemory + "bytes")
                    .withDetail("totalMemory", totalMemory + "bytes")
                    .withDetail("freeMemoryPercent", freeMemoryPercent + "%")
                    .build();
        }
        else{
            return Health.down()
                    .withDetail("freeMemory", freeMemory + "bytes")
                    .withDetail("totalMemory", totalMemory + "bytes")
                    .withDetail("freeMemoryPercent", freeMemoryPercent + "%")
                    .build();
        }
    }
}
