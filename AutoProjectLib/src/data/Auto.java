package data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class Auto implements Serializable {

	private static final long		serialVersionUID	= 1L;
	private ArrayList<OptionSet>	options				= new ArrayList<OptionSet>();
	private HashMap<String, Option>	selections			= new HashMap<String, Option>();
	private double					baseCost			= 0.0;
	private String					name				= "";
	private File					saveLocation		= null;
	private boolean					exists				= false;
	private String					make				= "";
	private int						UID					= 0;

	public ArrayList<OptionSet> getOptions(){
		return options;
	}

	public double calculateCost() {
		double total = baseCost;
		for(Option o : this.selections.values()) {
			total+=o.getCost();
		}
		return total;
	}
	
	public Auto (OptionSet[] Options, double BaseCost, String Name, int UUID) {
		this.options.addAll(Arrays.asList(Options));
		this.baseCost = BaseCost;
		this.name = Name;
		this.exists = true;
		this.setUUID(UUID);
	}

	public Auto (ArrayList<OptionSet> Options, double BaseCost, String name) {
		this.options = Options;
		this.baseCost = BaseCost;
		this.name = name;
		this.exists = true;
		Random r = new Random();
		this.UID = r.nextInt();
	}

	public Auto (Auto A) {
		this.options = A.getOptions();
		this.baseCost = A.getBaseCost();
		this.name = A.getName();
		this.exists = true;
	}

	public Auto () {
	}

	public boolean exists(){
		return this.exists;
	}

	public Auto get(){
		return this;
	}

	public File getStorageLocation(){
		return this.saveLocation;
	}

	public void LogVehicle(){
		System.out.printf("%s, With a Base Cost of: %7.2f \n", this.name, this.baseCost);
		for (OptionSet op : this.options) {
			System.out.printf("OptionSet titled %s ", op.getName());
			System.out.printf("with Options %s \n", op.formatOptionSetForFileOutput()); // logs the output from a helper
																						// method
			// written to ease the creation of new
			// input text files in future.
		}
	}

	public void LogVehicle(FileWriter o){
		try {
			o.write(this.name + "With a Base Cost of: " + this.baseCost);
			for (OptionSet opset : this.options) {
				o.write(opset.getName() + '\n' + opset.formatOptionSetForFileOutput() + '\n');
			}
		}
		catch (IOException ioe) {
			System.err.printf("Failed to log vehicle to file, exception %s was thrown.", ioe.getMessage());
		}
	}

	public OptionSet getOptionSetByName(String name) throws IllegalArgumentException{
		synchronized (this.options) {
			for (OptionSet o : this.options) {
				if (o.getName().equals(name)) { return o; }
			}
			throw new IllegalArgumentException();
		}
	}

	public Option getOptionInSetByName(String OptionSetName, String OptionName) throws IllegalArgumentException{
		synchronized (this.options) {
			for (OptionSet o : this.options) {
				if (o.getName().equals(OptionSetName)) {
					if (o.hasNamedOption(OptionName)) { return o.findOptionByName(OptionName); }
				}
			}
		}
		throw new IllegalArgumentException();
	}

	public synchronized void setOptions(data.OptionSet[] options){
		this.options.addAll(Arrays.asList(options));
	}

	public synchronized void setOptions(ArrayList<OptionSet> options){
		this.options = options;
	}

	public double getBaseCost(){
		return baseCost;
	}

	public synchronized void setBaseCost(double baseCost){
		this.baseCost = baseCost;
	}

	public String getName(){
		return name;
	}

	public synchronized void setName(String name){
		this.name = name;
	}

	public synchronized void setOptionPrices(String name, float updatedPrice){
		for (OptionSet s : this.options) {
			try {
				s.SetOptionByName(name, updatedPrice);
			}
			catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
		}
	}

	public synchronized void setOptionSetName(String existing, String update){
		for (OptionSet os : this.options) {
			if (os.getName().equals(existing)) {
				os.setName(update);
			}
		}
	}

	public void updateOptionNames(String existing, String updated){
		for (OptionSet o : this.options) {
			ArrayList<Option> op = o.getOptions();
			for (Option opt : op) {
				if (opt.getTitle().equals(existing)) {
					opt.setTitle(updated);
				}
			}
			o.setOptions(op);
		}
	}

	@Override
	public String toString(){
		StringBuffer c = new StringBuffer();
		c.append(this.name + "\n With a base cost of " + this.baseCost + "\n");
		for (OptionSet os : this.options) {
			c.append(os.formatOptionSetForFileOutput() + "\n");
		}
		return c.toString();
	}

	public synchronized void setMake(String make){
		this.make = make;
	}

	public String getMake(){
		return this.make;
	}

	public int getUUID(){
		return UID;
	}

	public synchronized void setUUID(int uUID){
		UID = uUID;
	}

	public HashMap<String, Option> getSelections(){
		return selections;
	}

	public synchronized void setSelections(HashMap<String, Option> selections){
		this.selections = selections;
	}

	/**
	 * Adds the parameters to this Auto's Selections map.
	 * 
	 * @param optionSet
	 *            OptionSet that the Selection will be associated with.
	 * @param option
	 *            Option to be associated.
	 */
	public synchronized void addSelection(OptionSet optionSet, Option option){
		synchronized (this.selections) {
			this.selections.put(optionSet.getName(), option);
		}
	}
}
