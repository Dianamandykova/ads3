import java.util.Random;

public class TestHashtable{
    public static void main(String[] args) {
        MyHashTable<MyTestingClass, Student> myHashTable = new MyHashTable<>();
        MyTestingClass test;
        Student val;
        for (int i = 0; i < 10000; i++) {
            test = new MyTestingClass(i, "name " + new Random().nextInt(94));
            val = new Student("Name" + i, "Students");
            myHashTable.put(test, val);
        }

        int[] size = new int[myHashTable.size()];
        for (MyTestingClass key : myHashTable.keySet()) {
            int index = Math.abs(key.hashCode()) % myHashTable.size();
            size[index]++;
            System.out.println(index + 1 + "=" + size[index]);
        }
    }
}

class Student {
    private String firstname;
    private String lastname;

    public Student(String firstname, String lastname){
        this.firstname = firstname;
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return "Student [ Surname: " + lastname + " Name " + firstname + " ]";
    }
}