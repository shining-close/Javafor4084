package Java.Exercise3;

import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class WordAnalyzer {
    private final List<String> words;

    // 构造方法：读取words.txt文件
    public WordAnalyzer(String filePath) throws IOException {
        this.words = Files.readAllLines(Paths.get(filePath));
    }

    /**
     * 验证输入是否为小写字母
     * @param c 输入字符
     * @throws IllegalArgumentException 若输入不是小写字母
     */
    private void validateLowercaseLetter(char c) {
        if (!Character.isLowerCase(c)) {
            throw new IllegalArgumentException("输入必须是小写字母");
        }
    }

    /**
     * 找到包含指定字母出现次数最多的单词（忽略大小写）
     * @param c 目标小写字母
     * @return 出现次数最多的单词；无匹配则返回null
     */
    public String wordWithMostOccurrencesOf(char c) {
        validateLowercaseLetter(c);
        String result = null;
        int maxCount = 0;
        char target = Character.toLowerCase(c); // 统一转为小写处理

        for (String word : words) {
            int count = 0;
            // 统计当前单词中目标字母的出现次数
            for (char ch : word.toCharArray()) {
                if (Character.toLowerCase(ch) == target) {
                    count++;
                }
            }
            // 更新最大次数和结果
            if (count > maxCount) {
                maxCount = count;
                result = word;
            }
        }
        return result;
    }

    /**
     * 找到以指定字母开头的最长单词（忽略大小写）
     * @param c 目标小写字母
     * @return 最长单词；无匹配则返回null
     */
    public String longestWordStartingWith(char c) {
        validateLowercaseLetter(c);
        String result = null;
        int maxLength = 0;
        char targetStart = Character.toLowerCase(c); // 统一转为小写处理

        for (String word : words) {
            if (word.isEmpty()) continue;
            // 检查单词首字母是否匹配（忽略大小写）
            char firstChar = Character.toLowerCase(word.charAt(0));
            if (firstChar == targetStart) {
                // 更新最长单词
                if (word.length() > maxLength) {
                    maxLength = word.length();
                    result = word;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        try {
            WordAnalyzer analyzer = new WordAnalyzer("/path/to/words.txt");
            System.out.println("以b开头的最长单词：" + analyzer.longestWordStartingWith('b'));
            System.out.println("包含a最多的单词：" + analyzer.wordWithMostOccurrencesOf('a'));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
