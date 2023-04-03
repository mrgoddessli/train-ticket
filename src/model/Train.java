package model;

/**
 * 车次类别实体
 * @author liyunfei
 *
 */
public class Train {

	private int train_id;
	private String train_start_place;
	private String train_final_place;
	
	private String train_start_time;
	private String train_final_time;
	
	private int train_chexiang;
	private String train_price;
	
	
	public Train() {
		super();
		// TODO Auto-generated constructor stub
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



	public int getTrain_chexiang() {
		return train_chexiang;
	}



	public void setTrain_chexiang(int train_chexiang) {
		this.train_chexiang = train_chexiang;
	}



	public String getTrain_price() {
		return train_price;
	}



	public void setTrain_price(String train_price) {
		this.train_price = train_price;
	}

	
	
	
	public Train(String train_start_place, String train_final_place)
	{
	    super();
		this.train_start_place = train_start_place;
		this.train_final_place = train_final_place;
		
		this.train_chexiang=2;
	}



	public Train(int train_id, String train_start_place, String train_final_place) {
		super();
		this.train_id = train_id;
		this.train_start_place = train_start_place;
		this.train_final_place = train_final_place;
		
		this.train_chexiang=2;
	}

	public Train(int train_id, String train_start_place, String train_final_place,String train_start_time,String train_final_time,String train_price) {
		super();
		this.train_id = train_id;
		this.train_start_place = train_start_place;
		this.train_final_place = train_final_place;
		this.train_start_time= train_start_time;
		this.train_final_time=train_final_time;
		this.train_price=train_price;
		
		this.train_chexiang=2;
	}
	public Train(String train_start_place, String train_final_place,String train_start_time,String train_final_time,String train_price) {
		super();
		this.train_start_place = train_start_place;
		this.train_final_place = train_final_place;
		this.train_start_time= train_start_time;
		this.train_final_time=train_final_time;
		this.train_price=train_price;
		
		this.train_chexiang=2;
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
	
	
}
