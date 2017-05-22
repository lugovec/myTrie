package lugovec;

/**
 * Created by andrey on 22.05.17.
 */
public interface ISearcher
{
    public void refresh(String[] classNames, long[] modificationDates);

    public String[] guess(String start);

}
