package io.spring.items.objectController;

import io.spring.items.Error;
import io.spring.items.objectService.ObjectService;
import io.spring.items.models.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.rmi.server.UID;
import java.util.List;

@RestController
public class ObjectController {
    @Autowired //This statemen  t declares objectService with the instance of the class ObjectService
    private ObjectService objectService;

    @RequestMapping("/items")
    public List<Objects> viewAll() {
        return objectService.viewAll();

    }
    //@RequestMapping("/items/{objectName}")
    public Objects viewById(String objectName) throws Error {

        return objectService.viewById(objectName);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addObjects(@RequestBody Objects object)
    {
        objectService.addObjects(object);
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "/items/{name}")
    public void deleteObjects(@PathVariable String name) throws Error
    {
        objectService.deleteObjects(name);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/items/{id}")
    public void modifyObject(@RequestBody Objects object)
    {
        objectService.modifyObject(object);
    }


}
