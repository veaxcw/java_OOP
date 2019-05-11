package com.chengw.ioStream.byteStream.object.serialization;

import com.chengw.ioStream.byteStream.object.pojo.Employee;
import com.chengw.ioStream.byteStream.object.pojo.Manager;

import java.io.*;

/**对象流测试**/
/**对象必须继承序列化接口**/
public class ObjectStream {

    public static void main(String[] args) {
        Employee harry = new Employee("Harry","5000");

        Manager carl = new Manager("Carl","80000");
        carl.setSecretary(harry);

        Manager tony = new Manager("Tony","1000000");
        tony.setSecretary(harry);

        Employee[] staff = new Employee[3];

        staff[0] = harry;
        staff[1] = carl;
        staff[2] = tony;

        File employee = new File("F:/employee.dat");
        if(!employee.exists()) {
            try {
                employee.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        /**将对象写入数据**/
        try ( ObjectOutput out = new ObjectOutputStream(new FileOutputStream(employee))){
           out.writeObject(staff);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(employee))) {
            Employee[] employees = (Employee[]) in.readObject();
            employees[0].setSalary("123456");
            for(Employee e:employees){
                System.out.println(e.toString());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

}
