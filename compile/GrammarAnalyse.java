package compilePrinciple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class GrammarAnalyse {

	// 假定用 $ 符号代表空 ε
	private static String[] arr = { "A->[B", "B->X]|BA", "X->Xa|Xb|a|b" };

	public static void main(String[] args) {
		System.out.println("the raw rules:");
		for (String s : arr) {
			System.out.println(s);
		}
		HashMap<Character, ArrayList<String>> map = removeRecur(arr);
		System.out.println("the map after removing Recur:");
		System.out.println(map);
		String[] arrC = mapToString(map);
		System.out.println("the rules after removing recur:");
		for (String s : arrC) {
			System.out.println(s);
		}
		System.out.println("the set of firsts:");
		System.out.println(getFirsts(arrC));
	}

	// 拆分规则
	public static HashMap<Character, ArrayList<String>> splitRules(String[] arr) {
		HashMap<Character, ArrayList<String>> mapRules = new HashMap<>();
		for (String item : arr) {
			String tmp = item.substring(3);
			ArrayList<String> list = new ArrayList<>();
			list.addAll(Arrays.asList(tmp.split("\\|")));
			// 如果map中存在键，则取出值添加进list中
			if (mapRules.containsKey(item.charAt(0))) {
				list.addAll(mapRules.get(item.charAt(0)));
			}
			mapRules.put(item.charAt(0), list);
		}
		return mapRules;
	}

	// 获取非终结符
	public static HashSet<Character> getNelements(String[] arr) {
		HashSet<Character> setLeft = new HashSet<Character>();
		for (String item : arr) {
			setLeft.add(item.charAt(0));
		}
		return setLeft;

	}

	// 获取终结符
	public static HashSet<Character> getTelements(String[] arr) {
		HashSet<Character> left = new HashSet<Character>();
		left = getNelements(arr);
		HashSet<Character> setRight = new HashSet<Character>();
		for (String item : arr) {
			for (int i = 3; i < item.length(); i++) {
				if (!left.contains(item.charAt(i)) && item.charAt(i) != '|') {
					setRight.add(item.charAt(i));
				}
			}
		}
		return setRight;
	}

	// 判断是不是终结符
	public static boolean isTelement(String[] arr, char ch) {
		if (getTelements(arr).contains(ch)) {
			return true;
		}
		return false;
	}

	// 消除左递归
	public static HashMap<Character, ArrayList<String>> removeRecur(String[] arr) {
		char[] toChange = { 'C', 'D', 'M', 'N', 'R', 'H', 'J', 'K' };
		int k = 0;
		// 首先实现消除直接左递归
		HashMap<Character, ArrayList<String>> mapRules = splitRules(arr);
		HashMap<Character, ArrayList<String>> mapRulesBackup = new HashMap<>();
		for (Character ch : mapRules.keySet()) {
			// 该规则存在左递归
			if (isLeftRecur(ch, mapRules.get(ch))) {
				ArrayList<String> list = new ArrayList<String>();
				ArrayList<String> listR = new ArrayList<String>();
				list.add("$");
				for (String s : mapRules.get(ch)) {
					if (s.charAt(0) == ch) {
						list.add(s.substring(1) + toChange[k]);
					} else {
						listR.add(s + toChange[k]);
					}
				}
				mapRulesBackup.put(ch, listR);
				mapRulesBackup.put(toChange[k], list);
				k++;
			} else {
				mapRulesBackup.put(ch, mapRules.get(ch));
			}
		}
		return mapRulesBackup;
	}

	// 将规则从map类型转化为String[]
	public static String[] mapToString(HashMap<Character, ArrayList<String>> map) {
		String[] arr = new String[map.size()];
		int k = 0;
		for (Character ch : map.keySet()) {
			String tmp = ch + "->";
			for (int i = 0; i < map.get(ch).size(); i++) {
				if (i == map.get(ch).size() - 1) {
					tmp += map.get(ch).get(i);
				} else {
					tmp += map.get(ch).get(i) + "|";
				}
			}
			arr[k++] = tmp;
		}
		return arr;
	}

	// 判断一条规则是否存在左递归，存在返回true
	public static boolean isLeftRecur(char ch, ArrayList<String> list) {
		for (String s : list) {
			if (s.charAt(0) == ch) {
				return true;
			}
		}
		return false;
	}

	// 初始化First集
	public static HashMap<Character, HashSet<Character>> initFirsts(String[] arr) {
		HashMap<Character, ArrayList<String>> mapRules = new HashMap<>();
		mapRules = splitRules(arr);
		HashMap<Character, HashSet<Character>> mapFirsts = new HashMap<>();
		for (Character ch : mapRules.keySet()) {
			HashSet<Character> setFirsts = new HashSet<>();
			for (String s : mapRules.get(ch)) {
				// 如果是终结符
				if (isTelement(arr, s.charAt(0))) {
					setFirsts.add(s.charAt(0));
				}
			}
			mapFirsts.put(ch, setFirsts);
		}
		return mapFirsts;
	}

	// 获取First集
	public static HashMap<Character, HashSet<Character>> getFirsts(String[] arr) {
		HashMap<Character, HashSet<Character>> firsts = initFirsts(arr);
		HashMap<Character, ArrayList<String>> mapRules = new HashMap<>();
		mapRules = splitRules(arr);
		for (Character ch : firsts.keySet()) {
			HashSet<Character> setFirsts = new HashSet<>();
			// 将初始化的firsts集加入集合
			setFirsts.addAll(firsts.get(ch));
			for (String s : mapRules.get(ch)) {
				if (!isTelement(arr, s.charAt(0))) {
					int i = 0;
					// 非终结符且含有空串 $
					while (!isTelement(arr, s.charAt(i)) && i < s.length() && firsts.get(s.charAt(i)).contains('$')) {
						setFirsts.addAll(firsts.get(s.charAt(i)));
						i++;
					}
					if (isTelement(arr, s.charAt(i))) {
						setFirsts.add(s.charAt(i));
					} else {
						setFirsts.addAll(firsts.get(s.charAt(i)));
					}
				}
			}
			firsts.put(ch, setFirsts);
		}
		return firsts;
	}

}
