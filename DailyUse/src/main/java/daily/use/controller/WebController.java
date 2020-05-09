package daily.use.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class WebController {

    @GetMapping("/request")
    public String RequestParam(@RequestParam String name) {
        if (name.isEmpty()) return "404";
        return name + ".This method uses @ParamVariable.";
    }

    @PostMapping("/variable/{name}")
    public String ParamVariable(@PathVariable String name) {
        if (name.isEmpty()) return "404";
        return name + ".This method uses @ParamVariable.";
    }

}
