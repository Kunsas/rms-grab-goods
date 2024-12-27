package com.kunsas.grabgoods.categoryservice.audit;

import com.kunsas.grabgoods.categoryservice.entity.BaseEntity;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditAwareImpl implements AuditorAware<String>  {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("CATEGORY_MS");
    }
}
