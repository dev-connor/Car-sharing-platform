package dev.connor.Carsharingplatform.common.config;

import dev.connor.Carsharingplatform.common.util.SecurityUtil;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditConfig implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        var name = SecurityUtil.getCurrentUsername();

        if (name.isPresent()) {
            return name;

        } else {
            return Optional.of("ANONYMOUS");
        }
    }
}