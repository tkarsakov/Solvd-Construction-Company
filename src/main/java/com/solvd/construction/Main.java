package com.solvd.construction;


import com.solvd.construction.ui.DaoMenu;
import jakarta.xml.bind.JAXBException;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws JAXBException, FileNotFoundException {
        DaoMenu.showMenu();
    }
}
