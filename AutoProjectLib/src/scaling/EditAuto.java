package scaling;

import adapter.*;

/**
 * Contains all asynchronous code required to handle CRUD operations for
 * {@link adapter.ProxyAuto}'s a1 {@link data.Auto} LinkedHashMap
 * 
 * @since 03-NOV-2018
 * @version 1
 */
public class EditAuto extends ProxyAuto {

	/**
	 * @param FilePath the full path to the txt file containing a textual
	 *                 representation of an {@link data.Auto} object, usable by
	 *                 {@link io.Parser}
	 * 
	 */
	public void BuildAutoSync(String FilePath) {
		CreateAuto a = new BuildAuto();
		synchronized (autoMap) {
			a.BuildAuto(FilePath);
		}
	}

	/**
	 * Takes a ModelName and prints it off to System.out.
	 * 
	 * @param ModelName Name of the {@link data.Auto} to be found.
	 */
	public void PrintAutoSync(String ModelName) {
		CreateAuto a = new BuildAuto();
		synchronized (autoMap) {
			a.PrintAuto(ModelName);
		}
	}

	/**
	 * @param ModelName     Name of the {@link data.Auto} object that will contain
	 *                      the {@link data.OptionSet}
	 * @param OptionSetName Name of the {@link data.OptionSet} to be updated.
	 * @param UpdatedName   The name to be set.
	 */
	public void UpdateOptionSetNameSync(String ModelName, String OptionSetName, String UpdatedName) {
		UpdateAuto a = new BuildAuto();
		synchronized (autoMap) {
			try {
				a.UpdateOptionSetName(ModelName, OptionSetName, UpdatedName);
			} catch (IllegalArgumentException e) {
				System.err.println("IllegalArgumentException caught in updateOptionSetNameSync");
			}
		}
	}

	/**
	 * @param ModelName    Name of the {@link data.Auto} object that will contain
	 *                     the {@link data.OptionSet}
	 * @param OptionName   Name of the {@link data.Option} to be updated.
	 * @param UpdatedPrice The price to set.
	 */
	public void UpdateOptionPriceSync(String ModelName, String OptionName, float UpdatedPrice) {
		UpdateAuto a = new BuildAuto();
		synchronized (autoMap) {
			try {
				a.UpdateOptionPrice(ModelName, OptionName, UpdatedPrice);
			} catch (IllegalArgumentException e) {
				System.err.println("IllegalArgumentException Caught in updateOptionPriceSync");
			}
		}
	}

	/**
	 * copies a {@link data.Option} to an {@link data.Auto}'s Selections map.
	 * 
	 * @param ModelName     Name of the {@link data.Auto} object that will be
	 *                      updated.
	 * @param OptionSetName Name of the {@link data.OptionSet} to be found.
	 * @param OptionName    Name of the {@link data.Option} object to be found.
	 */
	public void MakeSelectionSync(String ModelName, String OptionSetName, String OptionName) {
		BuildAuto b = new BuildAuto();
		synchronized (autoMap) {
			b.MakeSelection(ModelName, OptionSetName, OptionName);
		}
	}
}
