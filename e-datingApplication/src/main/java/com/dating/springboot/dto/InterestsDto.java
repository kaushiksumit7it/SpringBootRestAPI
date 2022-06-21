package com.dating.springboot.dto;

import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class InterestsDto {

	private Long interestId;
	@NotNull
	private Long userId;
	@NotBlank
	@Length(min = 3, max = 100)
	private String likes;
	@NotBlank
	@Length(min = 3, max = 100)
	private String dislikes;
	private List<String> hobbies;
	@NotBlank
	private String profileUrl;
	@NotBlank
	@Length(min = 3, max = 100)
	private String about;

	public Long getInterestId() {
		return interestId;
	}

	public void setInterestId(Long interestId) {
		this.interestId = interestId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getLikes() {
		return likes;
	}

	public void setLikes(String likes) {
		this.likes = likes;
	}

	public String getDislikes() {
		return dislikes;
	}

	public void setDislikes(String dislikes) {
		this.dislikes = dislikes;
	}

	public List<String> getHobbies() {
		return hobbies;
	}

	public void setHobbies(List<String> hobbies) {
		this.hobbies = hobbies;
	}

	public String getProfileUrl() {
		return profileUrl;
	}

	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	@Override
	public int hashCode() {
		return Objects.hash(about, hobbies, interestId, likes, dislikes, profileUrl, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InterestsDto other = (InterestsDto) obj;
		return Objects.equals(about, other.about) && Objects.equals(hobbies, other.hobbies)
				&& Objects.equals(interestId, other.interestId) && Objects.equals(likes, other.likes)
				&& Objects.equals(dislikes, other.dislikes)
				&& Objects.equals(profileUrl, other.profileUrl) && Objects.equals(userId, other.userId);
	}

}
