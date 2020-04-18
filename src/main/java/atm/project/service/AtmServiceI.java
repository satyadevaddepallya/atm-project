package atm.project.service;

import java.util.Map;

public interface AtmServiceI {

    Map<String,Integer> getDenominations();
    
    String displayDenominations();
 
    String processUserInputAndReturnBalanceSlip();
    

}
