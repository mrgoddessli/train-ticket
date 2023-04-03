package model;

public class Ticket {
	
	private String user_name;
	private int train_id;
	private String train_start_place;
	private String train_final_place;
	private String train_start_time;
	private String train_final_time;
	private int chexiang_id;
	private int seat_NO;
	private String train_price;
	
	public String getUser_name() {
		return user_name;
	}
	public Ticket(String user_name, int train_id, int chexiang_id, int seat_NO) {
		super();
		this.user_name = user_name;
		this.train_id = train_id;
		this.chexiang_id = chexiang_id;
		this.seat_NO = seat_NO;
	}
	public int getTrain_id() {
		return train_id;
	}
	public void setTrain_id(int train_id) {
		this.train_id = train_id;
	}
	public String getTrain_start_place() {
		return train_start_place;
	}
	public void setTrain_start_place(String train_start_place) {
		this.train_start_place = train_start_place;
	}
	public String getTrain_final_place() {
		return train_final_place;
	}
	public void setTrain_final_place(String train_final_place) {
		this.train_final_place = train_final_place;
	}
	public String getTrain_start_time() {
		return train_start_time;
	}
	public void setTrain_start_time(String train_start_time) {
		this.train_start_time = train_start_time;
	}
	public String getTrain_final_time() {
		return train_final_time;
	}
	public void setTrain_final_time(String train_final_time) {
		this.train_final_time = train_final_time;
	}
	public int getChexiang_id() {
		return chexiang_id;
	}
	public void setChexiang_id(int chexiang_id) {
		this.chexiang_id = chexiang_id;
	}
	public int getSeat_NO() {
		return seat_NO;
	}
	public void setSeat_NO(int seat_NO) {
		this.seat_NO = seat_NO;
	}
	public String getTrain_price() {
		return train_price;
	}
	public void setTrain_price(String train_price) {
		this.train_price = train_price;
	}
	public void setUser_id(String user_name) {
		this.user_name = user_name;
	}
	
	
	

}
