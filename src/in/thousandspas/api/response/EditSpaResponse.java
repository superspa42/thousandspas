package in.thousandspas.api.response;

public class EditSpaResponse extends GenericSuccessFailureResponse{
	private int generatedSpaId = -1;

	public int getGeneratedSpaId() {
		return generatedSpaId;
	}

	public void setGeneratedSpaId(int generatedSpaId) {
		this.generatedSpaId = generatedSpaId;
	}
	
}