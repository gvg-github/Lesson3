import java.io.*;

import java.util.ArrayList;

import java.util.Collections;

import java.util.Enumeration;

public class Main {

    private static final int SYMBOLS = 1800;

    public static void main(String[] args) throws IOException {

        fileInArray();

        fileFromSomeFiles();

        readPageFromBigFile();

    }

    public static void fileInArray() throws IOException {

        File file = new File("G:\\Java\\Lesson3\\byte.txt");

        byte[] bm = new byte[50];

        FileInputStream is = new FileInputStream(file);

        is.read(bm);

        for (int i = 0; i < bm.length; i++) {

            System.out.print(bm[i] + " ");

        }

        System.out.println();

        is.close();

    }

    public static void fileFromSomeFiles() throws IOException {

        File file1 = new File("G:\\Java\\Lesson3\\file1.txt");

        File file2 = new File("G:\\Java\\Lesson3\\file2.txt");

        File file3 = new File("G:\\Java\\Lesson3\\file3.txt");

        File file4 = new File("G:\\Java\\Lesson3\\file4.txt");

        File file5 = new File("G:\\Java\\Lesson3\\file5.txt");

        ArrayList<FileInputStream> al = new ArrayList<>();

        al.add(new FileInputStream(file1));

        al.add(new FileInputStream(file2));

        al.add(new FileInputStream(file3));

        al.add(new FileInputStream(file4));

        al.add(new FileInputStream(file5));

        Enumeration<FileInputStream> e = Collections.enumeration(al);

        SequenceInputStream sis = new SequenceInputStream(e);

        FileOutputStream out = new FileOutputStream("G:\\Java\\Lesson3\\file_all.txt");

        int b = sis.read();

        while (b != -1) {

            out.write(b);

            b = sis.read();

        }

        sis.close();

        out.close();

        System.out.println("All files data in file : file_all.txt");

    }

    public static void readPageFromBigFile() throws IOException {

        File bigFile = new File("G:\\Java\\Lesson3\\big_file.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        RandomAccessFile rndf = new RandomAccessFile(bigFile, "r");
        int x;
        do {
            byte[] b = new byte[1800];
            System.out.println("Input page number (a positive number, 0 for exit):");
            x = Integer.valueOf(br.readLine());
            if (x == 0) break;

            int tecSymbol = SYMBOLS * (x - 1);
            if (tecSymbol >= rndf.length()) {
                System.out.println("End file!");
                break;
            }
            int tecLength = (tecSymbol + SYMBOLS) > rndf.length() ? (int) (rndf.length() - tecSymbol) : SYMBOLS;
            rndf.seek(tecSymbol);
            rndf.read(b, 0, tecLength);
            for (int i = 0; i < tecLength; i++) {
//                if (i > 99 && i%100 == 0){
//                    System.out.println((char)b[i]);
//                }else{
//                    System.out.print((char)b[i]);
//                }
                System.out.print((char) b[i]);
            }
            System.out.println();
            System.out.println();

        } while (x != 0);

        System.out.println("Programm ends.");
    }
}