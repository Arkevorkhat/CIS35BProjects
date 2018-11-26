package adapter;

public interface UpdateAuto {
	public void UpdateOptionSetName(String ModelName, String OptionSetName, String UpdatedName) throws IllegalArgumentException;
	public void UpdateOptionPrice(String ModelName, String OptionName, float UpdatedPrice) throws IllegalArgumentException;
	public void UpdateOptionName(String ModelName, String OptionName, String UpdatedName) throws IllegalArgumentException;
}