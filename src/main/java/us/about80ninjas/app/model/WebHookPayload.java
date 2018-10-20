package us.about80ninjas.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonIgnoreProperties(ignoreUnknown=true)
public class WebHookPayload {

	@JsonDeserialize(as=Sender.class)
	@JsonProperty("sender")
	Sender sender;

	public Sender getSender() {
		return sender;
	}

	public void setSender(Sender sender) {
		this.sender = sender;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sender == null) ? 0 : sender.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WebHookPayload other = (WebHookPayload) obj;
		if (sender == null) {
			if (other.sender != null)
				return false;
		} else if (!sender.equals(other.sender))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "WebHookPayload [sender=" + sender + "]";
	}

	@JsonIgnoreProperties(ignoreUnknown=true)
	public class Sender {
		@JsonProperty("login")
		String login;

		public String getLogin() {
			return login;
		}

		public void setLogin(String login) {
			this.login = login;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((login == null) ? 0 : login.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Sender other = (Sender) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (login == null) {
				if (other.login != null)
					return false;
			} else if (!login.equals(other.login))
				return false;
			return true;
		}

		private WebHookPayload getOuterType() {
			return WebHookPayload.this;
		}

		@Override
		public String toString() {
			return "Sender [login=" + login + "]";
		}

	}

}
