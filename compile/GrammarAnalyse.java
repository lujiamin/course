package compilePrinciple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class GrammarAnalyse {

	// �ٶ��� $ ���Ŵ���� ��
	private static String[] arrRaw = { "E->E+T|T", "T->T*F|F", "F->(E)|i" };// "E->TA",
																			// "A->+TA|$",
																			// "T->FB",
																			// "B->*FB|$",
																			// "F->(E)|i"

	public static void main(String[] args) {
		System.out.println("the raw rules:");
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
		System.out.println("the map of follows");
		System.out.println(getFollows(arrC));
		System.out.println("**************************************************");
		analyseTable(arrC);
	}

	// ��ֹ���
	public static HashMap<Character, ArrayList<String>> splitRules(String[] arr) {
		HashMap<Character, ArrayList<String>> mapRules = new HashMap<>();
		for (String item : arr) {
			String tmp = item.substring(3);
			ArrayList<String> list = new ArrayList<>();
			list.addAll(Arrays.asList(tmp.split("\\|")));
			// ���map�д��ڼ�����ȡ��ֵ��ӽ�list��
			if (mapRules.containsKey(item.charAt(0))) {
				list.addAll(mapRules.get(item.charAt(0)));
			}
			mapRules.put(item.charAt(0), list);
		}
		return mapRules;
	}

	// ��ȡ���ս��
	public static HashSet<Character> getNelements(String[] arr) {
		HashSet<Character> setLeft = new HashSet<Character>();
		for (String item : arr) {
			setLeft.add(item.charAt(0));
		}
		return setLeft;

	}

	// ��ȡ�ս��
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

	// �ж��ǲ����ս��
	public static boolean isTelement(String[] arr, char ch) {
		if (getTelements(arr).contains(ch)) {
			return true;
		}
		return false;
	}

	// ������ݹ�
	public static HashMap<Character, ArrayList<String>> removeRecur(String[] arr) {
		char[] toChange = { 'C', 'D', 'M', 'N', 'R', 'H', 'J', 'K' };
		int k = 0;
		// ����ʵ������ֱ����ݹ�
		HashMap<Character, ArrayList<String>> mapRules = splitRules(arr);
		HashMap<Character, ArrayList<String>> mapRulesBackup = new HashMap<>();
		for (Character ch : mapRules.keySet()) {
			// �ù��������ݹ�
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

	// �������map����ת��ΪString[]
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

	// �ж�һ�������Ƿ������ݹ飬���ڷ���true
	public static boolean isLeftRecur(char ch, ArrayList<String> list) {
		for (String s : list) {
			if (s.charAt(0) == ch) {
				return true;
			}
		}
		return false;
	}

	// ��ʼ��First��
	public static HashMap<Character, HashSet<Character>> initFirsts(String[] arr) {
		HashMap<Character, ArrayList<String>> mapRules = new HashMap<>();
		mapRules = splitRules(arr);
		HashMap<Character, HashSet<Character>> mapFirsts = new HashMap<>();
		for (Character ch : mapRules.keySet()) {
			HashSet<Character> setFirsts = new HashSet<>();
			for (String s : mapRules.get(ch)) {
				// ������ս��
				if (isTelement(arr, s.charAt(0))) {
					setFirsts.add(s.charAt(0));
				}
			}
			mapFirsts.put(ch, setFirsts);
		}
		return mapFirsts;
	}

	// ��ȡFirst��
	public static HashMap<Character, HashSet<Character>> getFirsts(String[] arr) {
		HashMap<Character, HashSet<Character>> firsts = initFirsts(arr);
		HashMap<Character, ArrayList<String>> mapRules = new HashMap<>();
		mapRules = splitRules(arr);
		for (Character ch : firsts.keySet()) {
			HashSet<Character> setFirsts = new HashSet<>();
			// ����ʼ����firsts�����뼯��
			setFirsts.addAll(firsts.get(ch));
			for (String s : mapRules.get(ch)) {
				if (!isTelement(arr, s.charAt(0))) {
					int i = 0;
					// ���ս���Һ��пմ� $
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

	public static HashMap<Character, HashSet<Character>> getFollows(String[] arr) {
		HashMap<Character, HashSet<Character>> follows = new HashMap<>();
		HashMap<Character, ArrayList<String>> mapRules = new HashMap<>();
		HashMap<Character, HashSet<Character>> firsts = new HashMap<>();
		HashSet<String> set = new HashSet<>();// set ��������Ҳ�
		// ��ȡ�������ת��Ϊ map ����
		mapRules = splitRules(arr);
		firsts = getFirsts(arr);
		ArrayList<String> ruleList = new ArrayList<>();
		for (Character ch : mapRules.keySet()) {
			ruleList.addAll(mapRules.get(ch));
		}
		for (String s : ruleList) {
			// �ж��ַ����Ƿ������ͬ����
			if (!s.equals("$")) {
				set.add(s);
			}
		}
		// System.out.println(set);
		for (Character ch : mapRules.keySet()) {
			HashSet<Character> setFollows = new HashSet<>();
			// ��Follows���Ĺ���1. ����ǳ�ʼ�ַ����� # ����Follows��
			if (ch == arrRaw[0].charAt(0)) {
				setFollows.add('#');
			}
			// ��Follows���Ĺ���2
			for (String s : set) {
				int chIndex = s.indexOf(ch);
				if (chIndex >= 0 && chIndex <= s.length() - 2 && s.charAt(chIndex + 1) != '$') {
					// ��һ���ַ����ս��
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
			// �Ƴ��մ� $
			setFollows.remove('$');
			follows.put(ch, setFollows);
		}
		// System.out.println(follows);
		int i = 0;// ���÷���ִ�й���Ĵ���,ֻ��ʹ��5����һ����ŵĹ���
		while (i < 5) {
			for (Character ch : mapRules.keySet()) {
				HashSet<Character> setFollows = new HashSet<>();
				// ���Թ���3
				for (String s : mapRules.get(ch)) {
					// ����A->aB��ʽ
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
					// ����A->aBb��ʽ �� ����һ������ b->$
					if (s.length() >= 2 && !isTelement(arr, s.charAt(s.length() - 2))) {
						// �����е� $ ���ַ��� ,�����ַ�
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

	public static void analyseTable(String[] arr) {
		HashMap<Character, HashSet<Character>> firsts = new HashMap<>();
		HashMap<Character, HashSet<Character>> follows = new HashMap<>();
		HashSet<Character> Telem = new HashSet<>();
		HashSet<Character> Nelem = new HashSet<>();
		firsts = getFirsts(arr);
		follows = getFollows(arr);
		Telem = getTelements(arr);
		Nelem = getNelements(arr);
		System.out.println(Telem);
		System.out.println(Nelem);

	}

}
