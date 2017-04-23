import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Analyse {

	public static void main(String[] args) {
		String content = readFile();
		judge(content);

	}

	private static void judge(String content) {
		// remove the content between "/*" and "*/"
		while (content.contains("/*")) {
			content = content.substring(0, content.indexOf("/*"))
					+ content.substring(content.indexOf("*/") + 2, content.length());
		}
		ArrayList<String> list = new ArrayList<>();
		ArrayList<Integer> flag = new ArrayList<>();
		char[] c = content.toCharArray();
		String tmp = "";
		for (int i = 0; i < c.length; i++) {
			if ((c[i] == '+' && c[i + 1] == '=') || (c[i] == '-' && c[i + 1] == '=') || (c[i] == '/' && c[i + 1] == '=')
					|| (c[i] == '*' && c[i + 1] == '=') || (c[i] == '>' && c[i + 1] == '=')
					|| (c[i] == '<' && c[i + 1] == '=') || (c[i] == '!' && c[i + 1] == '=')
					|| (c[i] == '+' && c[i + 1] == '+') || (c[i] == '-' && c[i + 1] == '-')) {
				tmp = tmp + c[i] + c[i + 1];
				list.add(tmp);
				flag.add(4);
				tmp = "";
				i++;
			} else if (c[i] == '(' || c[i] == ')' || c[i] == ';' || c[i] == '{' || c[i] == '}' || c[i] == ',') {
				tmp += c[i];
				list.add(tmp);
				flag.add(5);
				tmp = "";
			} else if (c[i] == '+' || c[i] == '-' || c[i] == '*' || (c[i] == '/' && c[i + 1] != '/') || c[i] == '='
					|| c[i] == '<' || c[i] == '>') {
				tmp += c[i];
				list.add(tmp);
				flag.add(4);
				tmp = "";
			} else if (('a' <= c[i] && c[i] <= 'z') || ('A' <= c[i] && c[i] <= 'Z') || c[i] == '_') {// word
				tmp += c[i];
				if (c[i + 1] > 'z' || c[i + 1] < 'A' || (c[i + 1] < 'a' && c[i + 1] > 'Z')) {
					list.add(tmp);
					flag.add(0);
					tmp = "";
				}
			} else if (c[i] >= '0' && c[i] <= '9') { // number
				tmp += c[i];
				// the dot of number
				if(c[i+1]=='.'){
					tmp+=c[i+1];
					i++;
				}
				if (c[i + 1] < '0' || c[i + 1] > '9') {
					list.add(tmp);
					flag.add(3);
					tmp = "";
				}
			}
		}

		ArrayList<String> list1 = new ArrayList<>();// keyword list
		list1.add("int");
		list1.add("double");
		list1.add("float");
		list1.add("char");
		list1.add("auto");
		list1.add("long");
		list1.add("switch");
		list1.add("enum");
		list1.add("if");
		list1.add("break");
		list1.add("continue");
		list1.add("do");
		list1.add("while");
		list1.add("return");
		list1.add("for");
		list1.add("struct");
		list1.add("union");
		list1.add("default");
		list1.add("const");
		list1.add("static");
		list1.add("void");
		list1.add("typedef");

		for (int i = 0; i < list.size(); i++) {
			System.out.print("(");
			if (flag.get(i) == 0) {
				String word = list.get(i);
				if (list1.contains(word)) {
					System.out.print("1");
				} else {
					System.out.print("2");
				}
			} else {
				System.out.print(flag.get(i));
			}
			System.out.print(",\"");
			System.out.print(list.get(i));
			System.out.print("\"");
			System.out.println(")");

		}
	}

	// read file
	private static String readFile() {
		String URL = "test.txt";
		File file = new File(URL);
		BufferedReader bufferedReader = null;
		String content = new String();
		try {
			bufferedReader = new BufferedReader(new FileReader(file));
			String tmp = "";
			while ((tmp = bufferedReader.readLine()) != null) {
				if (tmp.contains("//")) {
					tmp = tmp.substring(0, tmp.indexOf("//"));
					// remove the string starts with "//" until the end of line
					content += tmp;
				} else {
					content = content + tmp;
				}
			}
			bufferedReader.close();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return content;

	}

}
