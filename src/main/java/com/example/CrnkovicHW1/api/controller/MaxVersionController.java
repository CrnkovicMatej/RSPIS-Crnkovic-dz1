package com.example.CrnkovicHW1.api.controller;

import com.example.CrnkovicHW1.api.model.Version;
import com.example.CrnkovicHW1.service.MaxVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The MaxVersionController class handles HTTP requests related to retrieving the maximum version between two provided versions.
 */
@RestController
public class MaxVersionController {

    /**
     * The service responsible for resolving the maximum version.
     */
    private MaxVersionService maxService;
    /**
     * Constructs a MaxVersionController with the specified MaxVersionService.
     * @param maxService The MaxVersionService to use for resolving maximum version.
     */
    @Autowired
    public MaxVersionController(MaxVersionService maxService){
        this.maxService = maxService;
    }


    /**
     * Handles HTTP GET requests to "/versions/max" endpoint.
     * This endpoint retrieves the maximum version between two provided versions.
     * @param v1 The first version string provided as a request parameter.
     * @param v2 The second version string provided as a request parameter.
     * @return The maximum version string as a response.
     */
    @GetMapping("/versions/max")
    public String getMaxVersion(@RequestParam("v1") String v1, @RequestParam("v2") String v2)
    {
        try {
            // Parse provided version strings into Version objects
            Version version1 = new Version(v1);
            Version version2 = new Version(v2);

            // Resolve the maximum version using the MaxVersionService
            String maxVersion = maxService.resolveMaxString(version1, version2);

            // Return the maximum version string as a response, followed by a newline character
            return maxVersion + "\n";
        }
        // currently we do not want to throw
        catch (IllegalArgumentException ex) {
            return null;
        } catch (UnsupportedOperationException ex) {
            return null;
        } catch (RuntimeException ex) {
            return null;
        }
    }
}
