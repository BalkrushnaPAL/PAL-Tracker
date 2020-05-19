package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EnvController {


    @Value("${port:NOT SET}")
    private String port;

    @Value("${memory.limit:NOT SET}")
    private String memoryLimit;

    @Value("${cf.instance.index:NOT SET}")
    private String cfInstanceIndex;

    @Value("${cf.instance.addr:NOT SET}")
    private String cfInstanceAddr;

    public EnvController() {
    }

    public EnvController(String port, String memoryLimit, String cfInstanceIndex, String cfInstanceAddr) {
        this.port = port;
        this.memoryLimit = memoryLimit;
        this.cfInstanceIndex = cfInstanceIndex;
        this.cfInstanceAddr = cfInstanceAddr;
    }

    @GetMapping("/env")
    public Map<String,String> getEnv() throws Exception {
        Map <String, String> envMap = new HashMap<>();
        envMap.put("PORT" , port);
        envMap.put("CF_INSTANCE_INDEX" , cfInstanceIndex);
        envMap.put("MEMORY_LIMIT" , memoryLimit);
        envMap.put("CF_INSTANCE_ADDR" , cfInstanceAddr);

        return envMap;
    }
}
