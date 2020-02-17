import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PersonList {
	Map<String, Customer> customerList = Collections.synchronizedMap(new HashMap<String,Customer>());
	Map<String, Person> adminList = Collections.synchronizedMap(new HashMap<String,Person>());
	
	public void addCustomer(String name, Customer customer) {
		customerList.put(name, customer);
	}
	public Customer getCustomer(String name) {
		return customerList.get(name);
	}
	public void deleteCustomer(String name) {
		customerList.remove(name);
	}
	
	public void addAdmin(String name, Person person) {
		adminList.put(name, person);
	}
	public Person getAdmin(String name) {
		return adminList.get(name);
	}
	public void deleteAdmin(String name) {
		adminList.remove(name);
	}
	
}