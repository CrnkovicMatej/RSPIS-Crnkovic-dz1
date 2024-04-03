package com.example.CrnkovicHW1.service;

import com.example.CrnkovicHW1.api.model.Version;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The NextVersionService class provides methods for resolving the next version based on the provided version and type.
 */
@Service
public class NextVersionService {

    /**
     * Resolves the next version based on the provided version and type.
     * @param version The current Version object.
     * @param type The type of version increment (MAJOR, MINOR, or PATCH).
     * @return The next version string.
     */
    public String resolveNextVersion(Version version, String type)
    {
        int majorPart = version.getMajorPart();
        int minorPart = version.getMinorPart();
        int patchPart = version.getPatchPart();

        // Increment version parts based on the provided type
        switch (type){
            case "MAJOR":
                majorPart++;
                minorPart = 0;
                patchPart = 0;
                break;
            case "MINOR":
                minorPart++;
                patchPart = 0;
                break;
            case "PATCH":
                patchPart++;
                break;
            default:
                return null;
        }

        return String.format("%d.%d.%d", majorPart, minorPart, patchPart);
    }
}
