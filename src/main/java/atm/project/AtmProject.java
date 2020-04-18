package atm.project;

import atm.project.service.AtmServiceI;
import atm.project.service.AtmServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class AtmProject {

    private static final Logger logger = LoggerFactory.getLogger(AtmProject.class);
    
    public static void main(String[] args) {
        
        logger.info("************************ ATM Machine Initialized on :" + getCurrentDate()
                + "************************ ");

        System.out.println("------------------------------------------------------------------------");

        System.out.println("************************ Welcome to Parabellum Bank ************************ ");

        System.out.println("------------------------------------------------------------------------");

        AtmServiceI atomService = new AtmServiceImpl();
        String denominationString = atomService.displayDenominations();

        System.out.println("************************ Instructions *********************************** ");

        System.out.println("Available Denominations in ATM Machine:" + denominationString);
        System.out.println("Enter First Denominations and followed by Value, sperated by =, "
                + "<denomination>=<Value>, Example:20=2, type E for exit:");

        System.out.println("------------------------------------------------------------------------");

        String denominationSlip = atomService.processUserInputAndReturnBalanceSlip();
        System.out.println(denominationSlip.toString());
        
        
    }

    private static Date getCurrentDate() {
        return new Date();
    }
    

}
