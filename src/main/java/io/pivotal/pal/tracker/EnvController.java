package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EnvController {

    private String port;
    private String memory;
    private String instanceIndex;
    private String instanceAddress;

    public EnvController(@Value("${port:NOT SET}") String port,
                         @Value("${memory.limit:NOT SET}") String memory,
                         @Value("${cf.instance.index:NOT SET}")String instanceIndex,
                         @Value("${cf.instance.addr:NOT SET}")String instanceAddress) {
        this.port = port;
        this.memory = memory;
        this.instanceIndex = instanceIndex;
        this.instanceAddress = instanceAddress;
    }

    @GetMapping("/env")
    public Map<String, String> getEnv() {
        Map<String, String> envMap = new HashMap<>(){{
            put("PORT", port);
            put("MEMORY_LIMIT", memory);
            put("CF_INSTANCE_INDEX", instanceIndex);
            put("CF_INSTANCE_ADDR", instanceAddress);
        }};

        return envMap;
    }
}
