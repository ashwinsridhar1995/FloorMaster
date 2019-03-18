/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floorMaster.advice;

import com.sg.floorMaster.dao.AuditDao;
import com.sg.floorMaster.dao.DaoPersistenceException;
import com.sg.floorMaster.service.InvalidFileException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
/**
 *
 * @author ashwinsridhar
 */
public class LoggingAdvice {
    AuditDao auditDao;
 
    public LoggingAdvice(AuditDao auditDao) {
        this.auditDao = auditDao;
    }
    
    public void createAuditEntry(JoinPoint jp) {
        Object[] args = jp.getArgs();
        String auditEntry = jp.getSignature().getName() + ": ";
        for (Object currentArg : args) {
            auditEntry += currentArg;
        }
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (DaoPersistenceException e) {
            System.err.println(
               "ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }
    
    @AfterThrowing(value = "(execution(* *.*(..)))", throwing = "invalidItemException")
    public void logException1(JoinPoint thisJoinPoint, InvalidFileException invalidFileException) {
        try {
            auditDao.writeAuditEntry(thisJoinPoint + " -> " + invalidFileException);
            //out.println(thisJoinPoint + " -> " + invalidItemException);
            // out.close();
        } catch (DaoPersistenceException ex) {
            Logger.getLogger(LoggingAdvice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
