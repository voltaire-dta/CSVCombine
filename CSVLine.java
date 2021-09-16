/*
 * @author Daniel Athey <daniel.t.athey@gmail.com>
 * Simple class which represents a single line in the Insurance Aggregator Inc.'s partner files. 
 */
public class CSVLine {

	private String providerName;
	private String campaignId;
	private Float cpac;
	private String redirect;
	private String phone;
	private String address;
	private String zip;

	/*
	 * no arg constructor
	 */
	public CSVLine() {
		providerName = "";
		campaignId = "";
		cpac = null;
		redirect = "";
		phone = "";
		address = "";
		zip = "";
	}

	/*
	 * all arg constructor
	 */
	public CSVLine(String pname, String cmpnId, Float cpac, String redir, String phone, String addr, String zip) {
		this.providerName = pname;
		this.campaignId = cmpnId;
		this.cpac = cpac;
		this.redirect = redir;
		this.phone = phone;
		this.address = addr;
		this.zip = zip;
	}

	/*
	 * helper method to determine if a line is eligible to be included in the merged
	 * file
	 * 
	 * @return boolean value which indicates eligibility
	 */
	public boolean isValidLine() {
		if (this.providerName.isBlank() || this.campaignId.isBlank() || this.cpac == null || this.redirect.isBlank()
				|| this.address.isBlank() || this.zip.isBlank()) {
			return false;
		}
		return true;
	}

	/*
	 * Getters & Setters for instance vars in the CSVLine class
	 */
	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(String campaignId) {
		this.campaignId = campaignId;
	}

	public Float getCpac() {
		return cpac;
	}

	public void setCpac(Float cpac) {
		this.cpac = cpac;
	}

	public String getRedirect() {
		return redirect;
	}

	public void setRedirect(String redirect) {
		this.redirect = redirect;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	/*
	 * helper method which prints the CSV-formatted lines the CombineCSV class for
	 * writing to the output file
	 * 
	 * @return String form of the CSV Line
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\"");
		sb.append(getProviderName());
		sb.append("\",\"");
		sb.append(getCampaignId());
		sb.append("\",\"");
		sb.append(getCpac());
		sb.append("\",\"");
		sb.append(getRedirect());
		sb.append("\",\"");
		sb.append(getPhone());
		sb.append("\",\"");
		sb.append(getAddress());
		sb.append("\",\"");
		sb.append(getZip());
		sb.append("\"");
		return sb.toString();
	}
}
