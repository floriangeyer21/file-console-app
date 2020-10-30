package file.console.app.search;

import java.text.Format;
import java.util.Formatter;


public class FormatOutput {
    private int recordMaxLength;

    public FormatOutput(int recordMaxLength) {
        this.recordMaxLength = recordMaxLength;
    }

    public void printHeader() {
        String format = "|%1$-5s|%2$-5s|%3$-" + recordMaxLength + "s|\n";
        System.out.format(format, "ID", "Files", "Path");
    }

    public void printFormat(int recordId, int count, String record) {
        String format = "|%1$-5s|%2$-5s|%3$-" + recordMaxLength + "s|\n";
        int length = record.length();
        if (length > recordMaxLength) {
            int loop = length / recordMaxLength + 1;
            int index = 0;
            while (loop > 0) {
                if (index == 0) {
                    System.out.format(format, recordId, count, record.substring(index, index + recordMaxLength));
                    index += recordMaxLength;
                    loop--;
                    continue;
                }
                System.out.format(format, "", "", record.substring(index));
                index += recordMaxLength;
                loop--;
            }
        } else {
            System.out.format(format, recordId, count, record);
        }
    }
}
