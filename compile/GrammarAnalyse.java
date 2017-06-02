package compilePrinciple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public class GrammarAnalyse {

	// 假定用 $ 符号代表空 ε
	private static String[] arrRaw = { "E->E+T|T", "T->T*F|F", "F->(E)|i" };

	public static void main(String[] args) {
		System.out.println("the raw rules:");
		// 打印原始文法
		for (String s : arrRaw) {
			System.out.println(s);
		}
		System.out.println();
		HashMap<Character, ArrayList<String>> map = removeRecur(arrRaw);
		System.out.println("the map after removing Recur:");
		System.out.println(map);
		System.out.println();
		String[] arrC = mapToString(map);
		System.out.println("the rules after removing recur:");
		for (String s : arrC) {
			System.out.println(s);
		}
		System.out.println();
		System.out.println("the map of firsts:");
		System.out.println(getFirsts(arrC));
		System.out.println();
		System.out.println("the map of follows:");
		System.out.println(getFollows(arrC));
		System.out.println();
		System.out.println("the analyseTable of LL(1):");
		HashMap<Character, HashMap<Character, String>> mapTable = analyseTable(arrC);
		printAnalyseTable(mapTable);
		System.out.println();
		// the string to analyze
		String test = "i+i*i";
		System.out.println("the analyse of string " + test);
		analyseString(arrC, test);
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
		// 给出终结符的firsts集
		for (Character ch : getTelements(arr)) {
			HashSet<Character> tmp = new HashSet<>();
			tmp.add(ch);
			firsts.put(ch, tmp);
		}
		return firsts;
	}

	public static HashMap<Character, HashSet<Character>> getFollows(String[] arr) {
		HashMap<Character, HashSet<Character>> follows = new HashMap<>();
		HashMap<Character, ArrayList<String>> mapRules = new HashMap<>();
		HashMap<Character, HashSet<Character>> firsts = new HashMap<>();
		HashSet<String> set = new HashSet<>();// set 保存规则右部
		// 获取各项规则转化为 map 类型
		mapRules = splitRules(arr);
		firsts = getFirsts(arr);
		ArrayList<String> ruleList = new ArrayList<>();
		for (Character ch : mapRules.keySet()) {
			ruleList.addAll(mapRules.get(ch));
		}
		for (String s : ruleList) {
			// 判断字符串是否包含相同内容
			if (!s.equals("$")) {
				set.add(s);
			}
		}
		// System.out.println(set);
		for (Character ch : mapRules.keySet()) {
			HashSet<Character> setFollows = new HashSet<>();
			// 求Follows集的规则1. 如果是初始字符，则将 # 加入Follows集
			if (ch == arrRaw[0].charAt(0)) {
				setFollows.add('#');
			}
			// 求Follows集的规则2
			for (String s : set) {
				int chIndex = s.indexOf(ch);
				if (chIndex >= 0 && chIndex <= s.length() - 2 && s.charAt(chIndex + 1) != '$') {
					// 下一个字符是终结符
					if (isTelement(arr, s.charAt(chIndex + 1))) {
						setFollows.add(s.charAt(chIndex + 1));
					} else {
						setFollows.addAll(firsts.get(s.charAt(chIndex + 1)));
					}
				}
			}
			if (follows.containsKey(ch)) {
				setFollows.addAll(follows.get(ch));
			}
			// 移除空串 $
			setFollows.remove('$');
			follows.put(ch, setFollows);
		}
		// System.out.println(follows);
		int i = 0;// 设置反复执行规则的次数,只能使用5次做一个大概的估算
		while (i < 5) {
			for (Character ch : mapRules.keySet()) {
				HashSet<Character> setFollows = new HashSet<>();
				// 尝试规则3
				for (String s : mapRules.get(ch)) {
					// 满足A->aB形式
					if (!isTelement(arr, s.charAt(s.length() - 1))) {
						setFollows.addAll(follows.get(ch));
						if (follows.containsKey(s.charAt(s.length() - 1))) {
							setFollows.addAll(follows.get(s.charAt(s.length() - 1)));
						}
						follows.put(s.charAt(s.length() - 1), setFollows);
					}
				}
			}
			// System.out.println(follows);
			for (Character ch : mapRules.keySet()) {
				HashSet<Character> setFollows = new HashSet<>();
				for (String s : mapRules.get(ch)) {
					// 满足A->aBb形式 且 经过一定步骤 b->$
					if (s.length() >= 2 && !isTelement(arr, s.charAt(s.length() - 2))) {
						// 规则中的 $ 是字符串 ,不是字符
						if (!isTelement(arr, s.charAt(s.length() - 1))
								&& mapRules.get(s.charAt(s.length() - 1)).contains("$")) {

							setFollows.addAll(follows.get(ch));
							if (follows.containsKey(s.charAt(s.length() - 2))) {
								setFollows.addAll(follows.get(s.charAt(s.length() - 2)));
							}
							follows.put(s.charAt(s.length() - 2), setFollows);
						}
					}
				}
			}
			i++;
		}
		return follows;
	}

	// 求LL(1)分析表
	public static HashMap<Character, HashMap<Character, String>> analyseTable(String[] arr) {
		HashMap<Character, HashSet<Character>> firsts = new HashMap<>();
		HashMap<Character, HashSet<Character>> follows = new HashMap<>();
		HashSet<Character> Telem = new HashSet<>();
		HashSet<Character> Nelem = new HashSet<>();
		HashMap<Character, ArrayList<String>> mapRules = new HashMap<>();
		HashMap<Character, HashMap<Character, String>> mapTable = new HashMap<>();

		firsts = getFirsts(arr);
		follows = getFollows(arr);
		Telem = getTelements(arr);
		Nelem = getNelements(arr);
		mapRules = splitRules(arr);

		Telem.add('#');
		Telem.remove('$');
		// System.out.println(Telem);
		// System.out.println(Nelem);
		for (Character N : Nelem) {
			for (Character T : Telem) {
				// System.out.println(T);
				HashMap<Character, String> tmp = new HashMap<>();
				for (String s : mapRules.get(N)) {
					// 规则中不存在空串
					if (!firsts.get(s.charAt(0)).contains('$')) {
						if (firsts.get(s.charAt(0)).contains(T)) {
							tmp.put(T, s);
							if (mapTable.keySet().contains(N)) {
								tmp.putAll(mapTable.get(N));
							}
							mapTable.put(N, tmp);
						}
					}
					// 如果存在空串
					if (firsts.get(s.charAt(0)).contains('$')) {
						// 将所有follows集中的终结符,对应的分析表中的位置填入规则
						for (Character follow : follows.get(N)) {
							tmp.put(follow, s);
							if (mapTable.keySet().contains(N)) {
								tmp.putAll(mapTable.get(N));
							}
							mapTable.put(N, tmp);
						}
					}
				}
			}
		}
		return mapTable;
	}

	// 打印LL(1)分析表
	public static void printAnalyseTable(HashMap<Character, HashMap<Character, String>> mapTable) {
		for (Character N : mapTable.keySet()) {
			System.out.printf("%-10s", N);
			for (Character T : mapTable.get(N).keySet()) {
				System.out.printf("%-15s", T + ":  " + N + "->" + mapTable.get(N).get(T) + "    ");
			}
			System.out.println();
		}
	}

	// 分析输入的字符串
	public static void analyseString(String[] arr, String string) {
		HashMap<Character, HashMap<Character, String>> mapTable = analyseTable(arr);
		mapTable = analyseTable(arr);
		// 输入串
		Stack<Character> stackString = new Stack<Character>();
		// 分析栈
		Stack<Character> stackAnalyse = new Stack<Character>();
		stackAnalyse.push('#');
		stackAnalyse.push(arrRaw[0].charAt(0));
		stackString.push('#');
		for (int i = string.length() - 1; i >= 0; i--) {
			stackString.push(string.charAt(i));
		}
		boolean flag = true;
		while (true) {
			if (stackAnalyse.peek() == '$') {
				System.out.printf("%-50s", stackAnalyse);
				System.out.printf("%-50s", StackReverse(stackString));
				System.out.println();
				stackAnalyse.pop();
				continue;
			}
			// 匹配成功退出
			if (stackAnalyse.peek() == '#' && stackString.peek() == '#') {
				System.out.printf("%-50s", stackAnalyse);
				System.out.printf("%-50s", StackReverse(stackString));
				System.out.println();
				break;
			}
			if (stackAnalyse.peek() == stackString.peek()) {
				System.out.printf("%-50s", stackAnalyse);
				System.out.printf("%-50s", StackReverse(stackString));
				System.out.println();
				stackAnalyse.pop();
				stackString.pop();
				continue;
			}
			// 匹配失败退出
			if (!mapTable.get(stackAnalyse.peek()).keySet().contains(stackString.peek())) {
				flag = false;
				break;
			} else {
				String tmp = mapTable.get(stackAnalyse.peek()).get(stackString.peek());
				char N = stackAnalyse.peek();
				System.out.printf("%-50s", stackAnalyse.toString());
				System.out.printf("%-50s", StackReverse(stackString));
				System.out.printf("%-50s", N + "->" + tmp);
				System.out.println();
				stackAnalyse.pop();
				for (int i = tmp.length() - 1; i >= 0; i--) {
					stackAnalyse.push(tmp.charAt(i));
				}
				continue;
			}
		}
		if (flag == false) {
			System.out.println("error");
		} else {
			System.out.println("success");
		}
	}

	// 为了配合剩余输入串的顺序，将stack化为字符串转置
	public static String StackReverse(Stack<Character> st) {
		String result = st.toString();
		String tmp = "[";
		for (int i = result.length() - 2; i > 0; i--) {
			tmp += result.charAt(i);
		}
		tmp += "]";
		return tmp;
	}

}
