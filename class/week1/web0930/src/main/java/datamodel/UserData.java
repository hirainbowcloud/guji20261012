package datamodel;

import java.util.List;

/**
 * 測試用使用者資料來源
 */
public class UserData {

	/**
	 * 取得可使用的名字清單
	 */
	public static List<String> userNames() {
		return List.of("mary", "john", "george", "alan", "danny");
	}

}
