package io.spring.items.mainPage;

import com.sun.org.apache.xpath.internal.operations.Mod;
import io.spring.items.Error;
import io.spring.items.models.ListSize;
import io.spring.items.models.SearchId;
import io.spring.items.objectController.ObjectController;
import io.spring.items.models.Objects;
import io.spring.items.models.SearchObject;
import jdk.nashorn.internal.runtime.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class MainPageController {


    @Autowired
    private ObjectController objectController;

    @RequestMapping(method = RequestMethod.GET, value="/home")

    public String viewMain()
    {
        System.out.println("In main page");
        return "homePage";
    }


    @RequestMapping(method = RequestMethod.GET, value="/why")
    public String whyPage()
    {
        return "whyPage";
    }
    @RequestMapping(method = RequestMethod.GET, value="/aboutUs")
    public String aboutUs()
    {
        return "aboutUs";
    }

    @RequestMapping(method = RequestMethod.GET, value="/contact")
    public String contact()
    {
        return "contact";
    }

    @RequestMapping(method = RequestMethod.GET, value="/options")
    public String options()
    {
        return "options";
    }

        // MAIN METHODS //
    //VIEW ALL
    @RequestMapping(method = RequestMethod.GET, value="/viewAll")
    public String viewAll(Model model)
    {
        ArrayList list = (ArrayList<Objects>)objectController.viewAll();
        model.addAttribute("list1",list);
        model.addAttribute("object",new Objects());
        ListSize listSize = new ListSize();
        listSize.setListSize(list.size());
        model.addAttribute("size",listSize);
        return "viewAllPage";
    }
    //CREATE OBJECT
    @RequestMapping(method = RequestMethod.GET, value="/insertObject")
    public String createObject(Model model)
    {
        model.addAttribute("object",new Objects());
        return "createObjects";
    }
    @RequestMapping(method = RequestMethod.POST, value = "/items")
    public String createObjectPost(@ModelAttribute Objects object)
    {
        objectController.addObjects(object);
        return "success";
    }
    //VIEW BY NAME
    @RequestMapping(method = RequestMethod.GET, value="/viewObject")
    public String viewObjectGet(Model model)
    {
        model.addAttribute("searchObject", new SearchObject());
        return "viewObjects";
    }
    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST}, value="/viewTheObject")
    public String viewObject(Model model, @ModelAttribute SearchObject searchObject)
    {
        try {
            Optional<Objects> obj = Optional.of(new Objects());
            obj = Optional.ofNullable(objectController.viewById(searchObject.getItemName()));
            model.addAttribute("item", obj.get());  // used obj.get() because the "obj" is of Optional type //
            return "viewObject1";
        }
        catch (Error e){
            Error obj = new Error();
            obj.setErrorMessage(e.getErrorMessage());
            model.addAttribute("error",obj);
            return "error";
            }

        }

    //DELETE AN OBJECT
    @RequestMapping(method = RequestMethod.GET, value="/delete")
    public String delete(Model model)
    {
        model.addAttribute("searchId", new SearchId());
        return "deleteObject";
    }

    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST}, value="/deleteObject")
    public String deleteObjectGet(Model model,@ModelAttribute SearchId searchId)
    {
        try {
            objectController.deleteObjects(searchId.getSearchId());
        } catch (Error e) {
            Error obj = new Error();
            obj.setErrorMessage(e.getErrorMessage());
            model.addAttribute("error",obj);
            return "error";
        }
        return "delSuccess";
    }
    //MODIFY AN OBJECT
    @RequestMapping(method = RequestMethod.GET, value="/modify")
    public String modify(Model model)
    {
        model.addAttribute("searchId", new SearchId());
        model.addAttribute("newObject",new Objects());
        return "modifyObject";
    }
    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST}, value="/modifyObject")
    public String modifyObject(@ModelAttribute SearchId searchId,@ModelAttribute Objects newObject)
    {
        newObject.setId(searchId.getSearchId());
        objectController.modifyObject(newObject);
        return "modSuccess";
    }

    //VIEWING OBJECT FROM "VIEW ALL" PAGE
    @RequestMapping(method = {RequestMethod.POST,RequestMethod.GET},value = "/viewing")
    public String viewing(Model model, @ModelAttribute Objects item)
    {
        Objects obj = new Objects();
        obj = item;
        model.addAttribute("item",obj);
        model.addAttribute("object", new Objects());
        return "viewObject1";
    }

    //DELETING OBJECT FROM THE "VIEW ALL" PAGE
    @RequestMapping(method = RequestMethod.POST,value = "/deleting")
    public String deleting(Model model, @ModelAttribute Objects item)
    {
        try {
            objectController.deleteObjects(item.getId());
        } catch (Error e) {
            Error obj = new Error();
            obj.setErrorMessage(e.getErrorMessage());
            model.addAttribute("error",obj);
            return "error";
        }
        return "delSuccess";
    }
    //MODIFYING OBJECT FROM "VIEW ALL" PAGE
    @RequestMapping(method = RequestMethod.POST, value="/modifyNew")
    public String modifyNew(@ModelAttribute Objects object, Model model)
    {
        Objects obj = new Objects();
        obj = object;
        model.addAttribute("oldObject",obj);
        return "modifyObject1";
    }

    @RequestMapping(method = RequestMethod.POST, value="/modifyNew1")
    public String modifyNew1(@ModelAttribute Objects object)
    {
        objectController.modifyObject(object);
        return "modSuccess";
    }

}
