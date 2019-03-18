/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floor;

import com.sg.floorMaster.controller.Controller;
import com.sg.floorMaster.dao.Dao;
import com.sg.floorMaster.dao.DaoException;
import com.sg.floorMaster.dao.DaoFileImpl;
import com.sg.floorMaster.dao.DaoPersistenceException;
import com.sg.floorMaster.service.InvalidFileException;
import com.sg.floorMaster.service.ServiceLayer;
import com.sg.floorMaster.service.ServiceLayerImpl;
import com.sg.floorMaster.ui.UserIO;
import com.sg.floorMaster.ui.UserIOConsoleImpl;
import com.sg.floorMaster.ui.View;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 *
 * @author ashwinsridhar
 */
public class App {
    public static void main(String[] args) throws DaoPersistenceException, DaoException {
     /* UserIO io = new UserIOConsoleImpl();
      View view = new View(io);
      Dao dao = new DaoFileImpl();
        
      ServiceLayer service = new ServiceLayerImpl(dao);
        
      Controller controller = 
              new Controller(service, view);
      controller.run();*/
        
      ApplicationContext ctx = 
        new ClassPathXmlApplicationContext("applicationContext.xml");
    Controller controller = 
           ctx.getBean("controller",Controller.class);
        controller.run();
    }
}
