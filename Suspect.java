public class Suspect {
	private int AFM;
	private String first_name, last_name;
	private double savings, taxed_income;
	
	//Constructor
	Suspect(int AFM, String first_name, String last_name, double savings, double taxed_income){
		this.AFM = AFM;
		this.first_name = first_name;
		this.last_name = last_name;
		this.savings = savings;
		this.taxed_income = taxed_income;
	}
	
	//Getters
	public int key() { return AFM; }
	
	public String getFirstName(){ return first_name; }
	
	public String getLastName() { return last_name; }
	
	public double getSavings() { return savings; }
	
	public double getTaxedIncome() { return taxed_income; }
	
	//toString
	public String toString(){
		return "AFM: " + AFM + "\n" +
			   "First Name: " + first_name + "\n" +
			   "Last Name: " + last_name + "\n" +
			   "Savings: " + savings + "\n" +
			   "Taxed Income: " + taxed_income + "\n";
	}
}
