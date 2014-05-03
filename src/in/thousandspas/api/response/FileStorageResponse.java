package in.thousandspas.api.response;

public class FileStorageResponse extends GenericSuccessFailureResponse{
	private String imgURL = "";

	public String getImgURL() {
		return imgURL;
	}

	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}
}
