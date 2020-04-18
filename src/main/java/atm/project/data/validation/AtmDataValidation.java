package atm.project.data.validation;

import atm.project.service.AtmServiceI;
import atm.project.service.AtmServiceImpl;

import java.util.Map;

public class AtmDataValidation {



    public static String validDenominationValue(Map<String, Integer> denominationMap) {
        String validationMessage = "";
        int totalDepositAmount = 0;
        for (Map.Entry<String, Integer> moneyMap : denominationMap.entrySet()) {
            boolean isValidDenominationValue = moneyMap.getValue().toString().chars().allMatch(Character::isDigit);
            if (isValidDenominationValue == false) {
                validationMessage = "Incorrect deposit amount, Please check Numbers entered against Denominations";
            } else {
                Integer denomination = new Integer(moneyMap.getKey().toString());
                Integer value = new Integer(moneyMap.getValue());
                Integer lineTotal = (denomination * value);
                totalDepositAmount += lineTotal;
            }

        }
        if (totalDepositAmount == 0) {
            validationMessage = "Deposit amount cannot be zero, Please check Numbers entered against Denominations";
        }
        return validationMessage;
    }
    
    public static boolean validDenomination(String denomination) {
        AtmServiceI atomService = new AtmServiceImpl();
        Map<String, Integer> denominations = atomService.getDenominations();
        if (denominations.containsKey(denomination)) {
            return true;
        } else {
            return false;
        }
    }
    

}
