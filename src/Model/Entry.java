package Model;

public class Entry {
	
	private int id; 
	private String name; 
	private int age;
	
	public Entry() {
		
	}
	
	public Entry(int id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public String toString(){
		return this.name + " (age: " + this.age + ")";
	}
}
