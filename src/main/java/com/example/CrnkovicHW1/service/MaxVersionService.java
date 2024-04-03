package com.example.CrnkovicHW1.service;

import com.example.CrnkovicHW1.api.model.Version;
import org.springframework.stereotype.Service;

/**
 * The MaxVersionService class provides methods for resolving the maximum version between two provided versions.
 */
@Service
public class MaxVersionService {

    /**
     * Resolves the maximum version between two provided Version objects.
     * @param v1 The first Version object.
     * @param v2 The second Version object.
     * @return The maximum version string.
     */
    public String resolveMaxString(Version v1, Version v2)
    {
        // Compare the two versions and return the version with higher precedence
        int result = v1.compareTo(v2);
        if(result > 0) return v1.getFullVersion();
        return v2.getFullVersion();
    }
}
