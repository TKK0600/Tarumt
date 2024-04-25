/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

import entity.Student;
import java.util.Comparator;

/**
 *
 * @author jiachuan
 * @param <V>
 */
public class JCComparator<V> implements Comparator<V> {

    private EnumComparator criterion;

    public JCComparator(EnumComparator criterion) {
        this.criterion = criterion;
    }

    @Override
    public int compare(V o1, V o2) {
        if (criterion == EnumComparator.studentID) {
            return compareByString(o1, o2);
        }

        return 0;
    }

    //pass object to here
    private int compareByInteger(V o1, V o2) {
        //extract data from object for comparing
        int num1 = getInt(o1);
        int num2 = getInt(o2);

        // if num1 > num2, return positive value; otherwise negative value
        return Integer.compare(num1, num2);
        
        //if compare string use id1.compareTo(id2);
    }
    

//        public int compare(Student s1, Student s2) {
//            // Compare students based on some criteria, such as student ID, name, etc.
//            // For example, let's say we want to sort students by their IDs
//            return s1.getStudentID().compareTo(s2.getStudentID());
//        }
   
    public int compare(String id1, String id2) {
        // Compare the student IDs lexicographically
        return id1.compareTo(id2);
    }
    
    private int compareByString(V o1, V o2) {
    if (o1 instanceof Student && o2 instanceof Student) {
        String id1 = ((Student) o1).getStudentID();
        String id2 = ((Student) o2).getStudentID();
        return id1.compareTo(id2);
    } else {
        throw new IllegalArgumentException("Objects must be instances of Student");
    }
}


    //get integer data from object
    private int getInt(V obj) {
        if (obj instanceof Student) {

            String id = ((Student) obj).getStudentID();

            String numericPart = id.substring(1);

            int intValue = Integer.parseInt(numericPart);

            return intValue;
        }

        return 0; // Handle other types accordingly
    }

}
