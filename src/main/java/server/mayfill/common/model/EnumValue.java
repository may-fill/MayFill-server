package server.mayfill.common.model;

public class EnumValue {

    private final String key;
    private final String value;

    public EnumValue(EnumModel enumModel) {
        key = enumModel.getKey();
        value = enumModel.getValue();
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

}
