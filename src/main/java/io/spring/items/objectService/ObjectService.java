package io.spring.items.objectService;

import io.spring.items.Error;
import io.spring.items.RandomGeneration;
import io.spring.items.models.Objects;
import io.spring.items.objectRepository.ObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service  //Whenever Service is encountered, Spring creates a new instance of the class "ObjectService"
public class ObjectService {

    @Autowired
    private ObjectRepository objectRepository;
    @Autowired
    private RandomGeneration randomGeneration;

    public List<Objects> viewAll() {
        ArrayList<Objects> list = new ArrayList<>();
        objectRepository.findAll().forEach(list::add);
        return list;
    }


    public Objects viewById(String objectName) throws Error {

        ArrayList<Objects> list = new ArrayList<>();
        objectRepository.findAll().forEach(list::add);
        int temp = 0;
        int flag = 0;
        for(int i = 0;i<list.size();i++)
        {
            Objects obj = list.get(i);
            if(obj.getObjectName().equalsIgnoreCase(objectName))
            {
                temp = i;
                flag = 1;
            }
        }
        if(flag == 1){
            return list.get(temp);
        }
        else{
            throw new Error("SORRY..OBJECT CANNOT BE FOUND !!");
        }
    }

    public void addObjects(Objects object) {
        object.setId(randomGeneration.randomNumberGenerator());
        objectRepository.save(object);
    }

    public void deleteObjects(String name) throws Error{
        try{
            objectRepository.deleteById(name);
        } catch (Exception e) {
            throw new Error("SORRY..OBJECT OF THAT ID IS NOT PRESENT");
        }

    }

    public void modifyObject(Objects object) {

        objectRepository.save(object);
    }
}
