package opticraft.energy;

public class Lux {
	int value;
	
	public Lux(){
		this.value = 0;
	}
	
	public Lux(int value){
		this.value = value;
	}
	
	public String toString(){
		return String.valueOf(value);
	}
	
	public boolean decreaseBy(int value, int min){
		if((this.value - value) < 0){
			return false;
		} else
			this.value = this.value - value;
			return true;
	}
	
	public boolean increaseBy(int value, double max){
		if((this.value + value) >= Integer.MAX_VALUE || this.value >= max){
			return false;
		} else
			this.value = this.value + value;
			return true;
	}
	
	public boolean canAcceptValue(int value, int max){
		if(this.value + value > max){
			return false;
		} else
			return true;
	}
	
	public double get(){
		return this.value;
	}
}
