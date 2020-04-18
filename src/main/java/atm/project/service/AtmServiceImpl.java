package atm.project.service;

import atm.project.data.validation.AtmDataValidation;
import atm.project.util.DenominationEnum;

import java.util.HashMap;
import java.util.Map;

public class AtmServiceImpl implements AtmServiceI {

    static Integer TOTAL_ACCOUNT_BALANCE = 2000;
    final boolean loop = true;
    
    @Override
    public Map<String, Integer> getDenominations() {
        // TODO Auto-generated method stub
        Map<String, Integer> denominationMap = new HashMap<String, Integer>();
        for (DenominationEnum s : DenominationEnum.values()) {
            denominationMap.put(s.getKey(), s.getValue());
        }
        return denominationMap;
    }

    public String displayDenominations() {
        StringBuilder denominationKeyBuilder = new StringBuilder();
        Map<String, Integer> denominations = this.getDenominations();
        denominations.forEach((type, value) -> denominationKeyBuilder.append("" + type + " "));
        return denominationKeyBuilder.toString();
    }

    public String processUserInputAndReturnBalanceSlip() {

        
        Map<String, Integer> denominationMap = new HashMap<String, Integer>();
               
        System.out.println("Hi John Wick your total balance in saving account :" + TOTAL_ACCOUNT_BALANCE);
        System.out.println("-------------------------------------------------------------------------");
        
        // 1. User Input Entry
        System.out.print("Select Trnsaction Type WithDrawl(W) / Deposit (D):");
        String transactionType = System.console().readLine();
        
        
        while (loop) {
            System.out.print("Enter Denomination:");
            String denomination = System.console().readLine();
            if (denomination.equalsIgnoreCase("E")) {
                break;
            } else {
                String[] denominationArray = denomination.replaceAll("\\s", "").split("=");
                if (AtmDataValidation.validDenomination(denominationArray[0].toString()) == false) {
                    System.out.println("Valid Denominations are:" + this.displayDenominations() + "");
                } else {
                    denominationMap.put(denominationArray[0].toString(), new Integer(denominationArray[1].toString()));
                }

            }

        }

        // 2. Validate Denomination Values
        String validationMessage = AtmDataValidation.validDenominationValue(denominationMap);

        if (validationMessage != null) {
            System.out.println(validationMessage);
        }

        // 3. Perform Transaction
        Integer totalDepositWithDrawalAmount = getTotalDepositAmount(denominationMap);
        Integer totalAvailableBalance = this.processTransaction(transactionType, totalDepositWithDrawalAmount);

        // 4. Display Transaction Slip
        StringBuilder denominationSlip = new StringBuilder();
        denominationSlip.append("Balance:");
        for (Map.Entry<String, Integer> moneyMap : denominationMap.entrySet()) {
            denominationSlip.append("" + moneyMap.getKey() + "=" + moneyMap.getValue() + ",");
        }
        denominationSlip.append("Total=" + totalAvailableBalance);
        return denominationSlip.toString();
    }
    

    
    
    
    public Integer processTransaction(String transactionType, Integer totalDepositWithDrawalAmount) {
        Integer totalAvailableBalance = new Integer(0);
        if (transactionType.toUpperCase().startsWith("W")) {
            if (totalDepositWithDrawalAmount > TOTAL_ACCOUNT_BALANCE) {
                System.out.println(
                        "You have insufficient funds, available balance within your account:" + TOTAL_ACCOUNT_BALANCE);
            } else {
                totalAvailableBalance = TOTAL_ACCOUNT_BALANCE - totalDepositWithDrawalAmount;
            }
        } else {
            totalAvailableBalance = TOTAL_ACCOUNT_BALANCE + totalDepositWithDrawalAmount;
        }
        return totalAvailableBalance;
    }




    public Integer getTotalDepositAmount(Map<String, Integer> denominationMap) {

        Integer totalDepositAmount = new Integer(0);
        for (Map.Entry<String, Integer> moneyMap : denominationMap.entrySet()) {
            Integer denomination = new Integer(moneyMap.getKey().toString());
            Integer value = new Integer(moneyMap.getValue());
            Integer lineTotal = (denomination * value);
            totalDepositAmount += lineTotal;

        }
        return totalDepositAmount;
    }
    

}
