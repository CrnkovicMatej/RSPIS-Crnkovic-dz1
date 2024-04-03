package com.example.CrnkovicHW1.api.controller;

import com.example.CrnkovicHW1.api.model.Version;
import com.example.CrnkovicHW1.service.MaxVersionService;
import com.example.CrnkovicHW1.service.NextVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The NextVersionController class handles HTTP requests related to retrieving the next version based on the provided version and type.
 */
@RestController
public class NextVersionController {

    /**
     * The service responsible for resolving the next version.
     */
    private NextVersionService nextService;

    /**
     * Constructs a NextVersionController with the specified NextVersionService.
     * @param nextService The NextVersionService to use for resolving next version.
     */
    @Autowired
    public NextVersionController(NextVersionService nextService){
        this.nextService = nextService;
    }

    /**
     * Handles HTTP GET requests to "/versions/next" endpoint.
     * This endpoint retrieves the next version based on the provided version and type.
     * @param v1 The version string provided as a request parameter.
     * @param type The type of version increment (MAJOR, MINOR, or PATCH) provided as a request parameter.
     * @return The next version string as a response.
     */
    @GetMapping("/versions/next")
    public String getNextVersion(@RequestParam("v") String v1, @RequestParam("type") String type)
    {
        // Parse provided version string into a Version object, Resolve the next version using the NextVersionService
        Version version = new Version(v1);
        String nextVersion = nextService.resolveNextVersion(version, type);
        return nextVersion + "\n";
    }
}
