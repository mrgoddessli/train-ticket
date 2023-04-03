package model;

public class Seat {
	
	private int seat_id;
	private int train_id;
	private int chexiang_id;
	private int yi_shouchu;
	private int seat_NO;
	
	public Seat(int seat_id, int train_id, int chexiang_id, int yi_shouchu, int seat_NO) {
		super();
		this.seat_id = seat_id;
		this.train_id = train_id;
		this.chexiang_id = chexiang_id;
		this.yi_shouchu = yi_shouchu;
		this.seat_NO = seat_NO;
	}
	
	

	public Seat(int train_id,  int seat_NO, int chexiang_id) {
		super();
		this.train_id = train_id;
		this.chexiang_id = chexiang_id;
		this.seat_NO = seat_NO;
	}



	public Seat(int train_id) {
		super();
		this.train_id = train_id;
	}



	public int getSeat_id() {
		return seat_id;
	}

	public void setSeat_id(int seat_id) {
		this.seat_id = seat_id;
	}

	public int getTrain_id() {
		return train_id;
	}

	public void setTrain_id(int train_id) {
		this.train_id = train_id;
	}

	public int getChexiang_id() {
		return chexiang_id;
	}

	public void setChexiang_id(int chexiang_id) {
		this.chexiang_id = chexiang_id;
	}

	public int getYi_shouchu() {
		return yi_shouchu;
	}

	public void setYi_shouchu(int yi_shouchu) {
		this.yi_shouchu = yi_shouchu;
	}

	public int getSeat_NO() {
		return seat_NO;
	}

	public void setSeat_NO(int seat_NO) {
		this.seat_NO = seat_NO;
	}
	
	
	

}
