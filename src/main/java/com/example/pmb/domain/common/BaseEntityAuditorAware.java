/*
 *
 *
 */

package com.example.pmb.domain.common;

import com.example.pmb.domain.auth.entity.User;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class BaseEntityAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            //log.debug("Not found AuthenticationToken");
            return null;
        }

        User user = (User)authentication.getDetails();
        return Optional.of(user.getUsername());
    }
}
