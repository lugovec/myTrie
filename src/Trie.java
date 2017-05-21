import java.util.*;

public class Trie
{
    static class TrieNode
    {
        int index;
        Map<Character, TrieNode> children = new TreeMap<Character, TrieNode>();
        boolean leaf;
    }

    TrieNode root = new TrieNode();

    public void put(String string, int index)
    {
        TrieNode node = root;
        for (char ch : string.toLowerCase().toCharArray())
        {
            if (!node.children.containsKey(ch))
            {
                TrieNode newNode = new TrieNode();
                newNode.index = index;
                node.children.put(ch, newNode);
            }
            node = node.children.get(ch);

        }
        node.leaf = true;

    }

    public int find(String s)
    {
        TrieNode node = root;
        for (char ch : s.toLowerCase().toCharArray())
        {
            if (!node.children.containsKey(ch))
                return -1;

            else
                node = node.children.get(ch);

        }
        return node.index;
    }

    // Usage example
    public static void main(String[] args) {
        Trie trie = new Trie();


        String symbols = "abcdefghijklmnopqrtufwvxsz1234567890";

        //Генератор строк
        String[] arrayString = new String[100000];

        for (int j = 0; j < arrayString.length; j++)
        {
            StringBuilder randString = new StringBuilder();

            int count = (int)(Math.random()*30); //Такая конструкция для генерации хороших непустых строк
            if (count == 0 || count ==1 || count == 2)
                count = 10;

            for (int i=0; i<count; i++)
            {
                randString.append(symbols.charAt((int)(Math.random()*(symbols.length()))));
            }

            arrayString[j] = randString.toString();

        }

        Arrays.sort(arrayString);

        String wordToFind = arrayString[98100];
        System.out.println(wordToFind);



        for (int i = 0; i < arrayString.length; i++)
        {
            trie.put(arrayString[i], i);
        }

        long date1 =   System.currentTimeMillis();
        int index = trie.find(wordToFind);
        long date2 = System.currentTimeMillis();


        System.out.println("index = " + index);
        System.out.println("проверка -> вот слово под найденным индексом: " + arrayString[index]);
        System.out.println("Время поиска: " + (date2 - date1));


    }
}