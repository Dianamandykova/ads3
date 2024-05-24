public class MyTestingClass {
    private int id;
    private String name;

    public MyTestingClass(int id, String name){
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        MyTestingClass that = (MyTestingClass) object;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int res = 1;
        res = prime * res + id;
        res = prime * res + ((name == null) ? 0 : name.hashCode());
        return res;
    }
