package constants;

public enum ErrorMessages {

	INVALID_PASSWORD("Received invalid password for supplied account name."),
	USER_NOT_FOUND("Received account name does not correspond to a registered user account."),
	REGISTRATION_NOT_FOUND("Received activation key does not correspond to a valid registration.");

	private final String details;

	ErrorMessages(String details) {
		this.details = details;
	}

	public String getDetails() {
		return details;
	}

}