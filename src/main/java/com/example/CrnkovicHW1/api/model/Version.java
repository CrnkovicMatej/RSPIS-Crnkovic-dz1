package com.example.CrnkovicHW1.api.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Version class represents a version string following the semantic versioning (SemVer) specification.
 * It provides methods for parsing and comparing version strings according to SemVer rules.
 */
public class Version implements Comparable<Version>
{
    /**
     * The original full version string.
     */
    private final String fullVersion;
    /**
     * The resolved version string without pre-release identifiers.
     */
    private String resolvedVersion;
    /**
     * Indicates whether the version has a pre-release identifier.
     */
    private Boolean hasPreRelease = false;
    /**
     * The pre-release identifier string.
     */
    private String preRelease;
    /**
     * Major/minor/patch version parts.
     */
    private int majorPart;
    private int minorPart;
    private int patchPart;

    /**
     * Constructs a Version object with the specified version string.
     * @param version The version string to parse.
     */
    public Version(String version)
    {
        this.fullVersion = version;
        ResolveVersion();

    }

    /**
     * Parses the version string and initializes version parts.
     */
    private void ResolveVersion()
    {
        String[] versionSplitted = this.fullVersion.split("\\.");
        try {
            this.majorPart = Integer.parseInt(versionSplitted[0]);
            this.minorPart = Integer.parseInt(versionSplitted[1]);
            String patchString = versionSplitted[2];

            Pattern pattern = Pattern.compile("\\d+");
            Matcher matcher = pattern.matcher(patchString);
            if (matcher.find()) {
                this.patchPart = Integer.parseInt(matcher.group());
            } else {
                this.patchPart = 0;
            }
            int dashIndex = patchString.indexOf('-');
            if (dashIndex != -1 && dashIndex >= matcher.end()) {
                this.hasPreRelease = true;
                this.preRelease = patchString.substring(dashIndex + 1);
            }
        }
        catch (NumberFormatException ex) {
            throw new RuntimeException("Invalid integer format", ex);
        } catch (IndexOutOfBoundsException ex) {
            throw new RuntimeException("Invalid version format", ex);
        }
        this.resolvedVersion = String.format("%d.%d.%d", majorPart, minorPart, patchPart);
    }
    // Getters for version parts and properties

    /**
     * Gets the major version part.
     * @return The major version part.
     */
    public int getMajorPart() {
        return majorPart;
    }

    /**
     * Gets the minor version part.
     * @return The minor version part.
     */
    public int getMinorPart() {
        return minorPart;
    }

    /**
     * Gets the patch version part.
     * @return The patch version part.
     */
    public int getPatchPart() {
        return patchPart;
    }

    /**
     * Gets the original full version string.
     * @return The full version string.
     */
    public String getFullVersion() {
        return fullVersion;
    }

    /**
     * Gets the resolved version string without pre-release identifiers.
     * @return The resolved version string.
     */
    public String getResolvedVersion() {
        return resolvedVersion;
    }
    /**
     * Checks if the version has a pre-release identifier.
     * @return True if the version has a pre-release identifier, false otherwise.
     */
    public Boolean getHasPreRelease() {
        return hasPreRelease;
    }

    /**
     * Gets the pre-release identifier string.
     * @return The pre-release identifier string.
     */
    public String getPreRelease() {
        return preRelease;
    }
    /**
     * Compares this Version object with the specified Version object for order.
     * @param other The Version object to compare to.
     * @return A negative integer, zero, or a positive integer as this Version object is less than, equal to, or greater than the specified Version object.
     */
    @Override
    public int compareTo(Version other) {
        if (this.majorPart != other.majorPart) {
            return Integer.compare(this.majorPart, other.majorPart);
        } else if (this.minorPart != other.minorPart) {
            return Integer.compare(this.minorPart, other.minorPart);
        }
        else if (this.patchPart != other.patchPart) {
            return Integer.compare(this.patchPart, other.patchPart);
        }
        // https://semver.org/spec/v2.0.0.html
        // points 10. and 11. states that "Build metadata does not figure into precedence"
        // so we only need to look at major, minor, patch and pre-release identifiers
        else {
            if (this.getHasPreRelease() && !other.getHasPreRelease()){
                return -1;
            }
            else if(!this.getHasPreRelease() && other.getHasPreRelease()){
                return 1;
            }
            else if(this.getHasPreRelease() && other.getHasPreRelease()){
                return ComparePreRelease(this.getPreRelease(), other.getPreRelease());
            }
            else {
                return 0;
            }
        }
    }

    /**
     * Compares pre-release identifiers.
     * @param lPreRelease The pre-release identifier of the left Version object.
     * @param rPreRelease The pre-release identifier of the right Version object.
     * @return A negative integer, zero, or a positive integer as the left pre-release identifier is less than, equal to, or greater than the right pre-release identifier.
     */
    private int ComparePreRelease(String lPreRelease, String rPreRelease){
        // primitive implementation
        // TODO - improve method
        return lPreRelease.compareTo(rPreRelease);
    }
}
