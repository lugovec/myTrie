package lugovec;


import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main implements ISearcher
{

    Trie trie = new Trie();

    String[][] array;


    @Override
    public void refresh(String[] classNames, long[] modificationDates)
    {
        String[][] newArray = new String[classNames.length][2];

        //Создаю двумерный массив и складываю туда имена классов и даты
        for (int i = 0; i < newArray.length; i++)
        {
            newArray[i][0] = classNames[i];
            newArray[i][1] = Long.toString(modificationDates[i]);
        }

        //Сортирую данные по первому столбцу (по имени класса)
        Arrays.sort(newArray, (a, b) -> a[0].compareTo(b[0]));

        array = newArray;

        //Заполняю дерево
        for (int i = 0; i < newArray.length; i++)
        {
            trie.put(newArray[i][0], i);

        }

    }

    @Override
    public String[] guess(String start)
    {
        int secondIndex;
        int firstIndex = trie.find(start);

        System.out.println("firstIndex = " + firstIndex);

        if (firstIndex != -1)
        {
            secondIndex = trie.findSecondIndex(start);

            System.out.println("secondIndex = " + secondIndex);


            String[][] arr = new String[(secondIndex - firstIndex) + 1][2];

            System.out.println("arr size = " + arr.length);


            for (int i = 0; i < arr.length; i++)
            {
                arr[i][0] = array[firstIndex][0];
                arr[i][1] = array[firstIndex][1];

                firstIndex++;
            }

            //Сортирую выбранные записи по дате
            Arrays.sort(arr, (a, b) -> a[1].compareTo(b[1]));

            if (arr.length >= 12)
            {
                String[] resultArray = new String[12];

                for (int i = 0; i < 12; i++)
                {
                    resultArray[i] = arr[i][0];
                }

                //тестирую
                for (int i = 0; i < 12; i++)
                {
                    System.out.println(arr[i][0] + " -> " + arr[i][1]);
                }


                return resultArray;
            }
            else if (arr.length < 12)
            {
                String[] resultArray = new String[arr.length];

                for (int i = 0; i < arr.length; i++)
                {
                    resultArray[i] = arr[i][0];
                }

                return resultArray;
            }
        }

        return new String[0];
    }

    public static void main(String[] args) {
       // Trie trie = new Trie();


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


        //Генератор long-ов
        Random random = new Random();

        long[] arrayLong = new long[100000];

        for (int i = 0; i < arrayLong.length; i++)
        {
            arrayLong[i] = random.nextLong();
        }

        Main mainClass = new Main();


        long date1 =   System.currentTimeMillis();
        mainClass.refresh(arrayString, arrayLong);
        long date2 =   System.currentTimeMillis();

        System.out.println(date2 - date1);


        Scanner in = new Scanner(System.in);
        System.out.println("Нужно ввести префикс: ");
        String wordToFind = in.nextLine();


        long date3 =   System.currentTimeMillis();
        String[] sting = mainClass.guess(wordToFind);
        long date4 =   System.currentTimeMillis();
        System.out.println("Время работы метода guess(): " + (date4 - date3));


        for (String i : sting)
        {
            System.out.println(i);

        }






        /*String[][] newArray = new String[100000][2];


        for (int i = 0; i < newArray.length; i++)
        {
            newArray[i][0] = arrayString[i];
            newArray[i][1] = Long.toString(arrayLong[i]);
        }

        //Сортирую данные по первому столбцу (по имени класса)
        Arrays.sort(newArray, (a, b) -> a[0].compareTo(b[0]));
*/

        /*//Заполняю дерево
        for (int i = 0; i < newArray.length; i++)
        {
            trie.put(newArray[i][0], i);

        }*/



       /* for (int i = 0; i < 13; i++)
        {
            System.out.println(i + " " + newArray[i][0] + " -> " + newArray[i][1]);
        }*/



        /*Scanner in = new Scanner(System.in);
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
*/

        //System.out.println("Время поиска: " + (date2 - date1));




    }




}
