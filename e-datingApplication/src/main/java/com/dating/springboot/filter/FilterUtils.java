package com.dating.springboot.filter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.dating.springboot.dto.UserDto;
import com.dating.springboot.entity.User;

public class FilterUtils {

	public static List<UserDto> applyAgeFilter(List<UserDto> filteredUsers, ArrayList<?> values) {
		int startAge = (Integer) values.get(0);
		int endAge = (Integer) values.get(1);
		List<UserDto> ageFiltered = new ArrayList<UserDto>();
		filteredUsers.forEach(user -> {
			if (user.getAge() >= startAge && user.getAge() <= endAge) {
				ageFiltered.add(user);
			}
		});
		return ageFiltered;
	}

	public static List<UserDto> applyLocationFilter(List<UserDto> filteredUsers, ArrayList<?> values, boolean byCity) {
		String location = values.get(0).toString();
		List<UserDto> locationFiltered = new ArrayList<>();
		filteredUsers.forEach(user -> {
			String userLocation = byCity ? user.getCity() : user.getCountry();
			if (userLocation.equalsIgnoreCase(location)) {
				locationFiltered.add(user);
			}
		});
		return locationFiltered;
	}

	public static List<UserDto> applyGenderFilter(List<UserDto> filteredUsers, ArrayList<?> values) {
		String gender = values.get(0).toString();
		List<UserDto> genderFiltered = new ArrayList<>();
		filteredUsers.forEach(user -> {
			String userSex = user.getGender();
			if (userSex.equals(gender)) {
				genderFiltered.add(user);
			}
		});
		return genderFiltered;
	}

	
}