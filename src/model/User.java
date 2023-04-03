package model;

/**
 * 用户实体
 * @author liyunfei
 *
 */
public class User {
	
	private int id; //编号
	private String userName;
	private String password;
	
	private String user_age;
	private String user_sex;
	private String user_phone;
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public User(String userName) {
		super();
		this.userName = userName;
	}




	public User(String userName, String user_age, String user_sex, String user_phone) {
		super();
		this.userName = userName;
		this.user_age = user_age;
		this.user_sex = user_sex;
		this.user_phone = user_phone;
	}




	public String getUser_age() {
		return user_age;
	}




	public void setUser_age(String user_age) {
		this.user_age = user_age;
	}




	public String getUser_sex() {
		return user_sex;
	}




	public void setUser_sex(String user_sex) {
		this.user_sex = user_sex;
	}




	public String getUser_phone() {
		return user_phone;
	}




	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}




	public User(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}




	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
