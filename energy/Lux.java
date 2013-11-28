package opticraft.energy;

public class Lux {
	double value;
	
	public Lux(){
		this.value = 0.0;
	}
	
	public Lux(double value){
		this.value = value;
	}
	
	public Lux(int value){
		this.value = (double)value;
	}
	
	public String toString(){
		return String.valueOf(value);
	}
	
	public boolean decreaseBy(double value, double min){
		if((this.value - value) < 0){
			return false;
		} else
			this.value = this.value - value;
			return true;
	}
	
	public boolean increaseBy(double value, double max){
		if((this.value + value) >= Integer.MAX_VALUE || this.value >= max){
			return false;
		} else
			this.value = this.value + value;
			return true;
	}
	
	public double get(){
		return this.value;
	}
}
