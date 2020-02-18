package Model;

public enum Size {
    XS("X-Small"), S("Small"), M("Medium"), L("Large"), XL("X-Large"), XXL("XX-Large");

    private String size;

    Size(String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
    }
}
