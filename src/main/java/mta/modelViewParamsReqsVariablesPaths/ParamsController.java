package mta.modelViewParamsReqsVariablesPaths;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;
/*
https://shareprogramming.net/huong-dan-su-dung-requestparam-trong-spring-boot/
https://shareprogramming.net/huong-dan-su-dung-pathvariable-trong-spring-boot/
 */

@Controller
public class ParamsController {

    @RequestMapping("/pathVariable/{id}/{name}")
    //http://localhost:8081/pathVariable/10/Hello
    public String getPathVars(@PathVariable String id, @PathVariable String name, Model m) {
        String msg;
        if (id != null && name != null) {
            msg = "Hello " + "ID: " + id + ", name: " + name;

        } else {
            msg = "Missing Parameters";
        }
        m.addAttribute("message", msg);
        return "params/ViewPageParamRequest";
        //return "ID: " + id + ", name: " + name;
    }

    @RequestMapping("/mapPathVariable/{id}/{name}")
    //http://localhost:8081/mapPathVariable/10/Helloo
    public String getMapPathVars(@PathVariable Map<String, String> pathVarsMap, Model m) {
        String id = pathVarsMap.get("id");
        String name = pathVarsMap.get("name");
        String msg;
        if (id != null && name != null) {
            msg = "Hello " + "ID: " + id + ", name: " + name;
        } else {
            msg = "Missing Parameters";
        }
        m.addAttribute("message", msg);
        return "params/ViewPageParamRequest";
    }

    //http://localhost:8081/readRequestParamsWithName?name=Aa&pass=Bb
    @RequestMapping("/readRequestParamsWithName") // From view: Index_ParamRequest.jsp
    public String display(@RequestParam("name") String nameValue, @RequestParam("pass") String passValue, Model m) {
        if (passValue.equals("admin")) {
            String msg = "Hello " + nameValue;
            m.addAttribute("message", msg);
            return "params/ViewPageParamRequest";
        } else {
            String msg = "Sorry " + nameValue + ". You entered an incorrect password";
            m.addAttribute("message", msg);
            return "params/ViewPageParamRequest";
        }
    }

    @GetMapping("/paramsRequestView")
    public String paramsMethod() {
        return "params/Index_ParamRequest";
    }


    @GetMapping("/goToViewPage")
    public ModelAndView passParametersWithModelAndView() {
        ModelAndView modelAndView = new ModelAndView("params/ViewPageParamRequest");
        modelAndView.addObject("message", "Baeldung");
        return modelAndView;
    }

    @GetMapping("/printViewPage")
    public String passParametersWithModelMap(ModelMap map) {
        map.addAttribute("welcomeMessage", "welcome");
        map.addAttribute("message", "Baeldung");
        return "params/ViewPageParamRequest";
    }

    @GetMapping("/showViewPage")
    public String passParametersWithModel(Model model) {
        Map<String, String> map = new HashMap<>();
        map.put("spring", "mvc");
        model.addAttribute("message", "Baeldung");
        model.mergeAttributes(map);
        return "params/ViewPageParamRequest";
    }
}
