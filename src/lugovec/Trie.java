package lugovec;

import java.util.*;

public class Trie
{
    static class TrieNode
    {
        int index;
        Character ch;
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
                node.ch = ch;
                newNode.index = index;
                node.children.put(ch, newNode);
            }
            node = node.children.get(ch);

        }
        node.leaf = true;

    }

    //Возвращает индекс найденного узла, если узла с введённым префиксом
    //не существует, то возвращает единицу
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

    public int findSecondIndex(String s)
    {
        TrieNode node = root;
        for (char ch : s.toLowerCase().toCharArray())
        {
            node = node.children.get(ch);
        }

        while (node.ch != null)
        {
            node = node.children.get(node.ch);
        }

        return node.index;

    }


    /*public static void main(String[] args) {
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
       // Arrays.sort(arrayString);

        //Генератор long-ов
        Random random = new Random();

        long[] arrayLong = new long[100000];

        for (int i = 0; i < arrayLong.length; i++)
        {
            arrayLong[i] = random.nextLong();
        }

        String[][] newArray = new String[100000][2];


        for (int i = 0; i < newArray.length; i++)
        {
            newArray[i][0] = arrayString[i];
            newArray[i][1] = Long.toString(arrayLong[i]);
        }

        //Сортирую данные по первому столбцу (по имени класса)
        Arrays.sort(newArray, (a, b) -> a[0].compareTo(b[0]));


        //Заполняю дерево
        for (int i = 0; i < newArray.length; i++)
        {
            trie.put(newArray[i][0], i);

        }



        for (int i = 0; i < 13; i++)
        {
            System.out.println(i + " " + newArray[i][0] + " -> " + newArray[i][1]);
        }



        Scanner in = new Scanner(System.in);
        System.out.println("Нужно ввести префикс: ");
        String wordToFind = in.nextLine();

        //long date1 =   System.currentTimeMillis();
        int index = trie.find(wordToFind);
        //long date2 = System.currentTimeMillis();
        System.out.println("index = " + index);
        System.out.println("проверка -> вот слово под найденным индексом: " + newArray[index][0] + " -> " + newArray[index][1]);


        System.out.println("Теперь поищем последний индекс, где встречается эта строка : ");
        Scanner ine = new Scanner(System.in);
        System.out.println("Нужно ввести префикс: ");
        String wordToFinde = in.nextLine();
        int lastIndex = trie.findSecondIndex(wordToFinde);
        System.out.println("lastIndex = " + lastIndex);
        System.out.println(lastIndex + 1 + " " + newArray[lastIndex + 1][0] + "    " + newArray[lastIndex + 1][1]);
        System.out.println(lastIndex  + " " + newArray[lastIndex][0] + "    " + newArray[lastIndex][1]);


        //System.out.println("Время поиска: " + (date2 - date1));




    }*/
}
