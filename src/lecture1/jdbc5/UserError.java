package lecture1.jdbc5;

import java.util.Arrays;

public class UserError implements MyError{

	public static final int ALL = -1;
	public static final int USERID = 0;
	public static final int NAME = 1;
	public static final int PASSWORD = 2;
	public static final int EMAIL = 3;
	public static final int DEPARTMENTID = 4;
	public static final int USERTYPE = 5;

	private String errorMessage;
	private boolean isError;
	User user;
	int[] check;
	
	public UserError(User user, int... check) {
		this.user = user;
		this.check = check;
	}

	private boolean isError(User user, int check) {
		switch(check) {
		case ALL:
		case USERID:
			if(this.isCorrectUserid(user))
				return true;
		case NAME:
			if(this.isCorrectName(user))
				return true;
		case PASSWORD:
			if(this.isCorrectPassword(user))
				return true;
		case EMAIL:
			if(this.isCorrectEmail(user))
				return true;
		case DEPARTMENTID:
			if(this.isCorrectDepartmentId(user))
				return true;
		case USERTYPE:
			if(this.isCorrectUserType(user))
				return true;
		}

		return false;
	}

	private boolean isCorrectUserid(User user) {
		if (user.getUserid() == null || user.getUserid().length() == 0) {
			errorMessage = "유저 아이디를 입력하세요";
			return true;
		}

		return false;
	}

	private boolean isCorrectName(User user) {
		if (user.getName() == null || user.getName().length() == 0) {
			errorMessage = "이름을 입력하세요";
			return true;
		}

		return false;
	}

	private boolean isCorrectPassword(User user) {
		if (user.getPassword() == null || user.getPassword().length() == 0) {
			errorMessage = "비밀번호를 입력하세요";
			return true;
		}

		return false;
	}

	private boolean isCorrectEmail(User user) {
		if (user.getEmail() == null || user.getEmail().length() == 0) {
			errorMessage = "이메일을 입력하세요";
			return true;
		}

		return false;
	}

	private boolean isCorrectDepartmentId(User user) {
		if (user.getDepartmentId() == -1) {
			errorMessage = "학과를 선택하세요";
			return true;
		}

		return false;
	}

	private boolean isCorrectUserType(User user) {
		if (user.getUserType() == null || user.getUserType().length() == 0) {
			errorMessage = "사용자 타입을 입력하세요.";
			return true;
		}

		return false;
	}

	@Override
	public String getErrorMessage() {
		if(isError()) return errorMessage;
		return "";
	}

	@Override
	public boolean isError() {
		Arrays.sort(check);
		
		for(int i = 0; i < check.length; i++) {
			isError = isError(user, check[i]);
			if(isError)
				break;
		}
		
		return isError;
	}
}
