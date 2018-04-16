package bank_to_int.bank_tensor;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by nafee on 4/16/18.
 */
public class BankDatasetReader {



    public static List<BankInstance> getBankInstanceList(String fileName) throws IOException
    {
        List<BankInstance> bankInstanceList = new ArrayList<>();

        List<String> lineList = getLines(fileName);


        lineList.remove(0); // first line doesn't contain any data instance
        // rather it gives a description of the dataset

        bankInstanceList = getBankInstanceList(lineList);

        return bankInstanceList;
    }

    private static List<String> getLines(String fileName) throws IOException
    {
        List<String> lineList = new ArrayList<>();

        FileReader fileReader = new FileReader(fileName);
        Scanner scanner = new Scanner(fileReader);


        while ( scanner.hasNext() )
        {
            String nextLine = scanner.nextLine();
            lineList.add(nextLine);
        }

        return lineList;
    }

    private static List<BankInstance> getBankInstanceList(List<String> lineList )
    {
        List<BankInstance> bankInstanceList = new ArrayList<>();
        if ( lineList == null )
        {
            throw new NullPointerException();
        }

        for (String line : lineList)
        {
            BankInstance bankInstance = new BankInstance(line);
            bankInstanceList.add(bankInstance);
        }

        return bankInstanceList;
    }

}
