package net.ripe.app.epigram.audit;

import org.springframework.data.domain.AuditorAware;

import java.nio.file.attribute.UserPrincipal;
import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
            //Note: If spring security is enabled, then I would return the username of the currently logged in user
            return Optional.of("system");
    }
}