package application;

import javafx.beans.property.SimpleStringProperty;

public class EdgeInfo {
	
	 private final SimpleStringProperty firstNd;
	 private final SimpleStringProperty secondNd;
	 private final SimpleStringProperty capacity;
	 
	public String getFirstNd() {
		return firstNd.get();
	}
	public String getSecondNd() {
		return secondNd.get();
	}
	public String getCapacity() {
		return capacity.get();
	}
	
	  public void setFirstNd(String Nd) {
		  firstNd.set(Nd);
      }
	
	  public void setSecondNd(String Nd) {
		  secondNd.set(Nd);
      }
	
	  public void setCapacity(String Nd) {
		  capacity.set(Nd);
      }
	
	
	
	public EdgeInfo(String firstNd, String secondNd, String capacity) {
	
		this.firstNd = new SimpleStringProperty(firstNd);
		this.secondNd = new SimpleStringProperty(secondNd);
		this.capacity = new SimpleStringProperty(capacity);
	}
	 

	
	
	
	
	

}
