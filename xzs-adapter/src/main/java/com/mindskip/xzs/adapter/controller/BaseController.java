package com.mindskip.xzs.adapter.controller;

import com.mindskip.xzs.exception.BusinessException;
import com.mindskip.xzs.exception.SystemCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public abstract class BaseController {
    protected Integer getCurrentUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) return null;
        Object principal = auth.getPrincipal();
        if (principal instanceof UserDetails user) {
            return Integer.parseInt(user.getUsername());
        }
        return null;
    }

    protected Integer getCurrentUserRole() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) return null;
        return auth.getAuthorities().stream()
                .findFirst()
                .map(g -> Integer.parseInt(g.getAuthority().replace("ROLE_", "")))
                .orElse(null);
    }

    protected void requireRole(Integer... roles) {
        Integer role = getCurrentUserRole();
        if (role == null) throw new BusinessException(SystemCode.UNAUTHORIZED);
        boolean match = false;
        for (Integer r : roles) {
            if (r.equals(role)) { match = true; break; }
        }
        if (!match) throw new BusinessException(SystemCode.FORBIDDEN);
    }
}
