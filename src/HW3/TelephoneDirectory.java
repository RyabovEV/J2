package HW3;

import java.util.*;

public class TelephoneDirectory {
    protected Set<String> phone;
    final protected HashMap<String, Set> phoneDir;

    public TelephoneDirectory(HashMap<String, Set> phoneDir) {
        this.phoneDir = phoneDir;
    }

    void addPhone(String lastName, String phone) {
        if (!phoneDir.containsKey(lastName)) {
            this.phone = new HashSet<>();
        }
        this.phone.add(phone);
        phoneDir.put(lastName, this.phone);
    }

    void getPhone(String lastName) {
        System.out.println(lastName + " " + phoneDir.get(lastName));
    }


}
