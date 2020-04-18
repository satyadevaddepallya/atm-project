package atm.project.util;

public enum DenominationEnum {

    ONE("1", 1), FIVE("5", 5), TEN("10", 10), TWENTIES("20", 20), FIFTIES("50", 50);

    private final String key;
    private final Integer value;

    DenominationEnum(String key, Integer value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public Integer getValue() {
        return value;
    }

}
