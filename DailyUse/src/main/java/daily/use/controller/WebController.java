package daily.use.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    @GetMapping("/request")
    public String RequestParam(@RequestParam String name) {
        if (name.isEmpty()) return "404";
        return name + ".This method uses @ParamVariable.";
    }

    @GetMapping("/variable/{name}")
    public String ParamVariable(@PathVariable String name) {
        if (name.isEmpty()) return "404";
        return name + ".This method uses @ParamVariable.";
    }

}
