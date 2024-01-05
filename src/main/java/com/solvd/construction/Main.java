package com.solvd.construction;


import com.solvd.construction.model.Project;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) throws JAXBException, FileNotFoundException {
        //DaoMenu.showMenu();
        JAXBContext context = JAXBContext.newInstance(Project.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Marshaller marshaller = context.createMarshaller();
        Project project = (Project) unmarshaller.unmarshal(new FileReader("p1.xml"));
        System.out.println(project.getBudget());
        System.out.println(project.getEmployeeList());
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(project, System.out);
    }
}
