package lugovec;


import java.util.Arrays;

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

        if (firstIndex != -1)
        {
            secondIndex = trie.findSecondIndex(start);
            String[][] arr = new String[(secondIndex - firstIndex) + 1][2];

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

                return resultArray;
            }
            else if (arr.length < 12)
            {
                String[] resultArray = new String[arr.length];

                for (int i = 0; i < arr.length; i++)
                {
                    resultArray[i] = arr[i][0];
                }
            }
        }
        
        return new String[0];
    }
}
