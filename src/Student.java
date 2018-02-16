
public class Student {

    public Student(String studentTuple) {
        ID = Integer.parseInt(studentTuple.substring(0,7));
        firstName = studentTuple.substring(8,17);
        lastName = studentTuple.substring(18,27);
        department = Integer.parseInt(studentTuple.substring(28,30));
        program = Integer.parseInt(studentTuple.substring(31,33));
        sinNumber = Integer.parseInt(studentTuple.substring(34,42));
        address = studentTuple.substring(43);
    }

    public int ID;
    public String firstName;
    public String lastName;
    public String address;
    public int department;
    public int program;
    public int sinNumber;

    public String toString(){
        return ID + firstName + lastName + department + program + sinNumber + address;
    }
}
