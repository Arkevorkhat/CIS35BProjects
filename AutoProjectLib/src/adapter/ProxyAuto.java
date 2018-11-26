package adapter;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Random;

import data.Auto;
import data.Option;
import data.OptionSet;

public abstract class ProxyAuto {

	protected static LinkedHashMap<Integer, Auto> autoMap = new LinkedHashMap<>();

	/**
	 * non thread-safe update method that updates the name of some number of
	 * optionsets.
	 * 
	 * @category U - Change
	 * @param ModelName     Name of the AutoModel object to work on.
	 * @param OptionSetName OptionSet to Update
	 * @param UpdatedName   Name to Set
	 * @throws AutoException If the AutoModel object is not found.
	 */
	public void UpdateOptionSetName(String ModelName, String OptionSetName, String UpdatedName)
			throws IllegalArgumentException {
		for (Auto a : autoMap.values()) {
			if (a.getName().equals(ModelName)) {
				a.setOptionSetName(OptionSetName, UpdatedName);
				return;
			}
		}
		throw new IllegalArgumentException();
	}

	/**
	 * not thread-safe.
	 * 
	 * @category U - Change
	 * @param ModelName    AutoModel Object to change
	 * @param OptionName   Option to change.
	 * @param UpdatedPrice final Option Price
	 * @throws IllegalArgumentException if the AutoModel is not found.
	 */
	public void UpdateOptionPrice(String ModelName, String OptionName, float UpdatedPrice)
			throws IllegalArgumentException {
		for (Auto a : autoMap.values()) {
			if (a.getName().equals(ModelName)) {
			}
		}
	}

	/**
	 * Not thread-safe.
	 * 
	 * @param ModelName
	 * @param OptionSetName
	 * @param OptionName
	 */
	public void MakeSelection(String ModelName, String OptionSetName, String OptionName) {
		for (Auto a : autoMap.values()) {
			if (a.getName().equals(ModelName)) {
				try {
					OptionSet ops = a.getOptionSetByName(OptionSetName);
					Option op = a.getOptionInSetByName(OptionSetName, OptionName);
					a.addSelection(ops, op);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * not thread-safe
	 * 
	 * @category C - Input
	 * @param FilePath The full path to the .txt file containing a structured
	 *                 representation of the Auto to be created. Must fit the format
	 *                 shown in src/driver/Ford ZTW.txt
	 */
	public void BuildAuto(String FilePath) {
	}

	/**
	 * not thread-safe
	 * 
	 * @category R - User Output
	 * @param ModelName The ModelName of the Auto object to be printed will only
	 *                  show the first instance of that named auto in the map.
	 * @throws AutoException if the ModelName does not exist.
	 */
	public void PrintAuto(String ModelName) throws IllegalArgumentException {
		boolean success = false;
		for (Auto a : autoMap.values()) {
			if (a.getName().equals(ModelName)) {
				success = true;
				System.out.println(a.toString());
			}
		}
		if (!success)
			throw new IllegalArgumentException();
	}

	public void UpdateOptionName(String ModelName, String OptionName, String UpdatedName) throws IllegalArgumentException {
		for (Auto a : autoMap.values()) {
			if (a.getName().equals(ModelName)) {
			}
		}
	}
}