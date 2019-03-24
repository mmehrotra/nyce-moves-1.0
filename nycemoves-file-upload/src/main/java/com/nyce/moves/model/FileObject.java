package com.nyce.moves.model;

public class FileObject {

	private String finalS3Path;

	private String preSignedUrl;

	public String getFinalS3Path() {
		return finalS3Path;
	}

	public void setFinalS3Path(String finalS3Path) {
		this.finalS3Path = finalS3Path;
	}

	public String getPreSignedUrl() {
		return preSignedUrl;
	}

	public void setPreSignedUrl(String preSignedUrl) {
		this.preSignedUrl = preSignedUrl;
	}

}
