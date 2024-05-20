package com.simform.departmentservice.healthcheck;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * A custom health check indicator that checks the amount of free memory in the JVM
*/

/**
 * Some urls:
 * <a href="http://localhost:8081/actuator">...</a>
 * a href="http://localhost:8081/actuator/health">...</a>
 * a href="http://localhost:8081/actuator/beans">...</a>
 */

@Component
public class MemoryHealthIndicator implements HealthIndicator {
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
